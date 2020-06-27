import java.sql.*;
import java.time.LocalDate;

public class Database {
    Connection connection;
    private static String username = "root";
    private static String password = "password";
    private static int portNum = 3306;
    private static String TABLE_NAME = "DATA";


    public void createTable() throws SQLException {
        String create = "CREATE TABLE " + TABLE_NAME +
                "("+ "ID INT AUTO_INCREMENT," + "LAST_UPDATED DATETIME," + "SCORE INT,"+ "PRIMARY KEY(id)" + ");";


        Statement statement = connection.createStatement();
        statement.execute(create);
        statement.close();
        System.out.println("SUCCESSFULLY CREATED");
    }
/*
    public void insertScore(){
        PreparedStatement statement = connection.prepareStatement("INSETR INTO "+ TABLE_NAME +" VALUES(?,?)");
        statement.setDate(1,Date.valueOf(LocalDate.now()));
        statement.setInt(2,age);
        statement.execute();
        statement.close();
    }
*/
    void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:" + portNum + "/MYDB", username, password);
            System.out.println("success");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
