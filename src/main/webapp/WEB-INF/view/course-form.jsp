<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Guardar Grupo</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-form-style.css">
</head>

<body>
	
		<%@ include file="/header.jsp" %>

	<div id="container">
		<h3>Guardar Grupo</h3>
	
		<form:form action="saveCourse" modelAttribute="course" method="POST">

			<!-- need to associate this data with course id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>Nombre:</label></td>
						<td><form:input path="name" />	
						<form:errors path="name" cssClass="error"/></td>
						
						
					</tr>
				
					<tr>
						<td><label>Nombre Maestro:</label></td>
						<td><form:input path="teacher" />
						<form:errors path="teacher" cssClass="error"/></td>
					</tr>
		
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/course/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










