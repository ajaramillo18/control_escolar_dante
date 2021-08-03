<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Pagar</title>

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
		<h3>Forma de Pago</h3>
	
		<form:form action="payment" modelAttribute="student" method="POST" id="studentForm" >

			<!-- need to associate this data with student id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>Nombre:</label></td>
						<td><form:input path="firstName" disabled = "true" readonly="true"/>	
						<form:errors path="firstName" cssClass="error"/></td>
						
						
					</tr>
				
					<tr>
						<td><label>Apellido:</label></td>
						<td><form:input path="lastName" disabled = "true" />
						<form:errors path="lastName" cssClass="error"/></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" disabled = "true" />
						<form:errors path="email" cssClass="error"/></td>
					</tr>
					
					<tr>
						<td><label>Telefono:</label></td>
						<td><form:input path="phone" disabled = "true"/>
						<form:errors path="phone" cssClass="error"/></td>
					</tr>
													
					<tr>
						<td><label>Concepto de pago:</label></td>
						<td>
						<form:select path="course"  items="${allConcepts}" id="concepto" multiple="false" size="1"/>					
						</td>
					</tr>		
					
					<tr>
						<td><label>Detalle:</label></td>
						<td><form:input path="detail" />
						</td>
					</tr>			
					
					<tr>
						<td><label>Monto:</label></td>
						<td><form:input path="tuition" />
						</td>
					</tr>	
					
					 
					<tr>
						<td><label></label></td>
						<td><input type="button" value="Pagar" class="save" onclick = "pagar();"/></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
		</p>
	
	</div>

</body>
<c:choose> 
	<c:when test="${student.status == 'P'}">
		<script type="text/javascript">
						
			function pagar(){
				if(document.getElementById('concepto').value == "Colegiatura") {
				     if (confirm('La colegiatura ya ha sido pagada este mes. ¿Esta seguro que desea pagar de nuevo?')){				      	
				        document.getElementById('studentForm').submit();
				     }else{				     	
				     	return false;
				     }
				}
				else{
					document.getElementById('studentForm').submit();
				}		     			
			}
			
			//document.getElementById('studentForm').addEventListener("submit", pagar);			
			
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			function pagar(){
				document.getElementById('studentForm').submit();
			}
		</script>
	</c:otherwise>
	
</c:choose>


</html>










