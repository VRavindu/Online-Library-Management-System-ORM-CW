package lk.ijse.service.impl;

import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.embeded.OrderDetailPrimaryKey;
import lk.ijse.entity.Book;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.repository.*;
import lk.ijse.service.PlaceOrderService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private Session session;

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    OrderRepository orderRepository = (OrderRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
    OrderDetailRepository orderDetailRepository = (OrderDetailRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER_DETAIL);
    @Override
    public List<BookIdWithTitle> getIdsAndTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookIdWithTitle> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }

    @Override
    public boolean placeOrder(PlaceOrderDto placeOrderDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        userRepository.setSession(session);
        Orders orders = new Orders(placeOrderDto.getOrderId(), placeOrderDto.getDate(), userRepository.get(placeOrderDto.getUserId()), null, null);

        bookRepository.setSession(session);
        Book book = bookRepository.get(placeOrderDto.getBId());

        orderRepository.setSession(session);
        Long saveOrder = orderRepository.save(orders);

        orderDetailRepository.setSession(session);
        boolean savedOrderDetail = orderDetailRepository.saveOrderDetail(new OrderDetail(new OrderDetailPrimaryKey(placeOrderDto.getOrderId(), placeOrderDto.getBId()), orders, book));

        book.setQty(book.getQty()-1);
        bookRepository.update(book);

        if (saveOrder > 0){
            if (savedOrderDetail){
                transaction.commit();
                return true;
            }
        }else
            transaction.rollback();
            session.close();
            return false;
    }


}
