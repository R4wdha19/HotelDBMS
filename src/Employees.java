package src;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Employees {

	public class Employee {

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

		public static void closingConnection() {
			try {
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		public static void CreateEmployeesTable() {

			String EmployeestableCreationsql = "create table  Employees (id integer PRIMARY KEY IDENTITY(1,1),"
					+ "employee_type_id int REFERENCES EmployeeType(id)," + "room_id int REFERENCES Rooms(id),"
					+ "created_date Date not null," + "updated_date Date," + "is_Active bit not null)";

			try {
				Statement st = con.createStatement();
				int executing = st.executeUpdate(EmployeestableCreationsql);
				if (executing >= 0) {
					System.out.println("Created Successfully : " + EmployeestableCreationsql);
				} else {
					System.out.println("Creation Is Failed");
				}
			} catch (Exception ex) {

				System.err.println(ex);
			}

		}

		public static void insertIntoTable() {

			System.out.println(" Please Enter The Number Of Rows To Be Added");
			Scanner inputScanner = new Scanner(System.in);
			int userInput = inputScanner.nextInt();
			Date date = new Date(System.currentTimeMillis());
			Random rn = new Random();
			Statement st = null;
			Integer idForEmployeeType = 1;
			Integer idForRoomType = 1;
			Integer idForHotel = 1;
			Integer idForRoom = 1;
			for (int i = 0; i <= userInput; i++) {
				Integer numberOfRandomUserInput = rn.nextInt(userInput); // ()my limit

				Scanner scanner = new Scanner(System.in);
				System.out.println(" Which Employee Type Do You Want ? " + " 1 : MANAGER " + " 2 : ATTENDANT"
						+ " 3 : VALET" + " 4 : BUTLER" + " 5 : DIRECTOR");
				int EmployeeType = scanner.nextInt();
				String EmployeeTypeName = "";
				if (EmployeeType > 0 && EmployeeType < 6) {
					if (idForEmployeeType == 1) {
						EmployeeTypeName = "MANAGER";
					} else if (EmployeeType == 2) {
						EmployeeTypeName = "ATTENDANT";
					} else if (EmployeeType == 3) {
						EmployeeTypeName = "VALET";
					} else if (EmployeeType == 4) {
						EmployeeTypeName = "BUTLER";
					} else {
						EmployeeTypeName = "DIRECTOR";
					}

				}
				String sqlQueryToGetEmployeeTypeId = "SELECT id From EmployeeType WHERE employee_type_name =" + " '"
						+ EmployeeTypeName + " '";
				try {
					st = con.createStatement();
					ResultSet resultSet = st.executeQuery(sqlQueryToGetEmployeeTypeId);
					while (resultSet.next()) {
						idForEmployeeType = resultSet.getInt("id");
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				System.out
						.println(" Which Room Type Do You Want ? " + " 1 : STANDARD " + " 3 : DELUXE" + " 4 : SINGLE");
				int roomType = scanner.nextInt();
				String roomTypeName = "";
				if (roomType > 0 && roomType < 6) {
					if (roomType == 1) {
						roomTypeName = "STANDARD";
					} else if (roomType == 3) {
						roomTypeName = "DELUXE";
					} else {
						roomTypeName = "SINGLE";
					}

				}

				String sqlQueryToGetRoomTypeId = "SELECT id From Room_Type WHERE room_type_name =" + " '" + roomTypeName
						+ " '";
				try {
					st = con.createStatement();
					ResultSet resultSet = st.executeQuery(sqlQueryToGetRoomTypeId);
					while (resultSet.next()) {
						idForRoomType = resultSet.getInt("id");
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				System.out.println(
						" Which Hotel Do You Want ? " + " 1 : Four Seasons " + " 2 : Hyatt" + " 3 : Hollywood ");
				int hotelId = scanner.nextInt();
				String hotelName = "";
				if (hotelId > 0 && hotelId < 4) {
					if (hotelId == 1) {
						hotelName = "Four Seasons";
					} else if (hotelId == 2) {
						hotelName = "Hyatt";
					} else {
						hotelName = "Hollywood";
					}

				}
				String sqlQueryToGetHotelId = "SELECT id From Hotels WHERE hotel_name =" + " '" + hotelName + " '";
				try {
					st = con.createStatement();
					ResultSet resultSet = st.executeQuery(sqlQueryToGetHotelId);
					while (resultSet.next()) {
						idForHotel = resultSet.getInt("id");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				String sqlQueryToGetRoomlId = "SELECT id From Rooms WHERE hotel_id =" + idForHotel
						+ " AND room_type_id = " + idForRoomType;
				try {
					st = con.createStatement();
					ResultSet resultSet = st.executeQuery(sqlQueryToGetHotelId);
					while (resultSet.next()) {
						idForRoom = resultSet.getInt("id");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				String sqlQueryToInsert = "INSERT INTO Employees (employee_type_id,room_id ,created_date,is_Active)"
						+ "Values (" + idForEmployeeType + "," + idForRoom + ",'" + date + "'," + 1 + ")";

				System.out.println("This is the query to insert into Employees table: " + sqlQueryToInsert);
				try {
					st = con.createStatement();
					ResultSet e = st.executeQuery(sqlQueryToInsert);

				} catch (SQLException e1) {
					System.out.println(e1.getLocalizedMessage());
				}
			}
		}

		public static void readFromTable() {
			System.out.println(" Please Enter The Number Of Rows To Be Shown ");
			Scanner inputScanner = new Scanner(System.in);
			int userInput = inputScanner.nextInt();
			String sqlQueryToRead = "SELECT TOP " + userInput + " * FROM Employees";
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
				while (resultSet.next()) {
					Integer id = resultSet.getInt("id");
					Integer employeeTypeId = resultSet.getInt("employee_type_id");
					Integer roomId = resultSet.getInt("room_id");
					Date createdDate = resultSet.getDate("created_date");
					Date updatedDate = resultSet.getDate("updated_date");
					Boolean isActive = resultSet.getBoolean("is_Active");

					System.out.println(id + " " + employeeTypeId + " " + roomId + " " + createdDate + " " + updatedDate
							+ " " + isActive);
				}

			} catch (Exception ex) {

				System.err.println(ex);
			}

		}

		public static void getById() {
			System.out.println(" Please Enter The ID Of The Row To Print");
			Scanner inputScanner = new Scanner(System.in);
			int userInput = inputScanner.nextInt();

			String sqlQueryToRead = "SELECT * FROM Employees WHERE id =" + userInput;
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
				while (resultSet.next()) {
					Integer id = resultSet.getInt("id");
					Integer employeeTypeId = resultSet.getInt("employee_type_id");
					Integer roomId = resultSet.getInt("room_id");
					Date createdDate = resultSet.getDate("created_date");
					Date updatedDate = resultSet.getDate("updated_date");
					Boolean isActive = resultSet.getBoolean("is_Active");

					System.out.println(id + " " + employeeTypeId + " " + roomId + " " + createdDate + " " + updatedDate
							+ " " + isActive);
				}

			} catch (Exception ex) {

				System.err.println(ex);
			}

		}

		public static void updateById() {
			System.out.println(" Please Enter The ID To Update Its Data");
			Scanner inputScanner = new Scanner(System.in);
			int userInput = inputScanner.nextInt();
			System.out.println(" Please Enter The New Employee Type");
			String EmployeeType = inputScanner.next();

			Date date = new Date(System.currentTimeMillis());
			String sqlQueryToUpdate = "UPDATE Employees SET employee_type_name = " + "'" + EmployeeType + "'" + ","
					+ " updated_date = " + "'" + date + "' WHERE id = " + userInput;
			System.out.println(sqlQueryToUpdate);
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
				System.out.println(resultSet);
				while (resultSet.next()) {
					Integer id = resultSet.getInt("id");
					Integer employeeTypeId = resultSet.getInt("employee_type_id");
					Integer roomId = resultSet.getInt("room_id");
					Date createdDate = resultSet.getDate("created_date");
					Date updatedDate = resultSet.getDate("updated_date");
					Boolean isActive = resultSet.getBoolean("is_Active");

					System.out.println(id + " " + employeeTypeId + " " + roomId + " " + createdDate + " " + updatedDate
							+ " " + isActive);
				}
			} catch (Exception ex) {

				System.err.println(ex);
			}
		}

		public static void deleteById() {
			System.out.println(" Please Enter The ID To Delete The Row");
			Scanner inputScanner = new Scanner(System.in);
			int userInput = inputScanner.nextInt();
			String sqlQueryToRead = "DELETE FROM Employees WHERE id =" + userInput;
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
				while (resultSet.next()) {
					Integer id = resultSet.getInt("id");
					Integer employeeTypeId = resultSet.getInt("employee_type_id");
					Integer roomId = resultSet.getInt("room_id");
					Date createdDate = resultSet.getDate("created_date");
					Date updatedDate = resultSet.getDate("updated_date");
					Boolean isActive = resultSet.getBoolean("is_Active");

					System.out.println(id + " " + employeeTypeId + " " + roomId + " " + createdDate + " " + updatedDate
							+ " " + isActive);
				}
			} catch (Exception ex) {

				System.err.println(ex);
			}

		}

		public static void updateIsActive() {
			Scanner inputScanner = new Scanner(System.in);
			System.out.println(" Please Enter The Number Of Rows  To Update Its Status");
			int userInput = inputScanner.nextInt();
			String sqlQueryToSelect = "SELECT TOP " + userInput + " id FROM Employees";
			Statement statement;
			List<Integer> listOfIds = new ArrayList<>();
			try {
				System.out.println(sqlQueryToSelect);
				statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQueryToSelect);

				while (resultSet.next()) {

					listOfIds.add(resultSet.getInt(1));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (Integer i : listOfIds) {
				System.out.println("Id is: " + i);
				String sqlQueryToUpdate = "UPDATE Employees SET is_active = 0 where id = " + i;
				System.out.println(sqlQueryToUpdate);
				try {
					statement = con.createStatement();
					int resultSet = statement.executeUpdate(sqlQueryToUpdate);
					System.out.println(resultSet);

				} catch (Exception ex) {

					System.err.println(ex);
				}
			}

		}

	}
}
