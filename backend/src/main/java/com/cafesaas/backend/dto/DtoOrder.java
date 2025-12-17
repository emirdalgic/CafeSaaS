package com.cafesaas.backend.dto;

import com.cafesaas.backend.model.enums.OrderSource;
import com.cafesaas.backend.model.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//burası qr menüden alışveriş yapan kişiler için(ama response terminale)
//ödeye tıklayınca orderstatus WAITING_FOR_APPROVAL(true) olarak dbye kaydedilcek
//paymentı tamamlamadan terminale bu orderı göstericez ve eğer işletme yapabilcek müsaitliğe sahipse
//WAITING_FOR_APPROVAL(false) olucak ardından APPROVED_PAYMENT_PENDING beklenicek
//ardından aslında buraya kadar bir problemle karşılaşılmazsa PREPARING READY COMPLETED şeklinde orderı son haline getirip
//dbdeki versiyonunu güncellicez. aslında burada 2 tane işlem için dbde çokca read write yapmak zorunda kalıcaz high cost bir işlem
//ama başka seçenek yok
public class DtoOrder {
    private UUID id; // sipariş id (onaylarken bunu kullancak)

    private String tableName; // burayı ilerde işletmeciye veri dönerken hangi masa daha hasılat yapıyo vs(burayı frontta zorunlu field olarak ayarlamamız lazım)

    private BigDecimal totalAmount;

    private OrderStatus status; // WAITING_FOR_APPROVAL

    private OrderSource source; // qr menü

    private LocalDateTime createdAt;

    private List<DtoOrderItem> items;
}
