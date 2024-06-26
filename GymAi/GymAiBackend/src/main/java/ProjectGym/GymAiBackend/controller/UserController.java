package ProjectGym.GymAiBackend.controller;


import ProjectGym.GymAiBackend.models.dto.UserDto;
import ProjectGym.GymAiBackend.models.entity.UserEntity;
import ProjectGym.GymAiBackend.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/v1/api")
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto){
        model.addAttribute("user",userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, UserDto userDto){
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSava(@ModelAttribute("user") UserDto userDto, Model model){
        UserEntity user = userService.findByUsername(userDto.getUsername());
        if(user == null){
            model.addAttribute("Userexist", user);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }


}
