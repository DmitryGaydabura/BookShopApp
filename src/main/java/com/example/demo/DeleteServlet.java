package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(DeleteServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String sid = request.getParameter("id");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(sid);
        OrdersRepository.delete(id);

        out.println("Order with ID " + id + " was deleted.");
        logger.info("Order with ID " + id + " was deleted.");
//        response.sendRedirect("viewServlet");

    }
}
