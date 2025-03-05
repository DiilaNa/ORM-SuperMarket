package lk.ijse.gdse.supermarket.bo.Custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    String getNextCustomerId() throws SQLException;
    List<CustomerDTO> getAllCustomers() throws SQLException;
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;
    List<String> getAllCustomerIds() throws SQLException;
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;

}
