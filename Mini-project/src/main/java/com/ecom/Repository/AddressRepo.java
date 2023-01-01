package com.ecom.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.Model.Address;

public interface AddressRepo extends JpaRepository< Address , Integer > {



}
