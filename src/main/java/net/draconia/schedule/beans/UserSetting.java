package net.draconia.scheduler.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import net.draconia.util.PropertyChangeable;

@Entity(name = "usersetting")
public class UserSetting extends PropertyChangeable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED NOT NULL AUTO_INCREMENT", insertable = true, name = "Id", nullable = false, table = "usersetting", unique = true, updatable = false)
	private Long mlId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "userId", table = "usersetting", nullable = false, unique = false, updatable = true)
	private User mObjUser;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name="settingId", nullable = false, table = "usersetting", unique = false, updatable = true)
	private Setting mObjSetting;
	
	@Column(columnDefinition = "VARCHAR(100) NULL", insertable = true, length = 100, name = "Value", nullable = true, table = "usersetting", unique = false, updatable = false)
	private String msValue;
	
	public UserSetting()
	{ }
	
	public UserSetting(final Long lId)
	{
		this(lId, null, null, null);
	}
	
	public UserSetting(final User objUser)
	{
		this(null, objUser, null, null);
	}
	
	public UserSetting(final User objUser, final Setting objSetting)
	{
		this(null, objUser, objSetting);
	}
	
	public UserSetting(final Long lId, final User objUser, final Setting objSetting)
	{
		this(lId, objUser, objSetting, null);
	}
	
	public UserSetting(final Long lId, final User objUser, final Setting objSetting, final String sValue)
	{
		setId(lId);
		setSetting(objSetting);
		setUser(objUser);
		setValue(sValue);
	}
	
	public long getId()
	{
		if(mlId == null)
			mlId = 0L;
		
		return(mlId);
	}
	
	public Setting getSetting()
	{
		if(mObjSetting == null)
			mObjSetting = new Setting();
		
		return(mObjSetting);
	}
	
	public User getUser()
	{
		return(mObjUser);
	}
	
	public String getValue()
	{
		if(msValue == null)
			msValue = "";
		
		return(msValue);
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
	
	public void setSetting(final Setting objSetting)
	{
		final Setting objOldSetting = getSetting();
		
		if(objSetting == null)
			mObjSetting = new Setting();
		else
			mObjSetting = objSetting;
		
		setChanged();
		notifyObservers("Setting", objOldSetting, mObjSetting);
	}
	
	public void setUser(final User objUser)
	{
		final User objOldUser = getUser();
		
		mObjUser = objUser;
		
		setChanged();
		notifyObservers("User", objOldUser, getUser());
	}
	
	public void setValue(final String sValue)
	{
		final String sOldValue = getValue();
		
		if(sValue == null)
			msValue = "";
		else
			msValue = sValue;
		
		setChanged();
		notifyObservers("Value", sOldValue, msValue);
	}
}