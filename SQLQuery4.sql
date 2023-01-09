USE [HotelDBMS]
GO

SELECT [id]
      ,[hotel_name]
      ,[hotel_location]
      ,[created_date]
      ,[updated_date]
      ,[is_Active]
  FROM [dbo].[Hotels] order by id desc

GO


 INSERT INTO Hotels (hotel_Name,hotel_Location,created_date,is_Active) VALUES ( 'Rawdha0', ' Rawdha0','2023-01-08', 1);


