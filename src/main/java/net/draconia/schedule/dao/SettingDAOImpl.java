package net.draconia.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.draconia.schedule.beans.Setting;

@Repository("settingDAO")
public class SettingDAOImpl extends AbstractDAO<Setting> implements SettingDAO
{
	@Autowired
	public SettingDAOImpl(final DataSource ds)
	{
		super(ds);
	}
	
	protected List<Setting> createListFromResults(final ResultSet rs) throws SQLException
	{
		List<Setting> lst = new ArrayList<Setting>();
		
		while(rs.next())
			lst.add(createObjectFromResult(rs));
		
		return(lst);
	}
	
	protected Setting createObjectFromResult(final ResultSet rs) throws SQLException
	{
		Long lId;
		String sDescription, sSettingName;
		
		lId = rs.getLong("Id");
		
		if(rs.wasNull())
			lId = null;
		
		sSettingName = rs.getString("SettingName");
		
		if(rs.wasNull())
			sSettingName = null;
		
		sDescription = rs.getString("Description");
		
		if(rs.wasNull())
			sDescription = null;
		
		return(new Setting(lId, sSettingName, sDescription));
	}
	
	protected void createTable(String sSchema, String sTable) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("CREATE TABLE Setting (Id INT UNSIGNED NOT NULL AUTO_INCREMENT, SettingName VARCHAR(100) NOT NULL DEFAULT ' ', Description VARCHAR(1000) NULL, PRIMARY KEY (Id), UNIQUE INDEX `SettingName_UNIQUE` (SettingName ASC) VISIBLE, UNIQUE INDEX `Id_UNIQUE` (Id ASC) VISIBLE) COMMENT = 'This holds a single setting - the key if you will for the Settings.  It will be foreign key referenced to indicate a certain setting is assigned a value';");
			
			stmt.executeUpdate();
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<Setting> getAllSettings() throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, SettingName, Description from Setting;");
			ResultSet rs = null;
			
			rs = stmt.executeQuery();
			
			return(createListFromResults(rs));
			}
		finally
			{
			closeConnection();
			}
	}
	
	public Setting getSettingById(final long lId) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, SettingName, Description from Setting where Id = ?;");
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


	
	public Setting getSettingBySettingName(final String sSettingName) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, SettingName, Description from Setting where SettingName = ?;");
			ResultSet rs = null;
			
			stmt.setString(1, sSettingName);
			
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
		return("Setting");
	}
	
	protected Setting insert(final Setting objToInsert) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into " + getTableName() + " (SettingName, Description) values(?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, objToInsert.getSettingName());
			stmt.setString(2, objToInsert.getDescription());
			
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
	
	protected Setting update(final Setting objToUpdate) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("update " + getTableName() + " set SettingName = ?, Description = ? where Id = ?;");
			
			stmt.setString(1, objToUpdate.getSettingName());
			stmt.setString(2, objToUpdate.getDescription());
			stmt.setLong(3, objToUpdate.getId());
			
			stmt.executeUpdate();
			
			return(objToUpdate);
			}
		finally
			{
			closeConnection();
			}
	}
	
	
}