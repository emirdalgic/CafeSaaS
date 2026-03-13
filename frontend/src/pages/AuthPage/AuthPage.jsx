import React from 'react';
import './AuthPage.css';

const AuthPage = () => {
  return (
    <div className="auth-wrapper">
      <div className="auth-card">
        
        <div className="auth-header">
          <h1>Giriş Yap</h1>
          <p>Mekan yönetim paneline hoş geldiniz.</p>
        </div>

        {/* Sadece tasarım amaçlıdır, onSubmit gibi işlevler yoktur */}
        <form className="auth-form" onClick={(e) => e.preventDefault()}>
          
          <div className="input-group">
            <label>E-posta Adresi</label>
            <input 
              type="email" 
              className="auth-input" 
              placeholder="ornek@mail.com" 
            />
          </div>

          <div className="input-group">
            <label>Şifre</label>
            <input 
              type="password" 
              className="auth-input" 
              placeholder="••••••••" 
            />
          </div>

          <button type="button" className="auth-button">
            Sisteme Gir
          </button>

        </form>

        <div className="auth-footer">
          Henüz kayıtlı değil misiniz? 
          <a href="#kayit-ol">Kayıt Ol</a>
        </div>

      </div>
    </div>
  );
};

export default AuthPage;