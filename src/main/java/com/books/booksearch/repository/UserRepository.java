package com.books.booksearch.repository;

import com.books.booksearch.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    User findByUsername(String name);

    User findByEmail(String email);
}
