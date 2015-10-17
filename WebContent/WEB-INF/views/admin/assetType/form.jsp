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
					<spring:url var = "action" value='/admin/assetType/save' />
						<form:form style="width: 100%" method="post" action="${action}" class="form form-horizontal" commandName="targetTypeForm" id="targetTypeForm">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Target Type Form</h5>
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
									<form:input type="hidden" path="targetTypeId" name="targetTypeId" value="${targetTypeId }"></form:input>
										
										<div class="control-group ">
											<label class="control-label">Name</label>
											<div class="controls">
												<form:input  id="name" name="name" path="name" class="span3" type="text" value="${name}" autocomplete="false"></form:input>
												<form:errors path="name" cssClass="error"/>
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
											<label class="control-label">
												Is Active?</label>
											<div class="controls">
												<input type="checkbox" style="width: 40px;" id="isActive" class="span5"
													value="is active" />
													<input type="hidden" id="active" value="${targetTypeForm.active }" name="active">
											</div>
										</div>
										<div class="control-group ">
											<label class="control-label">
												Status</label>
											<div class="controls">
												
												<c:forEach items="${statuses}" var="status" varStatus="statusRow">
												<c:set value="false" var="found"></c:set>
												<c:set var="assetTypeStatusId" value=""></c:set>
												<c:forEach items="${targetTypeForm.targetTypeStatusIds}" var="targettypestatusId" varStatus="targettypestatusIdRow">
													<c:if test="${ targettypestatusId == status.id}">
														<c:set var="found" value="true"></c:set>
														<c:set var="assetTypeStatusId" value="${ targettypestatusId}"></c:set>
													</c:if>
												</c:forEach>
												<input type="hidden" id="targetstatus${status.id }" name="targetTypeStatusIds[${statusRow.index }]" value="${assetTypeStatusId }"/>
													<div style="margin: 4px; display: inline-block;">
													<input ${found ? 'checked' : '' } type="checkbox" id="status${status.id }" name="statusRadio" value="${status.id }" onclick="setStatus(this)"/>
													<span>${status.name }</span>
													</div>
													
												</c:forEach> 
												<%-- <form:errors path="status" cssClass="error"/> --%>
											</div>
										</div>
										<div class="control-group ">
											<label class="control-label">
												Template</label>
											<div class="controls">
											<input type="hidden" id="temp" name="" value="${targetTypeForm.templateId }"/>
												<select name="templateId" id="template" class="span5">
													<option value="">select template</option>
													<c:forEach items="${activeTemplates}" var="template">
														<option value="${template.id}">
															<c:out value="${template.name}"></c:out>
														</option>
													</c:forEach>
												</select> <form:errors path="templateId" cssClass="error"/>
											</div>
										</div>
												
								</div>
								
								<div style="width:90%;">
									<footer id="submit-actions" class="form-actions">
										<button id="submit-button" type="submit"
											class="btn btn-primary" name="action" value="CONFIRM">Save</button>
										<button type="button"
											onclick="window.location='<spring:url value="/admin/assetType/list" htmlEscape="true" />'" class="btn"
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
		
		$('#isActive').on('click', function(){
			if($(this).is(":checked")){
				$('#active').val('true');
			} else {
				$('#active').val('false');
			}
		});
		
		function setStatus(obj) {
			if($(obj).is(":checked")){
				$('#targetstatus' + $(obj).val()).val($(obj).val());
			} else {
				$('#targetstatus' + $(obj).val()).val('');
			}
		}
		
		if($('#active').val() == 'true')
			$('#isActive').attr('checked', $('#active').val());
		
		$(document).ready(function() {
			$('#template').val($('#temp').val());
	
			$('#template').trigger('change');
		});

		</script>
</body>
</html>