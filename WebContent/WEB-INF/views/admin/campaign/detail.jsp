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
	    <%@ include file="../partials/menu.jsp" %>	
			<section class="page container">
				<div class="row">
				<%@ include file="../partials/treemenu.jsp" %>	
					<div class="span12">
						<div class="box" style="display: inline-block;">
							<div class="box-header">
								<i class="icon-bookmark"></i>
								<h5>Campaign Detail</h5>
								<div style="float: right;">
									<a href="<spring:url value="/admin/campaign/list" htmlEscape="true" />" class="btn btn-small btn-remove">
			                            <i class="btn-icon-only icon-remove"></i>
			                        </a>
									<a id="delete_link" data-href="<spring:url value="/admin/campaign/delete/${campaign.campaignId }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small trash">
			                            <i class="btn-icon-only icon-trash"></i>
			                        </a>									
			                        <a href="<spring:url value="/admin/campaign/form/${campaign.campaignId }" htmlEscape="true" />" class="btn btn-small btn-edit">
			                            <i class="btn-icon-only icon-edit"></i>
			                        </a>
								</div>
							</div>
							<div class="box-content">
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Name</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${campaign.name}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Description</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${campaign.description}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Target</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${campaign.asset.name}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Active</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${campaign.active ? 'Yes' : 'No'}</label>
										</div>
									</div>
								</div>
								
								<c:if test="${null != questionnaires }">
									<div>
									<legend>Available Questionnaires</legend>
									<table id="questionnaireTable" class="stripe table table-hover table-bordered tablesorter">
										<thead>
											<tr>
										      <th>Name</th>
										      <th>Description</th>
										      <th>Questions</th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${questionnaires}" var="questionnaire" varStatus="questionnaireRow">
												<c:set var="found" value="false"/> 
												<c:set var="questionnaireIdVal" value=""/> 
												<tr> 
													<td>
														<c:out value="${questionnaire.categoryName}" />
													</td>
													<td>
														<c:out value="${questionnaire.description}" />
													</td>
													<td>
														<a href="<spring:url value="/admin/questionnaire/detail/${questionnaire.groupId }" htmlEscape="true" />"> <c:out value="${questionnaire.questionSize}" /> questions</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
									</c:if>
									<c:if test="${null == questionnaires }">
										No Questionnaires Found
									</c:if>
									<div>
									<legend>Users in the campaign</legend>
										<table id="usersTable" class = "stripe table table-hover table-bordered tablesorter">
											<thead>
												<tr>
											      <th>Userid</th>							      
											      <th>Email</th>
											      <th>First Name</th>
											      <th>Last Name</th>
											      <th>Role</th>
											      <th>active</th>
									    		</tr>
									 		</thead>
								   			<tbody>
												<c:forEach items="${campaign.users}" var="user" varStatus="userRow">
												<c:set var="found" value="false"/> 
												<c:set var="userIdVal" value=""/> 
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
													</tr>
												</c:forEach>
											</tbody>
										</table>
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