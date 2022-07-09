package net.draconia.schedule.dao;

import java.sql.SQLException;
import java.util.List;

import net.draconia.schedule.beans.Setting;
import net.draconia.schedule.beans.SettingValue;

public interface SettingDAO
{
	public List<Setting> getAllSettings() throws SQLException;
	public Setting getSettingById(final long lId) throws SQLException;
	public Setting getSettingBySettingName(final String sSettingName) throws SQLException;
	public void removeById(final long lId) throws SQLException;
	public void remove(final Setting objToRemove) throws SQLException;
	public Setting save(final Setting objToSave) throws SQLException;
}