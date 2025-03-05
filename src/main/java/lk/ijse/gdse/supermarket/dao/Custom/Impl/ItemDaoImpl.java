package lk.ijse.gdse.supermarket.dao.Custom.Impl;

import lk.ijse.gdse.supermarket.dao.Custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.Util;
import lk.ijse.gdse.supermarket.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getALL() throws SQLException {
       ResultSet rst = Util.execute("select * from item");
       ArrayList<Item> items = new ArrayList<>();
       while (rst.next()) {
           items.add(new Item(
                   rst.getString(1),
                   rst.getString(2),
                   rst.getInt(3),
                   rst.getDouble(4)
           ));
       }
       return items;
    }

    @Override
    public boolean save(Item entity) throws SQLException {
       return Util.execute("insert into item values(?,?,?,?)",
               entity.getItemId(),
               entity.getItemName(),
               entity.getQuantity(),
               entity.getPrice()
               );
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        return Util.execute("update item set name=?,quantity=?,price=? where item_id=?",
                entity.getItemName(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getItemId()
                );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return Util.execute("delete from item where item_id=?",id);
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
    public Item search(String id) throws SQLException {
        ResultSet rst = Util.execute("select * from item where item_id=?", id);
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
        }
        return null;

    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException {
        ResultSet rst = Util.execute("select item_id from item");
        ArrayList<String> itemIds = new ArrayList<>();

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
