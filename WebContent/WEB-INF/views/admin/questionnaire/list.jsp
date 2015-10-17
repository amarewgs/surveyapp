<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
	<meta charset="utf-8">
	<title>${title}</title>
	<%@ include file ="../partials/styles.jsp" %>
<% response.setHeader("pragma", "no-cache");              
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	response.setHeader("Expires", "0");  %>
	
	<script type="text/javascript" language="javascript" class="init">

		$(document).ready(function() {
			$("#questionnaireTreeview").treeview({ speed: "fast", collapsed: true});
			
			$("#questionnaireTree").treetable({ expandable: true });
			
			// Drag & Drop Example Code
			$("#questionnaireTree.file, #questionnaireTree .folder").draggable({
			  helper: "clone",
			  opacity: .75,
			  refreshPositions: true,
			  revert: "invalid",
			  revertDuration: 300,
			  scroll: true
			});
			$('table#questionnaireTree tbody tr').mousedown(function () {
		        $('tr.selected').removeClass('selected');
		        $(this).addClass('selected');
		    });
		    $("table#questionnaireTree tbody tr span").mousedown(function () {
		        $($(this).parents("tr")[0]).trigger("mousedown");
		    });
		    
		    //$("#questionnaireTree").dataTable();
		    
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
		               		<div class="box">
			                   <div class="box-header">
			                       <i class="icon-bookmark"></i>
			                       <h5>Questionnaires Available</h5>
			                       <div style="float: right;">
			                       		<a href="<spring:url value="/admin/questionnaire/form" htmlEscape="true" />" class="btn btn-small btn-add">
				                            <i class="btn-icon-only icon-plus"></i>
				                        </a>
				                        <button id="submit-button" type="submit"
												class="btn btn-small btn-remove" name="action" value="CONFIRM"><i class="btn-icon-only icon-remove"></i></button>
			                       </div>
			                   </div>	
			                   <div class="box-content">
				                   <table id="questionnaireTree">
				                   <thead>
				                   	<th>name</th>
				                   	<th>Description</th>
				                   	<th></th>
				                   	<th>active</th>
				                   	<th></th>
				                   </thead>
				                   	 <tbody>
					                   	 <c:forEach items="${questionnaireList}" var="questionnaire" varStatus="questionnaireRow">
										  <tr data-tt-id="${questionnaire.groupId }">
										    <td><span><c:out value="${questionnaire.categoryName}" /></span></td>
										    <td><span style="padding: 0;"><c:out value="${questionnaire.description}" /></span></td>
										    <td><span style="padding: 0;"><c:out value="${questionnaire.questionSize}" /> Questions</span></td>
										    <td>
												<c:out value="${questionnaire.active ? 'YES' : 'NO'}" />
											</td>
										    <td style="width: 100px;">
											    <a id="delete_link" data-href="<spring:url value="/admin/questionnaires/delete/${questionnaire.groupId }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small btn-remove">
						                            <i class="btn-icon-only icon-trash"></i>
						                        </a>
						                        <a href="<spring:url value="/admin/questionnaire/form/${questionnaire.groupId }" htmlEscape="true" />" class="btn btn-small btn-edit">
						                            <i class="btn-icon-only icon-edit"></i>
						                        </a>
						                        <a href="<spring:url value="/admin/questionnaire/detail/${questionnaire.groupId }" htmlEscape="true" />" class="btn btn-small btn-detail">
						                            <i class="btn-icon-only icon-folder-open"></i>
						                        </a>
										    </td>
										  </tr>
											  <c:forEach items="${questionnaire.questions}" var="question" varStatus="questionRow">
											  <tr ondblclick="" data-tt-id="${question.id }" data-tt-parent-id="${questionnaire.groupId }">
											    <td><span style="float: left; padding: 0;"><c:out value="${question.name}" /></span></td>
											    <td><span style="padding: 0;"><c:out value="${question.question}" /></span></td>
											    <td><span style="padding: 0;">${question.text}</span></td>
											    <td>
													<c:out value="${question.active ? 'YES' : 'NO'}" />
												</td>
											    <td style="width: 100px;">
											    	<a id="delete_link" data-href="<spring:url value="/admin/questions/delete/${question.id }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small btn-remove">
								                            <i class="btn-icon-only icon-trash"></i>
								                        </a>
								                        <a href="<spring:url value="/admin/question/form/${question.id }" htmlEscape="true" />" class="btn btn-small btn-edit">
								                            <i class="btn-icon-only icon-edit"></i>
								                        </a>
								                         <a href="<spring:url value="/admin/question/detail/${question.id }" htmlEscape="true" />" class="btn btn-small btn-detail">
								                            <i class="btn-icon-only icon-folder-open"></i>
								                        </a>
											    </td>
											  </tr>
											   </c:forEach>
										  </c:forEach>
									  </tbody>
									</table>
			                   </div>
		                   	</div>
		                 </div>
					</div>
				</section>
		</div>
	</div>	
<!-- <div id="delete-dialog" title="Confirm Delete">
    <p>Are you sure you want to delete this record?</p>
</div> -->
<%@ include file ="../partials/delete_dialog.jsp" %>
	
</body>
</html>
			