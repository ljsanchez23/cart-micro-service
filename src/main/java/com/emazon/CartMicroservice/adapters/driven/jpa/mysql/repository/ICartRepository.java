package com.emazon.CartMicroservice.adapters.driven.jpa.mysql.repository;

import com.emazon.CartMicroservice.adapters.driven.jpa.mysql.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findById(Long id);
}
