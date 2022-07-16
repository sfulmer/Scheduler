package net.draconia.schedule.controllers;

import java.util.List;

import net.draconia.schedule.beans.Event;

import net.draconia.schedule.dao.EventDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScheduleController
{
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	private EventDAO mObjDAO;
	
	protected EventDAO getDAO()
	{
		return(mObjDAO);
	}
	
	@GetMapping("/events")
	public @ResponseBody List<Event> getEvents()
	{
		logger.debug("I got here");
		
		return(getDAO().getList());
	}
	
	@GetMapping("/")
	public String index()
	{
		return("index");
	}
}