package exercise;

import java.sql.*;
import java.util.Scanner;

public class TransactionTest {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = null;

            String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
            String ID = "C##yireh";
            String PW = "dlfp153";
            con = DriverManager.getConnection(JDBC_URL, ID, PW);
            System.out.print("Enter the keyword :");

            Scanner s = new Scanner(System.in);
            String keyword = s.nextLine();


            String sql = "SELECT btitle, bauthor, bprice ";
            sql += "FROM book WHERE btitle LIKE ?";
//            System.out.println(sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("btitle"));
            }

            rs.close();
            pstmt.close();
            con.close();
            System.out.println("DB Closed");

        } catch (Exception e){
            System.out.println("Error");
            System.out.println(e);
        }
    }
}