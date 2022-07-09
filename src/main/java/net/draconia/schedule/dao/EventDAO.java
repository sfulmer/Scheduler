package net.draconia.schedule.dao;

import java.sql.SQLException;

import java.util.List;

import net.draconia.schedule.beans.Event;

public interface EventDAO
{
	public Event getEventById(final long lId) throws SQLException;
	public List<Event> getList() throws SQLException;
	public void removeById(final long lId) throws SQLException;
	public void remove(final Event objEvent) throws SQLException;
	public Event save(final Event objEvent) throws SQLException;
}