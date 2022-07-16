package net.draconia.schedule.dao;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import net.draconia.schedule.beans.Event;

public interface EventDAO
{
	public Event getEventById(final long lId) throws EntityNotFoundException;
	public List<Event> getList();
	public void remove(final Event objEvent);
	public void removeById(final long lId);
	public Event save(final Event objEvent);
}