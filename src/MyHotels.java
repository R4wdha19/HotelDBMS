package src;

import java.util.Scanner;

class MyHotels {

	public static void HotelMenu() {
		boolean isExit = true;
		while (isExit) {
			Scanner sc = new Scanner(System.in);
			System.out.println(" Let Us Know What You Want To Do, Here Are The Options: ");
			System.out.println("1 : Create Table For Hotels ");
			System.out.println("2 : Insertion of  A Number Hotels ");
			System.out.println("3 : Showing A Number Of Hotels");
			System.out.println("4 : Showing A Hotel Information Based On Selected ID");
			System.out.println("5 : Updating A Hotel Information using ID");
			System.out.println("6 : Deleting A Hotel Information Using ID");
			System.out.println("7 : Changing A Certain Hotel 'is_Active' status ");
			System.out.println("8 : Go Back To Main Menu ");
			String option = sc.next();
			int op = Integer.parseInt(option);
			switch (op) {
			case 1:
				Hotels.CreateHotelsTable();
				break;
			case 2:
				Hotels.insertIntoTable();
				break;
			case 3:
				Hotels.readFromTable();
				break;
			case 4:
				Hotels.getById();
				break;
			case 5:
				Hotels.updateById();
				break;
			case 6:
				Hotels.deleteById();
				break;
			case 7:
				Hotels.updateIsActive();
				break;
			case 8:
				Main.mainMenu();
				break;
			}

		}
		isExit = false;
	}

}
