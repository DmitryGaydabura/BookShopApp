package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Setting the content type and character encoding of the response.
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Getting the response from the server.
        PrintWriter out = response.getWriter();

        // Getting the parameters from the form.
        String name = request.getParameter("name");
        String book = request.getParameter("book");
        String date = request.getParameter("date");
        String address = request.getParameter("address");


        CustomerOrder customerOrder = new CustomerOrder();

        // Setting the values of the Orders object.
        myOrder(name, book, date, address, customerOrder);

        boolean nameIsNull = false;
        boolean bookIsNull = false;
        boolean dateIsNull = false;
        boolean addressIsNull = false;

        // Checking if the name, email, and country are null.
        nameIsNull = isNull(name, nameIsNull);
        bookIsNull = isNull(book, bookIsNull);
        dateIsNull = isNull(date, dateIsNull);
        addressIsNull = isNull(address, addressIsNull);

        //out.println(customerOrder.toString());
        //out.println(OrdersRepository.getConnection());

        // Saving the Orders object to the database.
        int status = OrdersRepository.save(customerOrder);
        //out.println(status);

        if (status > 0) {
            // Printing out the status of the save to the database.
            out.println("Record saved successfully!");
            out.println("Name is null: " + nameIsNull + "(" + name + ")");
            out.println("Book is null: " + bookIsNull + "(" + book + ")");
            out.println("Date is null: " + dateIsNull + "(" + date + ")");
            out.println("Address is null: " + addressIsNull + "(" + address + ")");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }

    private boolean isNull(String name, boolean nameIsNull) {
        // Checking if the name is null.
        if (name == null) {
            nameIsNull = true;
        }
        return nameIsNull;
    }

    private void myOrder(String name, String book, String date, String address, CustomerOrder customerOrder) {
        // Setting the values of the Orders object.
        customerOrder.setName(name);
        customerOrder.setBook(book);
        customerOrder.setDate(date);
        customerOrder.setAddress(address);

    }
}
