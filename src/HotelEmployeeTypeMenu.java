package src;

import java.util.Scanner;

public class HotelEmployeeTypeMenu {
	public static void EmployeeTypeMenu() {
		boolean EmployeeTypeMenu = true;
		while (EmployeeTypeMenu) {
			Scanner sc = new Scanner(System.in);
			System.out.println(" Let Us Know What You Want To Do, Here Are The Options: ");
			System.out.println("1 : Create Table For Employee Type ");
			System.out.println("2 : Insertion of  A Number Employee Types ");
			System.out.println("3 : Showing A Number Of Employee Types");
			System.out.println("4 : Showing An Employee Type Information Based On Selected ID");
			System.out.println("5 : Updating An Employee Type Information using ID");
			System.out.println("6 : Deleting An Employee Type Information Using ID");
			System.out.println("7 : Changing An Employee Type  'is_Active' status ");
			System.out.println("8 : Go Back To Main Menu ");
			String option = sc.next();
			int op = Integer.parseInt(option);
			switch (op) {
			case 1:
				EmployeeType.CreateEmployeeType();

				break;
			case 2:
				EmployeeType.insertIntoTable();
				break;
			case 3:
				EmployeeType.readFromTable();
				break;
			case 4:
				EmployeeType.getById();
				break;
			case 5:
				EmployeeType.updateById();
				break;
			case 6:
				EmployeeType.deleteById();
				break;
			case 7:
				EmployeeType.updateIsActive();
				break;
			case 8:
				System.out.println(" Do You Want To Exit ? If Yes Press 1 If No Press 0 ");
				int confirmation = sc.nextInt();

				if (confirmation == 1) {
					EmployeeTypeMenu = false;

				}
				break;
			}

		}
		EmployeeTypeMenu = false;

	}
}
