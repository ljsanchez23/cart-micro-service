package com.emazon.CartMicroservice.configuration;

import com.emazon.CartMicroservice.adapters.driven.jpa.mysql.repository.ICartRepository;
import com.emazon.CartMicroservice.configuration.util.ConfigConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final ICartRepository cartRepository;
    public DataInitializer(ICartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @PostConstruct
    void init(){
        cartRepository.save(ConfigConstants.CLIENT_CART);
    }

}
