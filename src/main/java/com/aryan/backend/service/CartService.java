package com.aryan.backend.service;

import com.aryan.backend.dto.User.CartItemResponseDto;
import com.aryan.backend.dto.User.CartResopnseDto;
import com.aryan.backend.entity.Cart;
import com.aryan.backend.entity.CartItem;
import com.aryan.backend.entity.Product;
import com.aryan.backend.entity.User;
import com.aryan.backend.repository.CartItemRepository;
import com.aryan.backend.repository.CartRepository;
import com.aryan.backend.repository.ProductRepository;
import com.aryan.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    CartService(CartRepository cartRepo, CartItemRepository cartItemRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public CartResopnseDto addToCart(Integer productID, String userName) {
        User u = userRepo.findByName(userName);
        Cart cart = u.getCart();

        if(cart == null) {
            cart = new Cart();
            cart.setUser(u);
            u.setCart(cart);
            cartRepo.save(cart);
        }

        Product prod = productRepo.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> optionalItem = cartItemRepo.findByCartIdAndProductId(cart.getId(), prod.getId());

        if(optionalItem.isPresent()) {
            CartItem existingItem = optionalItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemRepo.save(existingItem);
        }
        else{
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(prod);
            newItem.setQuantity(1);
            newItem.setProductPrice(prod.getPrice());

            cart.getCartItems().add(newItem);

            cartItemRepo.save(newItem);
        }

        return getCartItems(u);
    }

    public CartResopnseDto getCartItems(User u) {
        Cart cart = u.getCart();

        CartResopnseDto cartResopnseDto = new CartResopnseDto();

        cartResopnseDto.setItems(new ArrayList<>());
        cartResopnseDto.setId(cart.getId());
        cartResopnseDto.setTotalItems(cartItemRepo.countItemsByUserId(u.getId()));
        cartResopnseDto.setTotalPrice(cartItemRepo.getTotalPriceByUserId(u.getId()));

        for(CartItem c : cart.getCartItems()) {
            CartItemResponseDto i = new CartItemResponseDto();
            i.setProductName(c.getProduct().getName());
            i.setProductId(c.getProduct().getId());
            i.setId(c.getId());
            i.setQuantity(c.getQuantity());
            i.setProductPrice(c.getProductPrice());

            cartResopnseDto.getItems().add(i);
        }

        return cartResopnseDto;
    }

    public CartResopnseDto getCartItems(String username) {
        User u = userRepo.findByName(username);
        Cart cart = u.getCart();

        CartResopnseDto cartResopnseDto = new CartResopnseDto();

        cartResopnseDto.setItems(new ArrayList<>());
        cartResopnseDto.setId(cart.getId());
        cartResopnseDto.setTotalItems(cartItemRepo.countItemsByUserId(u.getId()));
        cartResopnseDto.setTotalPrice(cartItemRepo.getTotalPriceByUserId(u.getId()));

        for(CartItem c : cart.getCartItems()) {
            CartItemResponseDto i = new CartItemResponseDto();
            i.setProductName(c.getProduct().getName());
            i.setProductId(c.getProduct().getId());
            i.setId(c.getId());
            i.setQuantity(c.getQuantity());
            i.setProductPrice(c.getProductPrice());

            cartResopnseDto.getItems().add(i);
        }

        return cartResopnseDto;
    }
}
