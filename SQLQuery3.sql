USE [HotelDBMS]
GO

SELECT [id]
      ,[room_type_id]
      ,[hotel_id]
      ,[created_date]
      ,[updated_date]
      ,[is_Active]
  FROM [dbo].[Rooms]order by id desc

GO


