package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao {
    final String url = "jdbc:postgresql://localhost:5432/postgres";
    final String user = "postgres";
    final String password = "keoratana13$";

    @Override
    public Connection connectionToDataBase() throws SQLException {
        return DriverManager.getConnection(
                url,
                user,
                password
        );
    }

    @Override
    public Customer searchCustomerById(Integer id) {
        String sql = """
                SELECT * FROM "customer" WHERE id = ?
                """;
        try(
                Connection connection = connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
        ){
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            Customer customer = null;
            while (resultSet.next()) {
                customer = Customer
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .is_deleted(resultSet.getBoolean("is_deleted"))
                        .build();
            }
            return customer;
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
                SELECT * FROM "customer"
                """;
        try(
                Connection connection = connectionToDataBase();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
                ){
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("created_date"),
                        resultSet.getString("bio")
                ));
            }
            return customers;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int deleteCustomerById(Integer id) {
        String sql = """
                DELETE FROM "customer" WHERE id = ?
                """;
        try(
                Connection connection = connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql)
                ){
            pre.setInt(1, id);
            return pre.executeUpdate();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomerById(Integer id) {
        String sql = """
                UPDATE "customer" SET name = ?, email = ?, password = ?, bio = ? WHERE id = ?
                """;
        try(
                Connection connection = connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
                ){
            Customer customer = searchCustomerById(id);
            if (customer == null) {
                System.out.println("No customer");
            }else{
                System.out.print("New Name:");
                pre.setString(1,new Scanner(System.in).nextLine());
                System.out.print("New Email:");
                pre.setString(2,new Scanner(System.in).nextLine());
                System.out.print("New Password:");
                pre.setString(3,new Scanner(System.in).nextLine());
                System.out.print("New Bio:");
                pre.setString(4,new Scanner(System.in).nextLine());
                pre.setInt(5,id);
                return pre.executeUpdate();
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int addNewCustomer(Customer customer) {
        String sql = """
                INSERT INTO customer (name, email, password, is_deleted, created_date, bio)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql)
        ){
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getEmail());
            pre.setString(3, customer.getPassword());
            pre.setBoolean(4, customer.getIs_deleted());
            pre.setDate(5, customer.getCreated_date());
            pre.setString(6, customer.getBio());
            return pre.executeUpdate();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }
}