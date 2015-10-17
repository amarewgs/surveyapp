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
				"order": [[ 1, 'asc' ]],
				stateSave: true
			});
		} );

	</script>
	<style type="text/css">
		.dataTables_wrapper select{
			width: 60px !important;
		}
	</style>
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
					 <c:if test="${null != questionList }">
					  <spring:url var = "action" value='/admin/questions/delete' htmlEscape="true"/>
						<form:form method="post" action="${action}" class="form" commandName="questionIdsForm" id="questionIdsForm">
	                     <div class="box-header" >
	                       <i class="icon-bookmark"></i>
	                       <h5>Question Bank</h5>
	                       <div style="float: right;">
	                       		<a href="<spring:url value="/admin/question/form" htmlEscape="true" />" class="btn btn-small btn-plus">
		                            <i class="btn-icon-only icon-plus"></i>
		                        </a>
		                        <%-- <a href="<spring:url value="/admin/question/form" htmlEscape="true" />" class="btn btn-small btn-remove">
		                            <i class="btn-icon-only icon-remove"></i>
		                        </a> --%>
		                        <button id="submit-button" type="submit"
												class="btn btn-small btn-remove" name="action" value="CONFIRM"><i class="btn-icon-only icon-remove"></i></button>
	                       </div>
	                  	 </div>	
	                   	 <div class="box-content">									
							<div style="width: 100%; display: inline-block;">
								<form:errors path="*" cssClass="errorblock span5" element="div" />
							</div>
							<table id="questionTable" class = "stripe table table-hover table-bordered tablesorter" style="font-size: 13px;">
								<thead>
									<tr>
									  <th></th>
								      <th>Name</th>
								      <th>Description</th>
								      <!-- <th>Text</th> -->
								      <th>Type</th>
								      <th>Comment enabled</th>
								      <th>Active</th>
								      <th></th>
						    		</tr>
						 		</thead>
					   			<tbody>
					   				
									<c:forEach items="${questionList}" var="question" varStatus="questionRow">
										<tr> 
											<td>
												<input type="checkbox" id="chkbox${questionRow.index }" onclick="insertQuestionId(this, ${question.id}, ${questionRow.index })" />
											</td>
											<td>
												<c:out value="${question.name}" />
											</td>
											<td>
												<c:out value="${question.question}" />
											</td>
											<%-- <td>
												${question.text}
											</td> --%>
											<td>
												<c:out value="${question.questionType}" />
											</td>
											<td>
												<c:out value="${question.commentEnabled ? 'YES' : 'NO'}" />
											</td>
											<td>
												<c:out value="${question.active ? 'YES' : 'NO'}" />
											</td>
											<%-- <td>
												<c:if test="${question.filePath != null}">
													<a href="<spring:url value="/admin/attachments/${question.id }" htmlEscape="true" />"><i class="btn-icon-only icon-download"></i></a>
												</c:if>
											</td> --%>
											
											<td style="width: 100px; ">
													<a id="delete_link" data-href="<spring:url value="/admin/questions/delete/${question.id }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small btn-trash">
							                            <i class="btn-icon-only icon-trash"></i>
							                        </a>
							                        <a href="<spring:url value="/admin/question/form/${question.id }" htmlEscape="true" />" class="btn btn-small btn-edit">
							                            <i class="btn-icon-only icon-edit"></i>
							                        </a>
							                        <a href="<spring:url value="/admin/question/detail/${question.id }" htmlEscape="true" />" class="btn btn-small">
							                            <i class="btn-icon-only icon-folder-open"></i>
							                        </a>
											</td>
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
							</div>
						  </form:form>
						</c:if>
						<c:if test="${questionList == null}">
							No Questions available
						</c:if>
						
						</div>
						<div class="">
						<div class="box">
						 	<div class="box-header">
		                       <i class="icon-bookmark"></i>
		                       <h5>Import Question File</h5>
	                       </div>
	                       <div class="box-content">
							
							<spring:url var = "actionUpload" value='/admin/question/uploadCSVFile' htmlEscape="true"/>
								 <form:form method="POST" action="${actionUpload}" commandName="csvUploadForm" enctype="multipart/form-data">
							        <div class="control-group ">
										<label class="control-label">File to upload:</label>
										 <div class="controls">
										 	<form:input path="file" type="file" name="file"/>
										</div>
									</div>
							        <%-- <div class="control-group ">
										<label class="control-label">File Name: </label>
										<div class="controls">
											<form:input path="name" type="text" name="name"/> 
										</div>
									</div> 
							        <div class="control-group">
										<label for="challengeQuestion" class="control-label">
											Type</label>
										<div class="controls">
											<form:select path="questionType" name="questionType" id="questionType" class="span3" >
												<option value="">question type</option>											
													<option value="YES_NO">Yes/No</option>
													<option value="OPEN_ENDED">Open Ended</option>
											</form:select>
										</div>
									</div> --%>
							        <input type="submit" value="Upload"> Press here to upload the file!
							    </form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
<div id="dialog-delete" title="Confirm Delete">
     <p>Are you sure you want to delete this record?</p>
 </div>
<script type="text/javascript">

	var FieldCount=0; //to keep track of text box added

	function insertQuestionId(obj, id) {
		if($(obj).is(":checked")){
		 	$('form').append('<input type="hidden" id="questionIds' + id + '" name="questionIds[' + FieldCount + ']" value="' + id + '"/>');
		 	FieldCount++;
		} else {
			FieldCount--;
			$('#questionIds'+id).remove();
		}
	}	
	 
	var deleteUrl = '';
	var isFormSubmission = false;
	
	$(document).ready(function(){
		$("#submit-button").click(function() {
			
			isFormSubmission = true;
			
			$('#dialog-delete').dialog('open');
			return false;
			
		});
	});
	
	// Dialog
    $('#dialog-delete').dialog({
        resizable: false,
        autoOpen: false,
        width: 300,
        height: 140,
        modal: true,
        buttons: {
            "Ok": function() {                
                if(isFormSubmission){
                	console.log('submitting form');
                	$('form').submit();
                }
                else window.location = deleteUrl;
                $(this).dialog("close");
            },
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });
	
    function openDeleteDialog(obj) {     
    	isFormSubmission = false;
    	deleteUrl = $(obj).attr('data-href');
    	console.log(deleteUrl);
        $('#dialog-delete').dialog('open');
        return false;
        
    }
</script>
</body>
</html>