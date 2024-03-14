package lk.ijse.repository.impl;

import lk.ijse.entity.User;
import lk.ijse.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;
    @Override
    public Long save(User user) {
        return (Long) session.save(user);
    }

    @Override
    public void update(User user) {
        session.update(user);
    }

    @Override
    public User get(Long id) {
        return session.get(User.class, id);
    }

    @Override
    public void delete(User user) {
        session.delete(user);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAll() {
        String sqlQuery = "FROM User";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long isExists(String username) {
        String hql = "SELECT COUNT(*) FROM User WHERE username = :username";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("username", username);

        return query.uniqueResult();
    }

    @Override
    public User getCustomerUsingUsername(String username) {
        String hql = "FROM User WHERE username = :username";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public String getUserPass(String username) {
        String hql = "SELECT u.password FROM User u WHERE u.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        String password = (String) query.uniqueResult();
        return password;
    }

    @Override
    public String getAdminPass(String adminName) {
        String hql = "SELECT u.password FROM Admin u WHERE u.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", adminName);
        String password = (String) query.uniqueResult();
        return password;
    }
}
