package src;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Guests {

	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	public static final String user = "sa";
	public static final String pass = "root";
	public static Connection con;

	static {
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closingConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void GuestsTableCreation() {

		String GueststableCreationsql = "create table  Guests (id integer PRIMARY KEY IDENTITY(1,1) ,guest_name VARCHAR (50) not null,"
				+ "guest_phone VARCHAR (50) not null, guest_accompanying_members int  not null,"
				+ "guest_payment_amount int  not null,room_id int REFERENCES Rooms(id),"
				+ "hotel_id  int REFERENCES Hotels(id)," + "created_date Date not null," + "updated_date Date,"
				+ "is_Active bit not null)";

		try {
			Statement st = con.createStatement();
			int Executing = st.executeUpdate(GueststableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + GueststableCreationsql);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

	public static void insertIntoTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Added");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		Random rn = new Random();
		Integer idForRoomType = 1;
		Integer idForHotel = 1;
		Date date = new Date(System.currentTimeMillis());
		for (int i = 0; i <= userInput; i++) {
			System.out.println(" What Is The Guest Name?");
			String guestName = inputScanner.next();
			System.out.println(" What Is The Guest Phone Number?");
			String guestPhone = inputScanner.next();
			System.out.println(" How Many Family Members Guest Have?");
			Integer guestAccompanyingMembers = inputScanner.nextInt();
			System.out.println(" How Much Amount Guest Has Paid?");
			Integer guestPaymentAmount = inputScanner.nextInt();

			System.out.println(" Which Room Type Do You Want ? " + " 1 : STANDARD " + " 3 : DELUXE" + " 4 : SINGLE");
			int roomType = inputScanner.nextInt();
			String roomTypeName = "";
			if (roomType > 0 && roomType < 6) {
				if (roomType == 1) {
					roomTypeName = "STANDARD";
				} else if (roomType == 3) {
					roomTypeName = "DELUXE";
				} else {
					roomTypeName = "SINGLE";
				}

			}

			String sqlQueryToGetId = "SELECT id From Room_Type WHERE room_type_name =" + " '" + roomTypeName + " '";
			try {
				Statement st = con.createStatement();
				ResultSet resultSet = st.executeQuery(sqlQueryToGetId);
				while (resultSet.next()) {
					idForRoomType = resultSet.getInt("id");
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			System.out.println(" Which Hotel Do You Want ? " + " 1 : Four Seasons " + " 2 : Hyatt" + " 3 : Hollywood ");
			int hotelId = inputScanner.nextInt();
			String hotelName = "";
			if (hotelId > 0 && hotelId < 4) {
				if (hotelId == 1) {
					hotelName = "Four Seasons";
				} else if (hotelId == 2) {
					hotelName = "Hyatt";
				} else {
					hotelName = "Hollywood";
				}

			}
			String sqlQueryToGetHotelId = "SELECT id From Hotels WHERE hotel_name =" + " '" + hotelName + " '";
			try {
				Statement st = con.createStatement();
				ResultSet resultSet = st.executeQuery(sqlQueryToGetHotelId);
				while (resultSet.next()) {
					idForHotel = resultSet.getInt("id");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			System.out.println(date);
			String sqlQueryToInsert = "  INSERT INTO Guests "
					+ "(guest_name,guest_phone,guest_accompanying_members,guest_payment_amount,"
					+ "room_id,hotel_id,created_date,is_Active)" + " VALUES ( '" + guestName + "'," + " ' " + guestPhone
					+ "'," + guestAccompanyingMembers + "," + guestPaymentAmount + "," + idForRoomType + ","
					+ idForHotel + ",' " + date + "'" + ", 1) ";

			System.out.println("This is the query: " + sqlQueryToInsert);

			try {
				Statement st = con.createStatement();
				int executing = st.executeUpdate(sqlQueryToInsert);
				if (executing >= 0) {
					System.out.println("Created Successfully : " + sqlQueryToInsert);
				} else {
					System.out.println("Creation Is Failed");
				}
			} catch (Exception ex) {

				System.err.println(ex);
			}
		}
	}

}
