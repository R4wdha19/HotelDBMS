import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Hotels {
	Scanner sc = new Scanner(System.in);

	public static void CreateHotelsTable() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;
		String hoteltableCreationsql = "create table  Hotels (id integer PRIMARY KEY, "
				+ "hotel_name VARCHAR (50) not null ,hotel_location VARCHAR (80), "
				+ "created_date Date not null, updated_date Date , is_Active bit not null)";

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();

			int Executing = st.executeUpdate(hoteltableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + hoteltableCreationsql);
			} else {
				System.out.println("Creation Is Failed");
			}

			con.close();
		}

		catch (Exception ex) {

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
			String hotelName = "Rawdha" + numberOfRandomUserInput;
			String hotelLocation = "Rawdha" + numberOfRandomUserInput;

			Date date = new Date(System.currentTimeMillis());

			System.out.println(date);
			String sqlQueryToInsert = "  INSERT INTO Hotels VALUES (" + id + ", '" + hotelName + "'," + " ' "
					+ hotelLocation + "'   " + ", '" + date + "','" + date + "', 1) ";

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
		String sqlQueryToRead = "SELECT * FROM hotels";
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			int count = 0;
			while (resultSet.next() && count <= userInput) {
				Integer id = resultSet.getInt("id");
				String hotelName = resultSet.getString("hotel_name");
				String hotelLocation = resultSet.getString("hotel_location");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotelName + " " + hotelLocation + " " + createdDate + " " + updatedDate
						+ " " + isActive);
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
		String sqlQueryToRead = "SELECT * FROM hotels WHERE id =" + userInput;
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			int count = 0;
			while (resultSet.next() && count <= userInput) {
				Integer id = resultSet.getInt("id");
				String hotelName = resultSet.getString("hotel_name");
				String hotelLocation = resultSet.getString("hotel_location");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");
				System.out.println(id + " " + hotelName + " " + hotelLocation + " " + createdDate + " " + updatedDate
						+ " " + isActive);
				count++;
			}
			con.close();
		}

		catch (Exception ex) {

			System.err.println(ex);
		}

	}

}
