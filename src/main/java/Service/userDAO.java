package Service;

import com.revature.entity.user;
import DatabaseConnections.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class userDAO {

        public void signUp(user user) {
            String query = "INSERT INTO user (username, password, phoneNumber, email, address) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getPhoneNumber());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getAddress());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public boolean login(String username, String password) {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // true if user exists with provided credentials
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public user getUserDetails(String username) {
            String query = "SELECT * FROM user WHERE username = ?";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user userDetails = new user(
                                resultSet.getString("Username"),
                                resultSet.getString("Password"),
                                resultSet.getString("PhoneNumber"),
                                resultSet.getString("Email"),
                                resultSet.getString("Address")
                        );
                        userDetails.setUserID(resultSet.getInt("UserID"));
                        return userDetails;
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
