package net.draconia.schedule.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.draconia.util.PropertyChangeable;

public class Configuration extends PropertyChangeable
{
	private List<SettingValue> mLstSettings;
	
	public Configuration()
	{ }
	
	public Configuration(final List<SettingValue> lstSettings)
	{
		setSettings(lstSettings);
	}
	
	public void addSetting(final Setting objSetting, final String sValue)
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		getSettings().add(new SettingValue(objSetting, sValue));
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	public void addSetting(final SettingValue objSetting)
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		getSettings().add(objSetting);
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	public void addSettings(final Collection<SettingValue> collSettings)
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		getSettingsInternal().addAll(collSettings);
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	protected void clearSettings()
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		getSettingsInternal().clear();
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	protected List<SettingValue> getSettingsInternal()
	{
		if(mLstSettings == null)
			mLstSettings = new ArrayList<SettingValue>();
		
		return(mLstSettings);
	}
	
	public List<SettingValue> getSettings()
	{
		return(Collections.unmodifiableList(getSettingsInternal()));
	}
	
	public void removeSetting(final Setting objSetting)
	{
		int iLoop = 0;
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		for(SettingValue objSearch : getSettings())
			if(objSearch.getSetting().equals(objSetting))
				{
				getSettingsInternal().remove(iLoop);
				
				break;
				}
			else
				iLoop++;
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	public void removeSettings(final Collection<SettingValue> collSettings)
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		getSettingsInternal().removeAll(collSettings);
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	public void retainSettings(final Collection<SettingValue> collSettings)
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		getSettingsInternal().retainAll(collSettings);
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
	
	protected void setSettings(final List<SettingValue> lstSettings)
	{
		final List<SettingValue> lstOldSettings = new ArrayList<SettingValue>(getSettings());
		
		if(lstSettings == null)
			mLstSettings = new ArrayList<SettingValue>();
		
		setChanged();
		notifyObservers("Settings", lstOldSettings, mLstSettings);
	}
}