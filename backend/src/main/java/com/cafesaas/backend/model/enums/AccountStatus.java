package com.cafesaas.backend.model.enums;

public enum AccountStatus {

    REGISTERED,

    ACTIVE,

    SUSPENDED,


    BANNED;

    public boolean canLogin() {
        return this != BANNED;
    }

    /**
     * Kullanıcı aktif olarak işlem yapabilir mi (Yeni kafe ekle, rapor gör vs)?
     * Sadece ACTIVE olanlar yapabilir.
     */
    public boolean canUseFeatures() {
        return this == ACTIVE;
    }
}
