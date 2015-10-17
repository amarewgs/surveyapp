<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="../partials/styles.jsp" %>
	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			$('#assetTable').dataTable();
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
                       <h5>Available Targets</h5>
                       <div style="float: right; bottom: 4px; margin-right: 36px;" class="btn-group">
                       		<button style="font-size: 12px;" type="button" class="btn btn-primary" onclick="javascript:window.location='<spring:url value="/admin/asset/form" htmlEscape="true" />'">New</button>
                       		<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                       			<span class="caret"></span>
                       			<span class="sr-only"></span>
                       		</button>
                       		<ul class="dropdown-menu" role="menu">
                       		<c:forEach items="${assetTypes}" var="assetType" varStatus="assetRow">
                       			<li><a href="<spring:url value="/admin/assetForm/${assetType.id }" htmlEscape="true" />">${assetType.name }</a></li>
                       			</c:forEach>
                       		</ul>
                       		<%-- <a href="<spring:url value="/admin/asset/form" htmlEscape="true" />" class="btn btn-small btn-add">
	                            <i class="btn-icon-only icon-plus"></i>
	                        </a> --%>
                       </div>
                   </div>	
                   <div class="box-content">					
								
						<table id="assetTable" class = "stripe table table-hover table-bordered tablesorter">
							<thead>
								<tr>
							      <th>Name</th>							      
							      <th>Description</th>
							      <th>Active</th>
							      <th>Target Type</th>
							      <th>Status</th>
							      <th></th>
					    		</tr>
					 		</thead>
				   			<tbody>
								<c:forEach items="${assetList}" var="asset" varStatus="assetRow">
									<tr> 
										<td>
											<c:out value="${asset.name}" />
										</td>	
										<td>
											<c:out value="${asset.description}" />
										</td>
										<td>
											<c:out value="${asset.active ? 'yes' : 'no'}" />
										</td>
										<td><c:out value="${asset.targetType.name }"></c:out></td>
										<td><c:out value="${asset.status.name }"></c:out></td>
										<td style="width: 100px; ">
												<a onclick="openDialog(this);" data-href="<spring:url value="/admin/asset/delete/${asset.id }" htmlEscape="true" />" class="btn btn-small btn-remove">
						                            <i class="btn-icon-only icon-trash"></i>
						                        </a>
						                        <a href="<spring:url value="/admin/asset/form/${asset.id }" htmlEscape="true" />" class="btn btn-small btn-edit">
						                            <i class="btn-icon-only icon-edit"></i>
						                        </a>
						                         <a href="<spring:url value="/admin/asset/detail/${asset.id }" htmlEscape="true" />" class="btn btn-small">
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