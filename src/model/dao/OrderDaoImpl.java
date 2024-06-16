package model.dao;

import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao{
    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (order_name, order_description, cus_id, ordered_at)
                VALUES (?, ?, ?, ?)
                """;
        String sql1 = """
                INSERT INTO "product_order" (pro_id, order_id)
                VALUES (?,?)
                """;
        try(
                Connection connection = customerDao.connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
                PreparedStatement pre1 = connection.prepareStatement(sql1)
        ){
            pre.setString(1, order.getOrderName());
            pre.setString(2, order.getOrderDescription());
            pre.setInt(3, order.getCustomer().getId());
            pre.setDate(4,order.getOrderAt());
            for(Product product : order.getProductList()){
                pre1.setInt(1, product.getId());
                pre1.setInt(2, order.getId());
            }
            int rowAffected = pre.executeUpdate();
            pre1.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateOrder(Integer id) {
        String sql = """
                UPDATE "order" SET order_name = ?, order_description = ? WHERE id = ?
                """;
        try(
                Connection connection = customerDao.connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
        ){
            Order order = searchOrderById(id);
            if (order == null) {
                System.out.println("No customer");
            }else{
                System.out.print("New Order Name:");
                pre.setString(1,new Scanner(System.in).nextLine());
                System.out.print("New Order Description:");
                pre.setString(2,new Scanner(System.in).nextLine());
                pre.setInt(3,id);
                return pre.executeUpdate();
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        String sql = """
                DELETE FROM "order" WHERE id = ?
                """;
        try(
                Connection connection = customerDao.connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql)
        ){
            Order order = searchOrderById(id);
            String msg = order == null ? "Cannot find oder" : "Found Order";
            System.out.println(msg);
            if(order != null) {
                pre.setInt(1,id);
                return pre.executeUpdate();
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = """
                SELECT * FROM "order"
                INNER JOIN public.customer c on c.id = "order".cus_id
                """;
        try (
                Connection connection = customerDao.connectionToDataBase();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(Order
                        .builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .orderAt(resultSet.getDate("ordered_at"))
                        .customer(Customer
                                .builder()
                                .id(resultSet.getInt("cus_id"))
                                .name(resultSet.getString("name"))
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("password"))
                                .is_deleted(resultSet.getBoolean("is_deleted"))
                                .created_date(resultSet.getDate("created_date"))
                                .bio(resultSet.getString("bio"))
                                .build())
                        .build()
                );
            }
            return orders;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Order searchOrderById(Integer id) {
        String sql = """
                SELECT * FROM "order" WHERE id = ?
                """;
        try(
                Connection connection = customerDao.connectionToDataBase();
                PreparedStatement pre = connection.prepareStatement(sql);
        ){
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                order = Order
                        .builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .customer(Customer.builder()
                                .id(resultSet.getInt("cus_id"))
                                .build())
                        .productList(new ArrayList<>())
                        .orderAt(resultSet.getDate("ordered_at"))
                        .build();
            }
            return order;
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

}