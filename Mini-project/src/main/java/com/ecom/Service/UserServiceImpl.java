package com.ecom.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ecom.DTO.UserResponseDTO;
import com.ecom.Exception.LoginException;
import com.ecom.Model.UserModel;
import com.ecom.Repository.ItemsRepository;
import com.ecom.Repository.RoleRepository;
import com.ecom.Repository.UserEntityRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserEntityRepository entityRepository;
    private RoleRepository roleEntityRepository;
    private ItemsRepository itemsRepository;

    @Autowired
    public void setEntityRepository(UserEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Autowired
    public void setItemsRepository(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public UserResponseDTO updateUserDetails(UserModel userModel) throws LoginException {

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userEmail = userDetails.getUsername();

        UserModel user = entityRepository.findByEmail(userEmail).
                orElseThrow(()->new LoginException("User with email '" + userEmail + "' does not exist"));

        user.setName(userModel.getName());

        UserModel dto = entityRepository.save(user);


        return  UserResponseDTO.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getName())
                .cart(dto.getCart())
                .mobileNumber(dto.getMobileNumber())
                .address(dto.getAddress())
                .build();
    }

 

}

