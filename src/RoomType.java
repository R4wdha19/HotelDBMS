import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

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

}
