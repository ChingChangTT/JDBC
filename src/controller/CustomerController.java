package controller;

import model.dto.CustomerDto;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerServiceImpl();
    }

    public boolean addCustomer(CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    public boolean updateCustomer(CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    public boolean deleteCustomer(int customerId) {
        return customerService.deleteCustomer(customerId);
    }

    public CustomerDto getCustomerById(int customerId) {
        return customerService.getCustomerById(customerId);
    }

    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void clearAllCustomers() {
        customerService.clearAllCustomers();
    }
}
