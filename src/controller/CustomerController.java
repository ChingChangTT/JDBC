package controller;

import excaption.ExceptionHandling;
import model.dto.CustomerDto;
import model.entity.Customer;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService = new CustomerServiceImpl();

    public List<CustomerDto> queryAllCustomers() throws ExceptionHandling {
        return customerService.queryAllCustomers();
    }

    public void addNewCustomer(){
        System.out.print("Customer Name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.print("Customer Email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.print("Customer Password: ");
        String password = new Scanner(System.in).nextLine();
        System.out.println("Customer Bio: ");
        String bio = new Scanner(System.in).nextLine();
        new CustomerServiceImpl().addNewCustomer(Customer.builder()
                            .id(1)
                            .name(name)
                            .email(email)
                            .password(password)
                            .bio(bio)
                            .is_deleted(false)
                            .created_date(Date.valueOf(LocalDate.now()))
                .build());
    }

    public void updateCustomer(){
        System.out.println("Customer ID: ");
        new CustomerServiceImpl().updateCustomerById(new Scanner(System.in).nextInt());
    }

    public void deleteCustomer(){
        System.out.println("Customer ID: ");
        new CustomerServiceImpl().deleteCustomerById(new Scanner(System.in).nextInt());
    }

}
