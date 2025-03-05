package lk.ijse.gdse.supermarket.bo.Custom.impl;

import lk.ijse.gdse.supermarket.bo.Custom.CustomerBO;
import lk.ijse.gdse.supermarket.dao.Custom.CustomerDao;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDao(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getNextCustomerId() throws SQLException {
        return customerDao.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDao.getALL();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(
               customer.getId(),
               customer.getName(),
               customer.getNic(),
               customer.getEmail(),
               customer.getPhone()
            ));
        }
        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
       return customerDao.save(new Customer(
          customerDTO.getId(),
          customerDTO.getName(),
          customerDTO.getNic(),
          customerDTO.getEmail(),
          customerDTO.getPhone()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDao.update(new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
       return customerDao.delete(id);
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        return customerDao.getAllCustomerIds();
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.search(id);
        return (customer != null) ?
                new CustomerDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getNic(),
                        customer.getEmail(),
                        customer.getPhone()) : null;
    }
}
