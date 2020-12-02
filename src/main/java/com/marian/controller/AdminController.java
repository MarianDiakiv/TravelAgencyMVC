package com.marian.controller;

import com.marian.domain.EmailSearch;
import com.marian.domain.HotelRequest;
import com.marian.domain.RoomRequest;
import com.marian.domain.UserEditRequest;
import com.marian.entity.*;
import com.marian.mapper.HotelMapper;
import com.marian.mapper.RoomMapper;
import com.marian.mapper.UserMapperClass;
import com.marian.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    private UserMapperClass userMapperClass;
    @Autowired
    public AdminController(CountryService countryService, HotelService hotelService, OrderService orderService, RoomService roomService,
                           TypeRoomService typeRoomService, UserEntiyService userEntiyService, HotelMapper hotelMapper,
                           RoomMapper roomMapper, UserMapperClass userMapperClass) {
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.orderService = orderService;
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.userEntiyService = userEntiyService;
        this.hotelMapper = hotelMapper;
        this.roomMapper = roomMapper;
        this.userMapperClass = userMapperClass;
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

    @GetMapping("/edit-room/{id}")
    public String editRoomPage(@PathVariable("id") int id, Model model){

        List<TypeRoom> typeRooms = typeRoomService.getAll();
        Map<String, String> map = new HashMap<>();
        for (TypeRoom typeRoom:typeRooms){
            map.put(typeRoom.getTypeRoom(),typeRoom.getTypeRoom());
        }
        Room room = roomService.getbyId(id);
        model.addAttribute("typesModel", map);
        model.addAttribute("roomId",id);
        model.addAttribute("hotelId",room.getHotel().getId());
//        model.addAttribute("roomModel", roomService.getbyId(id));
        model.addAttribute("roomRequestModel", roomMapper.roomToRequest(room));

        return "admin/edit_room";
    }
    @PostMapping("/edit-room/{hotelId}/{id}")
    public String editRoom(@PathVariable("hotelId") int hotelID, @PathVariable("id") int id, @ModelAttribute("roomRequestModel") RoomRequest request){
        Room room = roomMapper.requestToRoom(request,hotelID, id);
        roomService.update(room);
        return "redirect:/adminBoard/edit-hotel/"+room.getHotel().getId();
    }
    @PostMapping("/delete-room/{hoteLid}/{roomID}")
    public String deleteRoom(@PathVariable("hoteLid") int hotelId, @PathVariable("roomID") int roomId){
        System.out.println(roomId);
        return "redirect:/adminBoard/edit-hotel/"+hotelId;
    }
    @GetMapping("/all-user")
    public String getUsersListPage(Model model){
        List<UserEntity>  allUsers = userEntiyService.getAllUsers();
        List<UserEditRequest> requests = new ArrayList<>();
        for (UserEntity entity:allUsers){
            requests.add(userMapperClass.userToUserEditRequest(entity));
        }
        model.addAttribute("emailModel", new EmailSearch());
        model.addAttribute("usersModel",requests);
        return "admin/admin_user_page";
    }
    @PostMapping("/get-user-by-email")
    public String getUserByEmail(@ModelAttribute("emailModel") EmailSearch emailSearch,Model model){
        List<UserEditRequest> requests = new ArrayList<>();
        requests.add(userMapperClass.userToUserEditRequest(userEntiyService.getUserByEmail(emailSearch.getEmail())));
        model.addAttribute("emailModel", new EmailSearch());
        model.addAttribute("usersModel",requests);
        return "admin/admin_user_page";
    }
    @GetMapping("/user-orders-for-admin/{userEmail}")
    public String getUserOrderForAdmin(@PathVariable("userEmail") String email, Model model){
        System.out.println(email);
        UserEntity  userEntity = userEntiyService.getUserByEmail(email+".com");
        UserEditRequest request = userMapperClass.userToUserEditRequest(userEntity);
        List<Order> orderList =  orderService.getAllOrderByUser(userEntity);
        model.addAttribute("ordersModel", orderList);
        model.addAttribute("userModel", request);
        return "admin/admin_users_order";
    }

}




























