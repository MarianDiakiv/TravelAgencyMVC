package com.marian.controller;

import com.marian.entity.Country;
import com.marian.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    @Autowired
    public UserController(CountryService countryService, HotelService hotelService, OrderService orderService, RoomService roomService, TypeRoomService typeRoomService, UserEntiyService userEntiyService) {
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.orderService = orderService;
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.userEntiyService = userEntiyService;
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
}
