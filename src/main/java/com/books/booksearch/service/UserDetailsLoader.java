package com.books.booksearch.service;

import com.books.booksearch.bean.User;
import com.books.booksearch.bean.UserWithRoles;
import com.books.booksearch.repository.UserRepository;
import com.books.booksearch.bean.User;
import com.books.booksearch.bean.UserWithRoles;
import com.books.booksearch.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserDetailsLoader implements UserDetailsService {

    @Autowired
    private UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }
        return new UserWithRoles(user);
    }
}
