package exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NoTransaction {

    public static void main(String[] args) {

        // 필드 초기화
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Driver 등록
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loading Success");

            // DBMS와 연결
            String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
            String ID = "C##yireh";
            String PW = "dlfp153";
            con = DriverManager.getConnection(JDBC_URL,ID,PW);
            System.out.println("Database Connection Success");

            // SQL 생성
            String keyword = "여행";
            String sql = "select bisbn, btitle, bauthor, bprice from book where btitle like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");

            // SQL 전송
            rs = pstmt.executeQuery();

            // SQL 결과 수신
            while(rs.next()) {
                String tmp = "";
                tmp += rs.getString("bisbn") + "  ";
                tmp += rs.getString("btitle") + "  ";
                tmp += rs.getString("bauthor") + "  ";
                tmp += rs.getInt("bprice") + "  ";
                System.out.println(tmp);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                // 연결 닫기
                rs.close();
                pstmt.close();
                if (con != null)
                    con.close();
                System.out.println("connection close()");
            } catch (Exception e2) {

            }
        }

    }
}

// MySQL Driver Loading 성공!
// 데이터베이스 연결 성공
// 978-89-98756-21-5  IT CookBook, C++ 하이킹 : 객체지향과 만나는 여행  성윤정, 김태은  25000
// 979-11-85933-01-6  게스트하우스 창업 A to Z : 청춘여행자의 낭만적 밥벌이  김아람  15000
// 979-11-85933-10-8  크로아티아의 작은 마을을 여행하다 : 자다르의 일몰부터 두브로브니크의 붉은 성벽까지  양미석  15800
// 979-11-951538-1-7  도쿄의 오래된 상점을 여행하다 : 소세키의 당고집부터 백 년 된 여관까지  여지영, 이진숙  15000
// connection close()