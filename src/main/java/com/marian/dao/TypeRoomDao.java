package com.marian.dao;

import com.marian.entity.TypeRoom;

import java.util.List;

public interface TypeRoomDao {
    void save(TypeRoom typeRoom);
    List<TypeRoom> getAll();
    TypeRoom getByTypeRoomName(String name);
}
