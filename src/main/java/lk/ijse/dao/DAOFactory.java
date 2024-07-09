package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return(daoFactory == null)? daoFactory = new DAOFactory():daoFactory;
    }
    public enum DAOTypes {
        CUSTOMER,DELIVERY,EMPLOYEE,ITEM,MACHINE,PAYMENT,SALARY,STOCK,ORDER,ORDERITEMDETAIL
    }
        public SuperDAO getBO(DAOFactory.DAOTypes types){
            switch (types){
                case CUSTOMER:
                    return new CustomerDAOImpl();
                case DELIVERY:
                    return new DeliveryDAOImpl();
                case EMPLOYEE:
                    return new EmployeeDAOImpl();
                case ITEM:
                    return new ItemDAOImpl();
                case MACHINE:
                    return new MachineDAOImpl();
                case PAYMENT:
                    return new PaymentDAOImpl();
                case STOCK:
                    return new StockDAOImpl();
                case SALARY:
                    return new StockDAOImpl();
                case ORDER:
                    return new OrderDAOImpl();
                case ORDERITEMDETAIL:
                    return new OrderItemDetailDAOImpl();
                default:
                    return null;
            }
    }

}
