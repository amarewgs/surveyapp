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
		<%@ include file ="../partials/banner.jsp" %>
<div id="body-container">
   <div id="body-content">	
    	<%@ include file="../partials/menu.jsp" %>	
		    <section class="page container">
		       <div class="row">
				<%@ include file="../partials/treemenu.jsp" %>
		           <div class="span12">
		               <div class="box">
		                   <div class="box-header">
		                       <i class="icon-bookmark"></i>
		                       <h5>Questionnaires completed by ${user.fullName }</h5>
		                       <div style="float: right;">
									<a href="<spring:url value="/admin/users/questionnaireResults" htmlEscape="true" />" class="btn btn-small btn-save"> <i
										class="btn-icon-only icon-remove"></i>
									</a>
								</div>
		                       <%-- <div style="float: right;">
		                       		<a href="<spring:url value="/admin/question/questionnaire" htmlEscape="true" />" class="btn btn-small btn-add">
			                            <i class="btn-icon-only icon-plus"></i>
			                        </a>
		                       </div> --%>
		                   </div>	
		                   <div class="box-content">
		                   
							<c:if test="${null != questionnaires }">
								<table id="questionTable" class="stripe table table-hover table-bordered tablesorter">
									<thead>
										<tr>
									      <th>Name</th>
									      <th>Description</th>
									      <th>Questions</th>
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
													<a href="<spring:url value="/admin/question/questionsCompleted/${questionnaire.groupId }/${user.id }" htmlEscape="true" />"> <c:out value="${questionnaire.questionCompletedSize}" /> questions</a>
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
			