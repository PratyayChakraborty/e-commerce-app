package com.ecom.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModelDTO {

    private String name;

    private String mobileNumber;

    private String email;

}
