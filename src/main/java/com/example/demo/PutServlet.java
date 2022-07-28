package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Setting the content type of the response to text/html and getting the writer object to write the response.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting the id from the request and converting it to an integer.
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        // Getting the name and email from the request.
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Creating a new Employee object and setting the values of the employee object.
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setCountry(request.getParameter("country"));

        // Calling the update method in the EmployeeRepository class.
        int status = EmployeeRepository.update(employee);

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
        } finally {
            out.close();
        }
    }
}
