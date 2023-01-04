import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class RoomType {

	public static void RoomTypeTableCreation() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";

		String Room_TypetableCreationsql = "create table  Room_Type (id integer PRIMARY KEY,"
				+ "room_type_name VARCHAR (80) not null, created_date Date , " + "updated_date Date,"
				+ "is_Active bit not null )";

		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();
//				st.executeUpdate(tableCreationsql);
			// Executing query
			int Executing = st.executeUpdate(Room_TypetableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + Room_TypetableCreationsql);
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

	public static void insertIntoTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Added");
		Scanner sc1 = new Scanner(System.in);
		int userInput = sc1.nextInt();
		Random rn = new Random();
		for (int i = 0; i <= userInput; i++) {
			Integer numberOfRandomUserInput = rn.nextInt(userInput); // ()my limit
			Integer id = numberOfRandomUserInput;
			String room_type_name = "Rawdha" + numberOfRandomUserInput;
			Date date = new Date(System.currentTimeMillis());

			System.out.println(date);
			String sqlQueryToInsert = "  INSERT INTO Room_Type VALUES (" + id + ", '" + room_type_name + "'" + ", '"
					+ date + "','" + date + "', 1) ";

			System.out.println("This is the query: " + sqlQueryToInsert);

			String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

			String user = "sa";
			String pass = "root";
			Connection con = null;
			try {

				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

				DriverManager.registerDriver(driver);

				con = DriverManager.getConnection(url, user, pass);

				Statement st = con.createStatement();
				int Executing = st.executeUpdate(sqlQueryToInsert);
				if (Executing >= 0) {
					System.out.println("Created Successfully : " + sqlQueryToInsert);
				} else {
					System.out.println("Creation Is Failed");
				}

				con.close();
			}

			catch (Exception ex) {

				System.err.println(ex);
			}
		}
	}

	public static void readFromTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Showen ");
		Scanner sc1 = new Scanner(System.in);
		int userInput = sc1.nextInt();

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;
		String sqlQueryToRead = "SELECT * FROM Room_Type";
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			int count = 0;
			while (resultSet.next() && count <= userInput) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
				count++;
			}
			con.close();
		}

		catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void getById() {
		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner sc1 = new Scanner(System.in);
		int userInput = sc1.nextInt();

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;
		String sqlQueryToGet = "SELECT * FROM Room_Type WHERE id =" + userInput;
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToGet);
			int count = 0;
			while (resultSet.next() && count <= userInput) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
				count++;
			}

			con.close();
		}

		catch (Exception ex) {

			System.err.println(ex);
		}

	}

//	public static void updateById() {
//		System.out.println(" Please Enter The ID To Update Its Data");
//		Scanner sc1 = new Scanner(System.in);
//		int userInput = sc1.nextInt();
//		System.out.println(" Please Enter The New Room Type Name");
//		String roomTypeName = sc1.next();
//		Date date = new Date(System.currentTimeMillis());
//		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
//
//		String user = "sa";
//		String pass = "root";
//		Connection con = null;
//		String sqlQueryToUpdate = "UPDATE Room_Type SET room_type_name = " + "'" + roomTypeName + "',"
//				+ " updated_date = " + "'" + date + "' WHERE id =" + userInput;
//		System.out.println(sqlQueryToUpdate);
//		try {
//
//			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//
//			DriverManager.registerDriver(driver);
//
//			con = DriverManager.getConnection(url, user, pass);
//			Statement statement = con.createStatement();
//			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
//			System.out.println(resultSet);
//			int count = 0;
//			while (resultSet.next() && count <= userInput) {
//				Integer id = resultSet.getInt("id");
//				String roomType_Name = resultSet.getString("room_type_name");
//				Date createdDate = resultSet.getDate("created_date");
//				Date updatedDate = resultSet.getDate("updated_date");
//				Boolean isActive = resultSet.getBoolean("is_Active");
//				System.out.println(id + " " + roomType_Name + " " + createdDate + " " + updatedDate + " " + isActive);
//				count++;
//			}
//			con.close();
//		}
//
//		catch (Exception ex) {
//
//			System.err.println(ex);
//		}
//	}

	public static void deleteById() {
		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner sc1 = new Scanner(System.in);
		int userInput = sc1.nextInt();

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;
		String sqlQueryToRead = "DELETE FROM Room_Type WHERE id =" + userInput;
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			int count = 0;
			while (resultSet.next() && count <= userInput) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
				count++;
			}
			con.close();
		}

		catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateIsActive() {
		System.out.println(" Please Enter The Number Of Rows  To Updat Its Status");
		Scanner sc1 = new Scanner(System.in);
		int userInput = sc1.nextInt();
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;
		String sqlQueryToUpdate = "UPDATE Room_Type SET is_active = 0 where id ='" + userInput + "'";
		System.out.println(sqlQueryToUpdate);
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
			System.out.println(resultSet);
			int count = 0;
			while (resultSet.next() && count <= userInput) {
				Integer id = resultSet.getInt("id");
				String roomTypeName = resultSet.getString("room_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + roomTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
				count++;
			}
			con.close();
		}

		catch (Exception ex) {

			System.err.println(ex);
		}
	}

}
