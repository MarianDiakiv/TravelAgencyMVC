package com.marian.mapper;

import com.marian.domain.RegisterRequest;
import com.marian.entity.UserEntity;
import com.marian.entity.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperClass {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public  User toSecurityUser(UserEntity entity) {
        return new User(entity.getEmail(), entity.getPassword(),
                AuthorityUtils.createAuthorityList(String.valueOf(entity.getRole())));
    }

    public  UserEntity registerToUser(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword( passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        return user;
    }
}
