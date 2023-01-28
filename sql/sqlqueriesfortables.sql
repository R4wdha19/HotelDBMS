

SELECT [id]
      ,[hotel_name]
      ,[hotel_location]
      ,[created_date]
      ,[updated_date]
      ,[is_Active]
  FROM [dbo].[Hotels]

 INSERT INTO rooms (id,room_type_id, hotel_id, created_date, is_Active) values (1, 1, 1, GETDATE(), 1)

 SET IDENTITY_INSERT Rooms ON


  SELECT [id]
      ,[room_type_name]
      ,[created_date]
      ,[updated_date]
      ,[is_Active]
  FROM [dbo].[Room_Type]


  SELECT [id]
      ,[room_type_id]
      ,[hotel_id]
      ,[created_date]
      ,[updated_date]
      ,[is_Active]
  FROM [dbo].[Rooms]


  SELECT [id]
      ,[guest_name]
      ,[guest_phone]
      ,[guest_accompanying_members]
      ,[guest_payment_amount]
      ,[room_id]
      ,[hotel_id]
      ,[created_date]
      ,[updated_date]
      ,[is_Active]
  FROM [dbo].[Guests]