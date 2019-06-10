import java.sql.*;
import java.util.Date;
import java.util.Properties;

public class runner {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/car_rental?serverTimezone=Europe/Warsaw";
        Properties conectionProperties = new Properties();
        conectionProperties.setProperty("user", "root");
        conectionProperties.setProperty("password", "ghandi73");
        String queryString = "Select * from customer";

        try {

            Connection connection = DriverManager.getConnection(url, conectionProperties);
            Statement selectStatement = connection.createStatement();
            ResultSet set = selectStatement.executeQuery(queryString);

            String insertQuery = "Insert into customer (first_name,last_name ) values ('Jan','Kowalski')";
            Statement insertStatment = connection.createStatement();
            int resultCode = insertStatment.executeUpdate(insertQuery);
            System.out.println("Operatin with code: " + resultCode);
            insertStatment.close();

            while (set.next()) {
                Long id = set.getLong("cust_id");
                String firstName = set.getString("first_name");
                String lastName = set.getString("last_name");

                System.out.println(id + " " + firstName + " " + lastName);
            }
            set.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Coś nie hallo");
        }

    }
}
