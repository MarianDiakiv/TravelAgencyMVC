package com.marian.dao.Impl;

import com.marian.dao.HotelDao;
import com.marian.entity.Country;
import com.marian.entity.Hotel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HotelDaoImpl implements HotelDao {

    private SessionFactory sessionFactory;

    @Autowired
    public HotelDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Hotel hotel) {
        sessionFactory.getCurrentSession().save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return sessionFactory.getCurrentSession().createQuery("select h from Hotel h ").list();
    }

    @Override
    public List<Hotel> getbuCountry(Country country) {
        return  sessionFactory.getCurrentSession().createQuery("select  h from  Hotel h  join h.country c where c.id=:id")
                .setParameter("id", country.getId()).list();
    }

    @Override
    public Hotel getById(int id) {
        return sessionFactory.getCurrentSession().get(Hotel.class,id);
    }

    @Override
    public void update(Hotel hotel) {
        sessionFactory.getCurrentSession().update(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        sessionFactory.getCurrentSession().delete(hotel);
    }
}
