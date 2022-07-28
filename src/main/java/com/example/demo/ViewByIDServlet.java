package com.example.demo;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Setting the content type of the response to text/html and getting the writer object.
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Getting the id from the url and converting it to an integer.
            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);
            List<Employee> listEmployees = new ArrayList<>();

            try {
                // Getting all the users from the database.
                Connection connection = EmployeeRepository.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from users");
                ResultSet rs = ps.executeQuery();

                // Counting the number of employees in the database and checking if the ID is bigger than the number of
                // employees.
                countEmployees(listEmployees, rs);
                isIDBiggerThenList(out, id, listEmployees);

            } catch (SQLException e) {

                out.println("Something terrible happened");

            } finally {
                // Getting the employee by the ID and printing it out.
                Employee employee = EmployeeRepository.getEmployeeById(id);
                out.print(employee);
                out.close();
            }
        } catch (IOException e) {
            out.println("ID not found.");
        }
    }

    private void isIDBiggerThenList(PrintWriter out, int id, List<Employee> listEmployees) {
        if (id > listEmployees.size()) {
            out.println("There is no such ID in database");
        }
    }

    private void countEmployees(List<Employee> listEmployees, ResultSet rs) throws SQLException {
        // Counting the number of employees in the database.
        while (rs.next()) {
            Employee employee = new Employee();
            listEmployees.add(employee);
        }
    }
}
