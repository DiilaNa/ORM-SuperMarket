package lk.ijse.gdse.supermarket.dao.Custom.Impl;

import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.Custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.Util;
import lk.ijse.gdse.supermarket.entity.Customer;
import lk.ijse.gdse.supermarket.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<Item> getALL() throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<Item> query = session.createQuery("from Item", Item.class);
        List<Item> list = query.list();
        return list;
    }

    @Override
    public boolean save(Item entity) throws SQLException {
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
    public boolean update(Item entity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
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
    public boolean delete(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Item item = session.get(Item.class, id);
            if (item == null) {
                return false;
            }
            session.remove(item);
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
    public boolean exist(String id) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet rst = Util.execute("select item_id from item order by item_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("I%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "I001";
    }

    @Override
    public Optional<Item> findByPK(String pk) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Item item = session.get(Item.class, pk);
        session.close();
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);
    }



    @Override
    public List<String> getAllItemIds() throws SQLException {
        ResultSet rst = Util.execute("select item_id from item");
        List<String> itemIds = new ArrayList<>();

        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }
        return itemIds;
    }

    @Override
    public boolean reduceQty( String itemId, int qty) throws SQLException {
        return Util.execute("update item set quantity = quantity - ? where item_id = ?",
               qty,itemId
        );
    }
}
