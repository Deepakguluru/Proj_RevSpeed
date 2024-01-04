package Service;

import DatabaseConnections.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ForgetPassword {

    public static String requestPasswordReset(String username) {
        // Generate a unique reset token
        String resetToken = UUID.randomUUID().toString();

        // Save the reset token in the database, associated with the user's account
        saveResetToken(username, resetToken);

        // Send the reset token to the user (e.g., via email or SMS)
        sendResetToken(username, resetToken);
        return resetToken;
    }

    public static void resetPassword(String username, String resetToken, String newPassword) {
        // Verify that the reset token is valid
        if (isValidResetToken(username, resetToken)) {
            // Update the user's password in the database
            updatePassword(username, newPassword);

            // Optional: Invalidate the used reset token
            invalidateResetToken(username, resetToken);

            System.out.println("Password reset successful.");
        } else {
            System.out.println("Invalid reset token.");
        }
    }

    private static void saveResetToken(String username, String password) {
        // Save the reset token in the database
        // (In a real application, you would use a secure mechanism for storage)
        // For simplicity, we'll assume you have a 'password_reset_tokens' table
        String saveTempPasswordQuery = "UPDATE user SET password = ? WHERE username = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveTempPasswordQuery)) {

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Temporary password saved successfully.");
            } else {
                System.out.println("Failed to save temporary password. No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void sendResetToken(String username, String resetToken) {
        // Simulate sending the reset token to the user (replace this with actual email or SMS sending logic)
        System.out.println("Reset token sent to " + username + ": " + resetToken);
    }

    private static boolean isValidResetToken(String username, String resetToken) {
        // Check if the reset token is valid by querying the database
        String validateTokenQuery = "SELECT * FROM user WHERE Username = ? AND Password = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(validateTokenQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, resetToken);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Token is valid if there is a matching record
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void updatePassword(String username, String newPassword) {
        // Update the user's password in the database
        String updatePasswordQuery = "UPDATE user SET password = ? WHERE username = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updatePasswordQuery)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void invalidateResetToken(String username, String resetToken) {
        // Optional: Invalidate the used reset token by removing it from the database
        String invalidateTokenQuery = "DELETE FROM user WHERE username = ? AND Password = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(invalidateTokenQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, resetToken);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
