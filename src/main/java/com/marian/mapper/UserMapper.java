package com.marian.mapper;

import com.marian.domain.RegisterRequest;
import com.marian.entity.UserEntity;
import com.marian.entity.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserMapper {


    public static User toSecurityUser(UserEntity entity) {
        return new User(entity.getEmail(), entity.getPassword(),
                AuthorityUtils.createAuthorityList(String.valueOf(entity.getRole())));
    }

    public static UserEntity registerToUser(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        return user;
    }
}
