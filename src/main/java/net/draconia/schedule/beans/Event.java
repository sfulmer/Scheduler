package net.draconia.scheduler.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import net.draconia.util.PropertyChangeable;

@Entity(name = "events")
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
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "typeid", nullable = false, table = "events", unique = false, updatable = true)
	private Type mObjType;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User mObjUser;
	
	public Event(final User objUser)
	{
		this(null, null, null, null, null, null, objUser);
	}
	
	public Event(final Long lId, final User objUser)
	{
		this(lId, null, null, null, null, objUser);
	}
	
	public Event(final User objUser, final Type objType)
	{
		this(null, null, null, null, null, null, objUser, objType);
	}
	
	public Event(final Long lId, final User objUser, final Type objType)
	{
		this(lId, null, null, null, null, objUser, objType);
	}
	
	public Event(final String sTitle, final User objUser)
	{
		this(null, sTitle, null, null, null, null, objUser);
	}
	
	public Event(final Long lId, final String sTitle, final User objUser)
	{
		this(lId, sTitle, null, null, null, null, objUser);
	}
	
	public Event(final String sTitle, final User objUser, final Type objType)
	{
		this(null, sTitle, null, null, null, null, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final User objUser, final Type objType)
	{
		this(lId, sTitle, null, null, null, null, objUser, objType);
	}
	
	public Event(final String sTitle, final String sDescription, final User objUser)
	{
		this(null, sTitle, sDescription, null, null, null, objUser);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final User objUser)
	{
		this(lId, sTitle, sDescription, null, objUser);
	}
	
	public Event(final String sTitle, final String sDescription, final User objUser, final Type objType)
	{
		this(null, sTitle, sDescription, null, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final User objUser, final Type objType)
	{
		this(lId, sTitle, sDescription, null, objUser, objType);
	}
	
	public Event(final Date dtStart, final Date dtEnd, final User objUser)
	{
		this(null, null, null, dtStart, dtEnd, null, objUser);
	}
	
	public Event(final Long lId, final Date dtStart, final Date dtEnd, final User objUser)
	{
		this(lId, null, null, dtStart, dtEnd, null, objUser);
	}
	
	public Event(final Date dtStart, final Date dtEnd, final User objUser, final Type objType)
	{
		this(null, null, null, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final Long lId, final Date dtStart, final Date dtEnd, final User objUser, final Type objType)
	{
		this(lId, null, null, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final Boolean bAllDay, final User objUser)
	{
		this(null, null, null, null, null, bAllDay, objUser);
	}
	
	public Event(final Long lId, final Boolean bAllDay, final User objUser)
	{
		this(lId, null, null, null, null, bAllDay, objUser);
	}
	
	public Event(final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(null, null, null, null, null, bAllDay, objUser, objType);
	}
	
	public Event(final Long lId, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(lId, null, null, null, null, bAllDay, objUser, objType);
	}
	
	public Event(final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser)
	{
		this(null, null, null, dtStart, dtEnd, bAllDay, objUser);
	}
	
	public Event(final Long lId, final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser)
	{
		this(lId, null, null, dtStart, dtEnd, bAllDay, objUser);
	}
	
	public Event(final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(null, null, null, dtStart, dtEnd, bAllDay, objUser, objType);
	}
	
	public Event(final Long lId, final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(lId, null, null, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final String sTitle, final Date dtStart, final Date dtEnd, final User objUser)
	{
		this(null, sTitle, null, dtStart, dtEnd, null, objUser);
	}
	
	public Event(final Long lId, final String sTitle, final Date dtStart, final Date dtEnd, final User objUser)
	{
		this(lId, sTitle, null, dtStart, dtEnd, null, objUser);
	}
	
	public Event(final String sTitle, final Date dtStart, final Date dtEnd, final User objUser, final Type objType)
	{
		this(null, sTitle, null, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final Date dtStart, final Date dtEnd, final User objUser, final Type objType)
	{
		this(lId, sTitle, null, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final String sTitle, final Boolean bAllDay, final User objUser)
	{
		this(null, sTitle, null, null, null, bAllDay, objUser);
		
	}
	
	public Event(final Long lId, final String sTitle, final Boolean bAllDay, final User objUser)
	{
		this(lId, sTitle, null, null, null, bAllDay, objUser);
	}
	
	public Event(final String sTitle, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(null, sTitle, null, null, null, bAllDay, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(lId, sTitle, null, null, null, bAllDay, objUser, objType);
	}
	
	public Event(final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final User objUser)
	{
		this(null, sTitle, sDescription, dtStart, dtEnd, null, objUser);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final User objUser)
	{
		this(lId, sTitle, sDescription, dtStart, dtEnd, null, objUser);
	}
	
	public Event(final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final User objUser, final Type objType)
	{
		this(null, sTitle, sDescription, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final User objUser, final Type objType)
	{
		this(lId, sTitle, sDescription, dtStart, dtEnd, null, objUser, objType);
	}
	
	public Event(final String sTitle, final String sDescription, final Boolean bAllDay, final User objUser)
	{
		this(null, sTitle, sDescription, null, null, bAllDay, objUser);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Boolean bAllDay, final User objUser)
	{
		this(lId, sTitle, sDescription, null, null, bAllDay, objUser);
	}
	
	public Event(final String sTitle, final String sDescription, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(null, sTitle, sDescription, null, null, bAllDay, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(lId, sTitle, sDescription, null, null, bAllDay, objUser, objType);
	}
	
	public Event(final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser)
	{
		this(null, sTitle, sDescription, dtStart, dtEnd, bAllDay, objUser);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser)
	{
		this(lId, sTitle, sDescription, dtStart, dtEnd, bAllDay, objUser, null);
	}
	
	public Event(final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser, final Type objType)
	{
		this(null, sTitle, sDescription, dtStart, dtEnd, bAllDay, objUser, objType);
	}
	
	public Event(final Long lId, final String sTitle, final String sDescription, final Date dtStart, final Date dtEnd, final Boolean bAllDay, final User objUser, final Type objType)
	{
		setId(lId);
		setTitle(sTitle);
		setDescription(sDescription);
		setStartDate(dtStart);
		setEndDate(dtEnd);
		setAllDay(bAllDay);
		setUser(objUser);
		setType(objType);
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
	
	public Type getType()
	{
		return(mObjType);
	}
	
	public User getUser()
	{
		return(mObjUser);
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
	
	public void setType(final Type objType)
	{
		final Type objOldType = getType();
		
		mObjType = objType;
		
		setChanged();
		notifyObservers("Type", objOldType, getType());
	}
	
	public void setUser(final User objUser)
	{
		final User objOldUser = getUser();
		
		mObjUser = objUser;
		
		setChanged();
		notifyObservers("User", objOldUser, getUser());
	}
}