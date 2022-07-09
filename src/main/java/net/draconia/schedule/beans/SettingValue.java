package net.draconia.schedule.beans;

public class SettingValue extends Idable
{
	private Setting mObjSetting;
	private String msValue;
	
	public SettingValue()
	{ }
	
	public SettingValue(final Setting objSetting, final String sValue)
	{
		this(null, objSetting, sValue);
	}
	
	public SettingValue(final Long lId, final Setting objSetting, final String sValue)
	{
		super(lId);
		
		setSetting(objSetting);
		setValue(sValue);
	}
	
	public Setting getSetting()
	{
		if(mObjSetting == null)
			mObjSetting = new Setting();
		
		return(mObjSetting);
	}
	
	public String getValue()
	{
		if(msValue == null)
			msValue = "";
		
		return(msValue);
	}
	
	public void setSetting(final Setting objSetting)
	{
		final Setting objOldSetting = getSetting();
		
		if(objSetting == null)
			mObjSetting = new Setting();
		else
			mObjSetting = objSetting;
		
		setChanged();
		notifyObservers("Setting", objOldSetting, mObjSetting);
	}
	
	public void setValue(final String sValue)
	{
		final String sOldValue = getValue();
		
		if(sValue == null)
			msValue = "";
		else
			msValue = sValue;
		
		setChanged();
		notifyObservers("Value", sOldValue, msValue);
	}
}