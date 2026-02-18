import { terminalApi } from "../api/axiosInstance";

export const terminalService = {
    getPassiveProducts: async (page = 0, size = 10) => {
        const response = await terminalApi.get(`/private/terminal/passive?page=${page}&size=${size}`)
        return response.data
    },
    createOrder: async (orderData) => {
        const response = await terminalApi.post(`/private/terminal/order-create`, orderData)
        return response.data
    },
    searchItem: async (query, page = 0, size = 10) => {
        const response = await terminalApi.get(`/private/terminal/search?query=${query}&page=${page}&size=${size}`)
        return response.data
    },
    updateItemAvailability: async (itemId, isAvailable = false) => {
        const response = await terminalApi.patch(`/private/terminal/menu-item/${itemId}/availability?isAvailable=${isAvailable}`)
        return response.data
    }
}