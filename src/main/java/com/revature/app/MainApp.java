package com.revature.app;

import java.util.Scanner;
import Service.*;
import com.revature.entity.user;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServiceImpl impl=new ServiceImpl();
        userDAO dao=new userDAO();
        System.out.println("Choose an option:");
        System.out.println("1. User Registration");
        System.out.println("2. User Login");
        System.out.println("3. Exit");
        int options= scanner.nextInt();
        scanner.nextLine();
        switch (options) {
            case 1:
                // User Registration
                    //hiiismslmfmswmlwmf,mwf,m;
                    System.out.println("=== User Registration ===");
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    // Validate password
                    String password;
                    do {
                        System.out.print("Enter password (8-10 characters, at least one digit, and one special character): ");
                        password = scanner.nextLine();
                        if (!isPasswordValid(password)) {
                            System.out.println("Password does not meet the specifications. Please follow the requirements.");
                        }
                    } while (!isPasswordValid(password));

                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.nextLine();
                    // Validate email
                    String email;
                    do {
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                        if (!isEmailValid(email)) {
                            System.out.println("Invalid email format. Please enter a valid email address.");
                        }
                    } while (!isEmailValid(email));
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    user newUser = new user(username, password, phoneNumber, email, address);
                    impl.signUp(newUser);
                    System.out.println("Successfully created your Account");
                    break;
            case 2:
                //User Login
                System.out.println("\n1. User Login ");
                System.out.println("\n2. Forgot Password");
                    int option=scanner.nextInt();

                    switch (option) {
                        case 1:
                            System.out.print("Enter username: ");
                            String loginUsername = scanner.next();
                            System.out.print("Enter password: ");
                            String loginPassword = scanner.next();

                            boolean result = dao.login(loginUsername, loginPassword);
                            if (result) {
                                System.out.println("Login Successfully");
                                System.out.println("Welcome to BrightSpeed");
                                impl.getSubscriptions();

//                        impl.getPlanDetails("Basic Plan");

                            } else {
                                System.out.println("Login Failed Incorrect username/password");
                            }
                            break;
                        case 2:
                            System.out.println("Enter User Name: ");
                            String name = scanner.next();
                            String temppassword=ForgetPassword.requestPasswordReset(name);
                            System.out.println("Enter new Password");
                            String newpassword=scanner.next();
                            if (isPasswordValid(newpassword)) {
                                ForgetPassword.resetPassword(name, temppassword, newpassword);
                            }
                            else {
                                System.out.println("password doesn't reach specifications");
                            }
                            break;
                    }

                    break;

            case 3:
                System.exit(0);

            default:
                System.out.println("Invalid choice");
        }
    }
    private static boolean isPasswordValid(String password) {
        // Password must be 8-10 characters, contain at least one digit, and have at least one special character
        return password.matches("^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,10}$");
    }
    private static boolean isEmailValid(String email) {
        // Simple email validation using regex
        return email.matches("^[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");
    }
}