package com.marian.anotation;

import com.marian.dao.UserDao;
import com.marian.service.UserEntiyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

public class UniqueUserForRegistration  implements ConstraintValidator<UniqueUser,String> {
   //@Autowired
    //private UserEntiyService userEntiyService;

    @Autowired
    private UserDao userDao;
//    @Autowired
//    public UniqueUserForRegistration(UserEntiyService userEntiyService) {
//        this.userEntiyService = userEntiyService;
//    }
    @Override
    public void initialize(UniqueUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if(userDao.getUserByEmailForRegistration(email)!=null) {
            return false;
        }else {
            return true;
        }
    }
}
