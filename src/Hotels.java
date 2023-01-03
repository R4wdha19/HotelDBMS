import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Hotels {
	Scanner sc = new Scanner(System.in);

	public static void CreateHotelsTable() {
//		Constants.MyConstants();
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
//			st.executeUpdate(tableCreationsql);
			// Executing query
			int Executing = st.executeUpdate(hoteltableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + hoteltableCreationsql);
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
		// Integer NumberOfRandomUserInput = rn.nextInt(userInput); // ()my limit
		for (int i = 0; i <= userInput; i++) {
			Integer numberOfRandomUserInput = rn.nextInt(userInput); // ()my limit
			Integer id = numberOfRandomUserInput;
			String hotelName = "Rawdha" + numberOfRandomUserInput;
			String hotelLocation = "Rawdha" + numberOfRandomUserInput;

//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//			boolean is_active=true;
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
//				st.executeUpdate(tableCreationsql);
				// Executing query
				int Executing = st.executeUpdate(sqlQueryToInsert);
				if (Executing >= 0) {
					System.out.println("Created Successfully : " + sqlQueryToInsert);
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

}
