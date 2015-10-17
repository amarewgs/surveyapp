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
    <%@ include file="../partials/target_menu.jsp" %>
		<section class="page container">
			<div class="row">
			<%@ include file="../partials/treemenu.jsp" %>
				<div class="span12" style="margin: 0 auto;">
					<div class="box" style="display:inline-block; width: 100%">
					<spring:url var = "action" value='/admin/attribute/save' />
						<form:form style="width: 100%" method="post" action="${action}" class="form form-horizontal" commandName="attribute" id="questionForm">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Create New Attribute</h5>
							<!-- <div style="float: right;">
								<a href="#" class="btn btn-small btn-save"> <i
									class="btn-icon-only icon-ok"></i>
								</a>
							</div> -->
						</div>
						<div class="box-content">
						
								<div class="container span11">
								<div style="width: 100%; display: inline-block;">
									<form:errors path="*" cssClass="errorblock span5" element="div" />
								</div>
									<div>
										<form:input type="hidden" path="id" name="id" value="${id }"></form:input>
											
											<div class="control-group ">
												<label class="control-label">label</label>
												<div class="controls">
													<form:input  id="label" name="label" path="label" class="span3" type="text" value="${label}" autocomplete="false"></form:input>
													<form:errors path="label" cssClass="error"/>
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">Description</label>
												<div class="controls">
												<form:textarea path="description" name="description" id="description" value="${description }" class="span5"></form:textarea>
												<form:errors path="description" cssClass="error"/>
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">Field Type</label>
												<div class="controls">
													<form:select path="fieldType" name="fieldType" id="fieldType" class="span3">
														<option value="">field type</option>
														<c:forEach items="${types}" var="option">
															<option value="${option}" ${ option == attribute.fieldType ? 'selected="selected"' : ''}>
																<c:out value="${option.type}"></c:out>
															</option>
														</c:forEach>
													</form:select>
													<form:errors path="fieldType" cssClass="error"/>
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">value</label>
												<div class="controls">
													<form:input  id="value" name="value" path="value" class="span3" type="text" value="${value}" autocomplete="false"></form:input>
													<form:errors path="value" cssClass="error"/>
													<span style="display: none;" id="info"></span>
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">
													Is Active?</label>
												<div class="controls">
													<input type="checkbox" style="width: 40px;" id="isActive" class="span5"
														value="is active" />
														<input type="hidden" id="active" value="${attribute.active }" name="active">
												</div>
											</div>
											<div class="control-group ">
												<label class="control-label">
													Is Required?</label>
												<div class="controls">
													<input type="checkbox" style="width: 40px;" id="isRequired" class="span5"
														value="is required" />
														<input type="hidden" id="required" value="${attribure.required }" name="required">
												</div>
											</div>
									</div>
									
									<div style="width:90%;">
										<footer id="submit-actions" class="form-actions">
											<button id="submit-button" type="submit"
												class="btn btn-primary" name="action" value="CONFIRM">Save</button>
											<button type="button"
												onclick="window.location='<spring:url value="/admin/attribute/list" htmlEscape="true" />'" class="btn"
												name="action" value="CANCEL">Cancel</button>
										</footer>
									</div>
								</div>
							
						</div>
						</form:form>
					</div>
					
				</div>
				
			</div>
		</section>
	</div>
	</div>
	<script type="text/javascript">
		
		$('#isRequired').on('click', function(){
			if($(this).is(":checked")){
				$('#required').val('true');
			} else {
				$('#required').val('false');
			}
		});
		

		$('#isActive').on('click', function(){
			if($(this).is(":checked")){
				$('#active').val('true');
			} else {
				$('#active').val('false');
			}
		});
		
		if($('#active').val() == 'true')
			$('#isActive').attr('checked', $('#active').val());
		if($('#isRequired').val() == 'true')
			$('#isRequired').attr('checked', $('#required').val());
		
		$('#fieldType').on('change', function(){
			
			if($(this).val() == 'RADIO' || $(this).val() == 'MULTI_SELECT' || $(this).val() == 'DROPDOWN') {
				$('#info').css("display", "");
				$('#info').css("color", "red");
				$('#info').append("<br/> add comma separated values for the options");
			} else {
				$('#info').css("display", "none");
			}
		});
		
		</script>
</body>
</html>