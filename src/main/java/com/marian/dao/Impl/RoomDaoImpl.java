package com.marian.dao.Impl;

import com.marian.dao.RoomDao;
import com.marian.entity.Hotel;
import com.marian.entity.Room;
import com.marian.entity.TypeRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class RoomDaoImpl implements RoomDao {
    private SessionFactory  sessionFactory;

    @Autowired
    public RoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Room room) {
        sessionFactory.getCurrentSession().save(room);
    }

    @Override
    public List<Room> getRoomByHotel(Hotel hotel) {
      return   sessionFactory.getCurrentSession().createQuery("select r from Room r where r.hotel=:hotelName")
                .setParameter("hotelName", hotel).list();
    }

    @Override
    public Room getById(int id) {
        return sessionFactory.getCurrentSession().get(Room.class,id);
    }

    @Override
    public void update(Room room) {
        sessionFactory.getCurrentSession().update(room);
    }


    @Override
    public void delete(Room room) {
        sessionFactory.getCurrentSession().delete(room);
    }


}
