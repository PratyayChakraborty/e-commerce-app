package com.ecom.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {



}
