package lk.ijse.service;

import lk.ijse.dto.OrderDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Orders;

import java.util.List;

public interface OrderService extends SuperService {
    int getOrderId();
    boolean save(Orders orders);
    Long getBookCount(long id);

    List<OrderDto> getOrderByUserId(UserDto userDto);
}
