package com.softeem.test;


import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.dao.impl.BookDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImplTest {
    BookDao bookdao;
    @Before
    public  void liu(){
        bookdao = new BookDaoImpl();
    }
    @Test
    public void findAll() throws SQLException {
        List<Book> all = bookdao.findAll();
        for (Book book : all) {
            System.out.println("book = " + book);
        }
    }

    @Test
    public void save() throws SQLException {
        Book book = new Book(21,"测试", BigDecimal.valueOf(38),"刘大师",83,9999,"测试图片");
        bookdao.save(book);
    }

    @Test
    public void updateById() throws SQLException {
        Book book = new Book(21,"新测试", BigDecimal.valueOf(38),"新刘大师",83,9999,"新测试图片");
        bookdao.updateById(book);
    }

    @Test
    public void deleteById() throws SQLException {
        bookdao.deleteById(21);
    }

    @Test
    public void findById() throws SQLException {
        System.out.println(bookdao.findById(20));
    }

    @Test
    public void page() throws SQLException {
        List<Book> list = bookdao.page(3);
        for (Book book : list) {
            System.out.println("book = " + book);
        }
    }

    @Test
    public void pageRecord() throws SQLException {
        System.out.println(bookdao.pageRecord());
    }
    @Test
    public void Fuzzyquery() throws SQLException{
        List<Book> books = bookdao.Fuzzyquery("java");
        for (Book book : books) {
            System.out.println("book = " + book);
        }
    }

    @Test
    public void queryForPageTotalCount() throws SQLException {
        Integer integer = bookdao.queryForPageTotalCount();
        System.out.println("integer = " + integer);
    }

    @Test
    public void queryForPageItems() throws SQLException {
        List<Book> list = bookdao.queryForPageItems(0, 5);
        for (Book book : list) {
            System.out.println("book = " + book);
        }

    }

    @Test
    public void queryByPriceCount() throws SQLException {
        Integer integer = bookdao.queryByPriceCount(10, 100);
        System.out.println("integer = " + integer);
    }

    @Test
    public void queryByPriceItems() throws SQLException {
        List<Book> list = bookdao.queryByPriceItems(2,4,10, 100);
        for (Book book : list) {
            System.out.println("book = " + book);
        }
    }

    @Test
    public void testQueryForPageTotalCount() {

    }

    @Test
    public void testQueryForPageItems() throws SQLException {
        List<Book> list = bookdao.queryForPageItems(0, 5, null, null, new BigDecimal(20), new BigDecimal(80));
        for (Book book : list) {
            System.out.println("book = " + book);
        }
    }
}