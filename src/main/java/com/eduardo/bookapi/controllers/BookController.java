package com.eduardo.bookapi.controllers;

import com.eduardo.bookapi.models.Book;
import com.eduardo.bookapi.services.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> findAll(){
        return this.bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable(name = "id") Integer id){
        return this.bookService.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return this.bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable(name = "id") Integer id, @RequestBody Book book){
        return this.bookService.updateBook(book, id);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable(name = "id") Integer id){
        return this.bookService.deleteBook(id);
    }

}
