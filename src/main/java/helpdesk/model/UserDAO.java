package helpdesk.model;

import helpdesk.utils.Password;

import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/films";
    private static final String[] prisijungimas = new String[]{"root", ""} ;

    public static void create(User user) throws SQLException {
            Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, pswd, email, is_admin) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setBoolean(4, user.isAdmin());
            preparedStatement.executeUpdate();

            connection.close();
            System.out.println("User added succesfully");
    }

    public static int findUserIdByUsername(String username) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
        // TODO: Jeigu title yra tusčias, suformuoti select kuris grąžins visus duomenis esančius lentelėje. (Nereikia where)
        // tačiau jeigu title nėra tusčias vykdyti select kuris aprašytas eilute žemiau
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE username = ?");
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
//        preparedStatement.close();
//        connection.close();
        return resultSet.getInt(1);
    }

    public static User userLogin(String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
        preparedStatement.setString(1, username);
//        preparedStatement.setString(2, password);

        User vartotojas = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){ // Jeigu rastas vartotojas pagal username
            vartotojas = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getBoolean(5));
        } else { // Vartotojas nerastas
            return null;
        }

        if (Password.checkPassword(password, vartotojas.getPassword())) {
            return vartotojas;
        } else {
            return null;
        }
    }



}
