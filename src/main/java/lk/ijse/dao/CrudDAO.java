package lk.ijse.dao;

import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T>extends SuperDAO {
    public String currentId() throws SQLException, ClassNotFoundException ;

    public T search(String tel) throws SQLException, ClassNotFoundException ;

    public boolean save(T entity) throws SQLException, ClassNotFoundException ;

    public boolean update( T entity) throws SQLException, ClassNotFoundException ;

    public boolean delete(String tel) throws SQLException, ClassNotFoundException ;
    public List<T> getAll() throws SQLException, ClassNotFoundException ;

}
