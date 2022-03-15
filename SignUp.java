package login;

import java.sql.*;
import java.util.*;

public class SignUp {

	static final String url = "jdbc:mysql://localhost:3306/jdbc";
	static final String username = "root";
	static final String password = "";

	private String insertQuery = "insert into signup (username,password,name,city,account_number,user_id) values (?,?,?,?,?,?)";

	void signUpAccount() {
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
				Scanner sc = new Scanner(System.in);) {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("==== Welcome to XYZ bank account opening.. ====");
			System.out.println("Enter Your Full Name: ");
			String fullName = sc.nextLine();
//			System.out.println(fullName);
			System.out.println("Enter Your City Name: ");
			String city = sc.next();
			System.out.println("Enter Your Username: ");
			String username = sc.next();
			System.out.println("Enter Your Password: ");
			String password = sc.next();
			Random random = new Random();
			int ac = (int) (Math.random() * (9999999 - 1000000 + 1) + (1000000));
			String accountNumber = "XYZ" + String.valueOf(ac);
			int userid = (int) (Math.random() * (999999 - 100000 + 1) + (100000));

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, fullName);
			preparedStatement.setString(4, city);
			preparedStatement.setString(5, accountNumber);
			preparedStatement.setInt(6, userid);

			preparedStatement.executeUpdate();

			System.out.println("Your Account Number is: " + accountNumber);
			System.out.println("Registered Successfully!!");

			App app = new App();
			app.startApp();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
