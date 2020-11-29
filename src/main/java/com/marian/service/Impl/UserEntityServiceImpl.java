package com.marian.service.Impl;

import com.marian.dao.UserDao;
import com.marian.entity.UserEntity;
import com.marian.service.UserEntiyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserEntityServiceImpl implements UserEntiyService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserEntity getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void saveUser(UserEntity user) {
        userDao.saveUser(user);
    }
}
