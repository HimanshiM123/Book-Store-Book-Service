package com.bridgelabz.bookservice.controller;

import com.bridgelabz.bookservice.DTO.BooksDTO;
import com.bridgelabz.bookservice.model.BooksModel;
import com.bridgelabz.bookservice.service.IBooksService;
import com.bridgelabz.bookservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    IBooksService booksService;

    @PostMapping(value = "/addBooks")
    ResponseEntity<Response> addBooks(@RequestBody BooksDTO booksDTO, @RequestHeader String token) {
        Response response = booksService.addBooks(booksDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getBooks/{id}")
    ResponseEntity<Response> getBooks(@PathVariable long id){
        Response response = booksService.getBooksById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllBooks")
    public List<BooksModel> getAllBooks(@RequestHeader String token){

        return booksService.getAllBooks(token);
    }

    @PutMapping("updateBooks/{id}")
    ResponseEntity<Response> updateNotes(@Valid @RequestBody BooksDTO booksDTO, @PathVariable long id, @RequestHeader String token ){
        Response response = booksService.updateBooks(id, booksDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("deleteBooks/{id}")
    ResponseEntity<Response> deleteBooks(@PathVariable Long id, @RequestHeader String token){

        Response response = booksService.deleteBooks(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PutMapping("changeBooksQuantity/{id}")
//    ResponseEntity<Response> changeBooksQuantity(@PathVariable Long quantity, @PathVariable long bookId, @RequestHeader String token ){
//        Response response = booksService.changeBooksQuantity(quantity, bookId, token);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
