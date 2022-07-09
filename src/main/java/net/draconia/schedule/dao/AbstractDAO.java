package net.draconia.schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

import net.draconia.schedule.beans.Idable;

public abstract class AbstractDAO<T extends Idable>
{
	private Connection mObjConnection;
	private DataSource mDSDatasource;
	
	public AbstractDAO(final DataSource ds)
	{
		setDatasource(ds);
	}
	
	protected void closeConnection() throws SQLException
	{
		if(mObjConnection != null)
			{
			if(!mObjConnection.isClosed())
				mObjConnection.close();
			
			mObjConnection = null;
			}	
	}
	
	protected abstract List<T> createListFromResults(final ResultSet rs) throws SQLException;
	protected abstract T createObjectFromResult(final ResultSet rs) throws SQLException;
	protected abstract void createTable(final String sSchema, final String sTable) throws SQLException;
	
	protected void dropTable(final String sTable) throws SQLException
	{
		try
			{
			Connection objConn = getConnection();
			PreparedStatement stmt = objConn.prepareStatement("drop table " + sTable + ";");
			
			stmt.executeUpdate();
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected Connection getConnection() throws SQLException
	{
		if((mObjConnection == null) || mObjConnection.isClosed())
			mObjConnection = getDatasource().getConnection();
		
		return(mObjConnection);
	}
	
	protected DataSource getDatasource()
	{
		return(mDSDatasource);
	}
	
	public List<T> getList() throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from " + getTableName());
			ResultSet rs = stmt.executeQuery();
			
			return(createListFromResults(rs));
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected abstract String getTableName();
	
	protected abstract T insert(final T objToInsert) throws SQLException;
	
	public void remove(final T objToRemove) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from " + getTableName() + " where Id = ?");
			
			stmt.setLong(1, objToRemove.getId());
			
			stmt.executeUpdate();
			}
		finally
			{
			closeConnection();
			}
	}
	
	public void removeById(final long lId) throws SQLException
	{
		try
			{
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from " + getTableName() + " where Id = ?;");
			
			stmt.setLong(1, lId);
			
			stmt.executeUpdate();
			}
		finally
			{
			closeConnection();
			}
	}
	
	public T save(final T objToSave) throws SQLException
	{
		if(objToSave.getId() >= 1)
			return(update(objToSave));
		else
			return(insert(objToSave));
	}
	
	protected void setDatasource(final DataSource ds)
	{
		mDSDatasource = ds;
	}
	
	protected boolean tableExists(final String sSchema, final String sTable) throws SQLException
	{
		try
			{
			Connection objConn = getConnection();
			PreparedStatement objStmt = objConn.prepareStatement("select count(*) from information_schema.tables where table_schema = ? and table_name = ?;");
			ResultSet rs = null;
			
			objStmt.setString(1, sSchema);
			objStmt.setString(2, sTable);
			
			rs = objStmt.executeQuery();
			
			if(rs.next())
				{
				int iCount = rs.getInt(1);
				
				if(rs.wasNull())
					return(false);
				else
					return(iCount >= 1);
				}
			else
				return(false);
			}
		finally
			{
			closeConnection();
			}
	}
	
	protected abstract T update(final T objToUpdate) throws SQLException;	
}