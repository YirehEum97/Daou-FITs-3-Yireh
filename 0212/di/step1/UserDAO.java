package di.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO{
    public void insert(UserVO user){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String ID = "C##yireh";
            String PW = "dlfp153";
            Connection con = DriverManager.getConnection(url,ID,PW);

            String sql = "INSERT INTO USERS VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public UserVO select(String uid){
        UserVO user = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String ID = "C##yireh";
            String PW = "dlfp153";
            Connection con = DriverManager.getConnection(url,ID,PW);

            String sql = "SELECT * FROM USERS WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();
            rs.next();

            user = new UserVO((rs.getString("id")),
                    rs.getString("name"),
                    rs.getString("password"));

            ps.close();
            con.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
