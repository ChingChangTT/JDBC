package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Integer id;
    private String order_name;
    private  String orderDescription;
    private Date orderdAt;
    private Customer customer;

    public void setCustomer() {

    }
}
