package src;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
		for (int i = 0; i < userInput; i++) {
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

	public static void readFromTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Shown ");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT TOP " + userInput + " * FROM Guests";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String guestName = resultSet.getString("guest_name");
				String guestPhone = resultSet.getString("guest_phone");
				Integer guestAccompanyingMembers = resultSet.getInt("guest_accompanying_members");
				Integer guestPaymentAmount = resultSet.getInt("guest_payment_amount");
				Integer roomId = resultSet.getInt("room_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + guestName + " " + guestPhone + " " + guestAccompanyingMembers + " "
						+ guestPaymentAmount + " " + roomId + " " + hotelId + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}

		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public static void getById() {
		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();

		String sqlQueryToRead = "SELECT * FROM Guests WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String guestName = resultSet.getString("guest_name");
				String guestPhone = resultSet.getString("guest_phone");
				Integer guestAccompanyingMembers = resultSet.getInt("guest_accompanying_members");
				Integer guestPaymentAmount = resultSet.getInt("guest_payment_amount");
				Integer roomId = resultSet.getInt("room_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + guestName + " " + guestPhone + " " + guestAccompanyingMembers + " "
						+ guestPaymentAmount + " " + roomId + " " + hotelId + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}

		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateById() {
		System.out.println(" Please Enter The ID To Update Its Data");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		System.out.println(" Please Enter The New Guest Name");
		String newguestName = inputScanner.next();
		System.out.println(" Please Enter The New Guest Location");
		String newguestPhone = inputScanner.next();
		System.out.println(" Please Enter The New Guest Accompanying Members Number");
		Integer newguestAccompanyingMembers = inputScanner.nextInt();
		System.out.println(" Please Enter The New Guest Payment Amount");
		Integer newguestPaymentAmount = inputScanner.nextInt();
		Date date = new Date(System.currentTimeMillis());
		String sqlQueryToUpdate = "UPDATE Guests SET guest_name = " + "'" + newguestName + "'" + ", guest_phone = "
				+ "'" + newguestPhone + "',guest_accompanying_members = " + newguestAccompanyingMembers
				+ ",guest_payment_amount = " + newguestPaymentAmount + "," + " updated_date = " + "'" + date
				+ "' WHERE id =" + userInput;

		System.out.println(sqlQueryToUpdate);
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
			System.out.println(resultSet);

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String guestName = resultSet.getString("guest_name");
				String guestPhone = resultSet.getString("guest_phone");
				Integer guestAccompanyingMembers = resultSet.getInt("guest_accompanying_members");
				Integer guestPaymentAmount = resultSet.getInt("guest_payment_amount");
				Integer roomId = resultSet.getInt("room_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + guestName + " " + guestPhone + " " + guestAccompanyingMembers + " "
						+ guestPaymentAmount + " " + roomId + " " + hotelId + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}

		} catch (Exception ex) {

			System.err.println(ex);
		}
	}

	public static void deleteById() {
		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "DELETE FROM Guests WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String guestName = resultSet.getString("guest_name");
				String guestPhone = resultSet.getString("guest_phone");
				Integer guestAccompanyingMembers = resultSet.getInt("guest_accompanying_members");
				Integer guestPaymentAmount = resultSet.getInt("guest_payment_amount");
				Integer roomId = resultSet.getInt("room_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + guestName + " " + guestPhone + " " + guestAccompanyingMembers + " "
						+ guestPaymentAmount + " " + roomId + " " + hotelId + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}

		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateIsActive() {

		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Rows  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT TOP " + userInput + " id FROM Guests";
		Statement statement;
		List<Integer> listOfIds = new ArrayList<>();
		try {
			System.out.println(sqlQueryToSelect);
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToSelect);

			while (resultSet.next()) {

				listOfIds.add(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Integer i : listOfIds) {
			System.out.println("Id is: " + i);
			String sqlQueryToUpdate = "UPDATE Guests SET is_active = 0 where id = " + i;
			System.out.println(sqlQueryToUpdate);
			try {
				statement = con.createStatement();
				int resultSet = statement.executeUpdate(sqlQueryToUpdate);
				System.out.println(resultSet);

			} catch (Exception ex) {

				System.err.println(ex);
			}
		}

	}

}
