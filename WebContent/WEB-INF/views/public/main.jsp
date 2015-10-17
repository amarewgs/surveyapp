<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="partials/styles.jsp" %>

</head>
<body>
	<div class="container">
		<%@ include file ="partials/banner.jsp" %>
				
		<table class = "table">
			<thead>
				<tr>
			      <th>Userid</th>
			      <th>Username</th>
			      <th>Password</th>
			      <th>Enabled</th>
	    		</tr>
	 		</thead>
   			<tbody>
				<c:forEach items="${model.users}" var="user">
					<tr> 
						<td>
							<c:out value="${user.id}" />
						</td>
						<td>
							<c:out value="${user.userName}" />
						</td>
						<td>
							<c:out value="${user.password}" />
						</td>
						<td>
							<c:out value="${user.gender}" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
</body>
</html>