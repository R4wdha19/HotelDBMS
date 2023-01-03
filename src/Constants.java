import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class Constants {
	public static void MyConstants() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();
			con.close();
		} catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

	public static void InsertIntoTable(int numOfRows) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";
		Connection con = null;

		String sql = "INSERT INTO Hotels (id, hotel_name, hotel_location,created_date,updated_date,is_Active) VALUES (?, ?, ?,?, ?, ?)";

		PreparedStatement pstmt = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);
			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < numOfRows; i++) {
				Random rn = new Random();
				Integer number = rn.nextInt(100);
				String name = "Rawdh" + number;
				boolean boolToAdd = true;
				pstmt.setInt(1, number);
				pstmt.setString(2, name);
				pstmt.setString(3, name);
				pstmt.setDate(4, new Date(System.currentTimeMillis()));
				pstmt.setDate(5, new Date(System.currentTimeMillis()));
				pstmt.setBoolean(6, boolToAdd);
				pstmt.executeUpdate();
			}

			System.out.println(numOfRows + " rows inserted successfully!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
