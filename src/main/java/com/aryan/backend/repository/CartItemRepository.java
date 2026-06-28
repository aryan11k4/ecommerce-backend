package com.aryan.backend.repository;

import com.aryan.backend.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartIdAndProductId(Integer cartId, Integer productId);

    @Query("""
    SELECT COUNT(ci)
    FROM CartItem ci
    WHERE ci.cart.user.id = :userId
""")
    int countItemsByUserId(@Param("userId") Integer userId);

    @Query("""
    SELECT COALESCE(SUM(ci.productPrice * ci.quantity), 0)
    FROM CartItem ci
    WHERE ci.cart.user.id = :userId
""")
    BigDecimal getTotalPriceByUserId(@Param("userId") Integer userId);
}
