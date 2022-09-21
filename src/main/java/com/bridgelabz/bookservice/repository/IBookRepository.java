package com.bridgelabz.bookservice.repository;

import com.bridgelabz.bookservice.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<BooksModel, Long> {

}
