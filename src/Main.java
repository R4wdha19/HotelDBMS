import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Select An Option :");
		System.out.println("1 : Create Table For Hotels ");
		System.out.println("2 : Create Table For Room Type");
		System.out.println("3 : Create Table For Rooms ");
		System.out.println("4 : Create Table For Guests");
		System.out.println("5 : Create Table For Employee Type");
		System.out.println("6 : Create Table For Employees ");
		System.out.println("7 : Insertion of  10,000 Hotels ");
		System.out.println("8 : Insertion of 1 Hotel");
		System.out.println("9 : Printing 10 Hotels");
		System.out.println("10: Making the first 10 Hotels 'is_Active' = false");
		System.out.println("11: Printing hotel information according to user input");
		String option = sc.next();
		int op = Integer.parseInt(option);
		switch (op) {
		case 1:
			Hotels.CreateHotelsTable();
			break;
		case 2:
			RoomType.RoomTypeTableCreation();
			break;
		case 3:
			Rooms.RoomTableCreation();
			break;
		case 4:
			Guests.GuestsTableCreation();
			break;
		case 5:
			EmployeeType.EmployeeTypeTableCreation();
			break;
		case 6:
			Employees.EmployeesTableCreation();
			break;
		}
	}
}