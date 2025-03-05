package lk.ijse.gdse.supermarket.dao.Custom;

import lk.ijse.gdse.supermarket.dao.CrudDAO;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDao extends CrudDAO<Customer,String> {
    List<String> getAllCustomerIds() throws SQLException;
}
