package com.korede.ElasticSearch.controller;

import com.korede.ElasticSearch.dto.BookDto;
import com.korede.ElasticSearch.dto.BookResponseDto;
import com.korede.ElasticSearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

   @Autowired
    private BookService bookService;


    @PostMapping(value = "/saveBook")
    public ResponseEntity<?> saveBook(@RequestBody BookDto bookDto) {
        BookResponseDto saveBook = bookService.saveBook(bookDto);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") String id,@RequestBody BookDto bookDto) {
        BookResponseDto updateBook = bookService.updateBook(id, bookDto);
        return new ResponseEntity<>(updateBook, HttpStatus.CREATED);
    }


    @GetMapping(value = "/listAllBooks")
    public ResponseEntity<List<BookResponseDto>> listAllBooks(Pageable pageable) {
        List<BookResponseDto> listAllBooks = bookService.listAllBooks(pageable);
        return new ResponseEntity<>(listAllBooks, HttpStatus.OK);
    }


    @GetMapping(value = "/bookTitle/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        BookResponseDto getBookByTitle = bookService.getBookByTitle(title);
        return new ResponseEntity<>(getBookByTitle, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String id) {
     bookService.deleteBook(id);
     return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }


}
