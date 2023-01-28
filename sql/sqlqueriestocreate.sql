SELECT * FROM Room_Type;
SELECT * FROM Hotels ;
SELECT * FROM Rooms;
INSERT INTO Room_Type (room_type_name, created_date, is_Active) VALUES 
('STANDARD', getdate(), 1),('DELUXE', getdate(), 1),('SINGLE', getdate(), 1)


create table  Hotels (id integer PRIMARY KEY IDENTITY(1,1) , hotel_name VARCHAR (50) not null ,hotel_location VARCHAR (80), 
	created_date Date not null, updated_date Date , is_Active bit not null);
	
	INSERT INTO Hotels (hotel_name,hotel_location,created_date,is_Active)
	Values ('R','B',GETDATE(),1),('z','a',GETDATE(),1),('w','r',GETDATE(),1);



	create table  Room_Type (id integer PRIMARY KEY IDENTITY(1,1),room_type_name VARCHAR (80) not null, created_date Date ,updated_date Date,
		is_Active bit not null );
		create table  Rooms (id integer PRIMARY KEY IDENTITY(1,1) ,
		room_type_id int REFERENCES Room_Type(id),hotel_id int REFERENCES Hotels(id),
		created_date Date not null,updated_date Date, 
				is_Active bit not null);

				SELECT id FROM Room_Type  WHERE room_type_name = 'SINGLE';