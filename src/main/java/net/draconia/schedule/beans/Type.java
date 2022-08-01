package net.draconia.scheduler.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import net.draconia.util.PropertyChangeable;

@Entity(name = "types")
public class Type extends PropertyChangeable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED NOT NULL AUTO_INCREMENT", insertable = true, name = "id", nullable = false, table = "types", unique = true, updatable = false)
	private Long mlId;
	
	@Column(columnDefinition = "VARCHAR(100) NOT NULL", insertable = true, length = 100, name = "TypeName", nullable = false, table = "types", unique = false, updatable = true)
	private String msTypeName;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable	(	name = "userpreference"
				,	inverseJoinColumns = @JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "userid", nullable = false, table = "userpreference", unique = false, updatable = true)
				,	joinColumns = @JoinColumn(columnDefinition = "INT UNSIGNED NOT NULL", insertable = true, name = "typeid", nullable = false, table = "userpreference", unique = false, updatable = true))
	private List<User> mLstUsers;
	
	public Type(final Long lId)
	{
		this(lId, null);
	}
	
	public Type(final String sTypeName)
	{
		this(null, sTypeName);
	}
	
	public Type(final String sTypeName, final List<User> lstUsers)
	{
		this(null, sTypeName, lstUsers);
	}
	
	public Type(final Long lId, final String sTypeName)
	{
		this(lId, sTypeName, null);
	}
	
	public Type(final Long lId, final String sTypeName, final List<User> lstUsers)
	{
		setId(lId);
		setTypeName(sTypeName);
		setUsers(lstUsers);
	}
	
	public void addUser(final User objUser)
	{
		final List<User> lstOldUsers = getUsers();
		
		getUsersInternal().add(objUser);
		
		setChanged();
		notifyObservers("Users", lstOldUsers, getUsers());
	}
	
	public void addUsers(final Collection<User> collUsers)
	{
		final List<User> lstOldUsers = getUsers();
		
		getUsersInternal().addAll(collUsers);
		
		setChanged();
		notifyObservers("Users", lstOldUsers, getUsers());
	}
	
	public long getId()
	{
		if(mlId == null)
			mlId = 0L;
		
		return(mlId);
	}
	
	public String getTypeName()
	{
		if(msTypeName == null)
			msTypeName = "";
		
		return(msTypeName);
	}
	
	public List<User> getUsers()
	{
		return(Collections.unmodifiableList(getUsersInternal()));
	}
	
	protected List<User> getUsersInternal()
	{
		if(mLstUsers == null)
			mLstUsers = new ArrayList<User>();
		
		return(mLstUsers);
	}
	
	public void removeUser(final User objUser)
	{
		final List<User> lstOldUsers = getUsers();
		
		getUsersInternal().remove(objUser);
		
		setChanged();
		notifyObservers("Users", lstOldUsers, getUsers());
	}
	
	public void removeUsers(final Collection<User> collUsers)
	{
		final List<User> lstOldUsers = getUsers();
		
		getUsersInternal().removeAll(collUsers);
		
		setChanged();
		notifyObservers("Users", lstOldUsers, getUsers());
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
	
	public void setTypeName(final String sTypeName)
	{
		final String sOldTypeName = getTypeName();
		
		if(sTypeName == null)
			msTypeName = "";
		else
			msTypeName = sTypeName;
		
		setChanged();
		notifyObservers("TypeName", sOldTypeName, getTypeName());
	}
	
	protected void setUsers(final List<User> lstUsers)
	{
		final List<User> lstOldUsers = getUsers();
		
		if(lstUsers == null)
			mLstUsers = new ArrayList<User>();
		else
			mLstUsers = lstUsers;
		
		setChanged();
		notifyObservers("Users", lstOldUsers, getUsers());
	}
}