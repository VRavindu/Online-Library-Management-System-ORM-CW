package lk.ijse.service;

import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.projection.BookIdWithTitle;
import java.util.List;

public interface PlaceOrderService extends SuperService{
    public List<BookIdWithTitle> getIdsAndTitles();
    public boolean placeOrder(PlaceOrderDto placeOrderDto);
}
