package com.marian.dao.Impl;

import com.marian.dao.TypeRoomDao;
import com.marian.entity.TypeRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TypeRoomDaoImpl implements TypeRoomDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TypeRoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(TypeRoom typeRoom) {
        sessionFactory.getCurrentSession().save(typeRoom);
    }

    @Override
    public List<TypeRoom> getAll() {
        return sessionFactory.getCurrentSession().createQuery("select  t from TypeRoom t ").list();
    }

    @Override
    public TypeRoom getByTypeRoomName(String name) {
        return (TypeRoom) sessionFactory.getCurrentSession().createQuery("select  t from TypeRoom t where t.typeRoom =:name")
                .setParameter("name",name).getSingleResult();
    }
}
