package helpdesk.model;

import java.sql.*;

public class FilmsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/films";
    private static final String[] prisijungimas = new String[]{"root", ""} ;

    public static void create(Films requestForm) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO request_form (title, description, rating, category, user_id) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, requestForm.getTitle());
        preparedStatement.setString(2, requestForm.getDescription());
        preparedStatement.setDouble(3, requestForm.getRating());
        preparedStatement.setString(4, requestForm.getCategory());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void delete(int id) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM films WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void update(Films requestForm) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE films SET title = ?, description = ?, rating = ?, category = ?, WHERE id = ?");
        preparedStatement.setString(1, requestForm.getTitle());
        preparedStatement.setString(2, requestForm.getDescription());
        preparedStatement.setDouble(3, requestForm.getRating());
        preparedStatement.setString(4, requestForm.getCategory());
        preparedStatement.setInt(7, requestForm.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static ResultSet search(String title) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prisijungimas[0], prisijungimas[1]);
        PreparedStatement preparedStatement;
        if (User.getInstance().isAdmin()) { // Jeigu administratorius - atvaizduoti visų vartotojų įrašus
            if (title.isEmpty()){
                preparedStatement = connection.prepareStatement("SELECT * FROM request_form");
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM request_form WHERE title LIKE ?");
                preparedStatement.setString(1, title);
            }
        } else { // Jeigu vartotojas - atvaizduoti tik jo kurtus įrašus
            if (title.isEmpty()){
                preparedStatement = connection.prepareStatement("SELECT * FROM request_form WHERE user_id = ?");
                preparedStatement.setInt(1, User.getInstance().getUserID());
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM request_form WHERE title LIKE ? AND user_id = ?");
                preparedStatement.setString(1, title);
                preparedStatement.setInt(2, User.getInstance().getUserID());
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

}
