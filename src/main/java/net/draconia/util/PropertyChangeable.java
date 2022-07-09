package net.draconia.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PropertyChangeable
{
	Boolean mbChanged;
	List<PropertyChangeListener> mLstPropertyChangeListeners;
	
	public PropertyChangeable()
	{ }
	
	public void addPropertyChangelistener(final PropertyChangeListener objPropertyChangeListener)
	{
		getPropertyChangeListeners().add(objPropertyChangeListener);
	}
	
	public void clearChanged()
	{
		setChanged(false);
	}
	
	protected List<PropertyChangeListener> getPropertyChangeListeners()
	{
		if(mLstPropertyChangeListeners == null)
			mLstPropertyChangeListeners = new ArrayList<PropertyChangeListener>();
		
		return(mLstPropertyChangeListeners);
	}
	
	public boolean isChanged()
	{
		if(mbChanged == null)
			mbChanged = false;
		
		return(mbChanged);
	}
	
	public void notifyObservers(final String sProperty, final Object objOld, final Object objNew)
	{
		if(isChanged())
			for(PropertyChangeListener objPropertyChangeListener : getPropertyChangeListeners())
				objPropertyChangeListener.propertyChange(new PropertyChangeEvent(this, sProperty, objOld, objNew));
		
			clearChanged();
	}
	
	public void removePropertyChangeListener(final PropertyChangeListener objPropertyChangeListener)
	{
		getPropertyChangeListeners().remove(objPropertyChangeListener);
	}
	
	public void setChanged()
	{
		setChanged(true);
	}
	
	public void setChanged(final Boolean bChanged)
	{
		if(bChanged == null)
			mbChanged = true;
		else
			mbChanged = bChanged;
	}
}