import { createSlice } from "@reduxjs/toolkit";
import { jwtDecode } from "jwt-decode";

const token = localStorage.getItem("userAccessToken")
let initialState = {
    isAuthenticated: false,
    user: null
}

if (token) {
    try {
        const decoded = jwtDecode(token)
        initialState = { isAuthenticated: true, user: decoded }
    } catch (error) {
        localStorage.removeItem("userAccessToken")
        localStorage.removeItem("userRefreshToken")
    }
}

const userAuthSlice = createSlice({
    name: "userAuth",
    initialState,
    reducers: {
        loginUser: (state, action) => {
            const { accessToken, refreshToken } = action.payload

            localStorage.setItem("userAccessToken", accessToken)
            if (refreshToken) localStorage.setItem("userRefreshToken", refreshToken)

            state.isAuthenticated = true
            state.user = jwtDecode(accessToken)
        },
        logoutUser: (state) => {
            localStorage.removeItem("userAccessToken")
            localStorage.removeItem("userRefreshToken")
            state.isAuthenticated = false
            state.user = null
        }
    }
})

export const { loginUser, logoutUser } = userAuthSlice.actions
export default userAuthSlice.reducer