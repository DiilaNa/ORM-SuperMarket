package lk.ijse.gdse.supermarket.entity;


import lombok.*;

import java.sql.Date;


// Lombok annotations to generate methods automatically and reduce boilerplate code
@Getter                 // @Getter: Automatically generates getter methods for all fields
@Setter                 // @Setter: Automatically generates setter methods for all fields
@AllArgsConstructor     // @AllArgsConstructor: Generates a constructor with all fields as parameters
@NoArgsConstructor      // @NoArgsConstructor: Generates a no-argument constructor
@ToString               // @ToString: Automatically generates a string representation of the object


public class Order {

    private String orderId;
    private String customerId;
    private Date orderDate;

    // @orderDetailsDTOS: A list of OrderDetailsDTO objects, each representing an item in the order
}
