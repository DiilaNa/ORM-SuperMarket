package lk.ijse.gdse.supermarket.bo.Custom.impl;

import lk.ijse.gdse.supermarket.bo.Custom.ItemBO;
import lk.ijse.gdse.supermarket.dao.Custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.DaoTypes;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.entity.Item;
import org.modelmapper.ModelMapper;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO =  DAOFactory.getInstance().getDao(DaoTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        Item item = new ModelMapper().map(itemDTO, Item.class);
        return itemDAO.save(item);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
       Item item = new ModelMapper().map(itemDTO, Item.class);
       return itemDAO.update(item);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
      return itemDAO.delete(id);
    }

    @Override
    public List<ItemDTO> getAllItemList() throws SQLException {
       List<Item>items = itemDAO.getALL();
       return items.stream().map(item -> new ModelMapper().map(item, ItemDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Optional<Item> item = itemDAO.findByPK(id);
        if (item.isPresent()) {
            return new ModelMapper().map(item.get(), ItemDTO.class);
        }else {
            return null;
        }
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
