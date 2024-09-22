package com.emazon.CartMicroservice.adapters.driving.http.controller;

import com.emazon.CartMicroservice.adapters.driven.jpa.mysql.entity.CartEntity;
import com.emazon.CartMicroservice.adapters.driven.jpa.mysql.repository.ICartRepository;
import com.emazon.CartMicroservice.adapters.util.AdapConstants;
import com.emazon.CartMicroservice.configuration.security.jwt.JwtValidate;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping(AdapConstants.CART_URL)
public class CartRestControllerAdapter {

    private final ICartRepository cartRepository;

    public CartRestControllerAdapter(ICartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @PostMapping(AdapConstants.CART_ACTION_PATH)
    public ResponseEntity<String> addToCart(@PathVariable Long cartId, HttpServletRequest request) {

        if (!JwtValidate.tokenExists(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AdapConstants.JWT_MISSING);
        }

        Optional<CartEntity> cartEntity = cartRepository.findById(cartId);

        if (cartEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AdapConstants.CART_NOT_FOUND);
        }

        Claims claims;
        try {
            claims = JwtValidate.JwtValidation(request);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AdapConstants.JWT_INVALID);
        }

        Long idFromToken = claims.get(AdapConstants.USER_ID_FROM_TOKEN, Long.class);

        if (Objects.equals(cartEntity.get().getUserId(), idFromToken)) {
            return ResponseEntity.status(HttpStatus.OK).body(AdapConstants.USER_VALID_AND_OWNER);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(AdapConstants.USER_NOT_OWNER);
    }

    @DeleteMapping(AdapConstants.CART_ACTION_PATH)
    public ResponseEntity<String> deleteFromCart(@PathVariable Long cartId, HttpServletRequest request) {
        if (!JwtValidate.tokenExists(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AdapConstants.JWT_MISSING);
        }

        Optional<CartEntity> cartEntity = cartRepository.findById(cartId);

        if (cartEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AdapConstants.CART_NOT_FOUND);
        }

        Claims claims;
        try {
            claims = JwtValidate.JwtValidation(request);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AdapConstants.JWT_INVALID);
        }

        Long idFromToken = claims.get(AdapConstants.USER_ID_FROM_TOKEN, Long.class);
        if (Objects.equals(cartEntity.get().getUserId(), idFromToken)) {
            return ResponseEntity.status(HttpStatus.OK).body(AdapConstants.USER_VALID_AND_OWNER);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(AdapConstants.USER_NOT_OWNER);
    }
}
