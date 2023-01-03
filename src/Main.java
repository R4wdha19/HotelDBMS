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
		System.out.println("7 : Insertion of  A Number Hotels ");
		System.out.println("8 : Showing A Number Of Hotels");
		System.out.println("9 : Showing A Row Based On Selcted ID");
		System.out.println("10: Updating A Row using ID");
		System.out.println("11: Deleting A Row Using ID");
		System.out.println("12: Making A Certain Hotel 'is_Active' status false");
		String option = sc.next();
		int op = Integer.parseInt(option);
		switch (op) {
		case 1:
			RoomType.insertIntoTable();
//			Hotels.CreateHotelsTable();
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
		case 7:
			Hotels.insertIntoTable();
			break;
		case 8:
			Hotels.readFromTable();
			break;
		case 9:
			Hotels.getById();
			break;
		case 10:
			Hotels.updateById();
			break;
		case 11:
			Hotels.deleteById();
			break;
		case 12:
			Hotels.updateIsActive();
		}
	}
}
