package com.marian.service;

import com.marian.entity.TypeRoom;

import java.util.List;

public interface TypeRoomService {
    void save(TypeRoom typeRoom);
    List<TypeRoom> getAll();
    TypeRoom getTypeRoomByName(String name);
}
