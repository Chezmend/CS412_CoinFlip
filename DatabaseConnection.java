
import org.sqlite.SQLiteDataSource;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URI = "jdbc:sqlite:sample.db";
    private static Connection connection;

    public DatabaseConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS students ("
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

    public void create(int age, String name) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        conn.createStatement().executeUpdate(String.format("INSERT INTO students (name, age) VALUES ('%s',%d);", name, age));
        conn.close();
    }

    public void delete(int id) throws SQLException {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.createStatement().executeUpdate(String.format("DELETE FROM students WHERE id = %d;", id));
            conn.close();
    }

    public void read() throws SQLException {
        
    }

    public void update(String name, int age, int id) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        conn.createStatement().executeUpdate(String.format("UPDATE students SET name = '%s', age = %d WHERE id = %d;", name, age, id));
        conn.close();
    }
}
