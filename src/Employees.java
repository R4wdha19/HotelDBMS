import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Employees {
	public static void EmployeesTableCreation() {

		String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";

		String user = "sa";
		String pass = "root";

		String EmployeestableCreationsql = "create table  Employees (id integer PRIMARY KEY ,"
				+ "employee_type_id int REFERENCES EmployeeType(id)," + "room_id int REFERENCES Hotels(id),"
				+ "created_date Date not null," + "updated_date Date," + "is_Active bit not null)";

		Connection con = null;

		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);

			Statement st = con.createStatement();
//				st.executeUpdate(tableCreationsql);
			// Executing query
			int Executing = st.executeUpdate(EmployeestableCreationsql);
			if (Executing >= 0) {
				System.out.println("Created Successfully : " + EmployeestableCreationsql);
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
