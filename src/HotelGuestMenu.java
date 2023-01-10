package src;

import java.util.Scanner;

public class HotelGuestMenu {
	public static void GuestMenu() {
		boolean GuestMenu = true;
		while (GuestMenu) {
			Scanner sc = new Scanner(System.in);
			System.out.println(" Let Us Know What You Want To Do, Here Are The Options: ");
			System.out.println("1 : Create Table For Guests ");
			System.out.println("2 : Insertion of  A Number Guests ");
			System.out.println("3 : Showing A Number Of Guests");
			System.out.println("4 : Showing A Guest Information Based On Selected ID");
			System.out.println("5 : Updating A Guest Information using ID");
			System.out.println("6 : Deleting A Guest Information Using ID");
			System.out.println("7 : Changing A Certain Guest 'is_Active' status ");
			System.out.println("8 : Go Back To Main Menu ");
			String option = sc.next();
			int op = Integer.parseInt(option);
			switch (op) {
			case 1:
				Guests.GuestsTableCreation();

				break;
			case 2:
				Guests.insertIntoTable();
				break;
			case 3:
				Guests.readFromTable();
				break;
			case 4:
				Guests.getById();
				break;
			case 5:
				Guests.updateById();
				break;
			case 6:
				Guests.deleteById();
				break;
			case 7:
				Guests.updateIsActive();
				break;
			case 8:
				System.out.println(" Do You Want To Exit ? If Yes Press 1 If No Press 0 ");
				int confirmation = sc.nextInt();

				if (confirmation == 1) {
					GuestMenu = false;

				}
				break;
			}

		}
		GuestMenu = false;

	}
}
