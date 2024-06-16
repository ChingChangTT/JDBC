package model.service;

import excaption.ExceptionHandling;
import mapper.CustomerMapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;



import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private final CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public List<CustomerDto> queryAllCustomers() {
        try {
            List<Customer> customers = customerDao.queryAllCustomers();
            if(!(customers.isEmpty())){
                return customerDao.queryAllCustomers().stream().map(CustomerMapper::mapCustomerToCustomerDto).toList();
            }else {
                throw new ExceptionHandling("No Data !");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewCustomer(Customer customer){
        try{
            if (customerDao.addNewCustomer(customer) > 0) {
                throw new ExceptionHandling("Customer Added Successfully !");
            }else{
                throw new ExceptionHandling("Cant add customer");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateCustomerById(Integer id){
        try {
            if (customerDao.updateCustomerById(id) > 0) {
                throw new ExceptionHandling("Customer Updated Successfully !");
            }else {
                throw new ExceptionHandling("Cant update customer");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomerById(Integer id) {
        try {
            if (customerDao.deleteCustomerById(id) > 0) {
                throw new ExceptionHandling("Customer Deleted Successfully !");
            }else {
                throw new ExceptionHandling("Cant delete customer");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }


}