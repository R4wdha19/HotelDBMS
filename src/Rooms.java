package src;

import java.sql.*;
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
		Random rn = new Random();
		for (int i = 0; i <= userInput; i++) {
			Integer numberOfRandomUserInput = rn.nextInt(userInput); // ()my limit


			Scanner scanner=new Scanner(System.in);
			System.out.println(" Which Room Type Do You Want ? " +
					" 1 : STANDARD " +
					" 2 : DELUXE" +
					" 3 : SINGLE");
			int roomType=scanner.nextInt();
			String roomTypeName = "";
			if (roomType> 0 && roomType<4){
				if (roomType==1){
					roomTypeName = "STANDARD";
				}
				else if (roomType==2){
					roomTypeName = "DELUXE";
				}
				else{
					roomTypeName = "SINGLE";
				}

			}
			String sqlQueryToGetId="SELECT id From Room_Type WHERE room_type_name =" +" '"+ roomTypeName +" '" ;

			Statement st = null;
			Integer id = 1;
			// My options start with 1 therefore we did 1 if i make it 0 it will rise an error and it will by default 0 but that is not an option.
//			// So we make a choice that when there is an error the assigned roomTypeName is STANDARD by default

			try {
				st = con.createStatement();
				ResultSet resultSet= st.executeQuery(sqlQueryToGetId);
				while (resultSet.next()){
					id = resultSet.getInt("id");
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}





			String hotelName = "Rawdha" + numberOfRandomUserInput;
			String hotelLocation = "Rawdha" + numberOfRandomUserInput;
			Date date = new Date(System.currentTimeMillis());

			System.out.println(date);
			String sqlQueryToInsert = "  INSERT INTO Rooms (room_type_id,hotel_id ,created_date,updated_date,is_Active)"
					+ " VALUES ( ? , ? ,  " +'" + date+ ) ";

			System.out.println("This is the query: " + sqlQueryToInsert);

			try {
				Statement st = con.createStatement();
				int executing = st.executeUpdate(sqlQueryToInsert);
				if (executing >= 0) {
					System.out.println("Created Successfully : " + sqlQueryToInsert);
				} else {
					System.out.println("Creation Is Failed");
				}

//                closingConnection();
			} catch (Exception ex) {

				System.err.println(ex);
			}
		}
	}

	public static void readFromTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Shown ");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT TOP " + userInput + " * FROM hotels";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String hotelName = resultSet.getString("hotel_name");
				String hotelLocation = resultSet.getString("hotel_location");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotelName + " " + hotelLocation + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}
//            closingConnection();
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void getById() {
		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();

		String sqlQueryToRead = "SELECT * FROM hotels WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
//            int count = 0; we can not use count here because it will limit my results
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String hotelName = resultSet.getString("hotel_name");
				String hotelLocation = resultSet.getString("hotel_location");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotelName + " " + hotelLocation + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}
//            closingConnection();
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateById() {
		System.out.println(" Please Enter The ID To Update Its Data");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		System.out.println(" Please Enter The New Hotel Name");
		String hotelName = inputScanner.next();
		System.out.println(" Please Enter The New Hotel Location");
		String hotelLocation = inputScanner.next();
		Date date = new Date(System.currentTimeMillis());
		String sqlQueryToUpdate = "UPDATE HOTELS SET hotel_name = " + "'" + hotelName + "'" + ", hotel_location = "
				+ "'" + hotelLocation + "'," + " updated_date = " + "'" + date + "' WHERE id =" + userInput;
		System.out.println(sqlQueryToUpdate);
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
			System.out.println(resultSet);

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String hotel_Name = resultSet.getString("hotel_name");
				String hotel_Location = resultSet.getString("hotel_location");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotel_Name + " " + hotel_Location + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}
//            closingConnection();
		} catch (Exception ex) {

			System.err.println(ex);
		}
	}

	public static void deleteById() {
		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "DELETE FROM Hotels WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String hotelName = resultSet.getString("hotel_name");
				String hotelLocation = resultSet.getString("hotel_location");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotelName + " " + hotelLocation + " " + createdDate + " " + updatedDate
						+ " " + isActive);
			}
//            closingConnection();
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateIsActive() {

		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Rows  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT TOP " + userInput + " id FROM Hotels";
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
			String sqlQueryToUpdate = "UPDATE HOTELS SET is_active = 0 where id = " + i;
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
	public static void RoomTableCreation() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";


		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();
//				st.executeUpdate(tableCreationsql);
			// Executing query
			int Executing = st.executeUpdate(RoomtableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + RoomtableCreationsql);
			} else {
				System.out.println("Creation Is Failed");
			}

			// Closing the connections
			con.close();
		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}
}
