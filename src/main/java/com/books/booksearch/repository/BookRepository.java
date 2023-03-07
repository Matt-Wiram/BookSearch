package com.books.booksearch.repository;

import com.books.booksearch.bean.Book;
import com.books.booksearch.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByUser(User user);
}
