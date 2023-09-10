package com.javabro.controllers;

import com.javabro.dto.UserCartDTO;
import com.javabro.dto.UserProductCartDTO;
import com.javabro.model.UserCart;
import com.javabro.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserCartController {

    @Autowired
    UserCartService userCartService;

    @PostMapping("/addProductToCart")
    public UserCartDTO addProductToCart(@RequestBody UserCartDTO userCartDTO) {
        UserCart userCart = userCartService.addProductToCart(userCartDTO);
        userCartDTO.setId(userCart.getId());
        return userCartDTO;
    }

    @PutMapping("/addProductToCart")
    public UserCartDTO updateProductInCart(@RequestBody UserCartDTO userCartDTO, @PathVariable("id") Long id) {
        UserCart userCart = userCartService.updateProductInCart(userCartDTO, id);
        return userCartDTO;
    }

    @DeleteMapping("/addProductToCart")
    public void deleteProductFromCart(@PathVariable("id") Long id) {
        userCartService.deleteProductFromCart(id);
    }

    @GetMapping("/getProductsInCart/{userId}")
    public List<UserProductCartDTO> getProductsInCart(@PathVariable("userId") Long userId) {
        return userCartService.getProductsInCart(userId);
    }

}
