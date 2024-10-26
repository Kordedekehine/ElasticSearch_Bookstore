package com.korede.ElasticSearch.service;


import com.korede.ElasticSearch.dto.BookDto;
import com.korede.ElasticSearch.dto.BookResponseDto;
import com.korede.ElasticSearch.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BookService {

    BookResponseDto saveBook(BookDto bookDto) ;

    BookResponseDto updateBook(String id, BookDto bookDto);

    List<BookResponseDto> listAllBooks(Pageable pageable);

    BookResponseDto getBookByTitle(String title) ;

    String deleteBook(String id);


}
