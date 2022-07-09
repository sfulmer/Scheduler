<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Seth's Schedule</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
			$(() =>
			{
				function getInnerTable()
				{
					const days = ["Su", "M", "Tu", "W", "Th", "F", "Sa"];
					var tr = $("<tr></tr>");
				
					for(var iDayLength = 7, iDayLoop = 0; iDayLoop < iDayLength; iDayLoop++)
						tr.append($("<th></th>", {css:	{	"text-decoration": "underline"	}}).html(days[iDayLoop]));
				
					var thead = $("<thead></thead>").append(tr);
					var caption = $("<caption></caption>").html("")
					var table = $("<table></table>",	{	id:	"inner"	}).append(caption).append(thead);
					
					return(table);
				}
				
				function getOuterTable()
				{
					const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
					const today = new Date();
					var sMonth = months[today.getMonth()];
					var sYear = today.getYear() + 1900;
					var thPrevMonth = $("<th></th>", {	id:	"thPreviousMonth"	}).html("<<");
					var thNextMonth = $("<th></th>", {	id:	"thNextMonth"	}).html(">>");
					var thNow = $("<th></th>", {	id:	"thNow"	})
									.append($("<span></span>",	{"class":	"link"	}).html(sMonth))
									.append(
										$("<span></span>",	{	"class":	"link scrollArrow" })
											.append(
												$("<img />",	{	"src":		"/UpDownArrow.png"	
																,	"alt":		"Scroll"
																,	"usemap":	"#scrollmap"
																})))
									.append(", ")
									.append($("<span></span>",	{"class":	"link"	}).html(sYear))
									.append(
										$("<span></span>",	{	"class":	"link scrollArrow"	})
											.append(
												$("<img />",	{	"src":		"/UpDownArrow.png"	
																,	"alt":		"Scroll"
																,	"usemap":	"#scrollmap"
																})));
					var tr = $("<tr></tr>").append(thPrevMonth).append(thNow).append(thNextMonth);
					var thead = $("<thead></thead>").append(tr);
					var tbody = $("<tbody></tbody>")
						.append(
							$("<tr></tr>")
								.append(
									$("<td></td>",	{	"colspan":	3	})
										.append(getInnerTable())));
			
					var table = $("<table></table>",	{	id:	"outer"	}).append(thead).append(tbody);
					
					return(table);
				}
				
				var div = $("<div></div>").append(getOuterTable());
				
				$("body").append(div);
				
				$("#up").on("click", function(e)
				{
					e.preventDefault();
					
					alert("up");
				});
				
				$("#down").on("click", function(e)
				{
					e.preventDefault();
					
					alert("down");
				});

			});
		</script>
		<style type="text/css">
			body
			{
				cursor:	url("arrow.png"), default
			}
			
			span.link, th#thPreviousMonth, th#thNextMonth
			{
				cursor:	pointer;
			}
			
			span.scrollArrow
			{
				position: relative;
				top:	2px;
			}
			
			table#inner
			{
				border-spacing: 1em;
			}
			
			table#outer
			{
				border-spacing: 0px;
			}
		</style>
	</head>
	<body>
		<map name="scrollmap">
			<area shape="rect" coords="0,0,16,7" alt="up-arrow" id="up" href="javascript:alert('up');" />
			<area shape="rect" coords="0,8,16,16" alt="down-arrow" id="down" href="javascript:alert('down');" />
		</map>
	</body>
</html>