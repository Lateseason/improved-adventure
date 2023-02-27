package com.softeem.dao;

import com.softeem.bean.Book;
import com.softeem.utils.BaseInterface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


public interface BookDao extends BaseInterface<Book> {
    List<Book> Fuzzyquery(String keyword) throws SQLException;

    Book queryBookByID(Integer id) throws SQLException;

    Integer queryForPageTotalCount() throws SQLException;

    List<Book> queryForPageItems(int begin, int pageSize) throws SQLException;

    //方法重载
    Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException;

    //方法重载
    List<Book> queryForPageItems(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException;

    //自己写的原始版本,根据价格区间返回数据集
    List<Book> queryByPriceItems(int pageNo, int pageSize, int min, int max) throws SQLException;

    //自己写的原始版本,根据价格区间查询符合条件的总记录数
    Integer queryByPriceCount(int min, int max) throws SQLException;
}
