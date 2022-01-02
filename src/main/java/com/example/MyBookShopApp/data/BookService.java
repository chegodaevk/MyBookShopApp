package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    // создаём jdbcTemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbsTemplate) {
        this.jdbcTemplate = jdbsTemplate;
    }

    public List<Book> getBookData() {
        // создаём локальную переменную типа List работающую с типом Book с использованием лямбда выражения
        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            //
            Book book = new Book();
            // считываем из объекта ResultSet значения
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            return book;
        });
        return new ArrayList<>(books);
    }
}
