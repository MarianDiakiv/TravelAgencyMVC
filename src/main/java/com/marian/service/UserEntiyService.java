package com.marian.service;

import com.marian.entity.UserEntity;

import java.util.List;

public interface UserEntiyService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(int id);
    UserEntity getUserByEmail(String email);
    void saveUser(UserEntity user);

    public void updateUser(UserEntity userEntity);
}
