package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet("/deliverServlet")
public class DeliverServlet  extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String sid = request.getParameter("id");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(sid);
        CustomerOrder customerOrder = OrdersRepository.getOrderById(id);
        if(!customerOrder.isDelivered()){
            customerOrder.setDelivered(true);
            out.println("Order with ID " + id + " was delivered.");
            log.info("Order with ID " + id + " was delivered.");
            OrdersRepository.update(customerOrder);
        }else{
            out.println("Order with ID " + id + " was already delivered.");
            log.info("Order with ID " + id + " was already delivered.");
        }
    }
}
