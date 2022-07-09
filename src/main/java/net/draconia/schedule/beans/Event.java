package net.draconia.schedule.beans;

import java.util.Date;

public class Event extends Idable
{
	private Boolean mbAllDay;
	private Date mDtEnd, mDtStart;
	private String msDescription, msTitle;
	
	public Event()
	{ }
	
	public Event(final String sTitle, final Date dtStart, final Date dtEnd)
	{
		this(sTitle, dtStart, dtEnd, false);
	}
	
	public Event(final Long lId, final String sTitle, final Date dtStart, final Date dtEnd)
	{
		this(lId, sTitle, dtStart, dtEnd, false);
	}
	
	public Event(final String sTitle, final Date dtStart, final Date dtEnd, final boolean bAllDay)
	{
		this(sTitle, dtStart, dtEnd, bAllDay, " ");
	}
	
	public Event(final Long lId, final String sTitle, final Date dtStart, final Date dtEnd, final boolean bAllDay)
	{
		this(lId, sTitle, dtStart, dtEnd, bAllDay, " ");
	}
	
	public Event(final String sTitle, final Date dtStart, final Date dtEnd, final boolean bAllDay, final String sDescription)
	{
		super();
		
		setAllDay(bAllDay);
		setDescription(sDescription);
		setEnd(dtEnd);
		setStart(dtStart);
		setTitle(sTitle);
	}
	
	public Event(final Long lId, final String sTitle, final Date dtStart, final Date dtEnd, final boolean bAllDay, final String sDescription)
	{
		super(lId);
		
		setAllDay(bAllDay);
		setDescription(sDescription);
		setEnd(dtEnd);
		setStart(dtStart);
		setTitle(sTitle);
	}
	
	public boolean isAllDay()
	{
		if(mbAllDay == null)
			mbAllDay = false;
		
		return(mbAllDay);
	}
	
	public String getDescription()
	{
		if(msDescription == null)
			msDescription = " ";
		
		return(msDescription);
	}
	
	public Date getEnd()
	{
		return(mDtEnd);
	}
	
	public Date getStart()
	{
		return(mDtStart);
	}
	
	public String getTitle()
	{
		if(msTitle == null)
			msTitle = " ";
		
		return(msTitle);
	}
	
	public void setAllDay(final Boolean bAllDay)
	{
		final boolean bOldValue = isAllDay();
		
		if(bAllDay == null)
			mbAllDay = false;
		else
			mbAllDay = bAllDay;
		
		setChanged();
		notifyObservers("AllDay", bOldValue, mbAllDay);
	}
	
	public void setDescription(final String sDescription)
	{
		final String sOldDescription = getDescription();
		
		if(sDescription == null)
			msDescription = " ";
		else
			msDescription = sDescription;
		
		setChanged();
		notifyObservers("Description", sOldDescription, msDescription);
	}
	
	public void setEnd(final Date dtEnd)
	{
		final Date dtOldEnd = getEnd();
		
		mDtEnd = dtEnd;
		
		setChanged();
		notifyObservers("End", dtOldEnd, mDtEnd);
	}
	
	public void setStart(final Date dtStart)
	{
		final Date dtOldStart = getStart();
		
		mDtStart = dtStart;
		
		setChanged();
		notifyObservers("End", dtOldStart, mDtStart);
	}
	
	public void setTitle(final String sTitle)
	{
		final String sOldTitle = getTitle();
		
		if(sTitle == null)
			msTitle = " ";
		else
			msTitle = sTitle;
		
		setChanged();
		notifyObservers("Title", sOldTitle, msTitle);
	}
}