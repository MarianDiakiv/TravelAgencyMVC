package com.marian.dao.Impl;

import com.marian.dao.OrderDao;
import com.marian.entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
    private  SessionFactory sessionFactory;
    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getAllOrdered(Date date1) {
        return sessionFactory.getCurrentSession().createQuery("select  o from Order o where :date1 between o.dateOfSettlement and" +
                " o.departureDate")
                .setParameter("date1",date1)
                .list();
    }

    @Override
    public void save(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
}
