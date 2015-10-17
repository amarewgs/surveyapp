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
       	<%@ include file="../partials/treemenu.jsp" %>	
           <div class="span12">
               <div class="box" >
                   <div class="box-header">
                       <i class="icon-bookmark"></i>
                       <h5>Questionnaire Detail</h5>
                       <div style="float: right;">
                       		<a href="<spring:url value="/admin/questionnaire/list" htmlEscape="true" />" class="btn btn-small btn-remove">
	                            <i class="btn-icon-only icon-remove"></i>
	                        </a>
							<a id="delete_link" data-href="<spring:url value="/admin/questionnaire/delete/${questionnaire.groupId }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small trash">
	                            <i class="btn-icon-only icon-trash"></i>
	                        </a>									
	                        <a href="<spring:url value="/admin/questionnaire/form/${question.groupId }" htmlEscape="true" />" class="btn btn-small btn-edit">
	                            <i class="btn-icon-only icon-edit"></i>
	                        </a>
                       </div>
                   </div>	
                   <div class="box-content">
								
							<div style="width: 100%; display: inline-block;">
								<form:errors path="*" cssClass="errorblock span5" element="div" />
							</div>
							<form:input type="hidden" path="groupId" name="groupId" value="${groupId }"></form:input>
							<div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Name</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${questionnaire.categoryName}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Description</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${questionnaire.description}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Active</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${questionnaire.active ? 'Yes' : 'No'}</label>
										</div>
									</div>
								</div>
						</div>
						<div >                   
						<c:if test="${null != questionSheets }">
                       
						<legend>Questionnaire Questions</legend>
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
						</c:if>
						<c:if test="${questionSheets == null}">
							No Questionnaire available
						</c:if>
						</div>
					
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