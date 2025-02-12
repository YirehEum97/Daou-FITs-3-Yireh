package di.step6;

import java.sql.Connection;

public interface ConnectionMaker {
    public Connection makeNewConnection() throws Exception;
}
