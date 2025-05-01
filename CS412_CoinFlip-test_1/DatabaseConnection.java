
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URI = "jdbc:sqlite:sample.db";
    private static Connection connection;

    public DatabaseConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String createPlayersTable = "CREATE TABLE IF NOT EXISTS players ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username STRING NOT NULL UNIQUE,"
                    + "password STRING NOT NULL"
                    + ");";
            String createGameTable = "CREATE TABLE IF NOT EXISTS game ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "player_id INTEGER,"
                    + "amount INTEGER,"
                    + "FOREIGN KEY (player_id) REFERENCES players(id)"
                    + ");";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createPlayersTable);
            stmt.executeUpdate(createGameTable);

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
