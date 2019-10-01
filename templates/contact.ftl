<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Contact Us</title>
		<link href="form.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<h1>Contact Us Form</h1>
		
		<div id = "form">
		<p></p>
		<form action="Servlet" method="post">
			Name: <input type="text" name="name"/> <br/> 
			Email: <input type="text" name="email"/> <br/> 
			Message: <br/><textarea name="message" rows="14" cols="50"></textarea><br/>
			<p></p>
			<input id = "button" type ="submit" name = "contactButton" value ="Submit">
			<p></p>
		</form>
		
		</div>
	</body>
	
</html>