<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="../partials/styles.jsp" %>
	
	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			$('#sectionTable').dataTable();
		} );

	</script>
	
</head>
<body>

	<%@ include file ="../partials/banner.jsp" %>
<div id="body-container">
  <div id="body-content">	
    <%@ include file="../partials/target_menu.jsp" %>
     <section class="page container">    
       <div class="row">
       <%@ include file="../partials/treemenu.jsp" %>
           <div class="span12">
               <div class="box">
                   <div class="box-header">
                       <i class="icon-bookmark"></i>
                       <h5>Available Sections</h5>
                       <div style="float: right;">
                       		<a href="<spring:url value="/admin/section/form" htmlEscape="true" />" class="btn btn-small btn-add">
	                            <i class="btn-icon-only icon-plus"></i>
	                        </a>
                       </div>
                   </div>	
                   <div class="box-content">					
								
						<table id="sectionTable" class = "stripe table table-hover table-bordered tablesorter">
							<thead>
								<tr>
							      <th>Name</th>							      
							      <th>Description</th>
							      <th>is active</th>
							      <th></th>
					    		</tr>
					 		</thead>
				   			<tbody>
								<c:forEach items="${sectionList}" var="section" varStatus="sectionRow">
									<tr> 
										<td>
											<c:out value="${section.name}" />
										</td>	
										<td>
											<c:out value="${section.description}" />
										</td>
										<td>
											<c:out value="${section.active ? 'yes' : 'no' }"></c:out>
										</td>
										<td style="width: 100px; ">
														<a data-href="<spring:url value="/admin/section/delete/${section.id }" htmlEscape="true" />" onclick="openDialog(this)" class="btn btn-small btn-remove">
								                            <i class="btn-icon-only icon-trash"></i>
								                        </a>
								                        <a href="<spring:url value="/admin/section/form/${section.id }" htmlEscape="true" />" class="btn btn-small btn-edit">
								                            <i class="btn-icon-only icon-edit"></i>
								                        </a>
								                         <a href="<spring:url value="/admin/section/detail/${section.id }" htmlEscape="true" />" class="btn btn-small">
								                            <i class="btn-icon-only icon-folder-open"></i>
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
   <div id="dialog" title="Confirm Delete">
        <p>Are you sure you want to delete this record?</p>
    </div>
<%@ include file ="../partials/delete_dialog.jsp" %>
</body>
</html>