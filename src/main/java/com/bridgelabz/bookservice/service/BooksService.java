package com.bridgelabz.bookservice.service;

import com.bridgelabz.bookservice.DTO.BooksDTO;
import com.bridgelabz.bookservice.exception.BookException;
import com.bridgelabz.bookservice.model.BooksModel;
import com.bridgelabz.bookservice.repository.IBookRepository;
import com.bridgelabz.bookservice.util.Response;
import com.bridgelabz.bookservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements IBooksService{

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Response addBooks(BooksDTO booksDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            BooksModel booksModel = new BooksModel(booksDTO);
            booksModel.setRegisterDate(LocalDateTime.now());
            booksModel.setId(userId);
            bookRepository.save(booksModel);
            return new Response("Books Added Successfully", 200, booksModel);
        }
        throw new BookException(400, "Token Wrong");
    }

    @Override
    public Response getBooksById(long id) {
        Optional<BooksModel> booksModel = bookRepository.findById(id);
        return new Response("Books Found with id...", 200, booksModel.get());
    }

    @Override
    public List<BooksModel> getAllBooks(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<BooksModel> booksModel = bookRepository.findById(userId);
            if (booksModel.isPresent()){
                List<BooksModel> getAllBooks = bookRepository.findAll();
                if (getAllBooks.size() > 0){
                    return getAllBooks;
                } else
                    throw new BookException(400, "No Data Found");
            }
        }
        throw new BookException(400, "Books Not Found");
    }

    @Override
    public Response updateBooks(long bookId, BooksDTO booksDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<BooksModel> booksModel = bookRepository.findByUserIdAndId(userId, bookId);
            if (booksModel.isPresent()){
                booksModel.get().setBookName(booksDTO.getBookName());
                booksModel.get().setBookAuthor(booksDTO.getBookAuthor());
                booksModel.get().setBookDescription(booksDTO.getBookDescription());
                booksModel.get().setBookPrice(booksDTO.getBookPrice());
                booksModel.get().setBookQuantity(booksDTO.getBookQuantity());
                bookRepository.save(booksModel.get());
                return new Response("Books Updated Successfully", 200, null);
            }
        }
        throw new BookException(400, "Books Not Found");
    }

    @Override
    public Response deleteBooks(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<BooksModel> booksModel = bookRepository.findById(userId);
            if (booksModel.isPresent()) {
                bookRepository.delete(booksModel.get());
                return new Response("Books Deleted", 200, null);
                }
            }
        throw new BookException(400, "Books Not Found");
    }

    @Override
    public Response changeBooksQuantity(Long quantity, long bookId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<BooksModel> isBookPresent = bookRepository.findByUserIdAndId(userId, bookId);
            if (isBookPresent.isPresent()) {
                Long bookQuantity = isBookPresent.get().getBookQuantity() + quantity;
                isBookPresent.get().setBookQuantity(bookQuantity);
                bookRepository.save(isBookPresent.get());
                return new Response("Books Added", 200, bookQuantity);
            }
        }
        throw new BookException(400, "Cannot change Quantity");
    }

    @Override
    public Response changeBooksPrice(Long price, long bookId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://BS-USER-SERVICE:8083/user/verify/" + token, Boolean.class);
        if (isUserPresent) {
            Long userId = tokenUtil.decodeToken(token);
            Optional<BooksModel> isBookPresent = bookRepository.findByUserIdAndId(userId, bookId);
            if (isBookPresent.isPresent()){
               Long bookPrice = isBookPresent.get().getBookQuantity() * price;
               isBookPresent.get().setBookPrice(bookPrice);
               bookRepository.save(isBookPresent.get());
                return new Response("Books Added", 200, bookPrice);
            }
        }
        throw new BookException(400, "Cannot Change Price");
    }

    @Override
    public Boolean verify(String token) {
        Long bookId = tokenUtil.decodeToken(token);
        Optional<BooksModel> isBookPresent = bookRepository.findById(bookId);
        if (isBookPresent.isPresent()) {
            return true;
        }
        return false;
    }



}
