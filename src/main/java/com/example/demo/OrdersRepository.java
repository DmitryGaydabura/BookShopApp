package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {

//    public static void main(String[] args) {
//        getConnection();
//
//        CustomerOrder customerOrder = new CustomerOrder();
//
//        customerOrder.setName("oleg");
//        customerOrder.setBook(" ");
//        customerOrder.setDate(" ");
//        customerOrder.setAddress(" ");
//        save(customerOrder);
//    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/bookshopCustomers";
        String user = "postgres";
        String password = "dimitrius1011";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(CustomerOrder customerOrder) {
        int status = 0;
        try {
            Connection connection = OrdersRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into bookshopcustomers(name,book,date,address) values (?,?,?,?)");
            ps.setString(1, customerOrder.getName());
            ps.setString(2, customerOrder.getBook());
            ps.setString(3, customerOrder.getDate());
            ps.setString(4, customerOrder.getAddress());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(CustomerOrder customerOrder) {

        int status = 0;

        try {
            Connection connection = OrdersRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update bookshopcustomers set name=?,book=?,date=?,address=? where id=?");
            ps.setString(1, customerOrder.getName());
            ps.setString(2, customerOrder.getBook());
            ps.setString(3, customerOrder.getDate());
            ps.setString(4, customerOrder.getAddress());
            ps.setInt(5, customerOrder.getId());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {

        int status = 0;

        try {
            Connection connection = OrdersRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from bookshopcustomers where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static CustomerOrder getOrderById(int id) {

        CustomerOrder customerOrder = new CustomerOrder();

        try {
            Connection connection = OrdersRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from bookshopcustomers where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customerOrder.setId(rs.getInt(1));
                customerOrder.setName(rs.getString(2));
                customerOrder.setBook(rs.getString(3));
                customerOrder.setDate(rs.getString(4));
                customerOrder.setAddress(rs.getString(5));
            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return customerOrder;
    }

    public static List<CustomerOrder> getAllOrders() {

        List<CustomerOrder> listOrders = new ArrayList<>();

        try {
            Connection connection = OrdersRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from bookshopcustomers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CustomerOrder customerOrder = new CustomerOrder();

                customerOrder.setId(rs.getInt(1));
                customerOrder.setName(rs.getString(2));
                customerOrder.setBook(rs.getString(3));
                customerOrder.setDate(rs.getString(4));
                customerOrder.setAddress(rs.getString(5));

                listOrders.add(customerOrder);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOrders;
    }
}
