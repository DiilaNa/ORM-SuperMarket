package lk.ijse.gdse.supermarket.bo.Custom.impl;

import lk.ijse.gdse.supermarket.bo.Custom.ItemBO;
import lk.ijse.gdse.supermarket.dao.Custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.DaoTypes;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.entity.Item;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO =  DAOFactory.getInstance().getDao(DaoTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.save(new Item(
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getQuantity(),
                itemDTO.getPrice()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.update(new Item(
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getQuantity(),
                itemDTO.getPrice()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public List<ItemDTO> getAllItemList() throws SQLException {
        List<ItemDTO> list = new ArrayList<>();
        List<Item>items = itemDAO.getALL();
        for (Item item : items) {
            list.add(new ItemDTO(item.getItemId(),item.getItemName(),item.getQuantity(),item.getPrice()));
        }
        return list;
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {

        Item item= itemDAO.search(id);
        return(item!=null)?
                new ItemDTO(
               item.getItemId(),
               item.getItemName(),
               item.getQuantity(),
               item.getPrice()):null;
    }

    @Override
    public List<String> getAllItemIds() throws SQLException {
        return itemDAO.getAllItemIds();
    }

    @Override
    public String getNextItemId() throws SQLException {
        return itemDAO.generateNewId();
    }
}
