package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int deleteCustomerById(Integer id);
    int updateCustomerById(Integer id);

    int updateCustomerById(Integer id, Customer customer);

    int addNewCustomer(Customer customer);
}
