<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
	<meta charset="utf-8">
	<title>${title}</title>
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
    	<%@ include file="../partials/target_menu.jsp" %>	
		    <section class="page container">
		       <div class="row">
		           <div class="span13">
		               <div class="box" style="display:inline-block; width: 100%">
		               
					  	<spring:url var = "action" value='/admin/asset/questionnaire/save' htmlEscape="true"/>
						<form:form style="width: 100%" method="post" action="${action}" class="form" commandName="assetQuestionnaireForm" id="assetQuestionnaireForm">
			                   <div class="box-header">
			                       <i class="icon-bookmark"></i>
			                       <h5>Campaign For</h5>
			                       <div style="float: right;">
			                       		<span>Name</span> ${asset.name }
			                       		<span>Description</span> ${asset.description }
			                       		<span>Status</span> ${asset.status.name }
			                       		<span>Asset Type</span> ${asset.assetType.name }
			                       </div>
			                   </div>	
			                   <div class="box-content">
			                   
								<c:if test="${null != questionnaires }">
									<div style="width: 100%; display: inline-block;">
										<form:errors path="*" cssClass="errorblock span5" element="div" />
									</div>
									<table id="questionTable" class="stripe table table-hover table-bordered tablesorter">
										<thead>
											<tr>
												<th></th>
										      <th>Name</th>
										      <th>Description</th>
										      <th>Questions</th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${questionnaires}" var="questionnaire" varStatus="questionnaireRow">
												<tr> 
													<td>
														<input type="checkbox" id="chkbox${questionnaireRow.index }" onclick="insertQuestionnaireId(this, ${questionnaire.groupId}, ${questionRow.index })" />
													</td>
													<td>
														<c:out value="${questionnaire.categoryName}" />
													</td>
													<td>
														<c:out value="${questionnaire.description}" />
													</td>
													<td>
														<a href="<spring:url value="/admin/question/questionnaire/${questionnaire.groupId }" htmlEscape="true" />"> <c:out value="${questionnaire.questionSize}" /> questions</a>
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
									<div style="width:90%;">
									<footer id="submit-actions" class="form-actions">
										<button id="submit-button" type="submit"
											class="btn btn-primary" name="action" value="CONFIRM">Save</button>
										<button type="button"
											onclick="window.location='<spring:url value="/admin/asset/list" htmlEscape="true" />'" class="btn"
											name="action" value="CANCEL">Cancel</button>
									</footer>
								</div>
								</form:form>
							</div>
						</div>
					</div>
				</section>
		</div>
	</div>
    
	<script type="text/javascript">

	var FieldCount=0; //to keep track of text box added

	function insertQuestionnaireId(obj, id) {
		if($(obj).is(":checked")){
		 	$(obj).parent().append('<input type="hidden" id="groupIds' + id + '" name="groupIds[' + FieldCount + ']" value="' + id + '"/>');
		 	FieldCount++;
		} else {
			FieldCount--;
			$('#groupIds'+id).remove();
		}
	}
	$(document).ready(function(){
		$("#submit-button").click(function() {
			
			isFormSubmission = true;
			
			$('#confirm-delete').modal('show');
			return false;
			
		});
	});
	</script>
</body>
</html>
			