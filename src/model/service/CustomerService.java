package model.service;

import model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(CustomerDto customerDto);
    boolean updateCustomer(CustomerDto customerDto);
    boolean deleteCustomer(int customerId);
    CustomerDto getCustomerById(int customerId);
    List<CustomerDto> getAllCustomers();
    void clearAllCustomers();
}
