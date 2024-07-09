package lk.ijse.bo;


import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,DELIVERY,EMPLOYEE,ITEM,MACHINE,PAYMENT,STOCK,SALARY,SUPPLIER,USER,VEHICAL,PLACEORDER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case MACHINE:
                return new MachineBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            case VEHICAL:
                return new VehicalBOImpl();
            case PLACEORDER:
                return new PlacedOrderBOImpl();
            default:
                return null;
        }
    }
}
