package com.gulsun.BookStore.controller;

import com.gulsun.BookStore.domain.Book;
import com.gulsun.BookStore.dto.BookDTO;
import com.gulsun.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller Class ın asıl amacı requestleri karşılamak

@RestController
@RequestMapping("/books") //base url den sonra hangi end point ile gelen endpointler bu classa gelecek
public class BookController {

    @Autowired
    BookService bookService;

    //1-Spring Bootu selamlama:)->http://localhost:8080/books/hi

    @GetMapping("/hi")  // books dan sonra hi yazınca bu metoda gelsin
    public String greet(){
        return " Spring Boot ile  Book Store Projesine Merhaba";
    }

    // Book ları kaydeden methodu oluştur http://localhost:8080/books/save

    @PostMapping("/save")  // books dan sonra save yazıldığında bu metod çalışır
    public ResponseEntity<String> saveBook(@RequestBody BookDTO bookDTO){ // request in body sinden gelen mesajları BookDTO türünde bookDTO ya aktar

        bookService.saveBook(bookDTO); //service katmanımda bir saveBook() olsaydı gelen bu kitap bilgilerini onunla kaydetseydim

        String message ="Kitap kayıt işlemi başarıyla gerçekleşti.";

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //================================================================================================================

    //Tüm bookları getiren metodu oluştur  http://localhost:8080/books

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){

     List<Book> books = bookService.getAllBooks();
     return new ResponseEntity<>(books,HttpStatus.OK);
    }

    //================================================================================================================

   // Id ile tek bir customer getirme->http://localhost:8080/books/1
   // PathVariable ile

   @GetMapping("/{id}")  //Pathvarible de { } içine yaz
   public ResponseEntity<BookDTO> getBookByIdWithPathVariable(@PathVariable("id") Long id){

       BookDTO bookDTO= bookService.getBookById(id);

       return new ResponseEntity<>(bookDTO,HttpStatus.OK);

   }

   // RequestParam ile

    @GetMapping("/get")  // http://localhost:8080/books/get/?id=1
    public ResponseEntity<BookDTO> getBookByIdWithRequestParam(@RequestParam("id") Long id){

        BookDTO bookDTO= bookService.getBookById(id);

        return new ResponseEntity<>(bookDTO,HttpStatus.OK);

    }

    //================================================================================================================

    // Kitap adı ile kitapları getir

    @GetMapping("/getname")   // http://localhost:8080/books/getname/?name=Küçük Prens
    public ResponseEntity<List<Book>> getBookByName(@RequestParam("name") String name){

      List<Book> books= bookService.getBookByName(name);

      return new ResponseEntity<>(books,HttpStatus.OK);

    }
    //================================================================================================================

    // Yazar adı ile kitapları getir

    @GetMapping("/getauthor")   // http://localhost:8080/books/getauthor/?author=Bilgin Adalı
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam("author") String author){

        List<Book> books= bookService.getBookByAuthor(author);

        return new ResponseEntity<>(books,HttpStatus.OK);

    }

    //================================================================================================================

    // Yayınevi adı ile kitapları getir

    @GetMapping("/getpublisher")   // http://localhost:8080/books/getpublisher/?publisher=Can Çocuk
    public ResponseEntity<List<Book>> getBookByPublisher(@RequestParam("publisher") String publisher){

        List<Book> books= bookService.getBookByPublisher(publisher);

        return new ResponseEntity<>(books,HttpStatus.OK);

    }

    //================================================================================================================

    // request param ile birden fazla paremetre ile Book getirme
    // Kitap adı ve yayınevi adı ile book getir

    @GetMapping("/fullquery")  // http://localhost:8080/books/fullquery/?name=Küçük Prens&publisher=Can Çocuk
    public ResponseEntity<List<Book>> getBookByFullQuery(@RequestParam("name") String name, @RequestParam("publisher") String publisher){

    List<Book>  books =bookService.getBookByFullQuery(name,publisher);
    return new ResponseEntity<>(books,HttpStatus.OK);

    }

    //================================================================================================================



















    // id ile Book silme
    // id ile Book update etme
    // Tüm bookları page page gösterme
    // jpql ile price 30 dan büyük olanları getir
    // Adında a olan kitapları getir
















}
