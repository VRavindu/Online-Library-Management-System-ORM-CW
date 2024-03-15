package lk.ijse.repository;

import lk.ijse.entity.Orders;
import lk.ijse.entity.User;

import java.util.List;

public interface OrderRepository extends CrudRepository <Orders, Long>{
    List getOrderId();
    Long getBookCount(long id);

    List<Orders> getOrderByUserId(User user);
}
