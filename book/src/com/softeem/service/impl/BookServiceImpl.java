package com.softeem.service.impl;

import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.dao.impl.BookDaoImpl;
import com.softeem.service.BookService;
import com.softeem.utils.Page;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> QueryBook(String keyword) throws SQLException {
        return bookDao.Fuzzyquery(keyword);
    }

    @Override
    public List<Book> FindAll() throws SQLException {
        return bookDao.findAll();
    }

    @Override
    public void addBook(Book book) throws SQLException {
        bookDao.save(book);
    }

    @Override
    public Book queryBookByID(Integer id) throws SQLException {
        return bookDao.queryBookByID(id);
    }

    @Override
    public void deleteBookByID(Integer id) throws SQLException {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        bookDao.updateById(book);
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public Page<Book> page(int pageNo, int pageSize) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize - 1) / pageSize);//设置总页码
        page.setPageNo(pageNo);//设置当前页
        page.setItems(bookDao.queryForPageItems((page.getPageNo() - 1) * pageSize, pageSize));//设置分页查询的结果集
        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookDao.queryForPageTotalCount(name, author, min, max);
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize - 1) / pageSize);//设置总页码
        page.setPageNo(pageNo);//设置当前页
        page.setItems(bookDao.queryForPageItems((page.getPageNo() - 1) * pageSize, pageSize, name, author, min, max));//设置分页查询的结果集
        return page;
    }

    public Page<Book> queryByPrice(int pageNo, int pageSize, int min, int max) throws SQLException {
        Page<Book> page = new Page<>();
        Integer totalCount = bookDao.queryByPriceCount(min, max);
        page.setPageTotalCount(totalCount);//设置总记录数
        page.setPageTotal((totalCount + pageSize - 1) / pageSize);//设置总页码
        page.setPageNo(pageNo);//设置当前页
        page.setItems(bookDao.queryByPriceItems((page.getPageNo() - 1) * pageSize, pageSize, min, max));//设置分页查询的结果集
        return page;
    }

    public static void main(String[] args) throws SQLException {
        BookServiceImpl bookService = new BookServiceImpl();
        Page<Book> page = bookService.page(1, 4);
        System.out.println("page = " + page);
    }
}
