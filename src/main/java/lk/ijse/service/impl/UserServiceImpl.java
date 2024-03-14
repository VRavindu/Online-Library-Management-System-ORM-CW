package lk.ijse.service.impl;

import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.UserRepository;
import lk.ijse.service.UserService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Session session;

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);
    @Override
    public Long saveUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            Long id = userRepository.save(userDto.toEntity());
            transaction.commit();
            session.close();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1L;
        }
    }
    @Override
    public UserDto getUser(long id) {
        try {
            session = SessionFactoryConfig.getInstance()
                    .getSession();
            userRepository.setSession(session);
            User user = userRepository.get(id);
            session.close();
            return user.toDto();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    @Override
    public boolean updateUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.update(userDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userRepository.setSession(session);
            userRepository.delete(userDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            return false;
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        List<User> allUser = userRepository.getAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : allUser){
            dtoList.add(user.toDto());
        }
        return dtoList;
    }

    @Override
    public UserDto getUserUsingUsername(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        User user = userRepository.getCustomerUsingUsername(username);
        session.close();
        return user.toDto();
    }

    @Override
    public String getUserPass(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        String password = userRepository.getUserPass(username);
        session.close();
        return password;
    }

    @Override
    public String getAdminPass(String adminName) {
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        String password = userRepository.getAdminPass(adminName);
        session.close();
        return password;
    }


}
