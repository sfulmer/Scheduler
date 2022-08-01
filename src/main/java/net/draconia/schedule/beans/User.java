package net.draconia.scheduler.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import net.draconia.util.PropertyChangeable;

@Entity(name = "user")
public class User extends PropertyChangeable
{
	@Column(columnDefinition = "TINYINT NOT NULL DEFAULT 0", insertable = true, name = "validated", nullable = false, table = "user", unique = false, updatable = true)
	private Boolean mbValidated;
	
	@Column(columnDefinition = "DATETIME NOT NULL", insertable = true, name = "birthdate", nullable = false, table = "user", unique = false, updatable = true)
	private Date mDtBirth;
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL", insertable = true, length = 255, name = "email", nullable = false, table = "user", unique = false, updatable = true)
	private String msEmail;
	
	@Column(columnDefinition = "VARCHAR(100) NOT NULL", insertable = true, length = 100, name = "FirstName", nullable = false, table = "user", unique = false, updatable = true)
	private String msFirstName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED NOT NULL AUTO_INCREMENT", insertable = true, name = "id", nullable = false, table = "user", unique = true, updatable = false)
	private Long mlId;
	
	@Column(columnDefinition = "VARCHAR(100) NOT NULL", insertable = true, length = 100, name = "LastName", nullable = false, table = "user", unique = false, updatable = true)
	private String msLastName;
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL", insertable = true, length = 255, name = "pwd", nullable = false, table = "user", unique = false, updatable = true)
	private String msPassword;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable	(	name = "userpreference"
				,	inverseJoinColumns = @JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "typeid", nullable = false, table = "userpreference", unique = false, updatable = true)
				,	joinColumns = @JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "userid", nullable = true, table = "userpreference", unique = false, updatable = true))
	private List<Type> mLstPreferences;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mObjUser")
	private List<UserSetting> mLstSettings;
	
	@Column(columnDefinition = "VARCHAR(255) NOT NULL", insertable = true, length = 255, name = "username", nullable = false, table = "user", unique = false, updatable = true)
	private String msUserName;
	
	public User(final Long lId, final String sFirstName, final String sLastName, final Date dtBirth, final List<Type> lstPreferences)
	{
		setId(lId);
		setFirstName(sFirstName);
		setLastName(sLastName);
		setBirthDate(dtBirth);
		setPreferences(lstPreferences);
	}
	
	public void addPreference(final Type objType)
	{
		final List<Type> lstOldPreferences = getPreferences();
		
		getPreferencesInternal().add(objType);
		
		setChanged();
		notifyObservers("Preferences", lstOldPreferences, getPreferences());
	}
	
	public void addPreferences(final Collection<Type> collPreferences)
	{
		final List<Type> lstOldPreferences = getPreferences();
		
		getPreferencesInternal().addAll(collPreferences);
		
		setChanged();
		notifyObservers("Preferences", lstOldPreferences, getPreferences());
	}
	
	public Date getBirthDate()
	{
		return(mDtBirth);
	}
	
	public String getFirstName()
	{
		if(msFirstName == null)
			msFirstName = "";
		
		return(msFirstName);
	}
	
	public long getId()
	{
		if(mlId == null)
			mlId = 0L;
		
		return(mlId);
	}
	
	public String getLastName()
	{
		if(msLastName == null)
			msLastName = "";
		
		return(msLastName);
	}
	
	public List<Type> getPreferences()
	{
		return(Collections.unmodifiableList(getPreferencesInternal()));
	}
	
	protected List<Type> getPreferencesInternal()
	{
		if(mLstPreferences == null)
			mLstPreferences = new ArrayList<Type>();
		
		return(mLstPreferences);
	}
	
	public void removePreference(final Type objPreference)
	{
		final List<Type> lstOldPreferences = getPreferences();
		
		getPreferencesInternal().remove(objPreference);
		
		setChanged();
		notifyObservers("Preferences", lstOldPreferences, getPreferences());
	}
	
	public void removePreferences(final Collection<Type> collPreferences)
	{
		final List<Type> lstOldPreferences = getPreferences();
		
		getPreferencesInternal().removeAll(collPreferences);
		
		setChanged();
		notifyObservers("Preferences", lstOldPreferences, getPreferences());
	}
	
	public void setBirthDate(final Date dtBirth)
	{
		final Date dtOldBirth = getBirthDate();
		
		mDtBirth = dtBirth;
		
		setChanged();
		notifyObservers("Birthdate", dtOldBirth, getBirthDate());
	}
	
	public void setFirstName(final String sFirstName)
	{
		final String sOldFirstName = getFirstName();
		
		if(sFirstName == null)
			msFirstName = "";
		else
			msFirstName = sFirstName;
		
		setChanged();
		notifyObservers("FirstName", sOldFirstName, getFirstName());
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
	
	public void setLastName(final String sLastName)
	{
		final String sOldLastName = getLastName();
		
		if(sLastName == null)
			msLastName = "";
		else
			msLastName = sLastName;
		
		setChanged();
		notifyObservers("LastName", sOldLastName, getLastName());
	}
	
	public void setPreferences(final List<Type> lstPreferences)
	{
		final List<Type> lstOldPreferences = getPreferences();
		
		if(lstPreferences == null)
			mLstPreferences = new ArrayList<Type>();
		else
			mLstPreferences = lstPreferences;
		
		setChanged();
		notifyObservers("Preferences", lstOldPreferences, getPreferences());
	}
}