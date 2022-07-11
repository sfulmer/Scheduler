<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Seth's Schedule</title>
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
		<link rel="stylesheet" href="css/index.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<script src="js/Calendar.js"></script>
		<script src="js/index.js"></script>
	</head>
	<body>
		<div id="scheduler">
			<div id="pnlLeft">
				<div id="calendar"></div>
			
				<table id="tblButtons">
					<tbody>
						<tr>
							<td>
								<button accesskey="s" id="btnSettings"><span class="shortcut">S</span>ettings...</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="pnlRight">
			</div>
		</div>
	</body>
</html>