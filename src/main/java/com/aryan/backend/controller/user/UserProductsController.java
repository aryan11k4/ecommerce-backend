package com.aryan.backend.controller.user;

import com.aryan.backend.dto.cart.CartResponseDto;
import com.aryan.backend.dto.product.ProductResponseDto;
import com.aryan.backend.security.UserPrincipal;
import com.aryan.backend.service.CartService;
import com.aryan.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserProductsController {
    private final ProductService productService;
    private final CartService cartService;

    UserProductsController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("product/getAll")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> responseDtos = productService.getAllProducts();

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("cart/item/addToCart")
    public ResponseEntity<CartResponseDto> addToCart(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Integer productID) {
        CartResponseDto responseDto = cartService.addToCart(productID, userPrincipal.getUsername());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/cart/getAll")
    public ResponseEntity<CartResponseDto> getCartProducts(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        CartResponseDto resopnseDto = cartService.getCartItems(userPrincipal.getUsername());

        return ResponseEntity.ok(resopnseDto);
    }
}
