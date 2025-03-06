package lk.ijse.gdse.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")

public class OrderDetails implements SuperEntity {

    @Id
    private OrderDetailsId orderId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item itemId;

    private int quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal price;

}