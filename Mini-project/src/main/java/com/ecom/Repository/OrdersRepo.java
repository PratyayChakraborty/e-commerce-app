package com.ecom.Repository;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.Model.Orders;
import com.ecom.Model.UserModel;

@Repository
public interface OrdersRepo extends JpaRepository<Orders , Integer > {


    Optional<List<Orders>> findAllByUser(UserModel user);

    @Query(value = "SELECT o FROM Orders o WHERE o.orderDate = ?1")
    List<Orders> getTodaysSale(Date date);
}
