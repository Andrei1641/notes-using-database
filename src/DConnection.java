import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/yourSchema";
    private static final String USERNAME = "yourUsername";
    private static final String PASSWORD = "yourPassword";

    private Connection connection;

    public static List<TmpTabelle> listTmp = new ArrayList<>();

    static class TmpTabelle {
        int id = 0;
        int checkbox = 0;
        String name = "";
        String time = "";
        String tema = "";
        String text = "";



        public TmpTabelle(int id, int checkbox, String name, String time, String tema, String text) {
            this.id = id;
            this.checkbox = checkbox;
            this.name = name;
            this.time = time;
            this.tema = tema;
            this.text = text;
        }
    }

    public DConnection() {

        try  {
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (connection != null) {
                System.out.println("yes");
            readRecords(connection);



            } else {
                System.out.println("no");
            }

        } catch (SQLException e) {
            System.out.println("false");
        }

    }

    public Connection getConnection() {
        return this.connection;
    }

    public static void readRecords(java.sql.Connection connection) throws SQLException {
        String query = "SELECT * FROM yourTable";

        try (java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id1");
                int checkbox = resultSet.getInt("checkbox");
                String name = resultSet.getString("name");
                String time = resultSet.getString("time");
                String tema = resultSet.getString("tema");
                String text = resultSet.getString("text");

                TmpTabelle tmpObj = new TmpTabelle(id,checkbox,name,time,tema,text);
                listTmp.add(tmpObj);


            }
        }

    }

    public static void insertRecord(Connection connection, int checkbox, String name, String time, String tema, String text ) {
        String insertSql = "INSERT INTO yourTable (checkbox, name, time, tema, text) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setInt(1, checkbox);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, tema);
            preparedStatement.setString(5, text);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Insert failed.");
            }
        }catch (SQLException e){
            System.out.println("noo" + e.getMessage());
        }
    }
    public static void deleteRecord(Connection connection, int id){
        String deleteSql = "DELETE FROM yourTable WHERE id1 = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setInt(1, id);


            int rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "deleted");
            }
        } catch (SQLException e) {

            System.out.println("Error during deletion: " + e.getMessage());
        }
    }
}
