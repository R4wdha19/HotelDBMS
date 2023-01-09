package src;

import java.util.Scanner;

public class Main {

	static void mainMenu() {

		System.out.println("Please Select An Option :");
		System.out.println("1 : HotelMenu ");
		System.out.println("2 : RooM Type Menu");
		System.out.println("3 : Room Menu");
		System.out.println("4 : Guests Menu");
		System.out.println("5 : Employee Type Menu");
		System.out.println("6 : Employees Menu ");

	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		boolean mainMenu = true;
		while (mainMenu) {

			mainMenu();
			String option = sc.next();
			int op = Integer.parseInt(option);
			switch (op) {
			case 1:
				HotelsMenu.HotelMenu();
				break;
			case 2:
				HotelRoomTypeMenu.RoomTypeMenu();
				break;
			case 3:
				HotelRoomsMenu.RoomsMenu();
				break;
			case 4:
				Rooms.CreateRoomsTable();
				;
				break;
			case 5:
				EmployeeType.EmployeeTypeTableCreation();
				break;
			case 6:
				Employees.EmployeesTableCreation();
			}
		}
		mainMenu = false;

	}

}
