package com.bridgelabz.bookservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class BooksDTO {
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private Long bookPrice;
    private Long bookQuantity;
}
