package com.marian.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="room_type_table")
public class TypeRoom {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    @Column(name="type_room")
    private String typeRoom;

    @OneToMany(mappedBy="typeRoom")
    private List<Room> rooms = new ArrayList<>();

    public TypeRoom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
