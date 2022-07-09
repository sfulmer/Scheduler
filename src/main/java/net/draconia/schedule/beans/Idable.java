package net.draconia.schedule.beans;

import net.draconia.util.PropertyChangeable;

public abstract class Idable extends PropertyChangeable
{
	private Long mlId;
	
	public Idable()
	{ }
	
	public Idable(final Long lId)
	{
		setId(lId);
	}
	
	public long getId()
	{
		if(mlId == null)
			mlId = -1L;
		
		return(mlId);
	}
	
	public void setId(final Long lId)
	{
		if(lId == null)
			mlId = -1L;
		else
			mlId = lId;
	}
}