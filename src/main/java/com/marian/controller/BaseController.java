package com.marian.controller;

import com.marian.dao.UserDao;
import com.marian.domain.LoginRequest;
import com.marian.domain.RegisterRequest;
import com.marian.entity.UserEntity;
import com.marian.mapper.UserMapper;
import com.marian.mapper.UserMapperClass;
import com.marian.service.HotelService;
import com.marian.service.UserEntiyService;
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
import java.util.List;


@Controller
public class BaseController {
    @Autowired
    private UserEntiyService userEntiyService;
    @Autowired
    private UserMapperClass userMapperClass;
    @Autowired
    private HotelService hotelService;

    @GetMapping({"/", "/home"})
    public String jsp(Principal principal, Model model){

        if (principal!=null){
            model.addAttribute("username" ,principal.getName());
            UserEntity entity = userEntiyService.getUserByEmail(principal.getName());
            model.addAttribute("user",entity);
        }
        model.addAttribute("message", "HELLOMESSAGE");
//         List<UserEntity> list =  userEntiyService.getAllUsers();
//        for (UserEntity e:list){
//            System.out.println(e.getEmail());
//        }
//        System.out.println(userEntiyService.getUserById(1).getFullName());
//        System.out.println(userEntiyService.getUserByEmail("email").getEmail());
         return "hello_home";
    }
    @GetMapping("/login")
    public String showLogin(Model model) {

        model.addAttribute("userModel", new LoginRequest());
        return"/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("registerModel", new RegisterRequest());
        return"register";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute("registerModel") @Valid RegisterRequest request, BindingResult result) {
        UserEntity userEntity = new UserEntity();
        System.out.println(request.getEmail());
        userEntity= UserMapper.registerToUser(request);
        if(result.hasErrors()) {
            return"/register";
        }
        //UserMapperClass userMapperClass = new UserMapperClass();
        userEntiyService.saveUser(userMapperClass.registerToUser(request));

        return"redirect:/login";
    }

    @GetMapping("/adminBoard/{id}")
    public String getUserPage( Model model, @PathVariable("id") int id){
        model.addAttribute("message", userEntiyService.getUserById(id).getEmail());
        return "hello_home";

    }



}
