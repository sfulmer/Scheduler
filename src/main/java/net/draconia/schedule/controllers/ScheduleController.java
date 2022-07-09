package net.draconia.schedule.controllers;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import net.draconia.schedule.beans.Event;

import net.draconia.schedule.dao.EventDAO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
public class ScheduleController
{
	@Autowired
	private EventDAO mObjDAO;
	
	protected EventDAO getDAO()
	{
		return(mObjDAO);
	}
	
	@GetMapping("/getEvents")
	public @ResponseBody List<Event> getEvents()
	{
		try
			{
			return(getDAO().getList());
			}
		catch(SQLException objException)
			{
			return(new ArrayList<Event>());
			}
	}
	
	@GetMapping("/")
	public String index()
	{
		return("index");
	}
}