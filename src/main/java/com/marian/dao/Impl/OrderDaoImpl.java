package com.marian.dao.Impl;

import com.marian.dao.OrderDao;
import com.marian.entity.Order;
import com.marian.entity.Room;
import com.marian.entity.UserEntity;
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

    @Override
    public List<Order> getAllOrderByUser(UserEntity userEntity) {
        return sessionFactory.getCurrentSession().createQuery("select  o from Order o where o.userEntity=:user")
                .setParameter("user",userEntity).list();
    }

    @Override
    public void delete(Order order) {
        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    public List<Order> getOrderByRoomAndDate(Room room, Date currentDate) {
        return sessionFactory.getCurrentSession().createQuery("select  o from Order o where o.room=:room and :currentDate <= o.departureDate")
                .setParameter("currentDate",currentDate)
                .setParameter("room",room)
                .list();
    }

    @Override
    public Order getById(int id) {
        return sessionFactory.getCurrentSession().get(Order.class,id);
    }

    @Override
    public List<Order> getAllOrderByRoom(Room room) {
        return sessionFactory.getCurrentSession().createQuery("select o from Order o where o.room = :room")
                .setParameter("room",room).list();
    }
}
