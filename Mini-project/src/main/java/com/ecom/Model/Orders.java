package com.ecom.Model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Address orderAddress;

    @ManyToOne
    @JsonIgnore
    private UserModel user;

    private LocalDateTime orderDate;

    @Pattern(regexp = "^(Placed | Shipped | OutForDelivery | Delivered | Failed)")
    private String orderStatus;
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItemQuantity> itemList = new ArrayList<>();


}
