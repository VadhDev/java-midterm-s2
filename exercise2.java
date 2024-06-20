import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/the_product";
        String user = "root";
        String password = "";


        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Successfully connected to the database.");


            Statement statement = connection.createStatement();


            String query = "SELECT name, price_per_unit, active_for_sell FROM Product";
            ResultSet resultSet = statement.executeQuery(query);


            System.out.println("+------------------+----------------+----------------+");
            System.out.printf("| %-16s | %-14s | %-14s |%n", "Name", "Price per Unit", "Active for Sell");
            System.out.println("+------------------+----------------+----------------+");


            while (resultSet.next()) {
                System.out.printf("| %-16s | $%-13.2f | %-14s |%n",
                        resultSet.getString("name"),
                        resultSet.getDouble("price_per_unit"),
                        resultSet.getBoolean("active_for_sell") ? "1" : "0");
                System.out.println("+------------------+----------------+----------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}