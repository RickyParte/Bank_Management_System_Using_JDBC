package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

	void loginAccount() {
		String selectQuery = "select * from signup";
		try (Connection connection = DriverManager.getConnection(SignUp.url, SignUp.username, SignUp.password);
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
				ResultSet resultSet = preparedStatement.executeQuery();
				Scanner sc = new Scanner(System.in);) {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Enter Your Username: ");
			String username = sc.next();
			System.out.println("Enter Your Password: ");
			String password = sc.next();

			int balance = 0, userId = 0;

			boolean login = false;
			while (resultSet.next()) {
				String dbUsername = resultSet.getString("username");
				String dbPassword = resultSet.getString("password");

				if (username.equals(dbUsername) && password.equals(dbPassword)) {
					login = true;
					balance = resultSet.getInt("amountmoney");
					userId = resultSet.getInt("user_id");
					break;
				}
			}
			if (login) {
				System.out.println("Login Successfully!");

				AfterLogin(userId, balance);
			} else {
				System.out.println("Login Failed! Check Credentials.");
				System.out.println("Please Re-Enter Your Credentials..");
				loginAccount();
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void AfterLogin(int userId, int balance) {
		System.out.println("*** Choose What You want ***");
		System.out.println(" 1. Deposit Money \n 2. Withdraw Money \n 3. Check balance \n 4. Exit");
		System.out.println("Enter your option: ");
		PreparedStatement preparedStatement = null;
		try (Connection connection = DriverManager.getConnection(SignUp.url, SignUp.username, SignUp.password);
				Scanner sc = new Scanner(System.in);) {
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Rs which want to deposited: ");
				int depositMoney = sc.nextInt();
				String updateQuery = "update signup set amountmoney=? where user_id=" + userId;
				int newBalance = depositMoney + balance;
				preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setInt(1, newBalance);
				preparedStatement.executeUpdate();
				System.out.println("Your " + depositMoney + " deposited in your account");
				AfterLogin(userId, newBalance);
				break;
			case 2:
				int updateBalance = 0;
				System.out.println("Enter Rs which want to Withdraw: ");
				int withdrawMoney = sc.nextInt();
				String updateQ = "update signup set amountmoney=? where user_id=" + userId;
				if (withdrawMoney > balance) {
//						updateBalance=balance-withdrawMoney;
					System.out.println("Not Enough Money");
				} else {
					updateBalance = balance - withdrawMoney;
					preparedStatement = connection.prepareStatement(updateQ);
					preparedStatement.setInt(1, updateBalance);
					preparedStatement.executeUpdate();
					System.out.println("Your " + withdrawMoney + " withdrawn from your account");
				}
				AfterLogin(userId, updateBalance);
				break;
			case 3:
				System.out.println("Your Balance is: " + balance);
				AfterLogin(userId, balance);
				break;
			case 4:
				App app = new App();
				app.startApp();
				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
