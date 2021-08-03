<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	<title>Lista de Alumnos</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>
	
	
	<%@ include file="/header.jsp" %>

	
	

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
		
			<input type="button" value="Nuevo Estudiante"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
			
			<br/>
			<br/>
			<form:form action="search" method="GET">
				Busqueda por apellido:
				<br/>
				<input  id="name" name="name" size="50"/>
				<input type="submit" value="Buscar" class="save" />		
				
			<a href="${pageContext.request.contextPath}/student/list">    Reset</a>
		
			
			
			</form:form>
			
			<br/>
			<br/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Email</th>
					<th>Telefono</th>
					<th>Grupo</th>
					<th>Accion</th>
				</tr>
				
				<!-- loop over and print our students -->
				<c:forEach var="tempStudent" items="${students}">
				
					<!-- construct an "update" link with student id -->
					<c:url var="updateLink" value="/student/showFormForUpdate">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>					

					<!-- construct an "delete" link with student id -->
					<c:url var="deleteLink" value="/student/delete">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>	
					
					<!-- construct an "payment" link with student id -->
					<c:url var="paymentLink" value="/student/showFormForPayment">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>				
					
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.email} </td>
						<td> ${tempStudent.phone} </td>
						
						
						
						<td>
						<c:forEach items="${tempStudent.courses}" var="item">
						    ${item.name} <br>
						</c:forEach> </td>
						
						<td>
						
							<!-- display the payment link -->
							
							
							    <a href="${paymentLink}">Pagar</a>
							
							
							
							|
							<!-- display the update link -->
							<a href="${updateLink}">Modificar</a>
							<!-- 
							USER: <security:authentication property="principal.username"/>
							ROL: <security:authentication property="principal.authorities"/>
							-->
							<!-- this link is only visible to Director user -->
							<security:authorize access="hasAuthority('DIRECTOR')">
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('¿Esta seguro que desea eliminar a este alumno?'))) return false">Borrar</a>
							
							 </security:authorize>
							
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









