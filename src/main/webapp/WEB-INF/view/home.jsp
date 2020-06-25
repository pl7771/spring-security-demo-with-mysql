<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!doctype html>

<html>	
	<head>
		<title>Ilya Demo Home Page</title>
	</head>

	<body>
	  	<h2>WELCOME to home page HURRAAAYY</h2>
	  	<hr>
	  	<p>
	  	This is the home page
	  	</p>
	  	<hr>
	  	<!-- display user name and role -->
		  	<p>
		  	User: <security:authentication property="principal.username"/>
		  	<br>
		  	Role: <security:authentication property="principal.authorities"/>
		  	</p>
	  	<hr>
	  	
	  	<!-- add link only for leaders -->
	  	<!-- make it visible onlye for managers -->
	  	<security:authorize access="hasRole('manager')">
		  	<p>
		  		<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
		  		(Only for managers)
		  	</p>
	  	</security:authorize>
	  	
	  	<hr>
	  	
	  	<!-- add link only for admins -->
	  	<!-- make it visible onlye for admins -->
	  	<security:authorize access="hasRole('admin')">
		  	<p>
		  		<a href="${pageContext.request.contextPath}/admins">Admin page</a>
		  		(Only for managers)
		  	</p>
	  	</security:authorize>
	  	
	  	<hr>
	  	<!-- add logout button -->
	  	<form:form 
	  		action="${pageContext.request.contextPath}/logout"
	  		method="POST">
	  		<input type="submit" value="Logout" />
	  	</form:form>
	  	
	</body>
</html>