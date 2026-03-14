import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '../../../store/slices/userAuthSlice';
import './Settings.css';

const Settings = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { user } = useSelector((state) => state.userAuth);

  const handleLogout = () => {
    dispatch(logoutUser());
    navigate('/login');
  };

  return (
    <div className="settings-management-container">
      <div className="settings-header">
        <h2>Ayarlar</h2>
        <div className="header-actions">
          <button className="primary-btn" onClick={() => alert("Profil güncellenecek!")}>Değişiklikleri Kaydet</button>
        </div>
      </div>

      <div className="settings-stack">
        {/* Üst Yatay Kart: Kullanıcı Bilgileri */}
        <div className="settings-wide-card">
          <div className="card-section-title">
            <h3>Kullanıcı Profili</h3>
            <p>Kişisel bilgilerinizi buradan görüntüleyebilirsiniz.</p>
          </div>
          
          <div className="card-content-horizontal">
            <div className="info-group">
              <span className="label">Ad Soyad</span>
              <span className="value">{user?.firstName} {user?.lastName}</span>
            </div>
            <div className="info-group">
              <span className="label">E-posta Adresi</span>
              <span className="value">{user?.email}</span>
            </div>
            <div className="info-group">
              <span className="label">Hesap Türü</span>
              <span className="value role-badge">{user?.role}</span>
            </div>
          </div>
        </div>

        {/* Alt Yatay Kart: Oturum Yönetimi */}
        <div className="settings-wide-card danger-section">
          <div className="card-section-title">
            <h3>Oturum Yönetimi</h3>
            <p>Hesabınızın güvenliği için aktif oturumunuzu sonlandırın.</p>
          </div>
          
          <div className="card-content-footer">
            <div className="status-indicator">
              <span className="dot"></span>
              <span>Şu an aktifsiniz</span>
            </div>
            <button className="logout-btn" onClick={handleLogout}>Oturumu Kapat</button>
          </div>
        </div>

      </div>
    </div>
  );
};

export default Settings;