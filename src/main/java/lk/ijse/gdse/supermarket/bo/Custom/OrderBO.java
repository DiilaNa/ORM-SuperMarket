package lk.ijse.gdse.supermarket.bo.Custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    String generateNewOrderId() throws SQLException;
    boolean saveOrder(ArrayList<OrderDetailsDTO>orderDetailsDTOS, ArrayList<OrderDTO>orderDTOS) throws SQLException;
}
