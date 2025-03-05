package lk.ijse.gdse.supermarket.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailsId {
//    @Column(name = "order_id")
    private String orderId;

//    @Column(name = "item_id")
    private String itemId;
}
