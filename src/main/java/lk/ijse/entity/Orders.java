package lk.ijse.entity;

import lk.ijse.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "order_date_time")
    @CreationTimestamp
    private LocalDateTime dateTime;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column
    private LocalDate returnedDate;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Orders(long orderId, LocalDate dueDate, User user, LocalDate returnedDate, String status){
        this.orderId=orderId;
        this.dueDate=dueDate;
        this.user=user;
        this.returnedDate=returnedDate;
        this.status=status;

    }

    public Orders(LocalDateTime dateTime, LocalDate dueDate){
        this.dateTime=dateTime;
        this.dueDate=dueDate;
    }

    public Orders(LocalDateTime dateTime, LocalDate dueDate, User user){
        this.dateTime=dateTime;
        this.dueDate=dueDate;
        this.user=user;
    }

    public void toDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderId);
        orderDto.setDueDate(dueDate);
        orderDto.setUser(user);
    }
}
