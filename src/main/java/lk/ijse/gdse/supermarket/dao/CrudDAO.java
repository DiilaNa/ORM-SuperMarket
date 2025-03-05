package lk.ijse.gdse.supermarket.dao;

import lk.ijse.gdse.supermarket.entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDao{
    List<T> getALL () throws SQLException;
    boolean save (T dto) throws SQLException;
    boolean update (T dto) throws SQLException;
    boolean delete (String id) throws SQLException;
    boolean exist(String id) ;
    String generateNewId() throws SQLException;
    Optional<T> findByPK(ID pk)throws SQLException;
}
