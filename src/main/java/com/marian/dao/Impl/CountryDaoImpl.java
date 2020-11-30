package com.marian.dao.Impl;

import com.marian.dao.CountryDao;
import com.marian.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

    private SessionFactory sessionFactory;


    @Autowired
    public CountryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createCountry(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.save(country);
    }
    @Override
    public List<Country> getAllCountry(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select  c from Country c").list();
    }
    @Override
    public Country getById(int id){
        Session session = sessionFactory.getCurrentSession();
       return session.get(Country.class, id);
    }

    @Override
    public Country getByName(String country) {
        Session session = sessionFactory.getCurrentSession();
        Country country1 = (Country) session.createQuery("select c from Country c where c.country=:name ")
                .setParameter("name",country).getSingleResult();
        return country1;
    }
}
