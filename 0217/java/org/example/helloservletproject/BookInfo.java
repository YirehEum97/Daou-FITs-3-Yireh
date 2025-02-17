package org.example.helloservletproject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "book information", value="/bookinfo")
public class BookInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        String price = req.getParameter("price");
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        String author = req.getParameter("author");

        out.println("제목 : " + title + "<br>가격 : " + price + "<br>저자 : " + author + "<br>isbn : " + isbn);
    }
}
