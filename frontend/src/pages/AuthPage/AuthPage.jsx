import React, { useState } from 'react';
import { useDispatch } from 'react-redux'; 
import { useNavigate } from 'react-router-dom'; 
import { authService } from '../../services/authService'; 
import { loginUser } from '../../store/slices/userAuthSlice'; 
import './AuthPage.css';

const AuthPage = () => {
  // Form modunu kontrol etmek için (Login / Register)
  const [isLogin, setIsLogin] = useState(true);
  
  // Form alanları
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  });
  const [error, setError] = useState('');
  
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      let data;
      if (isLogin) {
        // Login işlemi
        data = await authService.login(formData.email, formData.password);
      } else {
        // Register işlemi (backend RegisterRequest DTO'su ile uyumlu)
        data = await authService.register(formData);
      }
      
      // Her iki durumda da AuthResponse (tokenlar) döner
      dispatch(loginUser({ 
        accessToken: data.accessToken, 
        refreshToken: data.refreshToken 
      }));
      
      navigate('/dashboard'); 
    } catch (err) {
      setError(isLogin ? 'Giriş başarısız.' : 'Kayıt başarısız.');
      console.error(err);
    }
  };

  return (
    <div className="auth-wrapper">
      <div className="auth-card">
        <div className="auth-header">
          <h1>{isLogin ? 'Giriş Yap' : 'Kayıt Ol'}</h1>
          <p>{isLogin ? 'Mekan yönetim paneline hoş geldiniz.' : 'Yeni hesap oluşturun.'}</p>
        </div>

        <form className="auth-form" onSubmit={handleSubmit}>
          {error && <p style={{ color: 'red' }}>{error}</p>}
          
          {/* Kayıt Modundaysa İsim-Soyisim Alanlarını Göster */}
          {!isLogin && (
            <>
              <div className="input-group">
                <label>Ad</label>
                <input 
                  name="firstName"
                  type="text" 
                  className="auth-input" 
                  value={formData.firstName}
                  onChange={handleChange}
                  required 
                />
              </div>
              <div className="input-group">
                <label>Soyad</label>
                <input 
                  name="lastName"
                  type="text" 
                  className="auth-input" 
                  value={formData.lastName}
                  onChange={handleChange}
                  required 
                />
              </div>
            </>
          )}

          <div className="input-group">
            <label>E-posta Adresi</label>
            <input 
              name="email"
              type="email" 
              className="auth-input" 
              placeholder="ornek@mail.com"
              value={formData.email}
              onChange={handleChange}
              required 
            />
          </div>

          <div className="input-group">
            <label>Şifre</label>
            <input 
              name="password"
              type="password" 
              className="auth-input" 
              placeholder="••••••••"
              value={formData.password}
              onChange={handleChange}
              required 
            />
          </div>

          <button type="submit" className="auth-button">
            {isLogin ? 'Sisteme Gir' : 'Hesabı Oluştur'}
          </button>
        </form>

        <div className="auth-footer">
          {isLogin ? 'Henüz kayıtlı değil misiniz?' : 'Zaten hesabınız var mı?'}
          <button 
            type="button" 
            className="toggle-auth-mode"
            onClick={() => setIsLogin(!isLogin)}
            style={{ background: 'none', border: 'none', color: 'blue', cursor: 'pointer', marginLeft: '5px' }}
          >
            {isLogin ? 'Kayıt Ol' : 'Giriş Yap'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default AuthPage;