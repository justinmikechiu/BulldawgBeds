<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign in</title>
		<link type="text/css" rel="stylesheet" href="form.css" />
		<script src ="scripts/mycode.js"></script>
	</head>
	
	<div class = "wrapper">
		<body>
			<h1>Bulldawg Beds Sign in</h1>
			
			<#if registerSuccessful??>
			<p>Thanks for registering ${name}!</p>
			</#if>
			
			<#if failedLogin??>
			<p>Erorr! Please make sure to input the correct login information.</p>
			</#if>
		
				<div id = "form">
					<p></p>
						<form action="Servlet" method="post">
							Email: <input type="text" name="email"/><br/>
							
							Password: <input type="password" name="password"/> <br/> 
							<input id ="button" type ="submit" name = "login" value ="Login">
							<p></p>
						</form>
				</div>
		</body>
	</div>
</html>