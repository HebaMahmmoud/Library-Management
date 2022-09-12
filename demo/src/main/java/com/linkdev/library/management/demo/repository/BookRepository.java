package com.linkdev.library.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linkdev.library.management.demo.entity.Book;



public interface BookRepository extends JpaRepository<Book, Integer> {

}
