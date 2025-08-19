import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class MainApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rooftop_delivery";
    private static final String USER = "root";

    public static void main(String[] args) {
        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.load();
        } catch (Exception e) {
            System.err.println("Error: .env file not found or could not be loaded.");
            return;
        }

        String dbPassword = dotenv.get("DB_PASS");

        if (dbPassword == null || dbPassword.isEmpty()) {
            System.err.println("Error: Database password not found.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, dbPassword)) {
            System.out.println("Connected to the database successfully!");
            DeliveryService service = new DeliveryService(conn);
            service.assignAllOrders();
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}