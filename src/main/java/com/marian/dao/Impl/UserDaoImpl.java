package com.marian.dao.Impl;

import com.marian.dao.UserDao;
import com.marian.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<UserEntity> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<UserEntity> list  = session.createQuery(" select  u From UserEntity u").list();

        return  list;
    }

    @Override
    public UserEntity getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserEntity.class, id);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity user =  (UserEntity) session.createQuery("select u from UserEntity u where u.email = :email " )
               .setParameter("email",email).getSingleResult();
        return  user ;
    }

    @Override
    public void saveUser(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void update(UserEntity user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public UserEntity getUserByEmailForRegistration(String email) {
        return (UserEntity) sessionFactory.getCurrentSession().createQuery("select u from UserEntity u where u.email = :email " )
                .setParameter("email",email).stream().findFirst().orElse(null);
    }
}
