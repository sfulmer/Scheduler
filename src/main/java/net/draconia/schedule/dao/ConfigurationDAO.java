package net.draconia.schedule.dao;

import java.sql.SQLException;

import java.util.List;

import net.draconia.schedule.beans.SettingValue;

public interface ConfigurationDAO
{
	public List<SettingValue> getAllSettingValues() throws SQLException;
	public SettingValue getSettingValueById(final long lId) throws SQLException;
	public SettingValue getSettingValueBySettingName(final String sSettingName) throws SQLException;
	public void removeById(final long lId) throws SQLException;
	public void remove(final SettingValue objEvent) throws SQLException;
	public SettingValue save(final SettingValue objEvent) throws SQLException;
}