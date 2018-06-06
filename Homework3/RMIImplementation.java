//***************************************************************************************************************************************************

// TODO : imports

//***************************************************************************************************************************************************


//***************************************************************************************************************************************************

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RMIImplementation implements RMIInterface {
    //=================================================================================================================================================

    private final String driver;
    private final String url;
    private final String database;
    private final String username;
    private final String password;
    private Connection connection;
    private Statement statement;

    //=================================================================================================================================================

    public RMIImplementation() throws Exception {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/";
        this.database = "Ceng443";
        this.username = "root";
        this.password = "123";
        this.connection = null;
        this.statement = null;

        connect();
    }

    //=================================================================================================================================================

    public RMIImplementation(String driver, String url, String database, String username, String password) throws Exception {
        this.driver = driver;
        this.url = url;
        this.database = database;
        this.username = username;
        this.password = password;
        this.connection = null;
        this.statement = null;

        connect();
    }

    //=================================================================================================================================================

    private void connect() throws Exception {
        // TODO
        // ....
        try {
            connection = DriverManager.getConnection(url + database, username, password);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    //=================================================================================================================================================

    @Override
    protected void finalize() throws Throwable {
        // TODO
        // ....

        connection.close();
        statement.close();
    }

    //=================================================================================================================================================

    @Override
    public SerializableResult processSQL(String sql, boolean isQuery) throws RemoteException, SQLException {
        // TODO
        // ....

        if(isQuery){
            try {
                statement = connection.createStatement();
                SerializableResult serializableResult = new SerializableResult(statement.executeQuery(sql));
                return serializableResult;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            statement = connection.createStatement();
            SerializableResult serializableResult = new SerializableResult(statement.executeUpdate(sql));
            return serializableResult;

        }

        return null;
    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
