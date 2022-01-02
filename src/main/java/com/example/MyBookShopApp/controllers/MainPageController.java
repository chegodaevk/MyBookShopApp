package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
@Controller
// задаём корневой мэппинг
@RequestMapping("/bookshop")
public class MainPageController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public MainPageController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/main")
    public String mainPage(Model model){
        // получаем объект модели и отправляем свойство bookData которое содержит в себе список данных книги
        model.addAttribute("bookData",bookService.getBookData());
        return "index";
    }

    @GetMapping("/genres")
    public String pageGenres(){
        return "genres/index";
    }

    @GetMapping("/authors")
    public String pageAuthors(Model model){
        model.addAttribute("authorData", authorService.getAuthorData());
        return "authors/index";
    }
}
