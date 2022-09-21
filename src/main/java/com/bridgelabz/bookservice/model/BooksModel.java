package com.bridgelabz.bookservice.model;

import com.bridgelabz.bookservice.DTO.BooksDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
public class BooksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private Long bookPrice;
    private Long bookQuantity;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;

    public BooksModel(BooksDTO booksDTO) {
        this.bookName = booksDTO.getBookName();
        this.bookAuthor = booksDTO.getBookAuthor();
        this.bookDescription = booksDTO.getBookDescription();
        this.bookPrice = booksDTO.getBookPrice();
        this.bookQuantity = booksDTO.getBookQuantity();
    }

    public BooksModel() {

    }
}
