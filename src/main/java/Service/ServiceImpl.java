package Service;

import DatabaseConnections.databaseConnection;
import com.revature.entity.subscriptions;
import com.revature.entity.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceImpl implements service{
    userDAO dao=new userDAO();
    Scanner sc=new Scanner(System.in);
    @Override
    public String signUp(user userDetails) {
        dao.signUp(userDetails);
        return "user register successfully";
    }

    @Override
    public void login(String userName) {
        user newuser=dao.getUserDetails(userName);
        System.out.println("Profile");
        System.out.println(newuser.getUsername());
        System.out.println(newuser.getEmail());
        System.out.println(newuser.getAddress());
        System.out.println(newuser.getPhoneNumber());
    }
    @Override
    public void getPlanDetails(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid name provided.");
            return;
        }
        String query = "SELECT * FROM subscriptionplans WHERE planName = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Plan Details:");
                    System.out.println("Plan Name: " + resultSet.getString("planName"));
                    System.out.println("Ott: " + resultSet.getString("Ott"));
                    System.out.println("Dth: " + resultSet.getString("Dth"));
                    System.out.println("Price: $" + resultSet.getDouble("price"));
                    System.out.println("Data Limit: " + resultSet.getInt("DataLimit") + " GB");
                    System.out.println("Speed: " + resultSet.getInt("Speed") + " Mbps");
                    System.out.println("Validity Period: " + resultSet.getInt("ValidityPeriod") + " days");
                    System.out.println("Type 1 to Activate");
                    int option= sc.nextInt();
                    if (option==1)
                    {
                        subscriptionService.activatePlanForUser(resultSet.getString("planName"));
                    }
                } else {
                    System.out.println("Plan not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getSubscriptions() {
            List<subscriptions> users = new ArrayList<>();
            String query = "SELECT * FROM subscriptions";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    subscriptions subs = new subscriptions(resultSet.getInt("user_id"), resultSet.getString("plan_name"), resultSet.getDouble("price"), resultSet.getDate("start_date"));
                    subs.setId((resultSet.getInt("id")));
                    subs.setPlanName(resultSet.getString("plan_Name"));
                    subs.setPrice(resultSet.getInt("price"));
                    subs.setStartDate(resultSet.getDate("start_date"));
                   // subs.setEndDate(resultSet.getDate("end_date"));
                    users.add(subs);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            for(subscriptions s: users)
            {
                System.out.print(s.getId()+", ");
                System.out.print(s.getPlanName()+", ");
                System.out.print("price: "+(s.getPrice())+"$, ");
                System.out.print("Plan StartDate: "+s.getStartDate()+", ");
                //System.out.print(s.getEndDate()+", ");
                System.out.println();
            }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice (1 for Basic, 2 for Standard, 3 for Premium): ");
        int result = sc.nextInt();
        if(result==1) {
            getPlanDetails("Basic Plan");
        }
        else if (result==2) {
            getPlanDetails("Standard Plan");
        }
        else if (result==3) {
            getPlanDetails("Premium Plan");
        }
        else {
            System.out.println("Invalid choice.please choose 1, 2, or 3.");
        }
    }


    }
