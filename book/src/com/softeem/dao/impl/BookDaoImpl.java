package com.softeem.dao.impl;


import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public List<Book> findAll() throws SQLException {
        return queryRunner.query("select * from t_book order by id desc ", new BeanListHandler<>(Book.class, getProcessor()));
    }

    @Override
    public void save(Book book) throws SQLException {
        queryRunner.update("insert into t_book values (null ,?,?,?,?,?,?)", book.getName(),
                book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgpath());
    }

    @Override
    public void updateById(Book book) throws SQLException {
        queryRunner.update("update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_path = ? where id = ?",
                book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgpath(), book.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        queryRunner.update("delete from t_book where id = ?", id);
    }

    @Override
    public Book findById(Integer id) throws SQLException {
        return queryRunner.query("select * from t_book where id = ?", new BeanHandler<>(Book.class, getProcessor()), id);
    }

    @Override
    public List<Book> page(Integer pageNumber) throws SQLException {
        return queryRunner.query("select * from t_book limit ?,?", new BeanListHandler<>(Book.class, getProcessor()), (pageNumber - 1) * pageSize, pageSize);
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return queryRunner.query("select count(*) from t_book", new ScalarHandler<Long>()).intValue();
    }

    @Override
    public List<Book> Fuzzyquery(String keyword) throws SQLException {
        String sql = "SELECT * FROM t_book WHERE NAME LIKE '%" + keyword + "%'";
        BeanListHandler<Book> all = new BeanListHandler<>(Book.class, getProcessor());
        List<Book> books = queryRunner.query(sql, all);
        return books;
    }

    @Override
    public Book queryBookByID(Integer id) throws SQLException {
        Book book = findById(id);
        queryRunner.update("update t_book set name = ?,price = ?,author = ?,sales = ?,stock = ?,img_path = ? where id = ?",
                book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgpath(), book.getId());
        return book;
    }

    /**
     * 查询book表的总记录数
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Integer queryForPageTotalCount() throws SQLException {
        String sql = "select count(*) from t_book";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler);
        return i.intValue();
    }

    /**
     * 分页查询
     *
     * @param begin    起始值
     * @param pageSize 每次查询几条数据
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from t_book order by id desc Limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<Book>(Book.class, getProcessor()), begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        StringBuilder sql = new StringBuilder("select count(*) from t_book where 1 = 1");
        ArrayList list = new ArrayList();
        if (name != null && !"".equals(name)) {
            sql.append(" and name like ?");
            list.add("%" + name + "%");
        }
        if (author != null && !"".equals("author")) {
            sql.append(" and author like ?");
            list.add("%" + author + "%");
        }
        if ((min != null && min.signum() == 1) && (max != null && max.signum() == 1)) {
            //min值大于max值时进行两值交换
            if (min.compareTo(max) == 1) {
                BigDecimal temp = min;
                min = max;
                max = min;
            }
            sql.append(" and price between ? and ?");
            list.add(min);
            list.add(max);
        } else if (min != null && min.signum() == 1) {
            sql.append(" and price > ?");
        } else if (max != null && max.signum() == 1) {
            sql.append(" and price < ?");
        }
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql.toString(), handler, list.toArray());
        return i.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from t_book where 1 = 1");
        ArrayList list = new ArrayList();
        if (name != null && !"".equals(name)) {
            sql.append(" and name like ?");
            list.add("%" + name + "%");
        }
        if (author != null && !"".equals("author")) {
            sql.append(" and author like ?");
            list.add("%" + author + "%");
        }
        if ((min != null && min.signum() == 1) && (max != null && max.signum() == 1)) {
            //min值大于max值时进行两值交换
            if (min.compareTo(max) == 1) {
                BigDecimal temp = min;
                min = max;
                max = min;
            }
            sql.append(" and price between ? and ?");
            list.add(min);
            list.add(max);
        } else if (min != null && min.signum() == 1) {
            sql.append(" and price > ?");
        } else if (max != null && max.signum() == 1) {
            sql.append(" and price < ?");
        }
        String end = " order by id desc limit ?,?";
        sql.append(end);
        list.add(begin);
        list.add(pageSize);
        return queryRunner.query(sql.toString(), new BeanListHandler<Book>(Book.class, getProcessor()), list.toArray());
    }

    @Override
    public Integer queryByPriceCount(int min, int max) throws SQLException {
        String sql = "select count(*) from t_book where price BETWEEN ? and ?";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler, min, max);
        return i.intValue();
    }

    @Override
    public List<Book> queryByPriceItems(int pageNo, int pageSize, int min, int max) throws SQLException {
        String sql = "select * from t_book where price BETWEEN ? and ? LIMIT ?,?;";
        return queryRunner.query(sql, new BeanListHandler<Book>(Book.class, getProcessor()), min, max, pageNo, pageSize);
    }
}
