<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="partials/styles.jsp" %>

</head>
<body>

	<%@ include file ="partials/banner.jsp" %>
<div id="body-container">
  <div id="body-content">	
  <%@ include file="partials/menu.jsp" %>
     <section class="page container">    
       <div class="row">
		<%@ include file="partials/treemenu.jsp" %>
           <div class="span12">
               <div class="box">
                   <div class="box-header">
                       <i class="icon-bookmark"></i>
                       <h5>Questionnaires Completed By users</h5>
                       <div style="float: right;">
                       		<a href="<spring:url value="/admin/register" htmlEscape="true" />" class="btn btn-small btn-add">
	                            <i class="btn-icon-only icon-plus"></i>
	                        </a>
                       </div>
                   </div>	
                   <div class="box-content">					
								
						<table class = "stripe table table-hover table-bordered tablesorter">
							<thead>
								<tr>
							      <th>Userid</th>							      
							      <th>Email</th>
							      <th>First Name</th>
							      <th>Last Name</th>
							      <th></th>
					    		</tr>
					 		</thead>
				   			<tbody>
								<c:forEach items="${users}" var="user" varStatus="userRow">
									<tr> 
										<td>
											<c:out value="${user.userName}" />
										</td>
										<td>
											<c:out value="${user.email}" />
										</td>
										<td>
											<c:out value="${user.firstName}" />
										</td>
										<td>
											<c:out value="${user.lastName}" />
										</td>
										<td>
											<a href="<spring:url value="/admin/questionnairesCompleted/${user.id }" htmlEscape="true" />">questionnaire completed</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>
</div>
</body>
</html>