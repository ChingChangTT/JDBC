import model.dao.CustomerDaoImpl;
import model.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        new CustomerDaoImpl().addNewCustomer(new Customer(
                1,
                "Koko",
                "koko123@gmail.com",
                "1@#$#$%$^^#%$#@$",
                false,
                Date.valueOf(LocalDate.now())
        ));
    }
}