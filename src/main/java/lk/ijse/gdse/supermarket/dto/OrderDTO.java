package lk.ijse.gdse.supermarket.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

// Lombok annotations to generate methods automatically and reduce boilerplate code
@Getter                 // @Getter: Automatically generates getter methods for all fields
@Setter                 // @Setter: Automatically generates setter methods for all fields
@AllArgsConstructor     // @AllArgsConstructor: Generates a constructor with all fields as parameters
@NoArgsConstructor      // @NoArgsConstructor: Generates a no-argument constructor
@ToString               // @ToString: Automatically generates a string representation of the object


public class OrderDTO {

    private String orderId;
    private String customerId;
    private Date orderDate;
}
