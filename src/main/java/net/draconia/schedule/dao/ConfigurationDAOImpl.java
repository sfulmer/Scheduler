package net.draconia.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.draconia.schedule.beans.Setting;
import net.draconia.schedule.beans.SettingValue;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository("configurationDAO")
public class ConfigurationDAOImpl extends AbstractDAO<SettingValue> implements ConfigurationDAO
{
	@Autowired
	private SettingDAO mObjSettingDAO;
	
	@Autowired
	public ConfigurationDAOImpl(final DataSource ds)
	{
		super(ds);
	}
	
	protected List<SettingValue> createListFromResults(final ResultSet rs) throws SQLException
	{
		List<SettingValue> lst = new ArrayList<SettingValue>();
		
		while(rs.next())
			lst.add(createObjectFromResult(rs));
		
		return(lst);
	}
	
	protected SettingValue createObjectFromResult(final ResultSet rs) throws SQLException
	{
		Long lId, lSettingId;
		Setting objSetting;
		String sValue;
		
		lId = rs.getLong("Id");
		
		if(rs.wasNull())
			lId = null;
		
		lSettingId = rs.getLong("SettingId");
		
		if(rs.wasNull())
			objSetting = null;
		else
			objSetting = getSettingDAO().getSettingById(lSettingId);
		
		sValue = rs.getString("Value");
		
		if(rs.wasNull())
			sValue = null;
		
		return(new SettingValue(lId, objSetting, sValue));
	}
	
	protected void createTable(String sSchema, String sTable) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("CREATE TABLE SettingValues (Id INT UNSIGNED NOT NULL AUTO_INCREMENT, SettingId INT UNSIGNED NOT NULL, Value VARCHAR(100) NULL, PRIMARY KEY (Id), UNIQUE INDEX `Id_UNIQUE` (Id ASC) VISIBLE, UNIQUE INDEX `SettingId_UNIQUE` (SettingId ASC) VISIBLE, CONSTRAINT `Settings_Setting` FOREIGN KEY (SettingId) REFERENCES schedule.setting (Id) ON DELETE NO ACTION ON UPDATE NO ACTION);");
			
			stmt.executeUpdate();
			}
		finally
			{
			closeConnection();
			}
	}
	
	public List<SettingValue> getAllSettingValues() throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, SettingId, Value from Settings;");
			ResultSet rs = null;
			
			rs = stmt.executeQuery();
			
			return(createListFromResults(rs));
			}
		finally
			{
			closeConnection();
			}
	}
	
	public SettingValue getSettingValueById(final long lId) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, SettingId, Value from Settings where Id = ?;");
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
	
	public SettingValue getSettingValueBySettingName(String sSettingName) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select Id, SettingId, Value from Settings inner join Setting on Settings.SettingId = Setting.Id and Setting.SettingName = ?;");
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
	
	protected SettingDAO getSettingDAO()
	{
		return(mObjSettingDAO);
	}
	
	protected String getTableName()
	{
		return("Events");
	}
	
	protected SettingValue insert(final SettingValue objToInsert) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into " + getTableName() + " (SettingId, Value) values(?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			if(objToInsert.getSetting().getId() < 0)
				getSettingDAO().save(objToInsert.getSetting());
			
			stmt.setLong(1, objToInsert.getSetting().getId());
			stmt.setString(2, objToInsert.getValue());
			
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
	
	protected SettingValue update(final SettingValue objToUpdate) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("update " + getTableName() + " set SettingId = ?, Value = ? where Id = ?;");
			
			if(objToUpdate.getSetting().getId() <= 0)
				getSettingDAO().save(objToUpdate.getSetting());
			
			stmt.setLong(1, objToUpdate.getSetting().getId());
			stmt.setString(2, objToUpdate.getValue());
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