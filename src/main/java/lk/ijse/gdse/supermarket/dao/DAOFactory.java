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
    public enum DAOType {
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS
    }
    public SuperDao getDao(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDaoImpl();
                    case ITEM:
                        return new ItemDaoImpl();
                        case ORDER:
                            return new OrderDaoImpl();
                            case ORDER_DETAILS:
                                return new OrderDetailDAOImpl();
                default:
                    return null;
        }
    }
}
