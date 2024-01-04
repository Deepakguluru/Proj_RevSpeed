package Service;

import DatabaseConnections.databaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class subscriptionService {
static Scanner sc=new Scanner(System.in);
        public static void activatePlanForUser( String planName) {
            // Fetch plan details from the 'subscription_plans' table
            String fetchPlanDetailsQuery = "SELECT * FROM subscriptionplans WHERE planName = ?";
            System.out.println("Enter UserName");
            String username=sc.next();
            LocalDate today= LocalDate.now();

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement fetchPlanDetailsStmt = connection.prepareStatement(fetchPlanDetailsQuery)) {

                fetchPlanDetailsStmt.setString(1, planName);

                try (ResultSet resultSet = fetchPlanDetailsStmt.executeQuery()) {
                    if (resultSet.next()) {
                        // Plan details
                        double price = resultSet.getDouble("price");
                        int dataLimit = resultSet.getInt("DataLimit");
                        int speed = resultSet.getInt("speed");
                        int validityPeriod = resultSet.getInt("ValidityPeriod");
                        System.out.println("Enter the amount");
                        double amount=sc.nextDouble();
                        if(amount==price) {
                            // Activate the plan for the user by adding a record in the 'user_subscriptions' table
                            String activatePlanQuery = "INSERT INTO user_subscriptions (username, plan_name, start_date, end_date, price, data_limit, speed, validity_period) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                            try (PreparedStatement activatePlanStmt = connection.prepareStatement(activatePlanQuery)) {
                                activatePlanStmt.setString(1, username);
                                activatePlanStmt.setString(2, planName);
                                activatePlanStmt.setDate(3, Date.valueOf(today));
                                activatePlanStmt.setDate(4, Date.valueOf(today.plusDays(validityPeriod)));
                                activatePlanStmt.setDouble(5, price);
                                activatePlanStmt.setInt(6, dataLimit);
                                activatePlanStmt.setInt(7, speed);
                                activatePlanStmt.setInt(8, validityPeriod);

                                int rowsAffected = activatePlanStmt.executeUpdate();
                                if (rowsAffected > 0) {
                                    System.out.println("Plan activated successfully for user: " + username);
                                } else {
                                    System.out.println("Failed to activate plan. No rows affected.");
                                }
                            }
                        }
                        else {
                            System.out.println("invalid amount details");
                        }
                    } else {
                        System.out.println("Plan not found: " + planName);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
