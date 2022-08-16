package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/viewServlet")
public class ViewServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ViewServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Setting the content type to html and getting the writer to write to the response.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<CustomerOrder> list = OrdersRepository.getAllOrders();

        // Printing all the Orders in the list.
        printAllOrders(out, list);
        logger.info("All orders were printed.");

        // Checking if the list is empty and if it is, it throws an IOException.
        try {
            if (list.size() == 0) {
                throw new IOException();
            }
        }catch (IOException e ){
            out.println("There were no orders found.");
            logger.info("Exception occurred .There were no orders found.");
        }finally {
            out.close();
        }

    }

    private void printAllOrders(PrintWriter out, List<CustomerOrder> list) {
        for (CustomerOrder order : list) {
            out.println(order);
        }
    }
}
