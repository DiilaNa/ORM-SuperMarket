package lk.ijse.gdse.supermarket.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;
}
