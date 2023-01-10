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

public class EmployeeType {

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

	public static void CreateEmployeeType() {

		String EmployeeTypetableCreationsql = "create table  EmployeeType (id integer PRIMARY KEY IDENTITY(1,1) ,employee_type_name VARCHAR (50) not null,"
				+ "created_date Date not null," + "updated_date Date," + "is_Active bit not null)";

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(EmployeeTypetableCreationsql);
			if (executing >= 0) {
				System.out.println("Created Successfully : " + EmployeeTypetableCreationsql);
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
		Random rn = new Random();
		for (int i = 0; i <= userInput; i++) {
			System.out.println(" Please Enter The Employee Type Name");
			String employeeTypeName = inputScanner.next();
			Date date = new Date(System.currentTimeMillis());

			String sqlQueryToInsert = "  INSERT INTO EmployeeType (employee_type_name,created_date,is_Active)"
					+ " VALUES ( '" + employeeTypeName + "','" + date + "', 1) ";

			try {
				Statement st = con.createStatement();
				int executing = st.executeUpdate(sqlQueryToInsert);
				if (executing >= 0) {
					System.out.println("Created Successfully : " + sqlQueryToInsert);
				} else {
					System.out.println("Creation Is Failed");
				}
			} catch (Exception ex) {

				System.err.println(ex);
			}
		}
	}

	public static void readFromTable() {
		System.out.println(" Please Enter The Number Of Rows To Be Shown ");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "SELECT TOP " + userInput + " * FROM EmployeeType";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String employeeTypeName = resultSet.getString("employee_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");

				System.out
						.println(id + " " + employeeTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
			}

		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void getById() {
		System.out.println(" Please Enter The ID Of The Row To Print");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();

		String sqlQueryToRead = "SELECT * FROM EmployeeType WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String employeeTypeName = resultSet.getString("employee_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");

				System.out
						.println(id + " " + employeeTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
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
		String sqlQueryToUpdate = "UPDATE EmployeeType SET employee_type_name = " + "'" + EmployeeType + "'" + ","
				+ " updated_date = " + "'" + date + "' WHERE id = " + userInput;
		System.out.println(sqlQueryToUpdate);
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToUpdate);
			System.out.println(resultSet);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String employeeTypeName = resultSet.getString("employee_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");

				System.out
						.println(id + " " + employeeTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}
	}

	public static void deleteById() {
		System.out.println(" Please Enter The ID To Delete The Row");
		Scanner inputScanner = new Scanner(System.in);
		int userInput = inputScanner.nextInt();
		String sqlQueryToRead = "DELETE FROM EmployeeType WHERE id =" + userInput;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQueryToRead);
			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String employeeTypeName = resultSet.getString("employee_type_name");
				Date createdDate = resultSet.getDate("created_date");
				Date updatedDate = resultSet.getDate("updated_date");
				Boolean isActive = resultSet.getBoolean("is_Active");

				System.out
						.println(id + " " + employeeTypeName + " " + createdDate + " " + updatedDate + " " + isActive);
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void updateIsActive() {

		Scanner inputScanner = new Scanner(System.in);
		System.out.println(" Please Enter The Number Of Rows  To Update Its Status");
		int userInput = inputScanner.nextInt();
		String sqlQueryToSelect = "SELECT TOP " + userInput + " id FROM EmployeeType";
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
			String sqlQueryToUpdate = "UPDATE EmployeeType SET is_active = 0 where id = " + i;
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
