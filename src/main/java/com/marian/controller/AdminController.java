package com.marian.controller;

import com.marian.domain.HotelRequest;
import com.marian.domain.RoomRequest;
import com.marian.entity.Country;
import com.marian.entity.Hotel;
import com.marian.entity.Room;
import com.marian.entity.TypeRoom;
import com.marian.mapper.HotelMapper;
import com.marian.mapper.RoomMapper;
import com.marian.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller("/adminBoard")
@Controller
@RequestMapping("/adminBoard")
public class AdminController {

    private CountryService countryService;
    private HotelService hotelService;
    private OrderService orderService;
    private RoomService roomService;
    private TypeRoomService typeRoomService;
    private UserEntiyService userEntiyService;

    private HotelMapper hotelMapper;
    private RoomMapper roomMapper;
    @Autowired
    public AdminController(CountryService countryService, HotelService hotelService, OrderService orderService, RoomService roomService,
                           TypeRoomService typeRoomService, UserEntiyService userEntiyService, HotelMapper hotelMapper, RoomMapper roomMapper) {
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.orderService = orderService;
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.userEntiyService = userEntiyService;
        this.hotelMapper = hotelMapper;
        this.roomMapper = roomMapper;
    }




    @GetMapping("/admin-page")
    public String getAdminPage(Model model){
        model.addAttribute("country", new Country());
        model.addAttribute("roomType", new TypeRoom());
        return "admin/admin_page";
    }


    @PostMapping("/addCountry")
    public String addCountry(@ModelAttribute("country") Country country){
        countryService.createCountry(country);
        return "redirect:/adminBoard/admin-page";
    }
    @PostMapping("/addTypeRoom")
    public String addRoomType(@ModelAttribute("roomType")  TypeRoom typeRoom ){
        typeRoomService.save(typeRoom);
        return "redirect:/adminBoard/admin-page";
    }




    @GetMapping("/create-hotel")
    public String createHotelPage(Model model){
        model.addAttribute("country", new Country());
        model.addAttribute("hotel", new HotelRequest());
        List<Country> countries = countryService.getAllCountry();
        Map<String, String> map =new HashMap<>();
        for (Country country:countries){
            map.put(country.getCountry(),country.getCountry());
        }
        model.addAttribute("countries", map);
        //model.addAttribute("countries", countryService.getAllCountry());
        return "admin/create_hotel";
    }

    @PostMapping("/create-hotel")
    public String saveHotel(@ModelAttribute("hotel") HotelRequest hotelRequest){
        Hotel hotel = hotelMapper.requestToHotel(hotelRequest);
        hotelService.save(hotel);
        return "redirect:/adminBoard/create-hotel"; // поміняти на список готелів
    }


    @GetMapping("/edit-hotel/{id}")
    public String getEditHotelPage(@PathVariable("id") int id, Model model){
        Hotel hotel = hotelService.getById(id);
        model.addAttribute("hotelModel", hotel);
        List<TypeRoom> typeRooms = typeRoomService.getAll();
        Map<String, String> map = new HashMap<>();
        for (TypeRoom typeRoom:typeRooms){
            map.put(typeRoom.getTypeRoom(),typeRoom.getTypeRoom());
        }
        model.addAttribute("typesModel", map);
        model.addAttribute("roomRequest" , new RoomRequest());
        model.addAttribute("rooms", roomService.getRoomByHotel(hotel));
        return "admin/edit_hotel";
    }
    @PostMapping("/edit-hotel/{id}")
    public  String editHotel( @PathVariable("id") int id, @ModelAttribute("hotel") Hotel hotel ){
        //System.out.println(hotel.getId()+ hotel.getCountry().getCountry());
        hotelService.update(hotel,id);
        return "redirect:/hotels";
    }
    @PostMapping("/create-room/{idHotel}")
    public String createRoom(@PathVariable("idHotel") int id, @ModelAttribute("roomRequest") RoomRequest request){
        roomService.save(roomMapper.requestToRoom(request,id));
        return "redirect:/adminBoard/edit-hotel/"+id;
    }
}




























