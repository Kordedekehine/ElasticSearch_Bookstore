package com.korede.ElasticSearch.repository;


import com.korede.ElasticSearch.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface BookRepository extends ElasticsearchRepository<Book,String> {

    Optional<Book> findBookByTitle(String title);

    //Lets try more flexible Pagination to get All Books

    Page<Book> findAll(Pageable pageable);
}

