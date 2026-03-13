import { useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ProtectedRoute from "../src/global-components/ProtectedRoutes"

// src/pages
import MenuPage from './pages/MenuPage/MenuPage'
import AuthPage from './pages/AuthPage/AuthPage'
import DashboardPage from './pages/DashboardPage/DashboardPage'
import TerminalPage from './pages/TerminalPage/TerminalPage'
import TerminalLogin from './pages/TerminalLogin/TerminalLogin'
import Unauthorized from './pages/Unauthorized/Unauthorized'
import LandingPage from './pages/LandingPage/LandingPage'
function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/*public routes*/}
        {/*//qr menu routes */}
        <Route path="/menu/:cafeId" element={<MenuPage />} />

        {/*//authorization routes*/}
        <Route path="/login" element={<AuthPage />} />
        <Route path="/terminal/login" element={<TerminalLogin />} />
        <Route path="/unauthorized" element={<Unauthorized />} />

        {/*Landing Page*/}
        <Route path="/" element={<LandingPage />} />
      {/*//private routes*/}
  
    {/*//sadece kafe sahibine özel*/}
        <Route element={<ProtectedRoute allowedRoles={["OWNER", "ADMIN"]} />}>
          <Route path="/dashboard" element={<DashboardPage />} />
          {/* İleride /ayarlar, /personel gibi sayfaları da buraya ekleyeceksin */}
        </Route>

        {/*//sadece kafe terminali*/}
        <Route element={<ProtectedRoute allowedRoles={["CAFE_TERMINAL"]} />}>
          <Route path="/terminal" element={<TerminalPage />} />
        </Route>

      </Routes>
    </BrowserRouter>
  )
}

export default App
