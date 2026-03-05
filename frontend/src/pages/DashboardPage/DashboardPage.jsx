import { useState } from 'react'; // 1. useState'i ekle
import './DashboardStyle.css';
import { FaQrcode } from "react-icons/fa";
import { MdDashboard, MdTableRestaurant } from "react-icons/md";
import { LuNotebookPen } from "react-icons/lu";
import { IoIosSettings } from "react-icons/io";

const DashboardPage = () => {
  // 2. Aktif olan menünün ismini tutan state
  const [activeMenu, setActiveMenu] = useState('Kontrol Panelim');

  // Menü elemanlarını bir liste olarak tanımlayalım
  const menuItems = [
    { name: 'Kontrol Panelim', icon: <MdDashboard size={'2em'} /> },
    { name: 'Siparişler', icon: <LuNotebookPen size={'2em'} /> },
    { name: 'Menü Yönetimi', icon: <FaQrcode size={'2em'} /> },
    { name: 'Masalar', icon: <MdTableRestaurant size={'2em'} /> },
    { name: 'Ayarlar', icon: <IoIosSettings size={'2em'} /> },
  ];

  return (
    <div className="dashboard-container">
      <div className="column sidebar">
        <div className="logo">
          <h1>Cafe SaaS</h1>
        </div>
        <ul className='side-menu'>
          {menuItems.map((item) => (
            <li 
              key={item.name}
              // 3. Eğer aktifse 'active' class'ını ekle
              className={activeMenu === item.name ? 'active' : ''}
              // 4. Tıklandığında aktif menüyü güncelle
              onClick={() => setActiveMenu(item.name)}
            >
              {item.icon}
              <span>{item.name}</span>
            </li>
          ))}
        </ul>
      </div>
      
      {/* 5. Ana içerikte hangisinin seçili olduğunu gösterebilirsin */}
      <div className="column main-content">
        <h2>{activeMenu} Sayfası</h2>
        <p>Burada {activeMenu.toLowerCase()} ile ilgili detaylar yer alacak.</p>
      </div>
    </div>
  );
};

export default DashboardPage;