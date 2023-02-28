package com.books.booksearch.controller;


//import com.books.booksearch.bean.User;
import com.books.booksearch.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.books.booksearch.bean.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(User user, Model model) {
        boolean validRegister = true;
        if (userDao.findByUsername(user.getUsername()) != null) {
            validRegister = false;
            model.addAttribute("usernameAlreadyInUse", true);
            model.addAttribute("usernameInUse", (String) user.getUsername());

        } else if (userDao.findByEmail(user.getEmail()) != null){
            validRegister = false;
            model.addAttribute("emailAlreadyInUse", true);
            model.addAttribute("emailInUse", (String) user.getEmail());
        }
        if (validRegister) {

            String pw = passwordEncoder.encode(user.getPassword());
            user.setPassword(pw);
            userDao.save(user);
            return "redirect:/login";
        } else {
            return "register";
        }

    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "profile";
    }

}
