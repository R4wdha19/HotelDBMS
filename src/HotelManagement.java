package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HotelManagement {

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

//	Guests who's name end with 'E'

	public static void GuestNameEndWithLetter() {

	}
//	SELECT * WHERE Guest_name Like %E%;
//	Rooms where guests are paying more than 1000
//	Count of guests who are staing in 'DELUXE' rooms
//	Guests who are staing in rooms and served by employee who have 'A' in their name
//	All rooms which are not active but room type is 'Deluxe'
//	All room type in hotels who's name have 'H' or are active but have more than 5 rooms.
}
