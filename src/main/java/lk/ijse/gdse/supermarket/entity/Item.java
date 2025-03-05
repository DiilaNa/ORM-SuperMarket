package lk.ijse.gdse.supermarket.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Item {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;
}

