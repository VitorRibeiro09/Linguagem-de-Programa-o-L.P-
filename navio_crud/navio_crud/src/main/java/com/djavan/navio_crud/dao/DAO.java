package com.djavan.navio_crud.dao;

import java.sql.SQLException;
import java.util.List;

public interface
DAO<T> {
    void create(T entity) throws SQLException;
    T read(int id) throws SQLException;
    List<T> readAll() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(int id) throws SQLException;
}
