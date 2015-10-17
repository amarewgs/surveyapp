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
	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			$('#questionnaireTable').dataTable({
				"order": [[ 1, 'asc' ]],
				stateSave: true
			});
			$('#usersTable').dataTable({
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
		<%@ include file="../partials/banner.jsp"%>
<div id="body-container">
   <div id="body-content">	
    <%@ include file="../partials/menu.jsp" %>
		<section class="page container">
			<div class="row">
			<%@ include file="../partials/treemenu.jsp" %>	
				<div class="span12" style="">
					<div class="box" style="display:inline-block; width: 100%">
					<spring:url var = "action" value='/admin/campaign/save' />
						<form:form style="width: 100%" method="post" action="${action}" class="form form-horizontal" commandName="campaignForm" id="campaignForm">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Create New Campaign</h5>
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
									<form:input type="hidden" path="campaignId" name="campaignId" value="${campaignId }"></form:input>
										
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
													<input type="hidden" id="active" value="${campaignForm.active }" name="active">
											</div>
										</div>
										
										<div class="control-group ">
											<label class="control-label">
												Status</label>
											<div class="controls">
												<input type="hidden" id="status" name="status.id" value="${campaignForm.status.id }"/>
												<c:forEach items="${statuses}" var="status">
													<div style="margin: 4px; display: inline-block;">
													<input ${campaignForm.status.id == status.id ? 'checked' : '' } type="radio" id="status${status.id }" name="statusRadio" value="${status.id }" onclick="setStatus(this)"/>
													<span>${status.name }</span>
													</div>
												</c:forEach>
												<form:errors path="status" cssClass="error"/>
											</div>
										</div>
										<div class="control-group ">
											<label class="control-label">
												Target</label>
											<div class="controls">
											<input type="hidden" id="asset" name="" value="${campaignForm.asset.id }"/>
												<select name="asset.id" id="assetTarget" class="span5">
													<option value="">select target</option>
													<c:forEach items="${assets}" var="asset">
														<option value="${asset.id}">
															<c:out value="${asset.name}"></c:out>
														</option>
													</c:forEach>
												</select> <form:errors path="asset" cssClass="error"/>
											</div>
										</div>
										
										<c:if test="${null != questionnaires }">
									<div>
									
									<div style="float: right;">
			                       		<a href="#" id ="checkAll" class="btn btn-small btn-add"> Check All
				                            <i class="btn-icon-only icon-plus"></i>
				                        </a>
				                        <a href="#" id ="unCheckAll" class="btn btn-small btn-add"> UnCheck All
				                            <i class="btn-icon-only icon-remove"></i>
				                        </a>
			                       </div>
                       
									<legend style="width: 50%;">Select One or more Questionnaires</legend>
									<table id="questionnaireTable" class="stripe table table-hover table-bordered tablesorter">
										<thead>
											<tr>
												<th></th>
										      <th>Name</th>
										      <th>Description</th>
										      <th>Questions</th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${questionnaires}" var="questionnaire" varStatus="questionnaireRow">
												<c:set var="found" value="false"/> 
												<c:set var="questionnaireIdVal" value=""/> 
												<tr> 
													<td>
													<c:forEach items="${campaignForm.questionnaireIds}" var="questionnaireId" varStatus="questionnaireIdRow">
															<c:if test="${questionnaireId == questionnaire.groupId}">																												
															<c:set var="found" value="true"/> 
															<c:set var="questionnaireIdVal" value="${questionnaireId }"/> 
															</c:if>
														</c:forEach>
														<input type="hidden" id="questionnaireIds${questionnaireRow.index }" name="questionnaireIds[${questionnaireRow.index }]" value="${questionnaireIdVal }"/>
														<input ${found ? 'checked' : '' } type="checkbox" id="chkbox${questionnaireRow.index }" onclick="insertQuestionnaireId(this, ${questionnaire.groupId}, ${questionnaireRow.index })" onchange="insertQuestionnaireId(this, ${questionnaire.groupId}, ${questionnaireRow.index })" />
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
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
									</c:if>
									<c:if test="${null == questionnaires }">
										No Questionnaires Found
									</c:if>
									<div style="margin-top:8px;">
									
									<div style="float: right;">
			                       		<a href="#" id ="checkAllUsers" class="btn btn-small btn-add"> Check All
				                            <i class="btn-icon-only icon-plus"></i>
				                        </a>
				                        <a href="#" id ="unCheckAllUsers" class="btn btn-small btn-add"> UnCheck All
				                            <i class="btn-icon-only icon-remove"></i>
				                        </a>
			                       </div>
			                       
									<legend style="width: 50%;">Select One or more Users</legend>
										<table id="usersTable" class = "stripe table table-hover table-bordered tablesorter">
											<thead>
												<tr>
												  <th></th>
											      <th>Userid</th>							      
											      <th>Email</th>
											      <th>First Name</th>
											      <th>Last Name</th>
											      <th>Role</th>
											      <th>active</th>
									    		</tr>
									 		</thead>
								   			<tbody>
												<c:forEach items="${users}" var="user" varStatus="userRow">
												<c:set var="found" value="false"/> 
												<c:set var="userIdVal" value=""/> 
													<tr> 
														<td>
														<c:forEach items="${campaignForm.users}" var="campaignUser" varStatus="campaignUserRow">
															<c:if test="${campaignUser.id == user.id}">																												
															<c:set var="found" value="true"/> 
															<c:set var="userIdVal" value="${campaignUser.id }"/> 
															</c:if>
														</c:forEach>
														<input type="hidden" id="userIds${userRow.index }" name="users[${userRow.index }].id" value="${userIdVal }"/>
														<input ${found ? 'checked' : '' } type="checkbox" id="chkbox${userRow.index }" onclick="insertUserId(this, ${user.id}, ${userRow.index })" onchange="insertUserId(this, ${user.id}, ${userRow.index })"/>
															
														</td>
														<td>
															<c:out value="${user.userName}" />
														</td>
														<td>
															<c:out value="${user.email}" />
														</td>
														<td>
															<c:out value="${user.firstName}" />
														</td>
														<td>
															<c:out value="${user.lastName}" />
														</td>
														<td>
															<c:out value="${user.role.name }" ></c:out>
														</td>
														<td>
															<c:out value="${user.active ? 'yes' : 'no'}" />
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
								</div>
								
								<div style="width:90%;">
									<footer id="submit-actions" class="form-actions">
										<button id="submit-button" type="submit"
											class="btn btn-primary" name="action" value="CONFIRM">Save</button>
										<button type="button"
											onclick="window.location='<spring:url value="/admin/campaign/list" htmlEscape="true" />'" class="btn"
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
	var FieldCount = 0;
	function insertQuestionnaireId(obj, id, index) {
		if($(obj).is(":checked")){
			$('#questionnaireIds' + index).val(id);
		} else {
			$('#questionnaireIds' + index).val('');
			//$('#sectionAttributes'+id).remove();
			//FieldCount--;
		}
	}	
	
	function insertUserId(obj, id, index) {
		if($(obj).is(":checked")){
			$('#userIds' + index).val(id);
		} else {
			$('#userIds' + index).val('');
			//$('#sectionAttributes'+id).remove();
			//FieldCount--;
		}
	}
		$('#isActive').on('click', function(){
			if($(this).is(":checked")){
				$('#active').val('true');
			} else {
				$('#active').val('false');
			}
		});
		
		function setStatus(obj) {
			
			$('#status').val($(obj).val());
		}
		
		$('#assetTarget').val($('#asset').val());

		$('#assetTarget').trigger('change');
		
		$("#checkAll").on('click', function(e){

	    	e.preventDefault();
			e.stopPropagation();
			$('#questionnaireTable tbody tr td input[type="checkbox"]').prop('checked', true).trigger('change');
			/* var table= $(e.target).closest('table');
			$('td input:checkbox',table).prop('checked',"true"); */
		    return false;
		});
		
		$("#unCheckAll").on('click', function(e){

	    	e.preventDefault();
			e.stopPropagation();
			$('#questionnaireTable tbody tr td input[type="checkbox"]').prop('checked', false).trigger('change');
			/* var table= $(e.target).closest('table');
			$('td input:checkbox',table).prop('checked',"true"); */
		    return false;
		});
		
		$("#checkAllUsers").on('click', function(e){

	    	e.preventDefault();
			e.stopPropagation();
			$('#usersTable tbody tr td input[type="checkbox"]').prop('checked', true).trigger('change');
			/* var table= $(e.target).closest('table');
			$('td input:checkbox',table).prop('checked',"true"); */
		    return false;
		});
		
		$("#unCheckAllUsers").on('click', function(e){

	    	e.preventDefault();
			e.stopPropagation();
			$('#usersTable tbody tr td input[type="checkbox"]').prop('checked', false).trigger('change');
			/* var table= $(e.target).closest('table');
			$('td input:checkbox',table).prop('checked',"true"); */
		    return false;
		});
		
		if($('#active').val() == 'true')
			$('#isActive').attr('checked', $('#active').val());
		
		</script>
</body>
</html>