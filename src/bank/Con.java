package bank;

import java.sql.*;

public class Con {
    public Connection connection;
    public Statement statement;

    public Con() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank", // Ensure 'bank' DB exists
                "root", // MySQL username
                "Rohit@7307395201" //  Replace with actual password
            );
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace(); // Very important for debugging!
        }
    }
}
