package lk.ijse.gdse.supermarket.dao;

import lk.ijse.gdse.supermarket.dao.Custom.Impl.CustomerDaoImpl;
import lk.ijse.gdse.supermarket.dao.Custom.Impl.ItemDaoImpl;
import lk.ijse.gdse.supermarket.dao.Custom.Impl.OrderDaoImpl;
import lk.ijse.gdse.supermarket.dao.Custom.Impl.OrderDetailDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    @SuppressWarnings("unchecked")//erase warnings
    public <T extends SuperDao> T getDao(DaoTypes type) {
        return switch (type) {
            case CUSTOMER -> (T) new CustomerDaoImpl();
            case ITEM -> (T) new ItemDaoImpl();
            case ORDER -> (T) new OrderDaoImpl();
            case ORDER_DETAILS -> (T) new OrderDetailDAOImpl();
            default -> null;
        };
    }
}
