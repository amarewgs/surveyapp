<%@page import="java.io.File"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
<meta charset="utf-8">
<title><fmt:message key="app.title" /></title>
<%@ include file="../partials/styles.jsp"%>
<%@ page session="false"%>
<%
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-control",
			"no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");
%>
</head>
<body>
<%@ include file="../partials/banner.jsp"%>
	<div id="body-container">
	   <div id="body-content">	
	    <%@ include file="../partials/target_menu.jsp" %>
			<section class="page container">
				<div class="row">
				<%@ include file="../partials/treemenu.jsp" %>
					<div class="span12">
						<div class="box" style="display: inline-block;">
							<div class="box-header">
								<i class="icon-bookmark"></i>
								<h5>Section Detail</h5>
								<div style="float: right;">
									<a href="<spring:url value="/admin/section/list" htmlEscape="true" />" class="btn btn-small btn-remove">
			                            <i class="btn-icon-only icon-remove"></i>
			                        </a>
									<a id="delete_link" data-href="<spring:url value="/admin/section/delete/${section.sectionId }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small trash">
			                            <i class="btn-icon-only icon-trash"></i>
			                        </a>									
			                        <a href="<spring:url value="/admin/section/form/${section.sectionId }" htmlEscape="true" />" class="btn btn-small btn-edit">
			                            <i class="btn-icon-only icon-edit"></i>
			                        </a>
								</div>
							</div>
							<div class="box-content">
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Name</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${section.name}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Description</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${section.description}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Active</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${section.active ? 'Yes' : 'No'}</label>
										</div>
									</div>
									<fieldset>
										<legend>Available Attributes</legend>
										<c:forEach items="${section.attributes}" var="attribute" varStatus="attributeRow">
											<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
												<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">label</label>
												<div class="" style="display: inline-block; width: 50%; float: none;">
													<label class="span4" style="">${attribute.label}</label>
												</div>
											</div>
											<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
												<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">description</label>
												<div class="" style="display: inline-block; width: 50%; float: none;">
													<label class="span4" style="">${attribute.description}</label>
												</div>
											</div>
										</c:forEach>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>