package com.gulsun.BookStore.dto;

import com.gulsun.BookStore.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
// entity yazmayız db de tablo oluşmayacak db ye gitmeyecek sadece data transfer edecek (DTO objeleri oluşacak)
public class BookDTO {

    @NotBlank
    @NotNull(message = "Ad boş bırakılamaz")
    @Size(min = 2 ,max = 50)
    private String name;

    @Size(min = 2 ,max = 50)
    private String author;

    @Size(min = 2 ,max = 50)
    private String publisher;

    @NotNull
    @NotBlank
    @Size(min = 4,max = 4)
    @Column(unique = true,nullable = false)
    private String isbn;

    private Double price;

    public BookDTO(Book book) {   // Book u BookDTO ya çeviriyoruz
        this.name = book.getName();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
        this.price = book.getPrice();
    }
}
