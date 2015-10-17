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
	<div class="container">
		<%@ include file="banner.jsp"%>

		<section class="page container">
			<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Take Questionnaire</h5>
							<div style="float: right;">
								<a href="#" class="btn btn-small btn-save"> <i
									class="btn-icon-only icon-ok"></i>
								</a>
							</div>
						</div>
						<div class="box-content">
						<spring:url var = "action" value='/public/auth/questionnaire/save' />
							<form:form method="post" action="${action}" class="form" commandName="question" id="questionForm">
								<div class="container span12">
									<div style="width: 100%; display: inline-block;">
										<form:errors path="*" cssClass="errorblock span5" element="div" />
									</div>
									<div>
									<c:if test="${null != questionGroupForms }">
										<c:forEach items="${questionGroupForms}" var="questionGroupForm">
											<div style="display: inline-block; width: 90%; border-bottom: 2px solid;">
													<label id="preview-question-name" class="span4" style="">${questionGroupForm.groupName}</label>
												</div>
										<c:forEach items="${questionGroupForm.questionForms}" var="questionForm">
										<!-- TODO: Add categorizing by question group -->
												<div style="display: inline-block; width: 90%; ">
													<label id="preview-question-name" class="span4" style="">${questionForm.question.question}</label>
												</div>
												<c:if test="${questionForm.question.questionType == 'YES_NO' }">
													<div id="preview-yesno">
														<div class="choice">
															<input id="choice_yen_no_0" type="radio" name="userChoice" style="margin-bottom: 5px;"
																value="Yes" /> <label for="choice_yen_no_0" style="display: inline-block;">Yes</label>
														</div>
														<div class="choice">
															<input id="choice_yen_no_1" type="radio" name="userChoice" style="margin-bottom: 5px;"
																value="No" /> <label for="choice_yen_no_1" style="display: inline-block;">No</label>
														</div>
													</div>
												</c:if>
												<c:if test="${questionForm.question.questionType == 'MULTICHOICE' }">
													<c:forEach items="${questionForm.answers }" var="answer">
														<div class="choice" id="multiple-answer-prev-0">
															<input type="checkbox" id="prev_choice_0" name="prev_choice_0" style="width: 20px; margin-bottom: 5px;"
																class="span5" value="" /> <label for="prev_choice_0" style="display: inline-block;">${answer.answer }</label>
														</div>
													</c:forEach>
												</c:if>
												<c:if test="${questionForm.question.questionType == 'LIST_CHOICE' }">
													<c:forEach items="${questionForm.answers }" var="answer">
														<div class="choice">
															<input id="choice_0" type="radio" name="choice" style="margin-bottom: 5px;"
																value="choice_0" /> <label for="choice_2" style="display: inline-block;">sample option 1</label>
														</div>
													</c:forEach>
												</c:if>
												<c:if test="${questionForm.question.questionType == 'OPEN_ENDED' }">
													<div class="controls">
														<textarea id="preview-open-ended" name="preview-open-ended" class="span5"
															type="text" value="" placeholder="User Open ended text"></textarea>
													</div>
												</c:if>
										</c:forEach>
										</c:forEach>
								</c:if>
							</div>
							<footer id="submit-actions" class="form-actions">
								<button id="submit-button" type="submit"
									class="btn btn-primary" name="action" value="CONFIRM">Save</button>
								<button type="button"
									onclick="window.location='questionnaire.html'" class="btn"
									name="action" value="CANCEL">Cancel</button>
							</footer>

						</div>
					</form:form>
				</div>

			</div>
			
		</div>
	</div>
</section>
</div>
	
				
</body>
</html>