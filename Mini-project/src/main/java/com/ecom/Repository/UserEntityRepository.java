package com.ecom.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Model.UserModel;

@Repository
public interface UserEntityRepository extends JpaRepository<UserModel, Integer> {


    public Optional<UserModel> findByEmail(String email);

    public boolean existsByEmail(String email);


}

