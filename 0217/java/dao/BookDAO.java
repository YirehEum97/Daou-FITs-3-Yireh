package dao;

import vo.BookVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO {

    private ConnectionMaker connectionMaker;

    public BookDAO(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public ArrayList<BookVO> select(String text, String price){
        ArrayList<BookVO> books = new ArrayList<BookVO>();
        try {
            Connection con = connectionMaker.makeNewConnection();

            // SQL 생성
            String sql = "select bisbn, btitle, bauthor, bprice from book where btitle like ? AND bprice BETWEEN (?-9999) AND ?";
            System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + text + "%");
            pstmt.setString(2, price);
            pstmt.setString(3, price);

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
            con.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("select method called");
        return books;
    }

}
