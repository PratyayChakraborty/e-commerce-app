package com.ecom.Service;



import java.util.List;

import com.ecom.Model.Items;
import com.ecom.Model.Orders;
import com.ecom.Model.UserModel;

public interface DashBoardService {

    List<Orders> getTodaySales();

    List<Orders> getAllOrders();

    Items addNewItem(Items items);

    Items updateItem(Items items);

    Items deleteItem(Integer itemId);

    UserModel getUserDetails(Integer userId);

    List<Orders> getUserOrderDetails(Integer userId);

}

