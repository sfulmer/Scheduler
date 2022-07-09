package net.draconia.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import net.draconia.schedule.beans.Event;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractDAO<Event> implements EventDAO
{
	@Autowired
	public EventDAOImpl(final DataSource ds)
	{
		super(ds);
	}
	
	protected List<Event> createListFromResults(final ResultSet rs) throws SQLException
	{
		List<Event> lst = new ArrayList<Event>();
		
		while(rs.next())
			lst.add(createObjectFromResult(rs));
		
		return(lst);
	}
	
	protected Event createObjectFromResult(final ResultSet rs) throws SQLException
	{
		Boolean bAllDay;
		Date dtEnd, dtStart;
		Long lId;
		String sDescription, sTitle;
		
		bAllDay = rs.getBoolean("AllDay");
		
		if(rs.wasNull())
			bAllDay = null;
		
		sDescription = rs.getString("Description");
		
		if(rs.wasNull())
			sDescription = null;
		
		dtEnd = rs.getDate("End");
		
		if(rs.wasNull())
			dtEnd = null;
		
		lId = rs.getLong("Id");
		
		if(rs.wasNull())
			lId = null;
		
		dtStart = rs.getDate("Start");
		
		if(rs.wasNull())
			dtStart = null;
		
		sTitle = rs.getString("Title");
		
		if(rs.wasNull())
			sTitle = null;
		
		return(new Event(lId, sTitle, dtStart, dtEnd, bAllDay, sDescription));
	}
	
	protected void createTable(String sSchema, String sTable) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("CREATE TABLE `schedule`.`events` (Id INT NOT NULL AUTO_INCREMENT, Title VARCHAR(50) NULL DEFAULT ' ', Description VARCHAR(1000) NOT NULL DEFAULT ' ', Start DATETIME NOT NULL, End DATETIME NOT NULL, All_Day TINYINT NULL DEFAULT 1, PRIMARY KEY (`id`));");
			
			stmt.executeUpdate();
			}
		finally
			{
			closeConnection();
			}
	}
	
	public Event getEventById(final long lId) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, Title, Start, End, All_Day, Description from Events where Id = ?;");
			ResultSet rs = null;
			
			stmt.setLong(1, lId);
			
			rs = stmt.executeQuery();
			
			if(rs.next())
				return(createObjectFromResult(rs));
			else
				return(null);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected String getTableName()
	{
		return("Events");
	}
	
	protected Event insert(final Event objToInsert) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into " + getTableName() + " (Title, Start, End, AllDay, Description) values(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, objToInsert.getTitle());
			stmt.setDate(2, new java.sql.Date(objToInsert.getStart().getTime()));
			stmt.setDate(3, new java.sql.Date(objToInsert.getEnd().getTime()));
			stmt.setBoolean(4, objToInsert.isAllDay());
			stmt.setString(5, objToInsert.getDescription());
			
			if(stmt.executeUpdate() == 1)
				{
				ResultSet rs = stmt.getGeneratedKeys();
				
				if(rs.next())
					objToInsert.setId(rs.getLong(1));
				
				return(objToInsert);
				}
			else
				return(objToInsert);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected Event update(final Event objToUpdate) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("update " + getTableName() + " set Title = ?, Start = ?, End = ?, All_Day = ?, Description = ? where Id = ?;");
			
			stmt.setString(1, objToUpdate.getTitle());
			stmt.setDate(2, new java.sql.Date(objToUpdate.getStart().getTime()));
			stmt.setDate(3, new java.sql.Date(objToUpdate.getEnd().getTime()));
			stmt.setBoolean(4, objToUpdate.isAllDay());
			stmt.setString(5, objToUpdate.getDescription());
			
			stmt.executeUpdate();
			
			return(objToUpdate);
			}
		finally
			{
			closeConnection();
			}
	}
}