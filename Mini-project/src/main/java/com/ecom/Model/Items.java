package com.ecom.Model;


import java.util.Locale.Category;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 4 , max = 55 , message = "name should not be less than 4 character")
//    @Pattern(regexp="^[A-Za-z]+$" , message = "name should not contain any special character")
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "1.00")
    private Double price;

//    @URL
    private String image;

    @Enumerated(EnumType.STRING)
    private Category category;

}
