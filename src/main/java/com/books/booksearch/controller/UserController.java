package com.books.booksearch.controller;


//import com.books.booksearch.bean.User;
import com.books.booksearch.repository.BookRepository;
import com.books.booksearch.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.books.booksearch.bean.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userDao;

    private final BookRepository bookDao;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, BookRepository bookDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.bookDao = bookDao;
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
        List<Book> books = bookDao.findAllByUser(user);
        model.addAttribute("books", books);
        return "profile";
    }

    @PostMapping("/profile")
    public String postProfile(@RequestParam("id") String id) {
        Long ids = Long.valueOf(id);
        bookDao.deleteById(ids);
        return "redirect:/profile";
    }
    @GetMapping("/search")
    public String search() {
        return "bookSearch";
    }

    @PostMapping("/search")
    public String postSearch(@RequestParam("author") String author, @RequestParam("title") String title, @RequestParam("isbn") String isbn) {
        System.out.println("author = " + author);
        System.out.println("title = " + title);
        System.out.println("isbn = " + isbn);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book book = new Book(author, title, isbn, user);
        bookDao.save(book);
        return "bookSearch";
    }

}
