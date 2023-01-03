package com.ecom.Service;





import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ecom.Exception.UserDoesNotExtistException;
import com.ecom.Model.Role;
import com.ecom.Model.UserModel;
import com.ecom.Repository.UserEntityRepository;
import com.ecom.Security.TokenGenerator;



@Service
public class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    private  UserEntityRepository repository ;

    @Autowired
    private TokenGenerator generator;

    @Override
    public UserDetails loadUserByUsername(String username) { /*   */

        UserModel userModel = repository
                                .findByEmail( username )
                                .orElseThrow( () ->
                                        new UserDoesNotExtistException("User does not exsist exception") );



        return new User( userModel.getEmail() ,
                         userModel.getPassword(),
                         mapRoleToUser( userModel.getRoles() )
                        );
    }

    private Collection<GrantedAuthority> mapRoleToUser(List<Role> roleList){

        return roleList
                .stream()
                .map( role -> new SimpleGrantedAuthority( role.getRole() ) )
                .collect(Collectors.toSet());

    }



}