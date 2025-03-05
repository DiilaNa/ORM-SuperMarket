package lk.ijse.gdse.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")

public class OrderDetails {
    @Id
    @Column(name = "order_id")
    private String id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;
}