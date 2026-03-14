import React, { useState } from 'react';
import './Menus.css';

const Menus = () => {
  // Daha sonra burası backendden çekilecek
  const mockImage = "https://images.unsplash.com/photo-1509042239860-f550ce710b93?q=80&w=500&auto=format&fit=crop";

  const [categories] = useState([
    { id: 1, name: 'Sıcak İçecekler' },
    { id: 2, name: 'Soğuk İçecekler' },
    { id: 3, name: 'Tatlılar' },
  ]);

  const [menuItems] = useState([
    { 
      id: 101, 
      categoryId: 1, 
      name: 'Filtre Kahve', 
      price: '85.00', 
      description: 'Taze çekilmiş çekirdeklerden.',
      imageUrl: mockImage 
    },
    { 
      id: 102, 
      categoryId: 1, 
      name: 'Latte', 
      price: '95.00', 
      description: 'Yumuşak içimli sütlü kahve.',
      imageUrl: mockImage
    },
  ]);

  const [selectedCategoryId, setSelectedCategoryId] = useState(1);
  const filteredItems = menuItems.filter(item => item.categoryId === selectedCategoryId);

  return (
    <div className="menu-management-container">
      <div className="menu-header">
        <h2>Menü Yönetimi</h2>
        <div className="header-actions">
          <button className="add-category-btn" onClick={() => alert("Kategori ekleme yakında!")}>+ Yeni Kategori</button>
          <button className="add-item-btn" onClick={() => alert("Ürün ekleme yakında!")}>+ Yeni Ürün Ekle</button>
        </div>
      </div>

      <div className="category-tabs">
        {categories.map((cat) => (
          <button
            key={cat.id}
            className={`category-tab ${selectedCategoryId === cat.id ? 'active' : ''}`}
            onClick={() => setSelectedCategoryId(cat.id)}
          >
            {cat.name}
          </button>
        ))}
      </div>

      <div className="menu-items-grid">
        {filteredItems.map((item) => (
          <div key={item.id} className="menu-item-card">
            <div className="item-image-container">
              <img src={item.imageUrl} alt={item.name} className="item-image" />
            </div>
            
            <div className="item-info">
              <h3>{item.name}</h3>
              <p>{item.description}</p>
            </div>
            <div className="item-footer">
              <span className="price">{item.price} ₺</span>
              <div className="item-actions">
                <button className="edit-btn">Düzenle</button>
                <button className="delete-btn">Sil</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Menus;