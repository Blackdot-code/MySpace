import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcExample {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "your_username";
        String password = "your_password";

        // Connection and statement variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1. Establishing the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");

            // 2. Preparing the SQL query
            String sql = "SELECT id, name, age FROM your_table_name WHERE age > ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 18); // Setting the age parameter

            // 3. Executing the query
            resultSet = preparedStatement.executeQuery();

            // 4. Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 5. Closing the resources
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
