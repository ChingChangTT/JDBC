package model.service;

import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImpl() {
        this.customerDao = new CustomerDaoImpl();
    }

    @Override
    public boolean addCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        return customerDao.addCustomer(customer) > 0;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        return customerDao.updateCustomer(customer) > 0;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        customerDao.deleteCustomer(customerId);
        return true;
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {
        Customer customer = customerDao.getCustomerById(customerId);
        return convertToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerDao.getAllCustomers();
        return customers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void clearAllCustomers() {
        customerDao.clearAllCustomers();
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .isDeleted(customerDto.getIsDeleted())
                .CreateDate(customerDto.getCreateDate())
                .build();
    }

    private CustomerDto convertToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .isDeleted(customer.getIsDeleted())
                .createDate(customer.getCreateDate())
                .build();
    }
}
