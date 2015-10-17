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
<%@ include file="banner.jsp"%>
<div id="body-container">
   <div id="body-content">	
		<section class="page container">
			<div class="row">
			<%@ include file="../partials/treemenu.jsp" %>
				<div class="span12">
					<div class="box">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Completed Questionnaire For ${questionGroupForm.categoryName}</h5>
							<div style="float: right;">
								<a href="<spring:url value="/public/auth/questionnaires/${campaignId }" htmlEscape="true" />" class="btn btn-small btn-save"> <i
									class="btn-icon-only icon-remove"></i>
								</a>
							</div>
						</div>
						<div class="box-content">
									<div id="form-completed">
									<c:if test="${null != questionGroupForm }">
												<div style="display: inline-block; border-bottom: 1px solid silver; margin-bottom: 4px; width: 100%; vertical-align: top;">
													<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
														<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Name</label> 
														<div class="" style="display: inline-block; width: 50%; float: none;">
															<label id="preview-question-name" class="span4" style="">${questionGroupForm.categoryName}</label>
														</div>
													</div>
													<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
														<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">
															Description</label> 
														<div class="" style="display: inline-block; width: 50%; float: none;">
															<label id="preview-question-name" class="span4" style="">${questionGroupForm.categoryDescription}</label>
														</div>
													</div>
										  		</div>
												<input type="hidden" name="groupId" value="${groupId }">
												<ul class="paging2">
												<c:forEach items="${questionGroupForm.userQuestionnaireCompletedForms}" var="questionForm" varStatus="questionRow">
												<li style="list-style: none;">
													<div style="display: inline-block; width: 2%; vertical-align: top; font-style: bold;">
														<c:out value="${questionRow.index+1}." />
													</div>
												<!-- TODO: Add categorizing by question group -->
														<div style="display: inline-block; width: 90%; ">
															<label id="preview-question-name" class="span4" style="">${questionForm.question.text}</label>
														</div>
														<input type="hidden" name="userAnswerForm[${questionRow.index }].questionId" value="${questionForm.question.id}">
														<c:if test="${questionForm.question.questionType == 'YES_NO' }">
														<input type="hidden" id="yesnoanswer${questionRow.index }" name="userAnswerForm[${questionRow.index }].booleanAnswer" value>
															<div style="margin-left: 20px;" id="preview-yesno">
																<div style="width: 10%; display: inline-block;" class="choice">
																	<input ${questionForm.answerSheetForm.booleanAnswer == true ? 'checked="checked"' : '' } id="useryesnoanswer${questionRow.index }0" type="radio" name="useryesnoChoice${questionRow.index }" style="margin-bottom: 5px;"
																		 onclick="handleYesNoOptionClick(this, ${questionRow.index }, yesnoanswer${questionRow.index })" value="true" /> <label for="choice_yen_no_0" style="display: inline-block;">Yes</label>
																</div>
																<div style="width: 10%; display: inline-block;" class="choice">
																	<input ${questionForm.answerSheetForm.booleanAnswer == false ? 'checked="checked"' : '' } id="useryesnoanswer${questionRow.index }1" type="radio" name="useryesnoChoice${questionRow.index }" style="margin-bottom: 5px;"
																		onclick="handleYesNoOptionClick(this, ${questionRow.index }, yesnoanswer${questionRow.index })" value="false" /> <label for="choice_yen_no_1" style="display: inline-block;">No</label>
																</div>
															</div>
														</c:if>
														<c:if test="${questionForm.question.questionType == 'MULTICHOICE' }">
															<input type="hidden" id="multipleAnswerCount${ questionRow.index}" value="0">
															<c:forEach items="${questionForm.answerSheetForm.chosenAnswers }" var="answer" varStatus="answerRow">
																<div class="choice" id="multiple-answer-prev-0">
																	<input checked="checked" type="checkbox" id="multipleAnswerChoice${answerRow.index }" name="prev_choice_0" style="width: 20px; margin-bottom: 5px;"
																		onclick="setMultiSelectAnswer(this, ${questionRow.index }, 'userMultiSelectAnswer${answerRow.index }', multipleAnswerCount${ questionRow.index})" class="span5" value="${answer.id }" /> <label for="prev_choice_0" style="display: inline-block;">${answer.answer }</label>
																</div>
															</c:forEach>
															<c:forEach items="${questionForm.answerSheetForm.answers }" var="answer" varStatus="answerRow">
																<div class="choice" id="multiple-answer-prev-0">
																	<input type="checkbox" id="multipleAnswerChoice${answerRow.index }" name="prev_choice_0" style="width: 20px; margin-bottom: 5px;"
																		onclick="setMultiSelectAnswer(this, ${questionRow.index }, 'userMultiSelectAnswer${answerRow.index }', multipleAnswerCount${ questionRow.index})" class="span5" value="${answer.id }" /> <label for="prev_choice_0" style="display: inline-block;">${answer.answer }</label>
																</div>
															</c:forEach>
														</c:if>
														<c:if test="${questionForm.question.questionType == 'LIST_CHOICE' }">
															<input type="hidden" id="userSingleAnswer${questionRow.index }" name="userAnswerForm[${questionRow.index }].answerIds[0]" value="${questionForm.answerSheetForm.chosenAnswers[0].id }">
															<c:forEach items="${questionForm.answerSheetForm.answers }" var="answer" varStatus="answerRow">
																<div class="choice">
																	<input ${questionForm.answerSheetForm.chosenAnswers[0].id == answer.id ? 'checked="checked"' : '' } id="choice_${answerRow.index }" type="radio" name="singleChoice${questionForm.question.id }" style="margin-bottom: 5px;"
																		value="${answer.id }" onclick="setSingleSelectAnswer(this, ${questionRow.index }, userSingleAnswer${questionRow.index })"/> <label for="choice_2" style="display: inline-block;" >${answer.answer }</label>
																</div>
															</c:forEach>
														</c:if>
														<c:if test="${questionForm.question.questionType == 'OPEN_ENDED' }">
															<div class="controls">
																<textarea id="userAnswerOpenEnded${questionRow.index }" name="userAnswerForm[${questionRow.index }].detailAnswer" class="span5"
																	type="text" value="${questionForm.answerSheetForm.detailAnswer }" placeholder="User Open ended text" style="font-size: 12px">${questionForm.answerSheetForm.detailAnswer }</textarea>
															</div>
														</c:if>
														<c:if test="${questionForm.question.commentEnabled }">
															<div class="control-group">
															<label for="challengeQuestion" class="control-label">
																Comment</label>
																<div class="controls">
																	<textarea id="userAnswerComment${questionRow.index}" name="userAnswerForm[${questionRow.index }].comment" class="span5"
																		type="text" value="${questionForm.answerSheetForm.comment }" placeholder="User can give comment" style="font-size: 12px">${questionForm.answerSheetForm.comment }</textarea>
																</div>
															</div>
														</c:if>
														</li>
											</c:forEach>
										</ul> 
								</c:if>
							</div>
							<footer id="submit-actions" class="form-actions">
								<button type="button"
									onclick="window.location='<spring:url value="/public/auth/questionnaires/${campaignId }" htmlEscape="true" />'" class="btn"
									name="action" value="CANCEL">Cancel</button>
							</footer>

						</div>
				</div>

			</div>
			
		</div>
		</section>
	</div>
</div>			
<script type="text/javascript">
	function handleYesNoOptionClick(radio, index, obj) {
		
		$(obj).val(radio.value);
	}
	function setSingleSelectAnswer(radio, index, obj) {
		
		$(obj).val(radio.value);
	}
	function setMultiSelectAnswer(chkbox, index, obj, countObj) {
		
		var count = $(countObj).val();
	    if( $( chkbox ).is(':checked') ) {
	    	
	      	$( chkbox ).parent().append( '<input type="hidden" id="'+ obj + '" name="userAnswerForm[' + index + '].answerIds[' + count +']" value="' + chkbox.value + '">' );
	      	count++;
	      	$(countObj).val(count);
	    } else {
	    	
		     $('#'+ obj ).remove();
		     count = count- 1;
		     $(countObj).val(count);
	    }
	  }
	$("ul.paging2").quickPager({pageSize:"3"});
	$("#form-completed :input").attr("disabled", true);
</script>	
</body>
</html>