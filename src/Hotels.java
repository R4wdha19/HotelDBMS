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

public class Hotels {
	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
	// static variables to be used from static function so it stays in static
	// context
//   " final " it can not change or update
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

	public static void CreateHotelsTable() {

		String hotelTableCreationSql = "create table  Hotels (id integer PRIMARY KEY IDENTITY(1,1) , "
				+ "hotel_name VARCHAR (50) not null ,hotel_location VARCHAR (80), "
				+ "created_date Date not null, updated_date Date , is_Active bit not null)";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(hotelTableCreationSql);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + hotelTableCreationSql);
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
			String hotelName = "Rawdha" + numberOfRandomUserInput;
			String hotelLocation = "Rawdha" + numberOfRandomUserInput;
			Date date = new Date(System.currentTimeMillis());

			System.out.println(date);
			String sqlQueryToInsert = "  INSERT INTO Hotels (hotel_Name,hotel_Location,created_date,is_Active)"
					+ " VALUES ( '" + hotelName + "'," + " ' " + hotelLocation + "','" + date + "'" + ", 1) ";

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

}
