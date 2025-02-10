package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionTest {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loading Success");

            Connection con = null;
            String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
            String ID = "C##yireh";
            String PW = "dlfp153";
            con = DriverManager.getConnection(JDBC_URL,ID,PW);
            // AutoCommit 끄기
            // transaction 시작
            con.setAutoCommit(false);
            System.out.println("Database Connection Success");

            String sql = "INSERT INTO MEMBERTBL values(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"1");
            pstmt.setString(2,"홍길동");

            int result = pstmt.executeUpdate();

            if (result == 1){
                // result가 1인 경우 commit
                con.commit();
                System.out.println("insert Success");
            } else {
                // result가 1이 아닌 경우 오류가 있는 것이기 때문에 rollback
                con.rollback();
            }

            // 접속 종료
            pstmt.close();
            con.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}