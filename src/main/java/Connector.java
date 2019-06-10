import java.sql.*;
import java.util.Properties;


public class Connector {
    private static String url = "jdbc:mysql://localhost:3306/car_rental?serverTimezone=Europe/Warsaw";
    private Properties connectionProperties = new Properties();

    String queryString = "Select * from customer";
    String insertQuery = "Insert into customer (first_name,last_name ) values ('Jan','Kowalski')";
    String queryTemplate = "SELECT * FROM customer WHERE first_name LIKE ? AND last_name LIKE ? ";
    Connection connection = null;


    public Connector() throws SQLException {
        connectionProperties.setProperty("user","root");
        connectionProperties.setProperty("password","ghandi73");
    }

    public void runn()

    {
        try {


            connection = DriverManager.getConnection(url, connectionProperties);

            Statement selectStatement = connection.createStatement();
            ResultSet set = selectStatement.executeQuery(queryString);


            Statement insertStatment = connection.createStatement();
            int resultCode = insertStatment.executeUpdate(insertQuery);
            System.out.println("Operatin with code: " + resultCode);
            insertStatment.close();


            PreparedStatement preparedStatement = connection.prepareStatement(queryTemplate);
            preparedStatement.setString(1, "Celina");
            preparedStatement.setString(2, "Kowal");
            boolean result = preparedStatement.execute();
            System.out.println("Rsult Prepared Statment :" + result);
            ResultSet resultset = preparedStatement.getResultSet();

            while (resultset.next()) {
                Long id_rs = resultset.getLong("cust_id");
                String firstName_rs = resultset.getString("first_name");
                String lastName_rs = resultset.getString("last_name");
                System.out.println(id_rs + " " + firstName_rs + " " + lastName_rs);

            }
            preparedStatement.close();
            while (set.next()) {
                Long id = set.getLong("cust_id");
                String firstName = set.getString("first_name");
                String lastName = set.getString("last_name");

                System.out.println(id + " " + firstName + " " + lastName);
            }
            set.close();
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
            System.out.println("Coś nie hallo");
        }
    }
}




