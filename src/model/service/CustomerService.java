package model.service;

import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> queryAllCustomers();

    void addNewCustomer(Customer customer);

    void updateCustomerById(Integer id);

    void deleteCustomerById(Integer id);
}