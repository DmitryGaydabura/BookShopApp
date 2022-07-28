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
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Employee employee = new Employee();

        // Setting the values of the employee object.
        myEmployee(name, email, country, employee);

        boolean nameIsNull = false;
        boolean emailIsNull = false;
        boolean countryIsNull = false;

        // Checking if the name, email, and country are null.
        nameIsNull = isNull(name, nameIsNull);
        emailIsNull = isNull(email, emailIsNull);
        countryIsNull = isNull(country, countryIsNull);

        //out.println(employee.toString());
        //out.println(EmployeeRepository.getConnection());

        // Saving the employee object to the database.
        int status = EmployeeRepository.save(employee);
        //out.println(status);

        if (status > 0) {
            // Printing out the status of the save to the database.
            out.println("Record saved successfully!");
            out.println("Name is null: " + nameIsNull + "(" + name + ")");
            out.println("Email is null: " + emailIsNull+ "(" + email + ")");
            out.println("Country is null: " + countryIsNull+ "(" + country + ")");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }

    private boolean isNull(String name, boolean nameIsNull) {
        // Checking if the name is null.
        if(name == null){
            nameIsNull = true;
        }
        return nameIsNull;
    }

    private void myEmployee(String name, String email, String country, Employee employee) {
        // Setting the values of the employee object.
        employee.setName(name);
        employee.setEmail(email);
        employee.setCountry(country);
    }
}
