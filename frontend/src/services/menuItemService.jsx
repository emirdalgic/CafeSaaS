import { adminApi, publicApi } from "../api/axiosInstance";

export const menuItemService = {
    getItemsByCategory: async (categoryId, page = 0, size = 10) => {
        const response = await publicApi.get(`/menu-items/list/${categoryId}?page=${page}&size=${size}`)
        return response.data
    },
    getItemById: async (itemId) => {
        const response = await publicApi.get(`/menu-items/list/${itemId}`)
        return response.data
    },
    getShowCase: async (cafeSlug) => {
        const response = await publicApi.get(`/menu-items/${cafeSlug}/showcase`)
        return response.data
    },
    //private
    deleteMenuItem: async (itemId) => {
        const response = await adminApi.delete(`/private/menu-items/delete/${itemId}`)
        return response.data
    },
    createMenuItem: async (menuItemData) => {
        const response = await adminApi.post(`/private/menu-items/save`, menuItemData)
        return response.data
    },
    updateMenuItem: async (itemId, menuItemData) => {
        const response = await adminApi.put(`/private/menu-items/update/${itemId}`, menuItemData)
        return response.data
    }
}