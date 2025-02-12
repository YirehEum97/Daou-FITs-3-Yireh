package booksearchmvc.dao;

import booksearchmvc.vo.BookVO;
import di.step6.ConnectionMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

    private ConnectionMaker connectionMaker;

    public BookDAO(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public ObservableList<BookVO> select(String text){
        ObservableList<BookVO> books = FXCollections.observableArrayList();
        try {
            Connection con = connectionMaker.makeNewConnection();

            // SQL 생성
            String sql = "select bisbn, btitle, bauthor, bprice from book where btitle like ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + text + "%");

            // SQL 전송
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                BookVO row = new BookVO(rs.getString("bisbn"),
                        rs.getString("btitle"),
                        rs.getString("bauthor"),
                        rs.getInt("bprice"));
                books.add(row);
            }

            rs.close();
            pstmt.close();
//            con.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return books;
    }

    public int delete(BookVO bookVO){
        ObservableList<BookVO> books = FXCollections.observableArrayList();
        int result = 0;

        try {
            Connection con = connectionMaker.makeNewConnection();

            // SQL 생성
            String sql = "delete from book where bisbn = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bookVO.getBisbn());

            // SQL 전송
            result = pstmt.executeUpdate();

            pstmt.close();
//            con.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
