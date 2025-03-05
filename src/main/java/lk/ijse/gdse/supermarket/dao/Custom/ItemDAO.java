package lk.ijse.gdse.supermarket.dao.Custom;

import lk.ijse.gdse.supermarket.dao.CrudDAO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    List<String> getAllItemIds() throws SQLException;
    boolean reduceQty(String itemId,int qty) throws SQLException;
}
