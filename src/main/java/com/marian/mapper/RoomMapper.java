package com.marian.mapper;

import com.marian.domain.RoomRequest;
import com.marian.entity.Room;
import com.marian.entity.TypeRoom;
import com.marian.service.HotelService;
import com.marian.service.RoomService;
import com.marian.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    private RoomService roomService;
    private TypeRoomService typeRoomService;
    private HotelService hotelService;



    @Autowired
    public RoomMapper(RoomService roomService, TypeRoomService typeRoomService, HotelService hotelService) {
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.hotelService = hotelService;
    }

    public Room requestToRoom(RoomRequest request, int hotelId){
    Room room = new Room();
        TypeRoom typeRoom = typeRoomService.getTypeRoomByName(request.getTypeRoom());
        room.setNumber(request.getNumber());
        room.setPrice(request.getPrice());
        room.setTypeRoom(typeRoom);
        room.setHotel(hotelService.getById(hotelId));
        return room;

}
}
