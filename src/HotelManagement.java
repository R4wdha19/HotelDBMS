package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HotelManagement {

	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelDBMS;encrypt=true;trustServerCertificate=true";
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

		String nameEndWithE = " SELECT * FROM GUESTS WHERE guest_name LIKE 'E%'";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(nameEndWithE);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + nameEndWithE);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

//	Rooms where guests are paying more than 1000
	public static void GuestpayingMoreThan1000() {

		String GuestpayingMoreThan1000 = " SELECT * FROM GUESTS WHERE guest_name LIKE 'E%'";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(GuestpayingMoreThan1000);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + GuestpayingMoreThan1000);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

//	Count of guests who are staying in 'DELUXE' rooms
	public static void GuestStayingInSpecificRoomType() {

		String GuestpayingMoreThan1000 = " SELECT * FROM GUESTS WHERE guest_name LIKE 'E%'";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(GuestpayingMoreThan1000);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + GuestpayingMoreThan1000);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

//	Guests who are staying in rooms and served by employee who have 'A' in their name
	public static void GuestServedByEmployee() {

		String GuestpayingMoreThan1000 = " SELECT * FROM GUESTS WHERE guest_name LIKE 'E%'";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(GuestpayingMoreThan1000);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + GuestpayingMoreThan1000);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

//	All rooms which are not active but room type is 'Deluxe'
	public static void RoomsIsActiveStatusAndSpecificRoomType() {

		String GuestpayingMoreThan1000 = " SELECT * FROM GUESTS WHERE guest_name LIKE 'E%'";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(GuestpayingMoreThan1000);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + GuestpayingMoreThan1000);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

//	All room type in hotels who's name have 'H' or are active but have more than 5 rooms.

	public static void hotelsNameWithSpecificRoomNumber() {

		String GuestpayingMoreThan1000 = " SELECT * FROM GUESTS WHERE guest_name LIKE 'E%'";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(GuestpayingMoreThan1000);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + GuestpayingMoreThan1000);
			} else {
				System.out.println("Creation Is Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}
}
