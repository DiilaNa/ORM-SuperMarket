package lk.ijse.gdse.supermarket.bo.Custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO) throws SQLException;
    boolean updateItem(ItemDTO itemDTO) throws SQLException;
    boolean deleteItem(String Id) throws SQLException;
    ArrayList<ItemDTO> getAllItemList() throws SQLException;
    ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemIds() throws SQLException;
    String getNextItemId() throws SQLException;
}
