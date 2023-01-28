package src;

import java.util.Scanner;

public class HotelManagementMenu {

    public static void hotelManagementMenuList(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("0- Go Back To Main Menu");
        System.out.println("1- Guests who's name end with 'E'");
        System.out.println("2- Rooms where guests are paying more than 200");
        System.out.println("3- Count of guests who are staying in 'DELUXE' rooms");
        System.out.println("4- Guests who are staying in rooms and served by employee who have 'A' in their name");
        System.out.println("5- All rooms which are not active but room type is 'Deluxe'");
        System.out.println("6- All room type in hotels who's name have 'H' or are active but have more than 5 Rooms");
        int option=scanner.nextInt();
        switch (option){
            case 0:
              //  i will ignore this 4 now

            case 1:
                HotelManagement.GuestNameEndWithLetter();
                break;
            case 2:
                HotelManagement.guestPayingMoreThan1000();
                break;
            case 3:
                HotelManagement.GuestStayingInSpecificRoomType();
                break;
            case 4:
                HotelManagement.GuestServedByEmployee();
                break;
            case 5:
               HotelManagement.RoomsIsActiveStatusAndSpecificRoomType();
               break;
            case 6:
                HotelManagement.hotelsNameWithSpecificRoomNumber();

        }
    }

}
