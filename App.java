package login;

import java.util.*;

public class App {
	public static void main(String[] args) {

		App app = new App();
		app.startApp();

	}

	public void startApp() {
		System.out.println("=== Welcome To Banking System ===");
		System.out.println(" 1. Create Account \n 2. Login  \n 3. Show Details \n 4. Exit");
		System.out.println("Enter Your Choice.");
		try (Scanner sc = new Scanner(System.in);) {
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Entered in SignUp");
				SignUp signUp = new SignUp();
				signUp.signUpAccount();
				break;
			case 2:
				Login login = new Login();
				login.loginAccount();
				break;
			case 3:
				System.out.println("===  Details of account Holder  ===");
				AccountHolderDetails accountHolderDetails = new AccountHolderDetails();
				accountHolderDetails.passbookUser();
				break;
			case 4:
				System.exit(0);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
