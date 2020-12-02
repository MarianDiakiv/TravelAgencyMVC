package com.marian.controller;

import com.marian.domain.DateSearch;
import com.marian.domain.UserEditRequest;
import com.marian.entity.Country;
import com.marian.entity.Hotel;
import com.marian.entity.Room;
import com.marian.entity.UserEntity;
import com.marian.mapper.UserMapperClass;
import com.marian.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private CountryService countryService;
    private HotelService hotelService;
    private OrderService orderService;
    private RoomService roomService;
    private TypeRoomService typeRoomService;
    private UserEntiyService userEntiyService;
    private UserMapperClass userMapper;


    @Autowired
    public UserController(CountryService countryService, HotelService hotelService, OrderService orderService,
                          RoomService roomService, TypeRoomService typeRoomService, UserEntiyService userEntiyService,
                          UserMapperClass userMapper) {
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.orderService = orderService;
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.userEntiyService = userEntiyService;
        this.userMapper = userMapper;
    }
    @GetMapping("/hotels")
    public String showHotelList( Model model){
        List<Country> countries = countryService.getAllCountry();
        Map<String, String> map =new HashMap<>();
        for (Country country:countries){
            map.put(country.getCountry(),country.getCountry());
        }
        model.addAttribute("countries", map);
        model.addAttribute("country", new Country());
        model.addAttribute("hotels", hotelService.getAll());
        return "hotels";
    }

    @PostMapping("/find-by-country")
    public String getCountrySearch(@ModelAttribute("country") String country){
        System.out.println(country);
        return "redirect:/hotels/"+country;
    }
    @GetMapping("/hotels/{country}")
    public String getHotelByCountry(@PathVariable("country") String countryName, Model model){
        List<Country> countries = countryService.getAllCountry();
        Map<String, String> map =new HashMap<>();
        for (Country country:countries){
            map.put(country.getCountry(),country.getCountry());
        }
        model.addAttribute("countries", map);
        model.addAttribute("country", new Country());

        Country country = countryService.getByName(countryName);
        model.addAttribute("hotels", hotelService.getByCountry(country));
        return "hotels";
    }

    @GetMapping("/hotel-info/{id}")
    public String getHotelInfoPage( @PathVariable("id") int id, Model model){
        Hotel hotel = hotelService.getById(id);
        model.addAttribute("hotelModel",hotel);
        model.addAttribute("dateModel", new DateSearch());
        model.addAttribute("roomsModel", roomService.getRoomByHotel(hotel));
        return "hotel_info";
    }

    @PostMapping("/free-rooms/{id}")
    public String findRoomS(@PathVariable("id") int hotelId, @ModelAttribute("dateModel") DateSearch dateSearch, Model model  ){
        System.out.println(dateSearch.getDate1());
        System.out.println(dateSearch.getDate2());
        List<Room> roomList = roomService.getFreeRoom(dateSearch,hotelId);

        model.addAttribute("hotelModel",hotelService.getById(hotelId));
        model.addAttribute("dateModel", new DateSearch());
        model.addAttribute("roomsModel", roomService.getFreeRoom(dateSearch,hotelId));
        model.addAttribute("dateModel",dateSearch);

        return "hotel_info";
    }
    @PostMapping("/order/{id}")
    public String orderRoom(@PathVariable("id") int roomId, @ModelAttribute("dateModel") DateSearch dateSearch, Principal principal){

        System.out.println("r"+ roomId);
        System.out.println("date1" + dateSearch.getDate1());
        System.out.println("date2" + dateSearch.getDate2());
        orderService.saveOrder(principal,dateSearch,roomId);
        return "redirect:/hotels";
    }

    @GetMapping("/profile")
    public String getUserPage(Principal principal, Model model){
        UserEntity userEntity = userEntiyService.getUserByEmail(principal.getName());
        model.addAttribute("userModel",userEntity);
        model.addAttribute("orderModel", orderService.getAllOrderByUser(userEntity));
        return "user_page";
    }
    @PostMapping("/cancel-order/{id}")
    public String cancerOrder(@PathVariable("id") int id){
        orderService.delete(id);
        return "redirect:/profile";
    }
    @GetMapping("/edit-user-rofile/{id}")
    public String editUserPage(@PathVariable("id") int userId, Model model){
        model.addAttribute("userId",userId);
        model.addAttribute("userEditModel", userMapper.userToUserEditRequest(userEntiyService.getUserById(userId)));
        return "edit_user";
    }
    @PostMapping("/edit-user-rofile/{id}")
    public String editUser(@PathVariable("id") int userId, @ModelAttribute("userEditModel") @Valid UserEditRequest request,
                           BindingResult result){
        if (result.hasErrors()){
            return "redirect:/edit-user-rofile/"+userId;
        }
        userEntiyService.updateUser(userMapper.editRequestToUser(request,userId));
        return "redirect:/profile";
    }
}
