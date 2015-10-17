<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="partials/styles.jsp" %>

	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			$('#usersTable').dataTable({
				"order": [[ 1, 'asc' ]],
				stateSave: true
			});
		} );

	</script>
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
                       <h5>Available Users</h5>
                       <div style="float: right;">
                       		<a href="<spring:url value="/admin/register" htmlEscape="true" />" class="btn btn-small btn-add">
	                            <i class="btn-icon-only icon-plus"></i>
	                        </a>
                       </div>
                   </div>	
                   <div class="box-content">					
								
						<table id="usersTable" class = "stripe table table-hover table-bordered tablesorter">
							<thead>
								<tr>
							      <th>Userid</th>							      
							      <th>Email</th>
							      <th>First Name</th>
							      <th>Last Name</th>
							      <th>Role</th>
							      <th>active</th>
							      <th></th>
					    		</tr>
					 		</thead>
				   			<tbody>
								<c:forEach items="${userList}" var="user" varStatus="userRow">
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
											<c:out value="${user.role.name }" ></c:out>
										</td>
										<td>
											<c:out value="${user.active ? 'yes' : 'no'}" />
										</td>
										<td style="width: 80px; ">
												<a onclick="openDialog(this);" data-href="<spring:url value="/admin/users/delete/${user.id }" htmlEscape="true" />" class="btn btn-small btn-remove">
						                            <i class="btn-icon-only icon-remove"></i>
						                        </a>
						                        <a href="<spring:url value="/admin/users/form/${user.id }" htmlEscape="true" />" class="btn btn-small btn-edit">
						                            <i class="btn-icon-only icon-edit"></i>
						                        </a>
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