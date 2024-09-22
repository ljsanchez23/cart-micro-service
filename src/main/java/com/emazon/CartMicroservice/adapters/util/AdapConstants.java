package com.emazon.CartMicroservice.adapters.util;

public class AdapConstants {
    public static final String CART_URL = "/cart";
    public static final String CART_ACTION_PATH = "/{cartId}";
    public static final String USER_VALID_AND_OWNER = "The user is valid and the owner of this cart";
    public static final String USER_ID_FROM_TOKEN = "userId";
    public static final String JWT_INVALID = "Invalid JWT token";
    public static final String JWT_MISSING = "JWT token is missing on the request";
    public static final String CART_NOT_FOUND = "Cart not found";
    public static final String USER_NOT_OWNER = "The user is not the owner of the cart";
}
