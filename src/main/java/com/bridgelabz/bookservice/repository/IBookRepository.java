package com.bridgelabz.bookservice.repository;

import com.bridgelabz.bookservice.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBookRepository extends JpaRepository<BooksModel, Long> {

    Optional<BooksModel> findByUserIdAndId(Long userId, long bookId);
}
