package com.softeem.service;

import com.softeem.bean.Book;
import com.softeem.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookService {
    List<Book> QueryBook(String keyword) throws SQLException;
    List<Book> FindAll() throws SQLException;
    void addBook(Book book) throws SQLException;
    Book queryBookByID(Integer id) throws SQLException;
    void  deleteBookByID(Integer id) throws SQLException;
    void updateBook(Book book) throws SQLException;
    Page<Book> page(int pageNo, int pageSize) throws SQLException;
    Page<Book> page(int pageNo, int pageSize, String name, String author, BigDecimal min,BigDecimal max) throws SQLException;
    Page<Book> queryByPrice(int pageNo,int pageSize,int min,int max) throws SQLException;
}
