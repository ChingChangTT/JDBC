package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    int addCustomer(Customer customer);
    int updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
    Customer getCustomerById(int customerId);
    List<Customer> getAllCustomers();
    void clearAllCustomers();
}
