<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
	<title>Lista de Grupos</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>
	
	
	<%@ include file="/header.jsp" %>	
	

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Course -->
		
			<input type="button" value="Nuevo Grupo"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
			
			<br/>
			<br/>
			<form:form action="search" method="GET">
				Busqueda:
				<br/>
				<input  id="name" name="name" size="50"/>
				<input type="submit" value="Buscar" class="save" />		
				
			<a href="${pageContext.request.contextPath}/course/list">    Reset</a>
		
			
			
			</form:form>
			
			<br/>
			<br/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>Nombre</th>
					<th>Maestro</th>
					<th>Total Alumnos</th>
					<th>Status</th>
				</tr>
				
				<!-- loop over and print our courses -->
				<c:forEach var="tempCourse" items="${courses}">
				
					<!-- construct an "update" link with course id -->
					<c:url var="updateLink" value="/course/showFormForUpdate">
						<c:param name="courseId" value="${tempCourse.id}" />
					</c:url>					

					<!-- construct an "delete" link with course id -->
					<c:url var="deleteLink" value="/course/delete">
						<c:param name="courseId" value="${tempCourse.id}" />
					</c:url>	
					
									
					
					<tr>
						<td> ${tempCourse.name} </td>
						<td> ${tempCourse.teacher} </td>
						<td> ${tempCourse.students.size()} </td>
						<td> ${tempCourse.status} </td>
				
						
						<td>
						
													
							
							<!-- display the update link -->
							<a href="${updateLink}">Modificar</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('¿Esta seguro que desea eliminar a este grupo?'))) return false">Borrar</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









