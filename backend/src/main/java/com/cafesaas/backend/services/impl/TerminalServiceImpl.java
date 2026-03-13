package com.cafesaas.backend.services.impl;

import com.cafesaas.backend.dto.DtoOrderCreateIU;
import com.cafesaas.backend.dto.DtoOrderItemIU;
import com.cafesaas.backend.entities.MenuItem;
import com.cafesaas.backend.entities.Order;
import com.cafesaas.backend.entities.OrderItem;
import com.cafesaas.backend.exception.BaseException;
import com.cafesaas.backend.exception.MessageType;
import com.cafesaas.backend.repository.CafeRepository;
import com.cafesaas.backend.repository.MenuItemRepository;
import com.cafesaas.backend.repository.OrderRepository;
import com.cafesaas.backend.services.ITerminalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TerminalServiceImpl implements ITerminalService {
    private final CafeRepository cafeRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;


    @Transactional
    @Override
    public void createOrderAtTerminal(DtoOrderCreateIU dtoOrderCreateIU, UUID cafeId) {
        Order order = new Order();
        order.setCafe(cafeRepository.getReferenceById(cafeId));
        order.setPaymentMethod(dtoOrderCreateIU.getPaymentMethod());

        BigDecimal calculatedTotal = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for(DtoOrderItemIU itemDto: dtoOrderCreateIU.getItems()){
            MenuItem product = menuItemRepository.findById(itemDto.getItemId())
                    .orElseThrow(()-> new BaseException(MessageType.NO_RECORD_EXIST));

            if (!menuItemRepository.existsByIdAndCategory_Cafe_Id(product.getId(), order.getCafe().getId())) {
                throw new AccessDeniedException("bu ürün bu kafeye ait değildir");
            }

            if(!product.isAvailable()){
                throw new RuntimeException(product.getName() + " şu anda satışa kapalıdır");
            }
            BigDecimal actualPrice = product.getPrice();
            String selectedVariant = itemDto.getSelectedVariant();

            if (selectedVariant != null && !selectedVariant.isEmpty()) {
                if (product.getVariants() != null && product.getVariants().containsKey(selectedVariant)) {
                    actualPrice = product.getVariants().get(selectedVariant);
                } else {
                    throw new RuntimeException(product.getName() + " için geçersiz varyant seçimi: " + selectedVariant);
                }
            } else {
                if (product.getVariants() != null && !product.getVariants().isEmpty()) {
                    throw new RuntimeException(product.getName() + " siparişi için boyut/seçenek belirtmek zorundasınız");
                }
            }

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(product)
                    .productName(product.getName())
                    .priceAtPurchase(actualPrice)
                    .selectedVariant(selectedVariant)
                    .quantity(itemDto.getQuantity())
                    .build();

            orderItems.add(orderItem);
            BigDecimal lineTotal = actualPrice.multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            calculatedTotal = calculatedTotal.add(lineTotal);
        }

        order.setTotalAmount(calculatedTotal);
        order.setItems(orderItems);

        orderRepository.save(order);
    }
}
