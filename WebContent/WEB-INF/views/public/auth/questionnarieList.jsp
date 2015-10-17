<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
	<meta charset="utf-8">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="../partials/styles.jsp" %>
<% response.setHeader("pragma", "no-cache");              
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	response.setHeader("Expires", "0");  %>
	
	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			$('#questionTable').dataTable();
		} );

	</script>
</head>
<body>
		<%@ include file ="banner.jsp" %>
<div id="body-container">
   <div id="body-content">		
		    <section class="page container">
		       <div class="row">
		       <%@ include file="../partials/treemenu.jsp" %>
		           <div class="span12">
		               <div class="box">
		                   <div class="box-header">
		                       <i class="icon-bookmark"></i>
		                       <h5>Questionnaires for ${campaign.name }</h5>
		                        <div style="float: right;">
				                      <%--  <button type="button"
											onclick="window.location='<spring:url value="/public/auth/campaigns" htmlEscape="true" />'" class="btn"
											name="action" value="CANCEL">Back</button> --%>
											
											<a href="<spring:url value="/public/auth/userCampaigns" htmlEscape="true" />" class="btn btn-small btn-add">
				                            <i class="btn-icon-only icon-remove"></i>
				                        </a>
								</div>
		                   </div>	
		                   <div class="box-content">
		                   
							<c:if test="${null != questionnaires }">
								<table id="questionTable" class="stripe table table-hover table-bordered tablesorter">
									<thead>
										<tr>
									      <th>Name</th>
									      <th>Description</th>
									      <th>Questions</th>
									      <th>completed</th>
							    		</tr>
							 		</thead>
						   			<tbody>
										<c:forEach items="${questionnaires}" var="questionnaire">
											<tr> 
												<td>
													<c:out value="${questionnaire.categoryName}" />
												</td>
												<td>
													<c:out value="${questionnaire.description}" />
												</td>
												<td>
													<c:if test="${questionnaire.remainingQuestions != 0 && questionnaire.questionSize > 0}">
														<a href="<spring:url value="/public/auth/questionnaire/${questionnaire.groupId }/${campaign.id }" htmlEscape="true" />"> <c:out value="${questionnaire.questionSize}" /> questions (${questionnaire.remainingQuestions} remained)</a>
													</c:if>
													<c:if test="${questionnaire.remainingQuestions == 0}">
														completed
													</c:if>
												</td>
												<td>
												<c:if test="${questionnaire.questionSize > 0 && questionnaire.questionCompletedSize > 0}">
													<a href="<spring:url value="/public/auth/questionnaireCompleted/${questionnaire.groupId }/${campaign.id }" htmlEscape="true" />"> <c:out value="${questionnaire.questionCompletedSize}" /> questions completed</a>
												</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</c:if>
								<c:if test="${null == questionnaires }">
									No Questionnaires Found
								</c:if>
								</div>
							</div>
						</div>
					</div>
				</section>
		</div>
	</div>
</body>
</html>
			