package lk.ijse.repository.impl;

import lk.ijse.entity.Orders;
import lk.ijse.entity.User;
import lk.ijse.repository.OrderRepository;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private Session session;

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public Long save(Orders orders) {
        return (Long) session.save(orders);
    }

    @Override
    public void update(Orders object) {

    }

    @Override
    public Orders get(Long aLong) {
        return null;
    }

    @Override
    public void delete(Orders object) {

    }

    @Override
    public List getOrderId() {
        String hql = "SELECT orderId FROM Orders WHERE id = (SELECT MAX(id) FROM Orders)";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        return query.list();

    }

    @Override
    public Long getBookCount(long id) {
        String hql = "SELECT COUNT(*) FROM Orders WHERE user = :value";
        org.hibernate.query.Query query = session.createQuery(hql);
        query.setParameter("value", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Orders> getOrderByUserId(User user) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> criteria = builder.createQuery(Orders.class);
        Root<Orders> root = criteria.from(Orders.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("user"), user));

        List<Orders> resultList = session.createQuery(criteria).getResultList();
        return resultList;
    }
}
