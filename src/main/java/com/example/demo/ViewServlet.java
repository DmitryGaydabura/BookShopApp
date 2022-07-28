package com.example.demo;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Setting the content type to html and getting the writer to write to the response.
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Employee> list = EmployeeRepository.getAllEmployees();

        // Printing all the employees in the list.
        printAllEmployees(out, list);

        // Checking if the list is empty and if it is, it throws an IOException.
        try {
            if (list.size() == 0) {
                throw new IOException();
            }
        }catch (IOException e ){
            out.println("There were no employees found.");
        }finally {
            out.close();
        }

    }

    private void printAllEmployees(PrintWriter out, List<Employee> list) {
        for (Employee employee : list) {
            out.println(employee);
        }
    }
}
