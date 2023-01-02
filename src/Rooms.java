import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Rooms {
	public static void RoomTableCreation() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";

		String RoomtableCreationsql = "create table  Rooms (id integer PRIMARY KEY ,room_type_id int REFERENCES Room_Type(id),"
				+ "hotel_id int REFERENCES Hotels(id),created_date Date not null,updated_date Date, "
				+ "is_Active bit not null)";

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
