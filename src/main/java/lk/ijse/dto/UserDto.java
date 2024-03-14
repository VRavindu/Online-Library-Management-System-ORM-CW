package lk.ijse.dto;

import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.User;

public class UserDto {
    private long id;
    private NameIdentifier name;
    private String address;
    private int age;
    private String email;
    private String username;
    private String password;

    public UserDto() {
    }

    public UserDto(long id, NameIdentifier name, String address, int age, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public NameIdentifier getName() {
        return name;
    }

    public void setName(NameIdentifier name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public java.lang.String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", age=" + age +
                ", email=" + email +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

    public User toEntity(){
        User userDto = new User();
        userDto.setId(this.id);
        userDto.setName(this.name);
        userDto.setAddress(this.address);
        userDto.setAge(this.age);
        userDto.setEmail(this.email);
        userDto.setUsername(this.username);
        userDto.setPassword(this.password);
        return userDto;
    }
}
