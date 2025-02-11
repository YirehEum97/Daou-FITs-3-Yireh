package booksearch.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import booksearch.vo.Book;

import java.sql.*;
import java.util.ArrayList;

public class Query {
    static Connection con = null;

    public static ObservableList<Book> query(String keyword) throws Exception{
//        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        String ID = "C##yireh";
        String PW = "dlfp153";
        con = DriverManager.getConnection(JDBC_URL,ID,PW);

        // SQL 생성
        String sql = "select bisbn, btitle, bauthor, bprice from book where btitle like ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + keyword + "%");

        // SQL 전송
        rs = pstmt.executeQuery();

        ObservableList<booksearch.vo.Book> books = FXCollections.observableArrayList();
        while (rs.next()){
            Book row = new Book(rs.getString("bisbn"),
                    rs.getString("btitle"),
                    rs.getString("bauthor"),
                    rs.getInt("bprice"));
            books.add(row);
        }

        rs.close();
//        con.close();

        return books;
    }

    public void close(Connection con) throws SQLException {
        con.close();
    }
}
