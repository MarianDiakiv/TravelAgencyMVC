package com.marian.dao.Impl;

import com.marian.dao.UserDao;
import com.marian.entity.UserEntity;
import com.marian.mapper.UserMapper;
import com.marian.mapper.UserMapperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapperClass userMapperClass;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity entity = userDao.getUserByEmail(email);

        if(entity==null)throw new UsernameNotFoundException("USER Not Found");
        return userMapperClass.toSecurityUser(entity);
    }
}
