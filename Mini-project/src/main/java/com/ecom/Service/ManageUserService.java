package com.ecom.Service;



import java.util.List;

import com.ecom.DTO.AuthenticatedResponseDto;
import com.ecom.DTO.ChangeUserPasswordDto;
import com.ecom.DTO.RegisterDto;
import com.ecom.DTO.UserLoginDto;
import com.ecom.Model.Address;
import com.ecom.Model.UserModel;

public interface ManageUserService {

    String registerCustomer(RegisterDto registerDto, String userType);
    
    AuthenticatedResponseDto loginUser(UserLoginDto loginDto);

    String addAddress(Address address);
    
    String changePassword(ChangeUserPasswordDto password);

    UserModel getUser();

    Address editAddress(Address address);
    
    String deleteAddress(Integer addressId);

    Address getAddress(Integer addressId);
    
    List<Address> getAllOfUser();
    
}
