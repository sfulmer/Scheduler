package net.draconia.schedule;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages =	{	"net.draconia.schedule.beans"
											,	"net.draconia.schedule.controller"
											,	"net.draconia.schedule.dao"
											})
public class ScheduleApp implements WebMvcConfigurer
{
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
        return(builder.sources(ScheduleApp.class));
    }
	
	public static void main(String[] args)
	{
		SpringApplication.run(ScheduleApp.class, args);
	}
}