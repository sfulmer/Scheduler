package net.draconia.schedule.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.draconia.util.PropertyChangeable;

public class Event extends PropertyChangeable
{
	@Column(columnDefinition = "TINYINT NOT NULL DEFAULT 1", insertable = true, name = "AllDay", nullable = false, table = "events", unique = false, updatable = true)
	private Boolean mbAllDay;
	
	@Column(columnDefinition = "VARCHAR(1000) NOT NULL DEFAULT ' '", insertable = true, length = 1000, name = "Description", nullable = false, table = "events", unique = false, updatable = true)
	private String msDescription;
	
	@Column(columnDefinition = "DATETIME NOT NULL", insertable = true, name = "end", nullable = false, table = "events", unique = false, updatable = true)
	private Date mDtEnd;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED NOT NULL AUTO_INCREMENT", insertable = true, name = "id", nullable = false, table = "events", unique = true, updatable = false)
	private Long mlId;
	
	@Column(columnDefinition = "DATETIME NOT NULL", insertable = true, name = "start", nullable = false, table = "events", unique = false, updatable = true)
	private Date mDtStart;
	
	@Column(columnDefinition = "VARCHAR(50) NULL DEFAULT ' '", insertable = true, length = 50, name = "Title", nullable = true, table = "events", unique = false, updatable = true)
	private String msTitle;

	public Event()
	{
		this(null, null, null, null, null, null);
	}
	
	public Event(final Long lId)
	{
		this(lId, null, null, null, null, null);
	}
	
	public Event(final String sTitle)
	{
		this(null, sTitle, null, null, null, null);
	}
	
	public Event(final Long lId, final String sTitle)
	{
		this(lId, sTitle, null, null, null, null);
	}
	
	public Event(final String sTitle, final String sDescription)
	{
		this(null, sTitle, sDescription, null, null, null);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription)
	{
		this(lId, sTitle, sDescription, null, null, null);
	}
	
	public Event(final String sTitle, final String sDescription, final Boolean bAllDay)
	{
		this(null, sTitle, sDescription, null, null, bAllDay);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Boolean bAllDay)
	{
		this(lId, sTitle, sDescription, null, null, bAllDay);
	}
	
	public Event(final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd)
	{
		this(null, sTitle, sDescription, dtStart, dtEnd, null);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd)
	{
		this(lId, sTitle, sDescription, dtStart, dtEnd, null);
	}
	
	public Event(final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final Boolean bAllDay)
	{
		this(null, sTitle, sDescription, dtStart, dtEnd, bAllDay);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final Boolean bAllDay)
	{
		setAllDay(bAllDay);
		setDescription(sDescription);
		setEndDate(dtEnd);
		setId(lId);
		setStartDate(dtStart);
		setTitle(sTitle);
	}
	
	public String getDescription()
	{
		if(msDescription == null)
			msDescription = "";
		
		return(msDescription);
	}
	
	public Date getEndDate() 
	{
		return(mDtEnd);
	}
	
	public Long getId()
	{
		if(mlId == null)
			mlId = 0L;
		
		return(mlId);
	}
	
	public Date getStartDate()
	{
		return(mDtStart);
	}
	
	public String getTitle()
	{
		if(msTitle == null)
			msTitle = "";
		
		return(msTitle);
	}

	public Boolean isAllDay()
	{
		if(mbAllDay == null)
			mbAllDay = false;
		
		return(mbAllDay);
	}
	
	public void setAllDay(final Boolean bAllDay)
	{
		final Boolean bOldAllDay = isAllDay();
		
		if(bAllDay == null)
			mbAllDay = false;
		else
			mbAllDay = bAllDay;
		
		setChanged();
		notifyObservers("AllDay", bOldAllDay, isAllDay());
	}

	public void setDescription(final String sDescription)
	{
		final String sOldDescription = getDescription();
		
		if(sDescription == null)
			msDescription = "";
		else
			msDescription = sDescription;
		
		setChanged();
		notifyObservers("Description", sOldDescription, getDescription());
	}

	public void setEndDate(final Date dtEnd)
	{
		Date dtOldEnd = getEndDate();
		
		mDtEnd = dtEnd;
		
		setChanged();
		notifyObservers("EndDate", dtOldEnd, getEndDate());
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

	public void setStartDate(final Date dtStart)
	{
		final Date dtOldStart = getStartDate();
		
		mDtStart = dtStart;
		
		setChanged();
		notifyObservers("StartDate", dtOldStart, getStartDate());
	}
	
	public void setTitle(final String sTitle)
	{
		final String sOldTitle = getTitle();
		
		if(sTitle == null)
			msTitle = "";
		else
			msTitle = sTitle;
		
		setChanged();
		notifyObservers("Title", sOldTitle, getTitle());
	}
}