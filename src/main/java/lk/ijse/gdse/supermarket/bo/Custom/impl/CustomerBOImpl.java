package lk.ijse.gdse.supermarket.bo.Custom.impl;

import lk.ijse.gdse.supermarket.bo.Custom.CustomerBO;
import lk.ijse.gdse.supermarket.dao.Custom.CustomerDao;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.DaoTypes;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerBOImpl implements CustomerBO {
    CustomerDao customerDao =  DAOFactory.getInstance().getDao(DaoTypes.CUSTOMER);

    @Override
    public String getNextCustomerId() throws SQLException {
        return customerDao.generateNewId();
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {
        List<Customer> customers = customerDao.getALL();
        return customers.stream()
                .map(customer -> new ModelMapper().map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = new ModelMapper().map(customerDTO, Customer.class);
        return customerDao.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
       Customer customer = new ModelMapper().map(customerDTO, Customer.class);
       return customerDao.update(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
       return customerDao.delete(id);
    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException {
        return customerDao.getAllCustomerIds();
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Optional<Customer> customer = customerDao.findByPK(id);
        if (customer.isPresent()) {
               CustomerDTO customerDTO = new ModelMapper().map(customer, CustomerDTO.class);
                return customerDTO;
    }else {
        return null;}
    }
}
