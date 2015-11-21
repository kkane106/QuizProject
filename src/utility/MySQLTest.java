package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/quizdb";
        String sqlText = "SELECT id, text FROM question";
        String userName = "webapp";
        String passWord = "webapp";
        try (Connection conn = DriverManager.getConnection(url, userName, passWord);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlText)) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + ": "+rs.getString(2));
            }
        }
        catch (SQLException sqle){
            System.err.println(sqle);
        }
    }
}