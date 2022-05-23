package com.eduardo.bookapi.services;

import com.eduardo.bookapi.models.Book;
import com.eduardo.bookapi.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public Book getBookById(Integer book_id){
        return this.bookRepository.findById(book_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Book saveBook(Book book){
        return this.bookRepository.save(book);
    }

    public Book updateBook(Book bookDetails, Integer book_id){
        Book book = this.bookRepository.findById(book_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(bookDetails.getBook_name() != null) book.setBook_name(bookDetails.getBook_name());
        if(bookDetails.getCurrent_owner() != null) book.setCurrent_owner(bookDetails.getCurrent_owner());
        if(bookDetails.getVolume() != null) book.setVolume(bookDetails.getVolume());
        if(bookDetails.getQuantity() != null) book.setQuantity(bookDetails.getQuantity());
        return this.bookRepository.save(book);
    }

    public Book deleteBook(Integer book_id){
        Book book = this.bookRepository.findById(book_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.bookRepository.delete(book);
        return book;
    }
}
