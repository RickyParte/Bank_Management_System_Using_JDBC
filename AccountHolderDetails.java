package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountHolderDetails {

	void passbookUser() {

		try (Connection connection = DriverManager.getConnection(SignUp.url, SignUp.username, SignUp.password);

				Scanner sc = new Scanner(System.in);) {

			System.out.println("+====+  Welcome  +====+");
			System.out.println("Please Enter Your Account Number");
			String accountNumber = sc.next();
			System.out.println("Please Enter Your Password");
			String password = sc.next();

			String selectQuery = "select * from signup";
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

			ResultSet resultSet = preparedStatement.executeQuery();

			boolean checkPassword = false;

			while (resultSet.next()) {
				String dbAccountNumber = resultSet.getString("account_number");
				String dbPassword = resultSet.getString("password");
				if (dbAccountNumber.equals(accountNumber) && password.equals(dbPassword)) {
					checkPassword = true;
					break;
				} else {
					checkPassword = false;
				}

			}

			if (checkPassword) {
				System.out.println("+=============================+");
				System.out.println("Your Username is: " + resultSet.getString("username"));
				System.out.println("Your Full Name: " + resultSet.getString("name"));
				System.out.println("Your City: " + resultSet.getString("city"));
				System.out.println("Your Account Number: " + resultSet.getString("account_number"));
				System.out.println("+=============================+");

			} else {
				System.out.println("Please Enter Correct Password And Username");
				passbookUser();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
