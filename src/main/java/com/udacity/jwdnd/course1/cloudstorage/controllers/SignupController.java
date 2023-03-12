package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView(){
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs){
        String signupError = null;
        System.out.println("user exist: " + this.userService.isUserAvailable(user.getUsername()));
        if(this.userService.isUserAvailable(user.getUsername())){
            System.out.println("signup" + userService.getUser(user.getUsername()));
            signupError = "Username already exist.";
        }

        if(signupError == null){
            int rowsAdded = this.userService.createUser(user);
            if(rowsAdded == 0) {
                signupError = "Error signing up. Please try again later.";
            }
        }

        if(signupError == null){
            redirectAttrs.addFlashAttribute("signupSuccess", true);
            return "redirect:/login";
        }
        else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}
