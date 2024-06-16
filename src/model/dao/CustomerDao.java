package model.dao;

import model.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int deleteCustomerById(Integer id);
    int updateCustomerById(Integer id);
    int addNewCustomer(Customer customer);
    Connection connectionToDataBase() throws SQLException;
    Customer searchCustomerById(Integer id);
}