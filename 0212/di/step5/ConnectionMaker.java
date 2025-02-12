package di.step5;

import java.sql.Connection;

public interface ConnectionMaker {
    public Connection makeNewConnection() throws Exception;
}
