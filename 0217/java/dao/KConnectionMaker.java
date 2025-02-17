package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class KConnectionMaker implements ConnectionMaker {

    static public Connection con = null;

    @Override
    public Connection makeNewConnection() throws Exception{
        if (con != null && !con.isClosed()){
            return con;
        }
        else {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                String ID = "C##yireh";
                String PW = "dlfp153";
                con = DriverManager.getConnection(url, ID, PW);
                con.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("make new connection");
        return con;
    }
}
