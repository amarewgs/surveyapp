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
			$('#questionTable').dataTable({
				stateSave: true
			});
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
		           <div class="span13">
		               <div class="box" style="display:inline-block; width: 100%">
		               
					  	<spring:url var = "action" value='/admin/questionnaires/delete' htmlEscape="true"/>
						<form:form style="width: 100%" method="post" action="${action}" class="form" commandName="questionnaireIdsForm" id="questionnaireIdsForm">
			                   <div class="box-header">
			                       <i class="icon-bookmark"></i>
			                       <h5>Questionnaires</h5>
			                       <div style="float: right;">
			                       		<a href="<spring:url value="/admin/question/questionnaire" htmlEscape="true" />" class="btn btn-small btn-add">
				                            <i class="btn-icon-only icon-plus"></i>
				                        </a>
				                        <button id="submit-button" type="submit"
												class="btn btn-small btn-remove" name="action" value="CONFIRM"><i class="btn-icon-only icon-remove"></i></button>
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
										      <th>Active</th>
										      <th></th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${questionnaires}" var="questionnaire" varStatus="questionnaireRow">
												<tr> 
													<td>
														<input type="checkbox" id="chkbox${questionnaireRow.index }" onclick="insertQuestionnaireId(this, ${questionnaire.groupId}, ${questionnaireRow.index })" />
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
													<td>
														<c:out value="${questionnaire.active ? 'YES' : 'NO'}" />
													</td>
													<td style="width: 80px; ">
														<a id="delete_link" data-href="<spring:url value="/admin/questionnaires/delete/${questionnaire.groupId }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small btn-remove">
								                            <i class="btn-icon-only icon-trash"></i>
								                        </a>
								                        <a href="<spring:url value="/admin/question/questionnaire/${questionnaire.groupId }" htmlEscape="true" />" class="btn btn-small btn-edit">
								                            <i class="btn-icon-only icon-edit"></i>
								                        </a>
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
								</form:form>
							</div>
						</div>
					</div>
				</section>
		</div>
	</div>	
<div id="delete-dialog" title="Confirm Delete">
    <p>Are you sure you want to delete this record?</p>
</div>
<%@ include file ="../partials/delete_dialog.jsp" %>
	
	<script type="text/javascript">
	
	var isFormSubmission = false;
	var FieldCount=0; //to keep track of text box added
	var deleteUrl = "";
	function insertQuestionnaireId(obj, id, index) {
		if($(obj).is(":checked")){
		 	$(obj).parent().append('<input type="hidden" id="groupIds' + id + '" name="groupIds[' + FieldCount + ']" value="' + id + '"/>');
		 	FieldCount++;
		} else {
			FieldCount--;
			$('#groupIds'+id).remove();
		}
	}
	$(document).ready(function(){
		
		// Dialog
        $('#delete-dialog').dialog({
            resizable: false,
            autoOpen: false,
            width: 300,
            height: 140,
            modal: true,
            buttons: {
                "Ok": function() {
                    $(this).dialog("close");
                    if(isFormSubmission) {
		                	$('form').submit();
		                	
                    }
                    else
                    	window.location = deleteUrl;
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            }
        });
		
		$("#submit-button").click(function() {
			
			isFormSubmission = true;
			
			$('#delete-dialog').dialog('open');
			return false;
			
		});
	});
    function openDeleteDialog(obj) { 
    	isFormSubmission = false;
    	deleteUrl = $(obj).attr('data-href');
    	console.log(deleteUrl);
        $('#delete-dialog').dialog('open');
        return false;
        
    }
	</script>
</body>
</html>
			