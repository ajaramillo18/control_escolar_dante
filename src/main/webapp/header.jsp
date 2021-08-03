<div id="wrapper">
		<div id="header">
			<h2>Control Escolar</h2>
		</div>
		<div id="logout">
<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   id = "logoutForm" method="POST">
	
		
	
	</form:form>
	MENU:<a href="${pageContext.request.contextPath}/student/list">Alumnos</a>
	|<a href="${pageContext.request.contextPath}/course/list">Grupos</a>
	|<a href="${pageContext.request.contextPath}/report/list">Reportes</a>
	|<a href="#" onclick="document.getElementById('logoutForm').submit();">Cerrar Sesión</a>

		</div>
	</div>