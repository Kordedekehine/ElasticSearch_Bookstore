package com.korede.ElasticSearch.service.serviceImpl;

import com.korede.ElasticSearch.dto.BookDto;
import com.korede.ElasticSearch.dto.BookResponseDto;
import com.korede.ElasticSearch.model.Book;
import com.korede.ElasticSearch.repository.BookRepository;
import com.korede.ElasticSearch.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Mixin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private  BookRepository bookRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public BookResponseDto saveBook(BookDto bookDto) {

        Optional<Book> optionalBook = bookRepository.findBookByTitle(bookDto.getTitle());

        if (optionalBook.isPresent()){
            throw new RuntimeException("Book already exists");
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());


        bookRepository.save(book);

        return convertToBookResponseDto(book);
    }

    private BookResponseDto convertToBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = modelMapper.map(book, BookResponseDto.class);
        return bookResponseDto;
    }

    @Override
    public BookResponseDto updateBook(String id, BookDto bookDto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));


        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());

        bookRepository.save(book);

        return convertToBookResponseDto(book);
    }

    @Override
    public List<BookResponseDto> listAllBooks(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable); // Returns a Page<Book>
        List<Book> books = bookPage.getContent(); // Now you can use getContent()

        return books.stream()
                .map(this::convertToBookResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getBookByTitle(String title) {

        Book book = bookRepository.findBookByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return convertToBookResponseDto(book);
    }

    @Override
    public String deleteBook(String id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookRepository.delete(book);
        return "Successfully deleted Book";
    }



}
