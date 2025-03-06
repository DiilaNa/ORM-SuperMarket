package lk.ijse.gdse.supermarket.bo.Custom.impl;

import lk.ijse.gdse.supermarket.bo.Custom.OrderBO;
import lk.ijse.gdse.supermarket.dao.Custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.Custom.OrderDAO;
import lk.ijse.gdse.supermarket.dao.Custom.OrderDetailDAO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.DaoTypes;
import lk.ijse.gdse.supermarket.db.DBConnection;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Order;
import lk.ijse.gdse.supermarket.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO =  DAOFactory.getInstance().getDao(DaoTypes.ORDER);
    OrderDetailDAO orderDetailDAO =  DAOFactory.getInstance().getDao(DaoTypes.ORDER_DETAILS);
    ItemDAO itemDAO =  DAOFactory.getInstance().getDao(DaoTypes.ITEM);
    @Override
    public String generateNewOrderId() throws SQLException {
        return  orderDAO.generateNewId();
    }

    @Override
    public boolean saveOrder(ArrayList<OrderDetailsDTO> orderDetailsDTOS, ArrayList<OrderDTO> orderDTOS) throws SQLException {
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);


            for (OrderDTO orderDTO : orderDTOS) {
                boolean b2 = orderDAO.save(new Order(
                        orderDTO.getOrderId(),
                        orderDTO.getCustomerId(),
                        orderDTO.getOrderDate()
                ));
                if (!b2){
                    connection.rollback();
                    return false;
                }

            }
            for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
                boolean b1= orderDetailDAO.save(new OrderDetails(
                        orderDetailsDTO.getOrderId(),
                        orderDetailsDTO.getItemId(),
                        orderDetailsDTO.getQuantity(),
                        orderDetailsDTO.getPrice()
                ));
                if (!b1){
                    connection.rollback();
                    return false;
                }
            }
            for (OrderDetailsDTO orderDetail : orderDetailsDTOS) {
                boolean isUpdated = itemDAO.reduceQty(orderDetail.getItemId(), orderDetail.getQuantity());
                if (!isUpdated) {
                    connection.rollback();
                    throw new SQLException("Failed to update quantity for Item ID: " + orderDetail.getItemId());
                }
            }
            connection.commit();
            return true;

        } catch (Exception e) {
           if (connection != null){
               connection.rollback();

           }
           e.printStackTrace();
           return false;
        }
    }
}
