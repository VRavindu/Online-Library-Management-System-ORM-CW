package lk.ijse.service;

import lk.ijse.entity.Orders;

public interface OrderService extends SuperService {
    public int getOrderId();
    public boolean save(Orders orders);
    public Long getBookCount(long id);
}
