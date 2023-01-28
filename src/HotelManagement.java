package src;

import java.sql.*;
import java.util.*;

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
            ResultSet executing = st.executeQuery(nameEndWithE);
            while (executing.next()) {
                String guestName = executing.getString("guest_name");
                System.out.println("Guests Are: " + guestName);
            }
        } catch (Exception ex) {

            System.err.println(ex);
        }

    }

//	Rooms where guests are paying more than 200

    // select room_id from guests where guest_payment_amount>200;
    public static void guestPayingMoreThan1000() {

        String guestPayingMoreThan1000 = "select room_id from guests where guest_payment_amount>200";

        try {
            Statement st = con.createStatement();
            ResultSet executing = st.executeQuery(guestPayingMoreThan1000);
            Set<Integer> id = new HashSet<>(); // we have to use Integer instead of int because  JDS
            // (java Data Structure ) works with Integer not int
            while (executing.next()) {
                int response = executing.getInt("room_id");

                id.add(response); // adding the ids to an array list because the query returns a list of ids
            }

            // select room_type_id from rooms where id = response ?
            StringBuffer sqlQueryToGetRooms = new StringBuffer("select room_type_id from rooms where id in ( ");
            for (Integer x : id) {
                sqlQueryToGetRooms.append(x + ",");

            }
            sqlQueryToGetRooms.deleteCharAt(sqlQueryToGetRooms.length() - 1);
            sqlQueryToGetRooms.append(")");
            System.out.println(sqlQueryToGetRooms);
            ResultSet executeQuery = st.executeQuery(sqlQueryToGetRooms.toString());

            Set<Integer> roomTypeIds = new HashSet<>();

            while (executeQuery.next()) {
                roomTypeIds.add(executeQuery.getInt("room_type_id"));
            }

            // select room_type_id from rooms where id = response ?
            StringBuffer sqlQueryToGetRoomTypeNames = new StringBuffer("SELECT id,room_type_name from room_type where id in (");
            for (Integer x : roomTypeIds) {
                sqlQueryToGetRoomTypeNames.append(x + ",");
            }
            sqlQueryToGetRoomTypeNames.deleteCharAt(sqlQueryToGetRoomTypeNames.length() - 1);
            sqlQueryToGetRoomTypeNames.append(")");
            System.out.println(sqlQueryToGetRoomTypeNames);
            ResultSet roomTypeData = st.executeQuery(sqlQueryToGetRoomTypeNames.toString());
            Map<Integer, String> dataMap = new HashMap<>(); // id= the key , room type name is the value ,
            // we used the hash map bcoz we want to keep them together
            while (roomTypeData.next()) {
                dataMap.put(roomTypeData.getInt("id"), roomTypeData.getString("room_type_name"));
                // (1, STANDARD)
            }

            for (Integer i : roomTypeIds) {
                System.out.println(i + ": " + dataMap.get(i)); // get by id
            }

        } catch (Exception ex) {

            System.err.println(ex);
        }


    }

    //	Count of guests who are staying in 'DELUXE' rooms
    public static void GuestStayingInSpecificRoomType() {

        String guestsStayingInDeluxe = "  select * from Guests g inner join Rooms r on g.room_id = r.id where r.room_type_id " +
                " in (  select id from Room_Type where room_type_name = 'DELUXE' );";


        try {
            Statement st = con.createStatement();
            ResultSet executing = st.executeQuery(guestsStayingInDeluxe);
            while (executing.next()) {
                String guestNames = executing.getString("guest_name");
                System.out.println("The Guests Are : " + guestNames);
            }
        } catch (Exception ex) {

            System.err.println(ex);
        }

    }

    //	Guests who are staying in rooms and served by employee who have 'A' in their name
    public static void GuestServedByEmployee() {

        String guestServedByEmployee = "select g.guest_name,e.employee_name " +
                "from Guests g inner join Employees e on g.room_id = e.room_id where e.employee_name like '%A%'";

        try {
            Statement st = con.createStatement();
            ResultSet executing = st.executeQuery(guestServedByEmployee);
            while (executing.next()) {
                String guestName = executing.getString("employee_name");
                System.out.println(" Guest Served By Employee  : " + guestName);
            }
        } catch (Exception ex) {

            System.err.println(ex);
        }

    }

    //	All rooms which are not active but room type is 'Deluxe'
    public static void RoomsIsActiveStatusAndSpecificRoomType() {

        String roomsIsActiveStatusAndSpecificRoomType = "select * from Rooms r inner join Room_type rt" +
                " on r.room_type_id = rt.id where r.is_Active = 0 and rt.room_type_name ='DELUXE'";

        try {
            Statement st = con.createStatement();
            ResultSet executing = st.executeQuery(roomsIsActiveStatusAndSpecificRoomType);
            while (executing.next()) {
                String roomType = executing.getString("room_type_name");
                System.out.println("The room that is not active and is deluxe is : " + roomType);
            }
        } catch (Exception ex) {

            System.err.println(ex);
        }

    }

//	All room type in hotels who's name have 'H' or are active but have more than 5 rooms.

    public static void hotelsNameWithSpecificRoomNumber() {
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String hotelsNameWithSpecificRoomNumber = " select r.hotel_id from Hotels h left join Rooms r" +
                " on h.id=r.hotel_id where( h.hotel_name like '%H%' OR h.is_Active =1)" +
                " group by (r.hotel_id)" +
                " having count(r.hotel_id)>0;";

        System.out.println(hotelsNameWithSpecificRoomNumber);
        try {
            Statement st = con.createStatement();
            ResultSet executing = st.executeQuery(hotelsNameWithSpecificRoomNumber);
            while (executing.next()) {
                int hotelId = executing.getInt("hotel_id");
                listOfIds.add(hotelId);

                System.out.println("The Hotel Ids is : " + hotelsNameWithSpecificRoomNumber);
            }
        } catch (Exception ex) {

            System.err.println(ex);
        }
        StringBuffer getRoomType = new StringBuffer("select rt.room_type_name from room_type rt inner join rooms r " +
                "on rt.id= r.room_type_id where r.hotel_id in (");
        for (Integer i : listOfIds) {
            getRoomType.append(i + ",");
        }
        getRoomType.deleteCharAt(getRoomType.length() - 1);
        getRoomType.append(")");
        System.out.println(getRoomType);
        try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(getRoomType.toString());
            while (rs.next()){
                String roomType = rs.getString("room_type_name");
                System.out.println("Room Type Is:"+roomType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
