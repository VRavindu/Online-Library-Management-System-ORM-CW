package lk.ijse.repository;

import lk.ijse.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{

    void setSession(Session session);
    List<User> getAll();
    Long isExists(String username);
    User getCustomerUsingUsername(String username);

    String getUserPass(String username);

    String getAdminPass(String adminName);
}
