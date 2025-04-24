
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URI = "jdbc:sqlite:sample.db";
    private static Connection connection;

    public DatabaseConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS players ("
                    + "id INTEGER PRIMARY KEY,"
                    + "name STRING,"
                    + "age INTEGER);";
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URI);
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

}
