import java.sql.*;

/**
 * Created by bokense on 25-May-16.
 */
public class Network {

    Connection conn;
    public Network(){

        String url = "jdbc:mysql://84.112.23.71:3306/?user=Joaco";
        String user = "Joaco";
        String password = "231031";
        // Load the Connector/J driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // Establish connection to MySQL
        try {
            conn = DriverManager.getConnection(url, user, password);

            System.out.println("Successfully connected to :  " + url );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getString(int stringID){


        String str = "";
        Statement stmt = null;

        try {
            stmt = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        try {
            if (stmt != null) {
                if (stringID == 0){
                    rs = stmt.executeQuery("SELECT * FROM grwrk.chat;");
                } else if (stringID == 1){
                    rs = stmt.executeQuery("SELECT * FROM grwrk.chat2;");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
           str = rs != null ? rs.getString(1) : "";

        } catch (SQLException e) {
           e.printStackTrace();
        }


        return str;
    }

    public void inputString(int inputID) {

        String input = GUIEngine.getUserInput();

        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stmt != null) {


                if (inputID == 0){
                    stmt.executeUpdate("DELETE FROM grwrk.chat");
                    stmt.executeUpdate("INSERT INTO grwrk.chat (user1) VALUES ('"+ input +"')");
                } else if (inputID == 1){
                    stmt.executeUpdate("DELETE FROM grwrk.chat2");
                    stmt.executeUpdate("INSERT INTO grwrk.chat2 (user2) VALUES ('"+ input +"')");
                }




            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /*


     System.out.println("Inserting records into the table...");
        Statement stmt = null;
        try {
            stmt = network.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stmt != null) {


                stmt.executeUpdate("DELETE FROM test.cards");
                stmt.executeUpdate("INSERT INTO test.cards VALUES ('100')");
                stmt.executeUpdate("INSERT INTO test.cards VALUES ('200')");


                ResultSet rs = stmt.executeQuery("SELECT * FROM test.cards;");

                System.out.println(rs.next());
                System.out.println(rs.getInt(1));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Inserted records into the table...");
     */
}
