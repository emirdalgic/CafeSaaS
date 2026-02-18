import { authApi } from "../api/axiosInstance"

export const authService = {
    login: async (email, password) => {
        const response = await authApi.post("/user/login", { email, password })
        return response.data
    },
    register: async (userData) => {
        const response = await authApi.post("/user/register", userData)
        return response.data
    }
}

export const cafeService = {
    login: async (cafeUserName, password) => {
        const response = await authApi.post("/cafe/login", { cafeUserName, password })
        return response.data
    }
}