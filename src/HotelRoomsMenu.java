package src;

import java.util.Scanner;

public class HotelRoomsMenu {
        public static void RoomsMenu() {
            boolean RoomsMenu = true;
            while (RoomsMenu) {
                Scanner sc = new Scanner(System.in);
                System.out.println(" Let Us Know What You Want To Do, Here Are The Options: ");
                System.out.println("1 : Create Table For Rooms ");
                System.out.println("2 : Insertion of  A Number Rooms ");
                System.out.println("3 : Showing A Number Of Rooms");
                System.out.println("4 : Showing A Room Information Based On Selected ID");
                System.out.println("5 : Updating A Room Information using ID");
                System.out.println("6 : Deleting A Room Information Using ID");
                System.out.println("7 : Changing A Certain Room 'is_Active' status ");
                System.out.println("8 : Go Back To Main Menu ");
                String option = sc.next();
                int op = Integer.parseInt(option);
                switch (op) {
                    case 1:
                        RoomType.RoomTypeTableCreation();
                        break;
                    case 2:
                        RoomType.insertIntoTable();
                        break;
                    case 3:
                        RoomType.readFromTable();
                        break;
                    case 4:
                        RoomType.getById();
                        break;
                    case 5:
                        RoomType.updateById();
                        break;
                    case 6:
                        RoomType.deleteById();
                        break;
                    case 7:
                        RoomType.updateIsActive();
                        break;
                    case 8:
                        System.out.println(" Do You Want To Exit ? If Yes Press 1 If No Press 0 ");
                        int confirmation = sc.nextInt();
                        sc.next();
                        if (confirmation == 1) {
                            RoomsMenu = false;
//					Main.mainMenu();

                        }
                        break;
                }

            }
            RoomsMenu = false;

        }
    }


