<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
	<meta charset="utf-8">
	<title>${title }</title>
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
           <div class="span15">
               <div class="box" >
                   <div class="box-header">
                       <i class="icon-bookmark"></i>
                       <h5>Questionnaire</h5>
                       <div style="float: right;">
                       		<a href="<spring:url value="/admin/question/form" htmlEscape="true" />" class="btn btn-small btn-add">
	                            <i class="btn-icon-only icon-plus"></i>
	                        </a>
                       </div>
                   </div>	
                   <div class="box-content">
                   
					<c:if test="${null != questionSheets }">
					<spring:url var = "action" value='/admin/questionnaire/save' htmlEscape="true"/>
							<form:form  method="post" action="${action}" class="form form-horizontal" commandName="questionnaireForm" id="questionnaireForm" style="width: 100%;">
								
							<div style="width: 100%; display: inline-block;">
								<form:errors path="*" cssClass="errorblock span5" element="div" />
							</div>
							<form:input type="hidden" path="groupId" name="groupId" value="${groupId }"></form:input>
							<div>
								<div class="control-group ">
									<label class="control-label">Name</label>
									<div class="controls">
										<form:input id="categoryName" path="categoryName" name="categoryName" class="span5" type="text"
											autocomplete="false"></form:input>
											<form:errors path="categoryName" cssClass="error"/>
			
									</div>
								</div>
								<div class="control-group ">
									<label class="control-label">Description</label>
									<div class="controls">
									<form:textarea path="description" name="description" id="description" class="span5"></form:textarea>
									<form:errors path="description" cssClass="error"/>
									</div>
								</div>
								<div class="control-group ">
									<label class="control-label">
										Is Active?</label>
									<div class="controls">
										<input type="checkbox" style="width: 40px;" id="isActive" class="span5" value="is active" ${questionnaireForm.active ? 'checked' : ''}/>
											<input type="hidden" id="active" value="${questionnaireForm.active }" name="active">
									</div>
								</div>
								<% int index=0; %>
								<c:forEach items="${questionSheets}" var="question" varStatus="questionRow">
								<c:if test="${question.isSelected }">
												<input type="hidden" id="questionIds${questionRow.index }" name="questionIds[<%=index %>]" value="${question.question.id}"/>
												<% index++; %>
											</c:if>
								</c:forEach>
								<input type="hidden" id="indexCount" value="<%=index %>">
						</div>
						<div >
						<div style="float: right;">
                       		<a href="#" id ="checkAll" class="btn btn-small btn-add"> Check All
	                            <i class="btn-icon-only icon-plus"></i>
	                        </a>
	                        <a href="#" id ="unCheckAll" class="btn btn-small btn-add"> UnCheck All
	                            <i class="btn-icon-only icon-remove"></i>
	                        </a>
                       </div>
						<legend>Select Questions to Add</legend>
						<table id="questionTable" class="stripe table table-hover table-bordered tablesorter">
							<thead>
								<tr>
								  <th></th>
							      <th>Name</th>
							      <th>Question</th>
							      <th>Type</th>
							      <th>Active</th>
							      <th>Comment enabled</th>
					    		</tr>
					 		</thead>
				   			<tbody>
								<c:forEach items="${questionSheets}" var="question" varStatus="questionRow">
									<tr> 
										<td>
											<input ${question.isSelected ? 'checked' : '' }  type="checkbox" id="chkbox${questionRow.index }" onclick="insertQuestionId(this, ${question.question.id})" />
											
										</td>
										<td>
											<c:out value="${question.question.name}" />
										</td>
										<td>
											<c:out value="${question.question.question}" />
										</td>
										<td>
											<c:out value="${question.question.questionType}" />
										</td>
										<td>
											<c:out value="${question.question.active ? 'YES' : 'NO'}" />
										</td>
										<td>
											<c:out value="${question.question.commentEnabled ? 'YES' : 'NO'}" />
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
						</div>
						<footer id="submit-actions" class="form-actions">
										<button id="submit-button" type="submit"
											class="btn btn-primary" name="action" value="CONFIRM">Save</button>
										<button type="button"
											onclick="window.location='<spring:url value="/admin/question/questionnaireList" htmlEscape="true" />'" class="btn"
											name="action" value="CANCEL">Cancel</button>
									</footer>
						
						</form:form>
					</c:if>
					<c:if test="${questionSheets == null}">
						No Questionnaire available
					</c:if>
					</div>
				</div>
			</div>
		</div>
		</section>
</div>
</div>
<script type="text/javascript">

	var FieldCount=$('#indexCount').val(); //to keep track of text box added
	//var table = $('#questionTable').DataTable();
	
	function insertQuestionId(obj, id) {
		if($(obj).is(":checked")){
		 	$('form').append('<input type="hidden" id="questionIds' + id + '" name="questionIds[' + FieldCount + ']" value="' + id + '"/>');
		 	FieldCount++;
		} else {
			$('#questionIds'+id).remove();
		}
	}	
	
	$('#isActive').on('click', function(){
		if($(this).is(":checked")){
			$('#active').val('true');
		} else {
			$('#active').val('false');
		}
	});
	
/* 	$("#submit-button").click(function() {
		var data = table.$('input').serialize();
		$('form').append(data);
		$('form').submit();
		
		return false;
		
	}); */
	
	$("#checkAll").on('click', function(e){

    	e.preventDefault();
		e.stopPropagation();
		$('tbody tr td input[type="checkbox"]').prop('checked', true).trigger('change');
		/* var table= $(e.target).closest('table');
		$('td input:checkbox',table).prop('checked',"true"); */
	    return false;
	});
	
	$("#unCheckAll").on('click', function(e){

    	e.preventDefault();
		e.stopPropagation();
		$('tbody tr td input[type="checkbox"]').prop('checked', false).trigger('change');
		/* var table= $(e.target).closest('table');
		$('td input:checkbox',table).prop('checked',"true"); */
	    return false;
	});
	
	if($('#active').val() == 'true')
		$('#isActive').attr('checked', $('#active').val());

</script>
</body>
</html>