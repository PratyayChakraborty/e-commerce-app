package com.ecom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.Model.Role;

@Repository
public interface RoleEntityRepository extends JpaRepository<Role, Integer> {


    public Optional<Role> findRoleByRole(String role);

}
