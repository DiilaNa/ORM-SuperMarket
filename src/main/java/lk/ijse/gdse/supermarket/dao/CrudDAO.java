package lk.ijse.gdse.supermarket.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDao {
    ArrayList<T> getALL () throws SQLException;
    boolean save (T dto) throws SQLException;
    boolean update (T dto) throws SQLException;
    boolean delete (String id) throws SQLException;
    boolean exist(String id) ;
    String generateNewId() throws SQLException;
    T search(String id) throws SQLException;
}
