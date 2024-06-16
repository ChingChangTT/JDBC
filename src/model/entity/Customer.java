package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean is_deleted;
    private Date created_date;
    private String bio;
}