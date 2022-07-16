package net.draconia.schedule.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import net.draconia.schedule.beans.Event;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO
{
	private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);
	
	@Autowired
	private EntityManager mObjEntityManager;
	
	protected EntityManager getEntityManager()
	{
		return(mObjEntityManager);
	}
	
	public Event getEventById(final long lId) throws EntityNotFoundException
	{
		Event objEvent;
		
		try
			{
			objEvent = getEntityManager().find(Event.class, lId);
			}
		catch(IllegalArgumentException objException)
			{
			logger.error("There was a problem obtaining an Event by id " + lId + ".", objException);
			
			objEvent = null;
			}
		
		if(objEvent == null)
			throw new EntityNotFoundException("Can't find Event by Id " + lId);
		
		return(objEvent);
	}
	
	public List<Event> getList()
	{
		try
			{
			return(getEntityManager().createQuery("from Event", Event.class).getResultList());
			}
		catch(IllegalArgumentException | IllegalStateException | PersistenceException objException)
			{
			logger.error("There appears to be a problem getting a list of events", objException);
			
			return(new ArrayList<Event>());
			}
	}
	
	public void remove(final Event objEvent)
	{
		EntityManager objEntityManager = getEntityManager();
		EntityTransaction objTransaction = objEntityManager.getTransaction();
		
		objTransaction.begin();
		
		try
			{
			objEntityManager.remove(objEvent);
			objEntityManager.flush();
			objEntityManager.clear();
			
			objTransaction.commit();
			}
		catch(IllegalArgumentException | PersistenceException objException)
			{
			logger.error("There was a problem removing the event with id " + objException);
			
			objTransaction.rollback();
			}
	}
	
	public void removeById(final long lId)
	{
		EntityManager objEntityManager = getEntityManager();
		Event objEvent = null;
		
		try
			{
			objEvent = objEntityManager.find(Event.class, lId);
			}
		catch(IllegalArgumentException objException)
			{
			logger.error("There was a problem obtaining an Event by Id " + lId + " for removal.", objException);
			
			objEvent = null;
			}
		
		if(objEvent != null)
			{
			EntityTransaction objTransaction = objEntityManager.getTransaction();
			
			objTransaction.begin();
			
			try
				{
				objEntityManager.remove(objEvent);
				objEntityManager.flush();
				objEntityManager.clear();
				
				objTransaction.commit();
				}
			catch(IllegalArgumentException | PersistenceException objException)
				{
				logger.error("There was an error removing the event with id " + lId, objException);
				
				objTransaction.rollback();
				}
			}
	}
	
	public Event save(final Event objEvent)
	{
		EntityManager objEntityManager = getEntityManager();
		EntityTransaction objTransaction = objEntityManager.getTransaction();
		Session objSession = objEntityManager.unwrap(Session.class);
		
		objTransaction.begin();
		
		try
			{
			objEvent.setId((Long)(objSession.save(objEvent)));
			
			objEntityManager.flush();
			objTransaction.commit();
			
			return(objEvent);
			}
		catch(IllegalStateException | PersistenceException objException)
			{
			logger.error("There was an error saving event with id " + objEvent.getId(), objException);
			
			objTransaction.rollback();
			
			return(null);
			}
	}
}