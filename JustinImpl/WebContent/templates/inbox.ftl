<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Bulldawg Beds</title>
		<link href="form.css" type="text/css" rel="stylesheet" />
	</head>
	
	<body>

		
		
		<table>
	  		<tr><th>Messages</th></tr>
	  	  	<#list messageList as read>
	  	 	 <tr><td>${read.message}</td></tr>
	  	  	</#list>
  		</table>
		
		
		
	</body>	
</html>