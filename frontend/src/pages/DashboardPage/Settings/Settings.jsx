import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '../../../store/slices/userAuthSlice'; // userAuthSlice içindeki logout action'ı
import './Settings.css'; // Eğer varsa CSS dosyan

const Settings = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  // Redux store'dan kullanıcı bilgisini çekebilirsin (isteğe bağlı)
  const { user } = useSelector((state) => state.userAuth);

  const handleLogout = () => {
    // Redux action'ı tetikleyerek localStorage'ı temizler ve state'i sıfırlar
    dispatch(logoutUser());
    
    // Kullanıcıyı giriş sayfasına yönlendirir
    navigate('/login');
  };

  return (
    <div className="settings-container">
      <h2>Ayarlar</h2>
      
      <div className="user-info-section">
        <h3>Kullanıcı Bilgileri</h3>
        <p><strong>Ad Soyad:</strong> {user?.firstName} {user?.lastName}</p>
        <p><strong>E-posta:</strong> {user?.email}</p>
        <p><strong>Rol:</strong> {user?.role}</p>
      </div>

      <div className="danger-zone">
        <h3>Oturum Yönetimi</h3>
        <p>Mevcut oturumunuzu sonlandırmak için aşağıdaki butonu kullanabilirsiniz.</p>
        <button 
          className="logout-button" 
          onClick={handleLogout}
          style={{
            backgroundColor: '#e74c3c',
            color: 'white',
            border: 'none',
            padding: '10px 20px',
            borderRadius: '5px',
            cursor: 'pointer',
            fontWeight: 'bold'
          }}
        >
          Oturumu Kapat
        </button>
      </div>
    </div>
  );
};

export default Settings;