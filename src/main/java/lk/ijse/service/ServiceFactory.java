package lk.ijse.service;

import lk.ijse.service.impl.*;

public class ServiceFactory {
    static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return serviceFactory == null ? new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        LOGIN, USER, BOOK, PLACE_ORDER, ORDER, BRANCH
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER: return new UserServiceImpl();
            case BOOK: return new BookServiceImpl();
            case PLACE_ORDER: return new PlaceOrderServiceImpl();
            case ORDER: return new OrderServiceImpl();
            case BRANCH: return new BranchServiceImpl();
            default: return null;
        }
    }
}
