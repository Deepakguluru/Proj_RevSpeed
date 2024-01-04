package Service;

import DatabaseConnections.databaseConnection;
import com.revature.entity.subscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class SubscriptionDAO {

        public List<subscriptions> getSubscriptions(int userId) {
            List<subscriptions> subscriptions = new ArrayList<>();
            String query = "SELECT * FROM subscriptions WHERE user_id = ?";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
//                        subscriptions subscription = new subscriptions(
//                                resultSet.getInt("user_id"),
//                                resultSet.getString("plan_name"),
//                                resultSet.getDouble("price"),
//                                resultSet.getDate("start_date"),
//                                resultSet.getDate("end_date")
                        subscriptions subscription=new subscriptions(
                                resultSet.getInt("user_id"),
                                resultSet.getString("plan_name"),
                                resultSet.getDouble("price"),
                                resultSet.getDate("start_date")
                        );
                        subscriptions.add(subscription);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return subscriptions;
        }
        public void applyNewSubscription(subscriptions subscription) {
            String query = "INSERT INTO subscriptions (user_id, plan_name, price, start_date) VALUES (?, ?, ?, ?)";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, subscription.getUserId());
                preparedStatement.setString(2, subscription.getPlanName());
                preparedStatement.setDouble(3, subscription.getPrice());
                preparedStatement.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));


                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void cancelSubscription(int subscriptionId) {
            String query = "DELETE FROM subscriptions WHERE id = ?";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, subscriptionId);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
