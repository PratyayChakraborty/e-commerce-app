package com.ecom.DTO;


import java.util.HashSet;
import java.util.Set;

import com.ecom.Model.Address;
import com.ecom.Model.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Integer id;

    private String name;

    private String mobileNumber;

    private String email;

    private Set<Address> address = new HashSet<>();

    private Cart cart;

}
