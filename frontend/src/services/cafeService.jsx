import { adminApi } from "../api/axiosInstance";

export const cafeService = {
    createCafe: async (cafeRegisterData) => {
        const response = await adminApi.post('/private/cafes/save', cafeRegisterData)
        return response.data
    },
    updateCafe: async (cafeId, cafeUpdateData) => {
        const response = await adminApi.put(`/private/cafes/update/${cafeId}`, cafeUpdateData)
        return response.data
    },
    deleteCafe: async (cafeId) => {
        const response = await adminApi.delete(`/private/cafes/delete/${cafeId}`)
        return response.data
    }
}