package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "keoratana13$";

    @Override
    public List<Customer> queryAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, name, email, password, is_deleted, created_date FROM customer";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setIsDeleted(resultSet.getBoolean("is_deleted"));
                customer.setCreateDate(resultSet.getDate("created_date"));
                customers.add(customer);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return customers;
    }

    @Override
    public int deleteCustomerById(Integer id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomerById(Integer id) {
        return 0;
    }

    @Override
    public int updateCustomerById(Integer id, Customer customer) {  // Added a customer parameter
        String sql = """
                UPDATE customer
                SET name = ?, email = ?, password = ?, is_deleted = ?, created_date = ?
                WHERE id = ?
                """;
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setBoolean(4, customer.getIsDeleted());
            preparedStatement.setDate(5, (Date) customer.getCreateDate());
            preparedStatement.setInt(6, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int addNewCustomer(Customer customer) {
        String sql = """
                INSERT INTO customer (name, email, password, is_deleted, created_date)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setBoolean(4, customer.getIsDeleted());
            preparedStatement.setDate(5, (Date) customer.getCreateDate());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Insert success.");
                return rowAffected;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }
}
