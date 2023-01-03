package com.ecom.Service;





import java.util.List;

import com.ecom.Model.Orders;

public interface OrderService {

    Orders orderItemsFromCart(Integer addressId);

    List<Orders> getOrderDetail();

    String cancelOrder (Integer orderId);

    Orders getSingleOrder(Integer orderId);

    Orders changeOrderStatus(Integer orderId , String Status);

}
