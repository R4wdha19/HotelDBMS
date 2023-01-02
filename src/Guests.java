import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Guests {
	public static void GuestsTableCreation() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";

		String GueststableCreationsql = "create table  Guests (id integer PRIMARY KEY ,guest_name VARCHAR (50) not null,"
				+ "guest_phone VARCHAR (50) not null, guest_accompanying_members int  not null,"
				+ "guest_payment_amount int  not null,room_id int REFERENCES Rooms(id),"
				+ "hotel_id  int REFERENCES Hotels(id)," + "created_date Date not null," + "updated_date Date,"
				+ "is_Active bit not null)";

		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();
//				st.executeUpdate(tableCreationsql);
			// Executing query
			int Executing = st.executeUpdate(GueststableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + GueststableCreationsql);
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
