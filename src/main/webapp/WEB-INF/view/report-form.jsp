<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Reportes</title>

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
		<h3>Reportes</h3>
	
		<h4>
		
		<a href="${pageContext.request.contextPath }/report/report_no_pay" target="_blank">Generar reporte de alumnos sin pagar en el mes</a>
		
		</h4>
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










