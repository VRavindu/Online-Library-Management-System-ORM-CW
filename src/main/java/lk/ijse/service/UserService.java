package lk.ijse.service;

import lk.ijse.dto.UserDto;

import java.util.List;

public interface UserService extends SuperService{

    Long saveUser(UserDto user);

    UserDto getUser(long id);

    boolean updateUser(UserDto user);

    boolean deleteUser(UserDto user);

    List<UserDto> getAllUser();

    UserDto getUserUsingUsername(String username);

    String getUserPass(String username);

    String getAdminPass(String adminName);
}
