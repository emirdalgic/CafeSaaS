import { useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ProtectedRoute from "../src/global-components/ProtectedRoutes"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        //public routes
        <Route>

        </Route>


      //private routes
        <Route></Route>

    //sadece kafe sahibine özel
        <Route>

        </Route>

        //sadece kafe terminali
        <Route element={<ProtectedRoute allowedRoles={["CAFE_TERMINAL"]} />}>
          <Route path="/terminal" element={<TerminalDashboard />} />
        </Route>

      </Routes>
    </BrowserRouter>
  )
}

export default App
