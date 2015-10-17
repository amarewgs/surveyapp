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
					<spring:url var = "action" value='/admin/question/save' />
						<form:form method="post" action="${action}" class="form form-horizontal" commandName="question" id="questionForm" enctype="multipart/form-data">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Create New Question</h5>
							<!-- <div style="float: right;">
								<a href="#" class="btn btn-small btn-save"> <i
									class="btn-icon-only icon-ok"></i>
								</a>
							</div> -->
						</div>
						<div class="box-content">
						
								<div class="container span11">
								<div style="width: 90%; display: inline-block;">
									<form:errors path="*" cssClass="errorblock span5" element="div" />
								</div>
								<c:if test="${successMsg != null }">
									<div style="width: 90%;">
										<div  class="success">${successMsg}</div>
									</div>
								</c:if>
									<div>
										<form:input type="hidden" path="id" name="id" value="${id }"></form:input>
											<input type="hidden" id="questionTypeEnum" value="${question.questionType }">
											
											<div class="control-group ">
												<label class="control-label">Name</label>
												<div class="controls">
													<form:input  id="name" name="name" path="name" class="span5" type="text" value="${name}" autocomplete="false"></form:input>
													<form:errors path="name" cssClass="error"/>
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">Description</label>
												<div class="controls">
												<form:textarea path="question" name="question" id="question" value="${question }" class="span5"></form:textarea>
												<form:errors path="question" cssClass="error"/>
												</div>
											</div>
											<div class="control-group">
												<label for="challengeQuestion" class="control-label">
													Type</label>
												<div class="controls">
													<form:select path="questionType" name="questionType" id="questionType" class="span3" value="${questionType }">
														<option value="">question type</option>
														<c:forEach items="${types}" var="option">
															<option value="${option}">
																<c:out value="${option.type}"></c:out>
															</option>
														</c:forEach>
													</form:select>
													<form:errors path="questionType" cssClass="error"/>
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">
													Is Active?</label>
												<div class="controls">
													<input type="checkbox" style="width: 40px;" id="isActive" class="span5" value="is active" ${question.active ? 'checked' : ''}/>
														<input type="hidden" id="active" value="${question.active }" name="active">
												</div>
											</div>											
											<div class="control-group ">
												<label class="control-label">Text</label>
												<div class="controls">
												<form:textarea path="text" name="text" id="text" value="${text }" class="span8" rows="10"></form:textarea>
												<form:errors path="text" cssClass="error"/>
												</div>
											</div>
											<div class="control-group ">

												<div class="controls">
													Enable Answer Comment 
													<form:checkbox style="width: 40px;" id="allowComment" path="commentEnabled" class="span5"
														value="Allow Comment" />
														<input type="hidden" id="commentEnabled" value="${question.commentEnabled }" name="commentEnabled">
												</div>
											</div>
																						
											<div class="control-group ">

												<div class="controls">
													Enable User File Attachment 
													<form:checkbox style="width: 40px;" id="allowAttachment" path="attachmentEnabled" class="span5"
														value="Allow Comment" />
														<input type="hidden" id="attachmentEnabled" value="${question.attachmentEnabled }" name="attachmentEnabled">
												</div>
											</div>
											
											<%-- <div class="control-group ">
											<label class="control-label">Attachment:</label>
											 <div class="controls">
											 	<input type="file" name="file"/>
											 	<c:if test="${question.filePath != null}">
														<a href="<spring:url value="/admin/attachments/${question.id }" htmlEscape="true" />"><i class="btn-icon-only icon-download"></i>${question.filePath}</a>
													</c:if>
											</div> 
										</div>--%>
									</div>

									<div id="multiple-answers">
										
										<fieldset>
											<legend>Answer Details</legend>
											<div style="margin-bottom: 5px;">
												<input type='button' value='Add' id='addButton' class="btn btn-primary">
											</div>
											
											<div id="multiple-wrapper">
												<c:if test="${answers == null }">
													<div class="control-group ">
														<div class="controls">
															<input type="text" id="answer_0" name="answers[0].answer"
																class="span5" placeholder="answer 1" autocomplete="false" /> 
																<a href="" onclick="removeAnswer(answer_0)"><i style="" class="btn-icon-only icon-remove"></i> </a>
														</div>
													</div>
												</c:if>
												<c:if test="${question.questionType == 'MULTICHOICE' || question.questionType == 'LIST_CHOICE'}">
												<c:forEach items="${answers}" var="answer" varStatus="answerRow">
													<div class="control-group ">
														<div class="controls">
															<input type="text" class="span5" name="answers[${answerRow.index }].answer" id="answer_${answerRow.index }" placeholder="Answer ${answerRow.index }" value="${answer.answer }"/>
															<a href="" onclick="removeAnswer(answer_${answerRow.index });"><i style="" class="btn-icon-only icon-remove"></i> </a>
														</div>
													</div>
												</c:forEach>
											</c:if>
											</div>											
										</fieldset>
									</div>		

									<div style="width:90%;">
										<footer id="submit-actions" class="form-actions">
											<button id="submit-button" type="submit"
												class="btn btn-primary" name="action" value="CONFIRM">Save</button>
											<button type="button"
												onclick="window.location='<spring:url value="/admin/question/list" htmlEscape="true" />'" class="btn"
												name="action" value="CANCEL">Cancel</button>
										</footer>
									</div>
								</div>
							
						</div>
						</form:form>
					</div>
					
				</div>
				<!-- <div class="span5" style="margin-left: 80px;">
						<Legend>Question Preview</Legend>
						<div>
							<label id="preview-question-name" class="span4" style=""></label>
						</div>
						<div id="preview-question">
							
							<div id="preview-multiple-answer">
								<div class="choice" id="multiple-answer-prev-0">
									<input type="checkbox" id="prev_choice_0" name="prev_choice_0" style="width: 20px; margin-bottom: 5px;"
										class="span5" value="" /> <label for="prev_choice_0" style="display: inline-block;">Answer choice 1</label>
								</div>
							</div>
							<div id="preview-yesno">
								<div class="choice">
									<input id="choice_yen_no_0" type="radio" name="choice" style="margin-bottom: 5px;"
										value="Yes" /> <label for="choice_yen_no_0" style="display: inline-block;">Yes</label>
								</div>
								<div class="choice">
									<input id="choice_yen_no_1" type="radio" name="choice" style="margin-bottom: 5px;"
										value="No" /> <label for="choice_yen_no_1" style="display: inline-block;">No</label>
								</div>
							</div>
							<div id="preview-single-answer">
								<div class="choice">
									<input id="choice_0" type="radio" name="choice" style="margin-bottom: 5px;"
										value="choice_0" /> <label for="choice_2" style="display: inline-block;">sample option 1</label>
								</div>
							</div>
							<div id="open-ended">
								<div class="controls">
									<textarea id="preview-open-ended" name="preview-open-ended" class="span5"
										type="text" value="" placeholder="User Open ended text"></textarea>
								</div>
							</div>
							<div id="preview-comment-cont" >
								<div class="controls">
									<textarea id="preview-comment" name="preview-comment" class="span5"
										type="text" value="" placeholder="comment for user answer"></textarea>
								</div>
							</div>
						</div>
					</div> -->
			</div>
		</section>
	</div>
	</div>
	<c:url value="/libs/images/nicEditorIcons.gif" var="nicEditIcons"/>
	<script type="text/javascript">
		$('#multiple-answers').hide();
		//$('#preview-multiple-answer, #preview-yesno, #preview-single-answer, #preview-comment-cont, #open-ended').hide();

		/* $('#text').on('keyup', function() {
		    $("#preview-question-name").text($(this).val());
		}); */
		
		$('#allowComment').on('click', function(){
			if($(this).is(":checked")){
				//$('#preview-comment-cont').show();
				$('#commentEnabled').val('true');
			} else {
				//$('#preview-comment-cont').hide();
				$('#commentEnabled').val('false');
			}
		});
		
		/* $('#questionType').on(
				'change',
				function() {
					
					//$('#preview-question').show();
					
					if ($(this).val() === 'MULTICHOICE') {
						
						$('#multiple-answers').show();
						$('#preview-multiple-answer').show();
						$('#preview-yesno').hide();
						$('#preview-single-answer').hide();
						$('#open-ended').hide();
					} else if ($(this).val() == 'LIST_CHOICE') {
						$('#multiple-answers').show();
						$('#preview-single-answer').show();
						$('#preview-yesno').hide();
						$('#preview-multiple-answer').hide();
						$('#open-ended').hide();
					} else if($(this).val() == 'YES_NO') {
						$('#multiple-answers').hide();
						$('#preview-yesno').show();
						$('#preview-single-answer').hide();
						$('#preview-multiple-answer').hide();
						$('#open-ended').hide();
					} else if($(this).val() == 'OPEN_ENDED') {
						$('#open-ended').show();
						$('#multiple-answers').hide();
						$('#preview-yesno').hide();
						$('#preview-single-answer').hide();
						$('#preview-multiple-answer').hide();
					}

				}); */
		
		$(document).ready(function() {

			var MaxInputs       = 8; //maximum input boxes allowed
			var InputsWrapper   = $("#multiple-wrapper"); //Input boxes wrapper ID
			var AddButton       = $("#AddMoreFileBox"); //Add button ID

			var x = InputsWrapper.length; //initlal text box count
			var FieldCount=0; //to keep track of text box added

			$('#addButton').click(function (e)  //on add input button click
			{
			        if(x <= MaxInputs) //max input box allowed
			        {
			            FieldCount++; //text box added increment
			            //add input box
			            /* if ($('#questionType').val() == 'MULTICHOICE')
				           	 $('#preview-multiple-answer').append('<div class="choice" id="multiple-answer-prev-' + FieldCount + '"><input type="checkbox" id="prev_choice_' + FieldCount + '" name="prev_choice_' + FieldCount + '" style="width: 20px; margin-bottom: 5px;" ' + 
										'class="span5" value="" /> <label for="prev_choice_' + FieldCount + '" style="display: inline-block;">Answer choice '+FieldCount+'</label>' +
								'</div>');
			            if ($('#questionType').val() == 'LIST_CHOICE')
				            $('#preview-single-answer').append('<div class="choice"><input id="choice_' + FieldCount + '" type="radio" name="choice" style="margin-bottom: 5px;"' 
				            		+ 'value="choice_' + FieldCount + '" /> <label for="choice_2" style="display: inline-block;">sample option' + FieldCount + '</label></div>'); */
			            var count = FieldCount-1;
			            
			            var rmvLink = $('<a data-value="answer_'+ FieldCount +'" href="" ><i style="" class="btn-icon-only icon-remove"></i> </a>');
			            
			            $(InputsWrapper).append('<input type="text" class="span5" name="answers[' + count + '].answer" id="answer_'+ FieldCount +'" placeholder="Answer '+ FieldCount +'"/>').append(rmvLink);
			            x++; //text box increment
			            		            
			            
			            $(rmvLink).on('click', function(e) {
							
			            	e.preventDefault();
			    			e.stopPropagation();
			    			
			    			var id = $(this).attr("data-value");
			    			$('#' + id).remove();
			    			$(this).remove();
			    			
			    			FieldCount = FieldCount - 1;
						});
			        }
					return false;
			});
			
			$('#questionType').val($('#questionTypeEnum').val());

			$('#questionType').trigger('change');
			
			new nicEditor({iconsPath : '${nicEditIcons}', fullPanel : true}).panelInstance('text');
			
			});
		
		function removeAnswer(obj) {
			
			alert('fuck');
			
			obj.preventDefault();
			obj.stopPropagation();
			$(obj).parent('.control-group').remove();
			
			return false;
			//alert('remove');
		}
		
		$('#isActive').on('click', function(){
			if($(this).is(":checked")){
				$('#active').val('true');
			} else {
				$('#active').val('false');
			}
		});
		
		$('#allowAttachment').on('click', function(){
			if($(this).is(":checked")){
				$('#commentEnabled').val('true');
			} else {
				$('#commentEnabled').val('false');
			}
		});
		
		if($('#active').val() == 'true')
			$('#isActive').attr('checked', $('#active').val());
		
		if($('#attachmentEnabled').val() == 'true')
			$('#allowAttachment').attr('checked', $('#active').val());
		
		if($('#commentEnabled').val() == 'true')
			$('#allowComment').attr('checked', $('#active').val());
		
	</script>
</body>
</html>