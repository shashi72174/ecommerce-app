package com.javabro.repository;

import com.javabro.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    public List<UserCart> findByUserId(Long userId);

}
