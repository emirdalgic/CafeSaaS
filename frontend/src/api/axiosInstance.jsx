import axios from "axios";

//const BASE_URL = "http://localhost:8000/api/v1"
const BASE_URL = "/api/v1"

const createApi = (accessTokenKey, refreshTokenKey) => {
    const instance = axios.create({ baseURL: BASE_URL })

    instance.interceptors.request.use(
        (config) => {
            const token = localStorage.getItem(accessTokenKey)
            if (token) {
                config.headers.Authorization = `Bearer ${token}`
            }
            return config
        },
        (error) => Promise.reject(error)
    )

    instance.interceptors.response.use(
        (response) => response,
        async (error) => {
            const originalRequest = error.config

            if (error.response?.status === 401 && !originalRequest._retry) {
                originalRequest._retry = true

                const refreshToken = localStorage.getItem(refreshTokenKey)
                if (refreshToken) {
                    try {
                        const { data } = await axios.post(`${BASE_URL}/token/refresh`, {
                            token: refreshToken,
                        })
                        localStorage.setItem(accessTokenKey, data.accessToken)

                        originalRequest.headers.Authorization = `Bearer ${data.accessToken}`
                        return instance(originalRequest)
                    } catch (refreshError) {
                        console.error("Token refresh failed:", refreshError)
                        localStorage.removeItem(accessTokenKey)
                        localStorage.removeItem(refreshTokenKey)
                        window.location.href = '/login'
                    }
                }
            }
            return Promise.reject(error)
        }
    )

    return instance
}

export const adminApi = createApi("userAccessToken", "userRefreshToken")
export const terminalApi = createApi("cafeAccessToken", "cafeRefreshToken")
export const authApi = axios.create({ baseURL: `${BASE_URL}/auth` })
export const publicApi = axios.create({ baseURL: `${BASE_URL}/public` })
