package net.draconia.scheduler.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.draconia.util.PropertyChangeable;

@Entity(name = "setting")
public class Setting extends PropertyChangeable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "Id", nullable = false, table = "setting", unique = true, updatable = false)
	private Long mlId;
	
	@Column(columnDefinition = "varchar(1000) null", insertable = true, length = 1000, name = "Description", nullable = true, table = "setting", unique = true, updatable = true)
	private String msDescription = null;
	
	@Column(columnDefinition = "VARCHAR(100) NOT NULL DEFAULT ' '", insertable = true, length = 100, name = "SettingName", nullable = false, table = "setting", unique = false, updatable = true)
	private String msSettingName = null;
	
	public Setting()
	{ }
	
	public Setting(final String sSettingName)
	{
		this(sSettingName, "");
	}
	
	public Setting(final Long lId, final String sSettingName)
	{
		this(lId, sSettingName, "");
	}
	
	public Setting(final String sSettingName, final String sDescription)
	{
		this(null, sSettingName, sDescription);
	}
	
	public Setting(final Long lId, final String sSettingName, final String sDescription)
	{
		setId(lId);
		
		setSettingName(sSettingName);
		setDescription(sDescription);
	}
	
	public String getDescription()
	{
		if(msDescription == null)
			msDescription = "";
		
		return(msDescription);
	}
	
	public long getId()
	{
		if(mlId == null)
			mlId = 0L;
		
		return(mlId);
	}
	
	public String getSettingName()
	{
		if(msSettingName == null)
			msSettingName = "";
		
		return(msSettingName);
	}
	
	public void setDescription(final String sDescription)
	{
		final String sOldDescription = getDescription();
		
		if(sDescription == null)
			msDescription = "";
		else
			msDescription = sDescription;
		
		setChanged();
		notifyObservers("Description", sOldDescription, msDescription);
	}
	
	public void setId(final Long lId)
	{
		final Long lOldId = getId();
		
		if(lId == null)
			mlId = 0L;
		else
			mlId = lId;
		
		setChanged();
		notifyObservers("Id", lOldId, getId());
	}
	
	public void setSettingName(final String sSettingName)
	{
		final String sOldSettingName = getSettingName();
		
		if(sSettingName == null)
			msSettingName = "";
		else
			msSettingName = sSettingName;
		
		setChanged();
		notifyObservers("SettingName", sOldSettingName, msSettingName);
	}
}