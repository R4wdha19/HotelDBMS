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

public class RoomType {

	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

	public static final String user = "sa";
	public static final String pass = "root";
	public static Connection con = null;

	public static Date date = new Date(System.currentTimeMillis());
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

	public static void RoomTypeTableCreation() {

		String roomTypeTableCreationSql = "create table  Room_Type (id integer PRIMARY KEY IDENTITY(1,1),"
				+ "room_type_name VARCHAR (80) not null, created_date Date , " + "updated_date Date,"
				+ "is_Active bit not null )";
		try {

			Statement st = con.createStatement();
			int executing = st.executeUpdate(roomTypeTableCreationSql);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + roomTypeTableCreationSql);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void insertIntoTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Added");
		Scanner sc1 = new Scanner(System.in);
		int userInput = sc1.nextInt();
		Random rn = new Random();
		for (int i = 0; i < userInput; i++) {
			Integer numberOfRandomUserInput = rn.nextInt(userInput); // ()my limit
			String roomTypeNameOne = "STANDARD";
			String roomTypeNameTwo = "DELUXE";
			String roomTypeNameThree = "SINGLE";
			String sqlQueryToInsert = "  INSERT INTO Room_Type (room_type_name,created_date,is_Active)" +
					" VALUES ( '" + roomTypeNameOne + "'" + ",'" + date + "', 1) ," +
					"( '" +roomTypeNameTwo +" '" +", ' "+ date + "', 1) ," +
					"( '"+roomTypeNameThree +" '" +",' "+ date + "', 1) ";

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
		System.out.println(" Please Enter The Number Of Rows To Be Showen ");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT TOP " + userInput + " * FROM Room_Type";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void getById() {
		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();

		String sqlQueryToGet = "SELECT * FROM Room_Type WHERE id =" + userInput;
		try {

			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToGet);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		}

		catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateById() {
		System.out.println(" Please Enter The ID To Update Its Data");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		System.out.println(" Please Enter The New Room Type Name");
		String roomTypeName = inputScanner.next();
		String sqlQueryToUpdate = "UPDATE Room_Type SET room_type_name = " + "'" + roomTypeName + "',"
				+ " updated_date = " + "'" + date + "' WHERE id =" + userInput;
		System.out.println(sqlQueryToUpdate);
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
			System.out.println(resultSet);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String roomType_Name = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomType_Name + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		}

		catch (Exception ex) {

			System.err.println(ex);
		}
	}

	public static void deleteById() {
		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "DELETE FROM Room_Type WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public static void updateIsActive() {

		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Rows  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT TOP " + userInput + " id FROM Room_Type";
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
			String sqlQueryToUpdate = "UPDATE Room_Type SET is_active = 0 where id = " + i;
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
