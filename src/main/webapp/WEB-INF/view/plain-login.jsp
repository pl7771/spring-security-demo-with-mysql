<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html>	
	<head>
		<title>Login form</title>
		<style>
			.failed {
				color: red;
			}
		</style>
	</head>

	<body>
	  	<h2>My custom login page</h2>
	  	<form:form 
	  		action="${pageContext.request.contextPath}/authenticateTheUser"
	  		method="POST">
	  	
	  	
	  	<!-- Check for login errror -->
	  	
	  	<c:if test="${param.error != null}">
	  		<b class="failed">Sorry, invalid name</b>
	  	</c:if>
	  	
	  		<p>
	  		User name: <input type="text" name="username">
	  		</p>
	  		<p>
	  		Password: <input type="password" name="password">
	  		</p>
	  		<input type="submit" value="Login">
	  		
	  	</form:form>
	  	
	  	
	</body>
</html>