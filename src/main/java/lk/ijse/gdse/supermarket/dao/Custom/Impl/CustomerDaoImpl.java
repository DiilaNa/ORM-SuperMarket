package lk.ijse.gdse.supermarket.dao.Custom.Impl;

import lk.ijse.gdse.supermarket.dao.Custom.CustomerDao;
import lk.ijse.gdse.supermarket.dao.Util;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public ArrayList<Customer> getALL() throws SQLException {
        ResultSet rst = Util.execute("select * from customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(
               rst.getString(1),
               rst.getString(2),
               rst.getString(3),
               rst.getString(4),
               rst.getString(5)

            ));
        }
        return customers;

    }

    @Override
    public boolean save(Customer entity) throws SQLException {
        return Util.execute(
                "insert into customer values (?,?,?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getNic(),
                entity.getEmail(),
                entity.getPhone()
        );
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return Util.execute(
                "update customer set name=?, nic=?, email=?, phone=? where customer_id=?",
                entity.getName(),
                entity.getNic(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getId()
        );
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return Util.execute("delete from customer where customer_id=?", Id);
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet rst = Util.execute("select customer_id from customer order by customer_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001";
    }

    @Override
    public Customer search(String id) throws SQLException {
        ResultSet rst = Util.execute("select * from customer where customer_id=?", id);

        if (rst.next()) {
            return new Customer(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getString(4),  // Email
                    rst.getString(5)   // Phone
            );
        }
        return null;

    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = Util.execute("select customer_id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }
}
