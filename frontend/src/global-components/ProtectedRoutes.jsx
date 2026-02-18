import { Navigate, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';

const ProtectedRoute = ({ allowedRoles }) => {
    const { isAuthenticated: isUserAuth, user } = useSelector((state) => state.userAuth)
    const { isAuthenticated: isCafeAuth, cafe } = useSelector((state) => state.cafeAuth)
    if (allowedRoles.includes("CAFE_TERMINAL")) {
        if (!isCafeAuth) return <Navigate to="/terminal/login" replace />//buralardaki navigateleri ilerde router yapısına göre değiştirebiliriz şimdilik böyle bıraktım
        const role = cafe?.role?.replace('ROLE_', '')
        if (role !== "CAFE_TERMINAL") return <Navigate to="/unauthorized" replace />

        return <Outlet />
    }
    if (allowedRoles.includes("OWNER") || allowedRoles.includes("ADMIN")) {
        if (!isUserAuth) return <Navigate to="/login" replace />
        const role = user?.role?.replace('ROLE_', '')
        const hasPermission = allowedRoles.includes(role)

        if (!hasPermission) {
            return <Navigate to="/" replace />
        }

        return <Outlet />
    }
    return <Navigate to="/" replace />
}

export default ProtectedRoute