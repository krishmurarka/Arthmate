package com.example.demo.controller;


;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String Signup(Model model)
    {

        model.addAttribute("user",new User());

        return "signup";
    }
    @PostMapping("/save")
    public String createUser(@ModelAttribute("user") User user){


            System.out.println(user);

            User createdUser = this.userService.createUser(user);

            return "result";
    }

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("user",new User());
        return "login";
    }

    @PostMapping("/login/fetch")
    public String loginUser(@ModelAttribute("user") User user){
        System.out.println("dfafa");
        System.out.println(user);
        boolean isUserPresent = this.userService.getUser(user);

        if(isUserPresent) return "success";
        return "failed";
    }

}
