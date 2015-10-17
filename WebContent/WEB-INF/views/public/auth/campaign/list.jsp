<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
	<meta charset="utf-8">
	<title>${title}</title>
	<%@ include file ="../../partials/styles.jsp" %>
<% response.setHeader("pragma", "no-cache");              
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	response.setHeader("Expires", "0");  %>
	
	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			//$("#questionnaireTreeview").treeview({ speed: "fast", collapsed: true});
			
			$("#campaignTree").treetable({ expandable: true });
			
			// Drag & Drop Example Code
			/* $("#campaignTree.file, #questionnaireTree .folder").draggable({
			  helper: "clone",
			  opacity: .75,
			  refreshPositions: true,
			  revert: "invalid",
			  revertDuration: 300,
			  scroll: true
			});
			$('table#campaignTree tbody tr').mousedown(function () {
		        $('tr.selected').removeClass('selected');
		        $(this).addClass('selected');
		    });
		    $("table#campaignTree tbody tr span").mousedown(function () {
		        $($(this).parents("tr")[0]).trigger("mousedown");
		    }); */
		    
		    //$("#questionnaireTree").dataTable();
		    
		} );

	</script>
</head>
<body>
		<%@ include file ="../banner.jsp" %>
<div id="body-container">
   <div id="body-content">	
		    <section class="page container">
		       <div class="row">
		       		<%@ include file="../../partials/treemenu.jsp" %>
						 <div class="span12">
		               		<div class="box">
			                   <div class="box-header">
			                       <i class="icon-bookmark"></i>
			                       <h5>Campaigns</h5>
			                       <%-- <div style="float: right;">
			                       		<a href="<spring:url value="/admin/question/questionnaire" htmlEscape="true" />" class="btn btn-small btn-add">
				                            <i class="btn-icon-only icon-plus"></i>
				                        </a>
				                        <button id="submit-button" type="submit"
												class="btn btn-small btn-remove" name="action" value="CONFIRM"><i class="btn-icon-only icon-remove"></i></button>
			                       </div> --%>
			                   </div>	
			                   <div class="box-content">
				                   <table id="campaignTree">
				                   <thead>
				                   	<th>name</th>
				                   	<th>Description</th>
				                   	<th></th>
				                   	<th></th>
				                   </thead>
				                   	 <tbody>
				                   	 <c:forEach items="${campaigns}" var="campaign" varStatus="campaignRow">
										  <tr data-tt-id="${campaign.campaignId }">
										    <td><span><c:out value="${campaign.name}" /></span></td>
										    <td><span style="padding: 0;"><c:out value="${campaign.description}" /></span></td>
										    <td></td>
										    <td></td>
											</tr>
					                   	 <c:forEach items="${campaign.questionnaires}" var="questionnaire" varStatus="questionnaireRow">
										  <tr data-tt-id="${campaign.campaignId }${questionnaire.groupId }" data-tt-parent-id="${campaign.campaignId }">
										    <td><span><c:out value="${questionnaire.categoryName}" /></span></td>
										    <td><span style="padding: 0;"><c:out value="${questionnaire.description}" /></span></td>										    
											<td>
												<c:if test="${questionnaire.remainingQuestions != 0 && questionnaire.questionSize > 0}">
													<a href="<spring:url value="/public/auth/questionnaire/${questionnaire.groupId }/${campaign.campaignId }" htmlEscape="true" />"> <c:out value="${questionnaire.questionSize}" /> questions (${questionnaire.remainingQuestions} remained)</a>
												</c:if>
												<c:if test="${questionnaire.remainingQuestions == 0}">
													<span>completed</span>
												</c:if>
											</td>
											<td>
											<c:if test="${questionnaire.questionSize > 0 && questionnaire.questionCompletedSize > 0}">
												<a href="<spring:url value="/public/auth/questionnaireCompleted/${questionnaire.groupId }/${campaign.campaignId }" htmlEscape="true" />"> <c:out value="${questionnaire.questionCompletedSize}" /> questions completed</a>
											</c:if>
											</td>										    
										  </tr>
											  <c:forEach items="${questionnaire.questions}" var="question" varStatus="questionRow">
											  <tr ondblclick="" data-tt-id="${question.id }" data-tt-parent-id="${campaign.campaignId }${questionnaire.groupId }">
											    <td><span style="display: inline-block; padding-left: 2.5em;"><c:out value="${question.name}" /></span></td>
											    <td><span style="padding: 0;"><c:out value="${question.question}" /></span></td>
											    <td><span style="padding: 0;">${question.text}</span></td>
											    
											  </tr>
											   </c:forEach>
										  </c:forEach>
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
			