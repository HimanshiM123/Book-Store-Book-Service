package com.bridgelabz.bookservice.service;

import com.bridgelabz.bookservice.DTO.BooksDTO;
import com.bridgelabz.bookservice.model.BooksModel;
import com.bridgelabz.bookservice.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBooksService {
    Response addBooks(BooksDTO booksDTO, String token);

    Response getBooksById(long id);

    List<BooksModel> getAllBooks(String token);

    Response updateBooks(long id, BooksDTO booksDTO, String token);

    Response deleteBooks(Long id, String token);

}
