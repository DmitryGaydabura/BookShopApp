package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


@WebServlet("/viewByIDServlet")
public class ViewByIDServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(ViewByIDServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Setting the content type of the response to text/html and getting the writer object.
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Getting the id from the url and converting it to an integer.
            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);
            List<CustomerOrder> listOrders = new ArrayList<>();

            try {
                // Getting all the users from the database.
                Connection connection = OrdersRepository.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from bookshopcustomers");
                ResultSet rs = ps.executeQuery();

                // Counting the number of Orders in the database and checking if the ID is bigger than the number of
                // Orders.
                countOrders(listOrders, rs);
                isIDBiggerThenList(out, id, listOrders);

            } catch (SQLException e) {

                logger.info("Something terrible happened");

            } finally {
                // Getting the Orders by the ID and printing it out.
                CustomerOrder customerOrder = OrdersRepository.getOrderById(id);
                out.print(customerOrder);
                logger.info("Order "+ customerOrder + " was showed.");
                out.close();
            }
        } catch (IOException e) {
            out.println("ID not found.");
            logger.info("Order was not found");

        }
    }

    private void isIDBiggerThenList(PrintWriter out, int id, List<CustomerOrder> listOrders) {
        if (id > listOrders.size()) {
            out.println("There is no such ID in database");
            logger.info("Order was not found");
        }
    }

    private void countOrders(List<CustomerOrder> listOrders, ResultSet rs) throws SQLException {
        // Counting the number of Orders in the database.
        while (rs.next()) {
            CustomerOrder customerOrder = new CustomerOrder();
            listOrders.add(customerOrder);
        }
    }
}
