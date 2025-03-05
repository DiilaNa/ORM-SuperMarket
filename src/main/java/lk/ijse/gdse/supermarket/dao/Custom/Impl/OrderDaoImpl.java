package lk.ijse.gdse.supermarket.dao.Custom.Impl;

import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.Custom.OrderDAO;
import lk.ijse.gdse.supermarket.dao.Util;
import lk.ijse.gdse.supermarket.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<Order> getALL() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Order dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet rst = Util.execute("select order_id from orders order by order_id desc limit 1");

        if (rst.next()) {
            // @lastId: Retrieves the last order ID
            String lastId = rst.getString(1); // e.g., "O002"
            // @substring: Extracts the numeric part from the order ID
            String substring = lastId.substring(1); // e.g., "002"
            // @i: Converts the numeric part to an integer
            int i = Integer.parseInt(substring); // 2
            // @newIdIndex: Increments the numeric part by 1
            int newIdIndex = i + 1; // 3
            // Returns the new order ID, formatted as "O" followed by a 3-digit number (e.g., O003)
            return String.format("O%03d", newIdIndex);
        }
        // Returns "O001" if no previous orders are found
        return "O001";
    }

    @Override
    public Optional<Order> findByPK(String pk) throws SQLException {
        return Optional.empty();
    }

}
