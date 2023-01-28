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

public class Rooms {
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

	public static void CreateRoomsTable() {
		String RoomtableCreationsql = "create table  Rooms (id integer PRIMARY KEY IDENTITY(1,1) ,room_type_id int REFERENCES Room_Type(id),"
				+ "hotel_id int REFERENCES Hotels(id),created_date Date not null,updated_date Date, "
				+ "is_Active bit not null)";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(RoomtableCreationsql);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + RoomtableCreationsql);
			} else {
				System.out.println("Creation Is Failed");
			}
//            closingConnection();
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void insertIntoTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Added");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		Date date = new Date(System.currentTimeMillis());
		Random rn = new Random();
		Statement st = null;
		Integer idForRoomType = 1; // Setting a default value In case of error
		Integer idForHotel = 1;
		// My options start with 1 therefore we did 1 if i make it 0 it will rise an
		// error and it will by default 0 but that is not an option.
//		// So we make a choice that when there is an error the assigned roomTypeName is STANDARD by default
		for (int i = 0; i < userInput; i++) { // WE REMOVE = BECAUSE ?
			//<= INPUT = 5, lOOPS RUN LIKE 0,1,2,3,4,5
			// < input = 5, LOOPS RUN LIKE 0,1,2,3,4
			Integer numberOfRandomUserInput = rn.nextInt(userInput); // ()my limit

			Scanner scanner = new Scanner(System.in);
			System.out.println(" Which Room Type Do You Want ? " + " 1 : STANDARD " + " 3 : DELUXE" + " 4 : SINGLE");
			int roomType = scanner.nextInt();
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
				st = con.createStatement();
				ResultSet resultSet = st.executeQuery(sqlQueryToGetId);
				while (resultSet.next()) {
					idForRoomType = resultSet.getInt("id");
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			System.out.println(" Which Hotel Do You Want ? " + " 1 : Four Seasons " + " 2 : Hyatt" + " 3 : Hollywood ");
			int hotelId = scanner.nextInt();
			String hotelName = "";
			if (hotelId > 0 && hotelId < 4) {
				if (hotelId == 1) {
					hotelName = "FourSeasons";
				} else if (hotelId == 2) {
					hotelName = "Hyatt";
				} else {
					hotelName = "Hollywood";
				}

			}
			String sqlQueryToGetHotelId = "SELECT id From Hotels WHERE hotel_name =" + " '" + hotelName + " '";
			try {
				st = con.createStatement();
				ResultSet resultSet = st.executeQuery(sqlQueryToGetHotelId);
				while (resultSet.next()) {
					idForHotel = resultSet.getInt("id");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sqlQueryToInsert = "INSERT INTO Rooms (room_type_id,hotel_id ,created_date,is_Active)" + "Values ("
					+ idForRoomType + "," + idForHotel + ",'" + date + "'," + 1 + ")";

			System.out.println("This is the query to insert into rooms table: " + sqlQueryToInsert);
			try {
				st = con.createStatement();
				ResultSet e = st.executeQuery(sqlQueryToInsert);

			} catch (SQLException e1) {
				System.out.println(e1.getLocalizedMessage());
			}
		}
	}

	public static void readFromTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Shown ");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT TOP " + userInput + " * FROM Rooms";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer roomTypeId = resultSet.getInt("room_type_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(
						id + " " + roomTypeId + " " + hotelId + " " + createdDate + " " + updatedDate + " " + isActive);
			}

		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public static void getById() {
		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();

		String sqlQueryToRead = "SELECT * FROM Rooms WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer roomTypeId = resultSet.getInt("room_type_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(
						id + " " + roomTypeId + " " + hotelId + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}
// We DONT USE UPDATEBYID FUN FOR ROOMS COZ WE CAN NOT UPDATE THE Foreign key

	public static void updateById() {
		System.out.println(" SORRY WE CAN NOT UPDATE IT");
	}

	public static void deleteById() {
		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "DELETE FROM Rooms WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer roomTypeId = resultSet.getInt("room_type_id");
				Integer hotelId = resultSet.getInt("hotel_id");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(
						id + " " + roomTypeId + " " + hotelId + " " + createdDate + " " + updatedDate + " " + isActive);
			}

		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateIsActive() {

		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Rows  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT TOP " + userInput + " id FROM Rooms";
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
			String sqlQueryToUpdate = "UPDATE Rooms SET is_active = 0 where id = " + i;
			System.out.println(sqlQueryToUpdate);
			try {
				statement = con.createStatement();
				int resultSet = statement.executeUpdate(sqlQueryToUpdate);
				System.out.println(resultSet);

//				closingConnection();
			} catch (Exception ex) {

				System.err.println(ex);
			}
		}

	}

}
