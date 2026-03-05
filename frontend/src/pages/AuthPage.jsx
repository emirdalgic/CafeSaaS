import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import { loginUser } from '../store/slices/userAuthSlice';

// import authService

const AuthPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const dispatch = useDispatch(); 
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      // Backend API Request
      // const response = await authService.login(email, password);
      // const { accessToken, refreshToken } = response.data;

      // TEST
      // fakeTokens
      const fakeAccessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJyb2xlIjoiUk9MRV9PV05FUiJ9.test";
      const fakeRefreshToken = "sahte-refresh-token-123";

    
      dispatch(loginUser({ accessToken: fakeAccessToken, refreshToken: fakeRefreshToken }));
      navigate('/dashboard');

    } catch (err) {
      setError('Giriş başarısız. Lütfen bilgilerinizi kontrol edin.');
    }
  };

  // TEST FORM
  return (
    <div style={{ padding: "50px", textAlign: "center", maxWidth: "400px", margin: "0 auto" }}>
      <h1>Mekan Sahibi Girişi</h1>
      
      <form onSubmit={handleLogin} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
        <input
          type="email"
          placeholder="E-posta adresiniz"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          style={{ padding: "10px", borderRadius: "5px", border: "1px solid #ccc" }}
        />
        
        <input
          type="password"
          placeholder="Şifreniz"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={{ padding: "10px", borderRadius: "5px", border: "1px solid #ccc" }}
        />
        
        <button type="submit" style={{ padding: "10px", backgroundColor: "#007BFF", color: "white", border: "none", borderRadius: "5px", cursor: "pointer" }}>
          Giriş Yap
        </button>
        
        {error && <p style={{ color: "red", margin: 0 }}>{error}</p>}
      </form>
    </div>
  );
};

export default AuthPage;