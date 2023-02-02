package com.gulsun.BookStore.repository;

import com.gulsun.BookStore.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {  //  JpaRepository den extend et ve <Entity Class adı, id data tipi>
    boolean existsByIsbn(String isbn);


    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByPublisher(String publisher);

    List<Book> findByNameAndPublisher(String name, String publisher);
}
