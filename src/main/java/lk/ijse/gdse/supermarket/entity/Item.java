package lk.ijse.gdse.supermarket.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")

public class Item {
    @Id
    @Column(name = "item_id")
    private String itemId;
    @Column(length = 100)
    private String name;

    private int quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @OneToMany(mappedBy = "item")
    private List<OrderDetails> orderDetails;
}

