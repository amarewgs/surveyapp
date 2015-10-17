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
	    <%@ include file="../partials/menu.jsp" %>
			<section class="page container">
				<div class="row">
				<%@ include file="../partials/treemenu.jsp" %>	
					<div class="span12">
						<div class="box" style="display: inline-block;">
							<div class="box-header">
								<i class="icon-bookmark"></i>
								<h5>Question Detail</h5>
								<div style="float: right;">
									<a href="<spring:url value="/admin/question/list" htmlEscape="true" />" class="btn btn-small btn-remove">
			                            <i class="btn-icon-only icon-remove"></i>
			                        </a>
									<a id="delete_link" data-href="<spring:url value="/admin/questions/delete/${question.id }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small trash">
			                            <i class="btn-icon-only icon-trash"></i>
			                        </a>									
			                        <a href="<spring:url value="/admin/question/form/${question.id }" htmlEscape="true" />" class="btn btn-small btn-edit">
			                            <i class="btn-icon-only icon-edit"></i>
			                        </a>
								</div>
							</div>
							<div class="box-content">
								<div class="form form-horizontal" style="display: inline-block; border-bottom: 1px solid silver; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Name</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${question.name}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">
											Description</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${question.question}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">
											Text</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${question.text}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Active</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${question.active ? 'Yes' : 'No'}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Comment Enabled</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${question.commentEnabled ? 'Yes' : 'No'}</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Question Type</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">
											<c:if test="${question.questionType == 'YES_NO'}">
												Yes/No Answer
											</c:if>
											<c:if test="${question.questionType == 'MULTICHOICE'}">
												Multiple Select Answer
											</c:if>
											<c:if test="${question.questionType == 'LIST_CHOICE'}">
												Single Select Answer
											</c:if>
											<c:if test="${question.questionType == 'OPEN_ENDED'}">
												Open Ended Answer
											</c:if>
											</label>
										</div>
									</div>
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Attachment</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<c:if test="${question.filePath != null}">
												<a href="<spring:url value="/admin/attachments/${question.id }" htmlEscape="true" />"><i class="btn-icon-only icon-download"></i>${question.filePath}</a>
											</c:if>
										</div>
									</div>
								</div>
								<div id="multiple-answers">										
									<fieldset>
										<legend>Answer Details</legend>										
										<div id="multiple-wrapper">
											<c:if test="${answers == null }">
												<div class="control-group ">
													<div class="controls">
														<input type="text" id="answer_0" name="answers[0].answer"
															class="span5" placeholder="answer 1" autocomplete="false" /> 
													</div>
												</div>
											</c:if>
											<c:if test="${question.questionType == 'MULTICHOICE' || question.questionType == 'LIST_CHOICE'}">
											<c:forEach items="${answers}" var="answer" varStatus="answerRow">
												<div class="control-group ">
													<div class="controls">
														<input type="text" class="span5" name="answers[${answerRow.index }].answer" id="answer_${answerRow.index }" placeholder="Answer ${answerRow.index }" value="${answer.answer }"/>
													</div>
												</div>
											</c:forEach>
										</c:if>
										</div>											
									</fieldset>
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