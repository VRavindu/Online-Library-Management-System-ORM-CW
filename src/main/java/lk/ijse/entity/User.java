package lk.ijse.entity;

import lk.ijse.dto.UserDto;
import lk.ijse.embeded.NameIdentifier;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column
    private NameIdentifier name;
    @Column
    private String address;
    @Column
    private int age;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
    public User() {
    }

    public User(long id, NameIdentifier name, String address, int age, String email, String username, String password) {
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
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", age=" + age +
                ", email=" + email +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

    public UserDto toDto(){
        UserDto userDto = new UserDto();
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
