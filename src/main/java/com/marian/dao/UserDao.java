package com.marian.dao;

import com.marian.entity.UserEntity;

import java.util.List;

public interface UserDao {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(int id);
    UserEntity getUserByEmail(String email);
    void saveUser(UserEntity user);
    void update(UserEntity user);
    UserEntity getUserByEmailForRegistration(String email);
}
