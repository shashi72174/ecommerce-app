package com.javabro.service;

import com.javabro.dto.UserCartDTO;
import com.javabro.model.UserCart;
import com.javabro.repository.UserCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserCartService {

    @Autowired
    UserCartRepository userCartRepository;


    public UserCart addProductToCart(UserCartDTO userCartDTO) {
        UserCart userCart = new UserCart();
        userCart.setUserId(userCartDTO.getUserId());
        userCart.setProductId(userCartDTO.getProductId());
        userCart.setAddedOn(new Date());
        userCart.setQuantity(userCartDTO.getQuantity());
        return userCartRepository.save(userCart);
    }

    public UserCart updateProductInCart(UserCartDTO userCartDTO, Long id) {
        UserCart userCart = new UserCart();
        userCart.setUserId(userCartDTO.getUserId());
        userCart.setProductId(userCartDTO.getProductId());
        userCart.setAddedOn(new Date());
        userCart.setQuantity(userCartDTO.getQuantity());
        userCart.setId(id);
        return userCartRepository.save(userCart);
    }

    public void deleteProductFromCart(Long id) {
        userCartRepository.deleteById(id);
    }

    public List<UserCartDTO> getProductsInCart(Long userId) {

        return null;
    }
}
