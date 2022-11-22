package projectJava2;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class RBI {

	public static void main(String[] args) {

		System.out.println("****Welcome to National Banking System*****");
		System.out.println("\n");
		System.out.println("Do you want to open an account : 1.Yes  2.No");

		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();

		if (choice.equalsIgnoreCase("1")) {
			OpenAccount obj = new OpenAccount();
			obj.createAccount();
		}

		if (choice.equalsIgnoreCase("2")) {
			BankAccount obj = new BankAccount();
			obj.showMenu();
		}

	}
}

;

class OpenAccount {
	String name;
	int AccountNum;
	String accountType;
	String dob;
	String bank;

	void createAccount() {
		Scanner sc = new Scanner(System.in);

		System.out.println("In Which Bank you want to open it :  1.SBI  2.ICICI  3.PNB");
		int choiceBank = sc.nextInt();
		if (choiceBank == 1) {
			bank = "SBI";
		}

		if (choiceBank == 2) {
			bank = "ICICI";
		}

		if (choiceBank == 3) {
			bank = "PNB";
		}

		System.out.println("Please Enter Your Name : ");
		sc.nextLine();
		name = sc.nextLine();

		System.out.println("Please Enter dob: ");
		dob = sc.nextLine();

		System.out.println("What type of Account you want to open :  1.saving  2.Current");
		int choice = sc.nextInt();
		if (choice == 1) {
			accountType = "Saving";
		}
		if (choice == 2) {
			accountType = "Current";
		}

		System.out.println("Your Account has been opened with following Details.");
		System.out.println("Bank:" + bank);
		System.out.println("Name: " + name);
		System.out.println("dob:" + dob);
		System.out.println("AccountType:" + accountType);
		System.out.println("Account Number:" + Math.random());

		System.out.println("\n");

		BankAccount obj1 = new BankAccount();
		obj1.showMenu();
		sc.close();

	}

}

class BankAccount {
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	String accountType;
	double totalInterest;

	void calculateInterest(double balance) {
		System.out.println("What type of account you have:  1.Saving  2.Current");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		if (choice == 1) {
			accountType = "saving";
			int r = 10;
			int t;
			System.out.println("Enter year to calculate Interest");
			t = sc.nextInt();

			double finalAmount = balance * (1 + r * t);

			totalInterest = finalAmount - balance;
			System.out.println("Total Interest earned is " + totalInterest);
		}

		if (choice == 2) {
			accountType = "Current";
			int r = 8;
			int t, n;
			System.out.println("Enter year to calculate Interest");
			t = sc.nextInt();

			System.out.println("Enter the frequency that interest has been compound in a year");
			n = sc.nextInt();

			double finalAmount = balance * (Math.pow((1 + r / n), n * t));

			totalInterest = finalAmount - balance;
			System.out.println("Total Interest earned is " + totalInterest);
			sc.close();
		}
	}

	void deposit(int amount) {
		if (amount != 0)
			;
		{
			balance = balance + amount;
			System.out.println("Balance after Deposit :" + balance);

		}
	}

	void withdraw(int amount) {
		if (amount != 0)
			;
		{
			balance = balance - amount;
			System.out.println("Balance after withdraw : " + balance);
			previousTransaction = -amount;

		}
	}

	void getpreviousTranscation() {
		FileOutputStream out;
		PrintStream p;

		try {
			out = new FileOutputStream("k.txt");
			p = new PrintStream(out);

			if (previousTransaction > 0) {
				p.append("Deposited: " + previousTransaction);
				System.out.println("Deposited: " + previousTransaction);
			} else if (previousTransaction < 0) {
				p.append("withdrawn :" + previousTransaction);
				System.out.println("Withdrawn: " + Math.abs(previousTransaction));
			} else {
				System.out.println("No Transaction occured");

			}

			p.close();
		} catch (Exception e) {
			System.out.println("Error in printing the data" + e);

		}

	}

	void showMenu() {
		char option = '\0';
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the menu");
		System.out.println("\n");
		System.out.println("A. Check Balance");
		System.out.println("B. Deposit Amount");
		System.out.println("C. Withdraw Amount");
		System.out.println("D. See previous Transaction");
		System.out.println("E. Calculate Interest");
		System.out.println("F.Exit");

		do {
			System.out.println("*******************");
			System.out.println("Enter an option");
			System.out.println("**********************");
			option = scanner.next().charAt(0);
			System.out.println("\n");

			switch (option) {
			case 'A':
				System.out.println("-----------");
				System.out.println("Balance = " + balance);
				System.out.println("\n");
				break;

			case 'B':
				System.out.println("-----------");
				System.out.println("Enter the Amount to Deposit : ");
				int amount = scanner.nextInt();
				deposit(amount);
				System.out.println("\n");
				break;

			case 'C':
				System.out.println("-----------");
				System.out.println("Enter the Amount to Withdraw : ");
				int amount2 = scanner.nextInt();
				withdraw(amount2);
				System.out.println("\n");
				break;

			case 'D':
				System.out.println("-----------");
				getpreviousTranscation();
				System.out.println("\n");
				break;

			case 'E':
				System.out.println("-----------");
				calculateInterest(balance);
				System.out.println("\n");
				break;

			case 'F':
				System.out.println("------------------------");
				break;

			default:
				System.out.println("Entered Invalid Option1. Please enter Again");
				break;

			}
		} while (option != 'F');
		System.out.println("Thank you for using our services");
		scanner.close();
	}
}
