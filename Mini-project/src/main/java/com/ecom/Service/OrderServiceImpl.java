package com.ecom.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Model.OrderItemQuantity;
import com.ecom.Model.Orders;
import com.ecom.Model.UserModel;
import com.ecom.Repository.AddressRepo;
import com.ecom.Repository.OrdersRepo;
import com.ecom.Repository.UserEntityRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private  ManageUserService userService;

    @Autowired
    private  AddressRepo addressRepo;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private OrdersRepo ordersRepo;


    @Override
    public Orders orderItemsFromCart(Integer addressId) {

        UserModel user = userService.getUser();

        Orders orders = Orders.builder()
                              .orderAddress(addressRepo.findById(addressId).orElseThrow(() -> new RuntimeException("Please add a valid address")))
                              .user(user)
                              .itemList(new ArrayList<>())
                              .orderDate(LocalDateTime.now())
                              .orderStatus("Placed")
                              .build();

        if( user.getCart().getItems().size() == 0 ) throw new RuntimeException( "No item present in cart" );

        List<OrderItemQuantity> orderItems =  user.getCart().getItems()
                      .stream()
                      .map( i -> new OrderItemQuantity( i.getItem() , i.getQuantity() ) )
                      .toList();

        orders.setItemList( orderItems );
        user.getCart().setItems( new ArrayList<>() );
        user.getCart().setTotalPrice( 0.0 );

        userEntityRepository.save(user);
        ordersRepo.save(orders);

        return orders;
    }

    /**
     * @return Orders list of the current user
     */

    @Override
    public List<Orders> getOrderDetail() {

        UserModel userModel = userService.getUser();

        return ordersRepo.findAllByUser(userModel).orElseThrow(() -> new RuntimeException("No order made by the user"));

    }

    @Override
    public String cancelOrder(Integer orderId) {

        UserModel model = userService.getUser();

        Orders order =  ordersRepo.findById(orderId).
                orElseThrow(()-> new RuntimeException
                                ("Order not found by the id " + orderId));

        if( Objects.equals( order.getUser().getId() , model.getId() ) ){
            ordersRepo.delete(order);
            return "Order deleted successfully";
        }

        throw new RuntimeException("Invalid Order id " + orderId);
    }

    @Override
    public Orders getSingleOrder(Integer orderId) {

        Orders order = ordersRepo.findById(orderId).
                orElseThrow(()-> new RuntimeException("Order not found by the id " + orderId));

        return order;

    }

    @Override
    public Orders changeOrderStatus(Integer orderId, String status) {

        Orders order = ordersRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Invalid Order Id Passed By User"));

        order.setOrderStatus(status);

        return  ordersRepo.save(order);
    }

}
