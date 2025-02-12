package di.step4;

import java.sql.Connection;
import java.sql.DriverManager;

public class SimpleConnectionMaker {

    public Connection makeNewConnection() throws Exception{
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String ID = "C##yireh";
            String PW = "dlfp153";
            con = DriverManager.getConnection(url, ID, PW);
        } catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
