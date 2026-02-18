import { configureStore } from '@reduxjs/toolkit';
import userAuthReducer from './slices/userAuthSlice';
import cafeAuthReducer from './slices/cafeAuthSlice';

export const store = configureStore({
    reducer: {
        userAuth: userAuthReducer,
        cafeAuth: cafeAuthReducer,
    }
})