import { adminApi } from "../api/axiosInstance";

//private
export const menuCategoryService = {
    deleteCategoryById: async (categoryId) => {
        const response = await adminApi.delete(`/private/categories/delete/${categoryId}`)
        return response.data
    },
    createCategory: async (cateryItemData) => {
        const response = await adminApi.post(`/private/categories/save`, cateryItemData)
        return response.data
    },
    updateCategory: async (categoryId, categoryItemData) => {
        const response = await adminApi.put(`/private/categories/update/${categoryId}`, categoryItemData)
        return response.data
    }
}