package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(PutServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {


        // Setting the content type of the response to text/html and getting the writer object to write the response.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting the id from the request and converting it to an integer.
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        // Getting the name and email from the request.
        String name = request.getParameter("name");
        String book = request.getParameter("book");
        String date = request.getParameter("date");
        String address = request.getParameter("address");

        // Creating a new Orders object and setting the values of the Orders object.
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(id);
        customerOrder.setName(name);
        customerOrder.setBook(book);
        customerOrder.setDate(date);
        customerOrder.setAddress(address);


        // Calling the update method in the OrdersRepository class.
        int status = OrdersRepository.update(customerOrder);

        // This is a try-catch block. If the status is greater than 0, then the user is redirected to the viewServlet. If
        // the status is not greater than 0, then an IOException is thrown. If an IOException is thrown, then the user is
        // shown a message saying that the record could not be updated. Finally, the writer is closed.
        try {
            if (status > 0) {
                response.sendRedirect("viewServlet");
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            out.println("Sorry! unable to update record");
            logger.info("Unable to update the record.");
        } finally {
            out.close();
        }
    }
}
