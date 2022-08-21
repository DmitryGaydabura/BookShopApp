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
        OrdersRepository.deliver(sid, out);
    }


}
