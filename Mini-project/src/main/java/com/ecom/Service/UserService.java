package com.ecom.Service;



import com.ecom.DTO.UserResponseDTO;
import com.ecom.Model.UserModel;

public interface UserService {

    UserResponseDTO updateUserDetails(UserModel userModel) throws Exception;



}
