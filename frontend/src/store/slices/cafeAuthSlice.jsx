import { createSlice } from "@reduxjs/toolkit";
import { jwtDecode } from "jwt-decode";

const token = localStorage.getItem("cafeAccessToken")
let initialState = {
    isAuthenticated: false,
    cafe: null
}

if (token) {
    try {
        const decoded = jwtDecode(token)
        initialState = { isAuthenticated: true, cafe: decoded }
    } catch (error) {
        localStorage.removeItem("cafeAccessToken")
        localStorage.removeItem("cafeRefreshToken")
    }
}

const cafeAuthSlice = createSlice({
    name: "cafeAuth",
    initialState,
    reducers: {
        loginCafe: (state, action) => {
            const { accessToken, refreshToken } = action.payload

            localStorage.setItem("cafeAccessToken", accessToken)
            if (refreshToken) localStorage.setItem("cafeRefreshToken", refreshToken)

            state.isAuthenticated = true
            state.cafe = jwtDecode(accessToken)
        },
        logoutCafe: (state) => {
            localStorage.removeItem("cafeAccessToken")
            localStorage.removeItem("cafeRefreshToken")
            state.isAuthenticated = false
            state.cafe = null
        }
    }
})

export const { loginCafe, logoutCafe } = cafeAuthSlice.actions
export default cafeAuthSlice.reducer