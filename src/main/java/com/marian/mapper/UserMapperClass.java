package com.marian.mapper;

import com.marian.dao.UserDao;
import com.marian.domain.RegisterRequest;
import com.marian.domain.UserEditRequest;
import com.marian.entity.UserEntity;
import com.marian.entity.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperClass {
    //@Autowired
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    public UserMapperClass(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

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
    public UserEditRequest userToUserEditRequest(UserEntity user){
        UserEditRequest request = new UserEditRequest();
        request.setId(user.getId());
        request.setAge(user.getAge());
        request.setEmail(user.getEmail());
        request.setFullName(user.getFullName());
        return request;
    }
    public UserEntity editRequestToUser(UserEditRequest request, int userID){
        UserEntity userEntity = userDao.getUserById(userID);
        userEntity.setFullName(request.getFullName());
        userEntity.setAge(request.getAge());
        userEntity.setEmail(request.getEmail());
        userEntity.setId(userID);
        return userEntity;
    }
}
