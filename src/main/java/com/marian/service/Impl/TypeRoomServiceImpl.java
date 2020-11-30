package com.marian.service.Impl;

import com.marian.dao.TypeRoomDao;
import com.marian.entity.TypeRoom;
import com.marian.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeRoomServiceImpl implements TypeRoomService {
    private TypeRoomDao typeRoomDao;

    @Autowired
    public TypeRoomServiceImpl(TypeRoomDao typeRoomDao) {
        this.typeRoomDao = typeRoomDao;
    }

    @Override
    public void save(TypeRoom typeRoom) {
        typeRoomDao.save(typeRoom);
    }

    @Override
    public List<TypeRoom> getAll() {
        return typeRoomDao.getAll();
    }

    @Override
    public TypeRoom getTypeRoomByName(String name) {
        return typeRoomDao.getByTypeRoomName(name);
    }
}
