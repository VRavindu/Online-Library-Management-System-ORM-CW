package lk.ijse.service;

import lk.ijse.service.impl.BookServiceImpl;
import lk.ijse.service.impl.OrderServiceImpl;
import lk.ijse.service.impl.PlaceOrderServiceImpl;
import lk.ijse.service.impl.UserServiceImpl;

public class ServiceFactory {
    static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return serviceFactory == null ? new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        LOGIN, USER, BOOK, PLACE_ORDER, ORDER
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER: return new UserServiceImpl();
            case BOOK: return new BookServiceImpl();
            case PLACE_ORDER: return new PlaceOrderServiceImpl();
            case ORDER: return new OrderServiceImpl();
            default: return null;
        }
    }
}
