package src;

import java.util.Scanner;

import src.Employees.Employee;

public class HotelEmployeeMenu {
	public static void EmployeeMenu() {
		boolean EmployeeMenu = true;
		while (EmployeeMenu) {
			Scanner sc = new Scanner(System.in);
			System.out.println(" Let Us Know What You Want To Do, Here Are The Options: ");
			System.out.println("1 : Create Table For Employee ");
			System.out.println("2 : Insertion of  A Number Employees ");
			System.out.println("3 : Showing A Number Of Employees");
			System.out.println("4 : Showing An Employee Information Based On Selected ID");
			System.out.println("5 : Updating An Employee Information using ID");
			System.out.println("6 : Deleting An Employee Information Using ID");
			System.out.println("7 : Changing An Certain Employee 'is_Active' status ");
			System.out.println("8 : Go Back To Main Menu ");
			String option = sc.next();
			int op = Integer.parseInt(option);
			switch (op) {
			case 1:
				Employee.CreateEmployeesTable();

				break;
			case 2:
				Employee.insertIntoTable();
				break;
			case 3:
				Employee.readFromTable();
				break;
			case 4:
				Employee.getById();
				break;
			case 5:
				Employee.updateById();
				break;
			case 6:
				Employee.deleteById();
				break;
			case 7:
				Employee.updateIsActive();
				break;
			case 8:
				System.out.println(" Do You Want To Exit ? If Yes Press 1 If No Press 0 ");
				int confirmation = sc.nextInt();

				if (confirmation == 1) {
					EmployeeMenu = false;

				}
				break;
			}

		}
		EmployeeMenu = false;

	}
}
