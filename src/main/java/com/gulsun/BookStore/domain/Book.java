package com.gulsun.BookStore.domain;

import com.gulsun.BookStore.dto.BookDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//entity den sonra otomatik olarak oluşması için getter setter All/NoArgsConstructor annotationları koy
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Setter(AccessLevel.NONE)  // AccessLevel i none yap setter metodu olmasın
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Otomatik generate edilsin
    @Id   // Bu field id olacak
    private Long id;

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

    public Book(BookDTO bookDTO) {  // BookDTO yu Book a çevirir
        this.name = bookDTO.getName();
        this.author =bookDTO.getAuthor();
        this.publisher = bookDTO.getPublisher();
        this.isbn = bookDTO.getIsbn();
        this.price = bookDTO.getPrice();
    }
}
