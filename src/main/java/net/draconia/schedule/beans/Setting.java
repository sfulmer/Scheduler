package net.draconia.schedule.beans;

public class Setting extends Idable
{
	private String msDescription = null, msSettingName = null;
	
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
		super(lId);
		
		setSettingName(sSettingName);
		setDescription(sDescription);
	}
	
	public String getDescription()
	{
		if(msDescription == null)
			msDescription = "";
		
		return(msDescription);
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