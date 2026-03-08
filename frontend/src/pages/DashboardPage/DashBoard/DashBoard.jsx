import React from 'react';
import './DashBoard.css';
import { useState, useEffect } from 'react';
import { FiSearch } from "react-icons/fi";
import { GoBell } from "react-icons/go";
import { FiArrowRight, FiChevronDown } from "react-icons/fi";
import menuIcon from './icons/menu-icon.png';
import moneyBagIcon from './icons/money-bag.png';
import usersIcon from './icons/users-icon.png';
export default function DashBoard() {

    const [currentTime, setCurrentTime] = useState(new Date());

    useEffect(() => {
        const timer = setInterval(() => {
        setCurrentTime(new Date());
        }, 1000);

        return () => clearInterval(timer);
    }, []);

    const formattedDate = currentTime.toLocaleDateString('tr-TR', {
        day: 'numeric',
        month: 'long',
        weekday: 'long'
    });
    const formattedTime = currentTime.toLocaleTimeString('tr-TR');
    return (
        <div>
            <div className='header'>
                <div>
                    <h1>Kontrol Paneli</h1>
                    <p>{formattedDate} - {formattedTime}</p>
                </div>
                <div className='header-right'>
                    <button className='bell-button'><GoBell /></button>
                    <div className="search-container">
                        <div className="search-icon-wrapper">
                            <FiSearch size={24} color="#000" />
                        </div>
                        <input 
                            type="text" 
                            className="search-input" 
                            placeholder="Buradan arama yapabilirsiniz..." 
                        />
                        </div>
                </div>
            </div>
            <div className="dashboard-grid-container">
      
      {/* Üst Kartlar */}
      <div className="top-cards">
        <div className="stat-card mini">
          <span>Toplam Sipariş</span>
          <h2>65</h2>
          <img src={menuIcon} alt="menu" className="card-img" />
        </div>
        <div className="stat-card mini">
          <span>Toplam Kazanç</span>
          <h2>36K</h2>
          <img src={moneyBagIcon} alt="money" className="card-img small-icon-fix" />
        </div>
        <div className="stat-card mini">
          <span>Toplam Müşteri</span>
          <h2>87</h2>
          <img src={usersIcon} alt="users" className="card-img" />
        </div>
      </div>

      {/* Alt Paneller */}
      <div className="bottom-panels">
        {/* Sipariş Özeti */}
        <div className="panel wide">
          <div className="panel-header">
            <h3>Sipariş Özeti</h3>
            <button className="dropdown-btn">Aylık <FiChevronDown /></button>
          </div>
          <div className="panel-content">
            <div className="progress-circle">
               {/* Buraya bir Chart kütüphanesi veya CSS Circle gelebilir */}
               <div className="circle-text">80%</div>
            </div>
            <div className="summary-info">
              <h1>120.678 TL</h1>
              <a href="#">Siparişleri Yönet <FiArrowRight /></a>
            </div>
          </div>
        </div>

        {/* İstatistikler */}
        <div className="panel narrow">
          <h3>İstatistikler</h3>
          <div className="chart-placeholder">
            {/* Buraya çizgi grafiği gelecek */}
            <div className="mock-chart"></div>
            <div className="chart-labels">
              <span>Ocak</span><span>Şubat</span><span>Mart</span><span>Nisan</span>
            </div>
          </div>
        </div>
      </div>

    </div>
        </div>
    );
}