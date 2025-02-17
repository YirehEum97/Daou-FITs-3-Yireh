package org.example.helloservletproject;

import dao.BookDAO;
import dao.ConnectionMaker;
import dao.KConnectionMaker;
import vo.BookVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/myServlet")
public class MyFirstServlet extends HttpServlet {

    public static ConnectionMaker connectionMaker = new KConnectionMaker();

    public MyFirstServlet() {
        System.out.println("constructor called");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init called");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        String price = req.getParameter("price");
        String title = req.getParameter("title");
//        String isbn = req.getParameter("isbn");


        BookDAO bookDAO = new BookDAO(connectionMaker);
        ArrayList<BookVO> books;
        books = bookDAO.select(title, price);


        out.println("<html>");
        out.println("<head></head><h1>검색 결과입니다.</h1>");
        out.println("<h3>검색 키워드 : " + title + "</h3>");
        out.println("<h3>검색 가격 : " + price + "</h3><br>");
        for (BookVO book:books){
            String link = "/HelloServletProject/bookinfo";
            link += query(book);
            out.println("<A href = \"" + link + "\">" + book.getBtitle() + " " + book.getBprice()+"<br></A>");
//            out.println(book.getBtitle() + " " + book.getBprice()+"<br>");
        }
        out.println("</html>");
        out.flush();
        out.close();

        System.out.println("Post called");
    }

    private String query(BookVO book){
        String link = "?title=";
        link += book.getBtitle();
        link += "&author=";
        link += book.getBauthor();
        link += "&price=";
        link += book.getBprice();
        link += "&isbn=";
        link += book.getBisbn();

        return link;
    }

//    private String hyperLink(String link, String text){
//        String newLink = "<A href = \"" + link + "\">" + text + "</A>";
//    }
}