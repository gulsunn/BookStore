package com.gulsun.BookStore.service;

import com.gulsun.BookStore.domain.Book;
import com.gulsun.BookStore.dto.BookDTO;
import com.gulsun.BookStore.exception.ConflictException;
import com.gulsun.BookStore.exception.ResourceNotFoundException;
import com.gulsun.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public void saveBook(BookDTO bookDTO) {
        //Önce kaydedilen kitabın isbn numarası zaten var mı kontrol et

       boolean isExistBook= bookRepository.existsByIsbn(bookDTO.getIsbn());
       if(isExistBook){
           throw  new ConflictException("Bu ISBN no ile daha önce kitap kaydı yapılmıştır.");
       }

        //DTO yu normal objeye çevir çünkü DB ye DTO gitmez

        Book book = new Book(bookDTO); //Book Entity Class da bir constructor oluşturmuştum

        //  Girilen isbn(unique olması gerekiyordu) db de yoksa artık kaydedebilirsin
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDTO getBookById(Long id) {
     //    findById() ile bulduk            //Girilen id li kitap ya yoksa diye orElseThrow kullandık
      Book book = bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Girdiğiniz id ile Kitap Bulunamadı"));
      BookDTO bookDTO = new BookDTO(book);// Book u BookDTO ya çevirdik
      return bookDTO;
    }

    public List<Book> getBookByName(String name) {

    List<Book>  books= bookRepository.findByName(name);

    return books;

    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    public List<Book> getBookByFullQuery(String name, String publisher) {
       return bookRepository.findByNameAndPublisher(name,publisher);  // findByNameAndPublisher  findBy dan sonra and ile iki paremetre yazıldı
    }
}
