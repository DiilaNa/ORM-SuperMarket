package lk.ijse.gdse.supermarket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;
}
