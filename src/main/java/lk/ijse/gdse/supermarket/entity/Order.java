package lk.ijse.gdse.supermarket.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


// @Setter: Automatically generates setter methods for all fields
@AllArgsConstructor     // @AllArgsConstructor: Generates a constructor with all fields as parameters
@NoArgsConstructor      // @NoArgsConstructor: Generates a no-argument constructor
@Data
@Entity
@Table(name = "orders")

public class Order implements SuperEntity {
    @Id
    @Column(name = "order_id")
    private String orderId;

    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customerId;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;
}
