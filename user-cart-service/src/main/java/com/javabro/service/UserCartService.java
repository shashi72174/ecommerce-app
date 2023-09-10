package com.javabro.service;

import com.javabro.dto.InventoryDTO;
import com.javabro.dto.UserCartDTO;
import com.javabro.dto.UserProductCartDTO;
import com.javabro.model.UserCart;
import com.javabro.repository.UserCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserCartService {

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    RestTemplate restTemplate;

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

    public List<UserProductCartDTO> getProductsInCart(Long userId) {
        return userCartRepository.findByUserId(userId).stream().map(userCart -> {
            UserProductCartDTO userProductCartDTO = new UserProductCartDTO();
            Map<String, Object> map = new HashMap<>();
            map.put("id", userCart.getProductId());
            InventoryDTO inventoryDTO = restTemplate.getForObject("http://localhost:9090/v1/products/{id}", InventoryDTO.class, map);
            userProductCartDTO.setId(userCart.getId());
            userProductCartDTO.setQuantity(userCart.getQuantity());
            userProductCartDTO.setProductName(inventoryDTO.getProductName());
            userProductCartDTO.setProductDescription(inventoryDTO.getProductDescription());
            userProductCartDTO.setProductPrice(inventoryDTO.getProductPrice());
            return userProductCartDTO;
        }).collect(Collectors.toList());
    }
}
