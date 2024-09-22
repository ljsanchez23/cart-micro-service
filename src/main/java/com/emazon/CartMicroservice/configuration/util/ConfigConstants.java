package com.emazon.CartMicroservice.configuration.util;

import com.emazon.CartMicroservice.adapters.driven.jpa.mysql.entity.CartEntity;

public class ConfigConstants {
    public static final Long CART_ID = 1L;
    public static final Long CART_USER_ID = 3L;
    public static final CartEntity CLIENT_CART = new CartEntity(ConfigConstants.CART_ID, ConfigConstants.CART_USER_ID);
}
