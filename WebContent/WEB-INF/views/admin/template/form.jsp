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
			$('#attributeTable').dataTable();
		} );

	</script>
</head>
<body>
		<%@ include file="../partials/banner.jsp"%>
<div id="body-container">
   <div id="body-content">	
    <%@ include file="../partials/target_menu.jsp" %>
		<section class="page container">
			<div class="row">
			<%@ include file="../partials/treemenu.jsp" %>
				<div class="span12" style="margin: 0 auto; ">
					<div class="box" style="display:inline-block; width: 100%">
					<spring:url var = "action" value='/admin/template/save' />
						<form:form style="width: 100%" method="post" action="${action}" class="form form-horizontal" commandName="templateForm" id="templateForm">
						<div class="box-header">
							<i class="icon-bookmark"></i>
							<h5>Create New Template</h5>
						</div>
						<div class="box-content">
						
								<div class="">
								<div style="width: 100%; display: inline-block;">
									<form:errors path="*" cssClass="errorblock span5" element="div" />
								</div>
									<div>
										<form:input type="hidden" path="templateId" name="templateId" value="${templateId }"></form:input>
											
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
													<input type="checkbox" style="width: 40px;" id="isActive" class="span5" value="is active" ${sectionForm.active ? 'checked' : ''}/>
														<input type="hidden" id="active" value="${templateForm.active }" name="active">
												</div>
											</div>										
									</div>
									<div><legend>Add Sections</legend>
									<table id="attributeTable" class="stripe table table-hover table-bordered tablesorter">
										<thead>
											<tr>
												<th></th>
										      <th>Name</th>							      
										      <th>Description</th>
										      <th>Active</th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${sections}" var="section" varStatus="sectionRow">
											<c:set var="found" value="${false }"/> 
												<tr> 
													<td>
													<input type="hidden" id="sectionAttributes${sectionRow.index }" name="sectionIds[${sectionRow.index }]" value=""/>
														
														<c:forEach items="${templateForm.sectionIds}" var="sectionId" varStatus="sectionIdRow">
															<c:if test="${sectionId == section.id}">																												
															<c:set var="found" value="${true}"/> 
															</c:if>
														</c:forEach>
														<input ${found ? 'checked' : '' } type="checkbox" id="chkbox${attributeRow.index }" onclick="insertAttributeId(this, ${section.id}, ${sectionRow.index })" />
														
													</td>
													<td>
														<c:out value="${section.name}" />
													</td>	
													<td>
														<c:out value="${section.description}" />
													</td>
													<td>
														<c:out value="${section.active}" />
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
									<div style="width:90%;">
										<footer id="submit-actions" class="form-actions">
											<button id="submit-button" type="submit"
												class="btn btn-primary" name="action" value="CONFIRM">Save</button>
											<button type="button"
												onclick="window.location='<spring:url value="/admin/template/list" htmlEscape="true" />'" class="btn"
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

	var FieldCount=0; //to keep track of text box added

	function insertAttributeId(obj, id, index) {
		console.log(index);
		if($(obj).is(":checked")){
			$('#sectionAttributes' + index).val(id);
		} else {
			$('#sectionAttributes' + index).val('');
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

	if($('#active').val() == 'true')
		$('#isActive').attr('checked', $('#active').val());
	
</script>

</body>
</html>