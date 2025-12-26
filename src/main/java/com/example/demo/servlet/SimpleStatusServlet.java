package com.example.demo.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/simple-status")

public class SimpleStatusServlet extends HttpServlet {
    
   
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        writer.write("Supplier Diversity Tracker is running");
        writer.flush();
    }
}