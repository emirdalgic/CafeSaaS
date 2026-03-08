import { useState } from 'react'; // 1. useState'i ekle
import './DashboardStyle.css';
import { FaQrcode } from "react-icons/fa";
import { MdDashboard, MdTableRestaurant } from "react-icons/md";
import { LuNotebookPen } from "react-icons/lu";
import { IoIosSettings } from "react-icons/io";
import DashBoard from './DashBoard/DashBoard';
import Orders from './Orders/Orders';
import Menus from './Menus/Menus';
import Tables from './Tables/Tables';
import Settings from './Settings/Settings';

const DashboardPage = () => {
  // 2. Aktif olan menünün ismini tutan state
  const [activeTab, setActiveTab] = useState('Kontrol Panelim');
  const menuItems = [
    { name: 'Kontrol Panelim', icon: <MdDashboard size={'2em'} />, component: <DashBoard /> },
    { name: 'Siparişler', icon: <LuNotebookPen size={'2em'} />, component: <Orders /> },
    { name: 'Menü Yönetimi', icon: <FaQrcode size={'2em'} />, component: <Menus /> },
    { name: 'Masalar', icon: <MdTableRestaurant size={'2em'} />, component: <Tables /> },
    { name: 'Ayarlar', icon: <IoIosSettings size={'2em'} />, component: <Settings /> },
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
              className={activeTab === item.name ? 'active' : ''}
              // 4. Tıklandığında aktif menüyü güncelle
              onClick={() => setActiveTab(item.name)}
            >
              {item.icon}
              <span>{item.name}</span>
            </li>
          ))}
        </ul>
      </div>
      
      {/* 5. Ana içerikte hangisinin seçili olduğunu göster */}
      <div className="column main-content">
        {menuItems.find(item => item.name === activeTab)?.component}
      </div>
    </div>
  );
};

export default DashboardPage;