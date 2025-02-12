package di.step4;

import di.step5.ConnectionMaker;
import di.step5.KConnectionMaker;

import java.sql.*;

public class UserDAO {

    private ConnectionMaker connectionMaker;

    public UserDAO(){
        connectionMaker = new KConnectionMaker();
    }

    public void insert(UserVO user){
        try{
            Connection con = connectionMaker.makeNewConnection();

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
            Connection con = connectionMaker.makeNewConnection();

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
