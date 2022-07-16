var Calendar = (function()
{
	const days = ["Su", "M", "Tu", "W", "Th", "F", "Sa"];
	const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]; 
	var mDtThisMonth = new Date();
	
	function getCalendarBody()
	{
		const iFirstDay = -getFirstDayOfMonth();
		const iLastDate = getLastDateOfMonth();
		var iLoop = iFirstDay;
		
		var tbody = $("<tbody></tbody>");
		
		while(iLoop <= iLastDate)
			{
			var iDay = 0;
			var tr = $("<tr></tr>");
			
			for(; iDay < 7; iLoop++, iDay++)
				{
				var td = $("<td></td>");
				
				if((iLoop >= 0) && (iLoop < iLastDate))
					td.append($("<span></span>",	{	"class":	"link" }).append(iLoop + 1));
				else
					td.append("&nbsp;");
				
				tr.append(td);
				}
		
			tbody.append(tr);
			}
			
		return(tbody);
	}
	
	function getDayLabelHeader()
	{
		var tr = $("<tr></tr>");
		var thead = $("<thead></thead>");
		
		for(var iDayLength = 7, iDayLoop = 0; iDayLoop < iDayLength; iDayLoop++)
			tr.append($("<th></th>", {css:	{	"text-decoration": "underline"	}}).html(days[iDayLoop]));
			
		thead.append(tr)
		
		return(thead);
	}
	
	function getFirstDayOfMonth()
	{
		var dtFirstDay = new Date();
		dtFirstDay.setMonth(getThisMonth().getMonth());
		dtFirstDay.setFullYear(getThisMonth().getFullYear());
		dtFirstDay.setDate(1);
		
		var iFirstDay = dtFirstDay.getUTCDay();
		
		return(iFirstDay);
	}
	
	function getImageMap(sPrefix)
	{
		return	(	$("<map></map>",	{ "name":	sPrefix + "_scrollmap"	})
						.append(
							$("<area />",	{	"alt":		"up-arrow"
											,	"coords":	"0,0,16,7"
											,	"href":		"#"
											,	"shape":	"rect"
											})
								.click(function(e)
								{
									e.preventDefault();
									
									eval("set" + sPrefix.substr(0, 1).toUpperCase() + sPrefix.substr(1) + "(get" + sPrefix.substr(0, 1).toUpperCase() + sPrefix.substr(1) + "() + 1);");
								}))
						.append(
							$("<area />",	{	"alt":		"down-arrow"
											,	"coords":	"0,8,16,16"
											,	"href":		"#"
											,	"shape":	"rect"
											})
								.click(function(e)
								{
									e.preventDefault();
									
									eval("set" + sPrefix.substr(0, 1).toUpperCase() + sPrefix.substr(1) + "(get" + sPrefix.substr(0, 1).toUpperCase() + sPrefix.substr(1) + "() - 1);");
								})));
	}
			
	function getInnerTable()
	{
		return($("<table></table>",	{	id:	"inner"	}).append(getDayLabelHeader()).append(getCalendarBody()));
	}
			
	function getLastDateOfMonth()
	{
		var dtLastDay = new Date();
		dtLastDay.setMonth(getThisMonth().getMonth() + 1);
		dtLastDay.setFullYear(getThisMonth().getFullYear());
		dtLastDay.setDate(0);
		
		var iLastDate = dtLastDay.getDate();
		
		return(iLastDate);
	}
	
	function getMonth()
	{
		return(getThisMonth().getMonth());
	}
	
	function getMonthHeader()
	{
		return	(	$("<th></th>", {	id:	"thNow"	})
						.append($("<span></span>",	{	"class":	"link"
													,	"id":		"thNow_month"
													}).html(months[getThisMonth().getMonth()]))
						.append(
							$("<span></span>",	{	"class":	"link scrollArrow" })
								.append(getScrollComponent("month"))
								.append(getImageMap("month")))
						.append(", ")
						.append($("<span></span>",	{	"class":	"link"
													,	"id":		"thNow_year"
													}).html(getThisMonth().getFullYear()))
						.append(
							$("<span></span>",	{	"class":	"link scrollArrow" })
								.append(getScrollComponent("year"))
								.append(getImageMap("year"))));
	}
	
	function getMonthHeaderRow()
	{
		var thPrevMonth = $("<th></th>", {	id:	"thPreviousMonth"	})
							.html("<<")
							.click(function()
							{
								setMonth(getMonth() - 1);
							});
		var thNextMonth = $("<th></th>", {	id:	"thNextMonth"	})
								.html(">>")
								.click(function()
								{
									setMonth(getMonth() + 1);
								});
					
		return($("<tr></tr>").append(thPrevMonth).append(getMonthHeader()).append(thNextMonth));
	}
	
	function getMonthName(iMonth)
	{
		if(typeof(iMonth) == "undefined")
			iMonth = getMonth();
			
		return(months[iMonth]);
	}
			
	function getOuterTable()
	{
		var thead = $("<thead></thead>").append(getMonthHeaderRow());
		var tbody = $("<tbody></tbody>")
			.append(
				$("<tr></tr>")
					.append(
						$("<td></td>",	{	"colspan":	3	})
							.append(getInnerTable())));

		var table = $("<table></table>",	{	id:	"outer"	})
						.append(thead)
						.append(tbody);
		
		return(table);
	}
	
	function getScrollComponent(sPrefix)
	{
		return	(	$("<img />",	{	"src":		"images//UpDownArrow.png"
									,	"alt":		"Scroll"
									,	"usemap":	"#" + sPrefix + "_scrollmap"
									}));
	}
	
	function  getThisMonth()
	{
		return(mDtThisMonth);
	}
	
	function getYear()
	{
		return(getThisMonth().getFullYear());
	}
	
	function redrawCalendarDays()
	{
		$("table#inner tbody").remove();
		$("table#inner").append(getCalendarBody());
	}
	
	function setMonth(iMonth)
	{
		const iOldYear = getYear();
		
		if(typeof(iMonth) == "undefined")
			iMonth = getMonth();
		
		if(iMonth > 11)
			{
			getThisMonth().setMonth(iMonth % 12);
			getThisMonth().setYear(getYear() + (iMonth / 12));
			}
		else
			getThisMonth().setMonth(iMonth);
			
		$("#thNow_month").html(getMonthName());
		
		if(iOldYear != getYear())
			$("#thNow_year").html(getYear());
			
			redrawCalendarDays();
	}
	
	function setYear(iYear)
	{
		if(typeof(iYear) == "undefined")
			iYear = getYear();
			
		getThisMonth().setFullYear(iYear);
		
		$("#thNow_year").html(iYear);
		
		redrawCalendarDays();
	}
	
	return(getOuterTable());
});