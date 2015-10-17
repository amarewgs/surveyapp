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
			$('#questionTable').dataTable();
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
		               <div class="box" style="display:inline-block; width: 100%">
		               
					  	<spring:url var = "action" value='/admin/campaigns/delete' htmlEscape="true"/>
						<form:form style="width: 100%" method="post" action="${action}" class="form" commandName="campaignIdsForm" id="campaignIdsForm">
			                   <div class="box-header">
			                       <i class="icon-bookmark"></i>
			                       <h5>Campaigns</h5>
			                       <div style="float: right;">
			                       		<a href="<spring:url value="/admin/campaign/form" htmlEscape="true" />" class="btn btn-small btn-add">
				                            <i class="btn-icon-only icon-plus"></i>
				                        </a>
				                        <button id="submit-button" type="submit"
												class="btn btn-small btn-remove" name="action" value="CONFIRM"><i class="btn-icon-only icon-trash"></i></button>
			                       </div>
			                   </div>	
			                   <div class="box-content">
			                   
								<c:if test="${null != campaignList }">
									<div style="width: 100%; display: inline-block;">
										<form:errors path="*" cssClass="errorblock span5" element="div" />
									</div>
									<table id="questionTable" class="stripe table table-hover table-bordered tablesorter">
										<thead>
											<tr>
												<th></th>
										      <th>Name</th>
										      <th>Description</th>
										      <th>Active</th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${campaignList}" var="campaign" varStatus="campaignRow">
												<tr> 
													<td>
														<input type="checkbox" id="chkbox${campaignRow.index }" onclick="insertQuestionnaireId(this, ${campaign.id}, ${campaignRow.index })" />
													</td>
													<td>
														<a href="<spring:url value='/admin/campaign/form/${campaign.id }' htmlEscape="true"/>"> <c:out value="${campaign.name}" /></a>
													</td>
													<td>
														<c:out value="${campaign.description}" />
													</td>
													<td>
														<c:out value="${campaign.active ? 'YES' : 'NO'}" />
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</c:if>
									<c:if test="${null == campaignList }">
										No Campaigns Found
									</c:if>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</section>
		</div>
	</div>	
<div id="delete-dialog" title="Confirm Delete">
    <p>Are you sure you want to delete this record?</p>
</div>
<%@ include file ="../partials/delete_dialog.jsp" %>
	
	<script type="text/javascript">

	var FieldCount=0; //to keep track of text box added

	function insertQuestionnaireId(obj, id) {
		if($(obj).is(":checked")){
		 	$(obj).parent().append('<input type="hidden" id="campaignIds' + id + '" name="campaignIds[' + FieldCount + ']" value="' + id + '"/>');
		 	FieldCount++;
		} else {
			FieldCount--;
			$('#groupIds'+id).remove();
		}
	}
	$(document).ready(function(){
		
		// Dialog
        $('#delete-dialog').dialog({
            resizable: false,
            autoOpen: false,
            width: 300,
            height: 140,
            modal: true,
            buttons: {
                "Ok": function() {
                    $(this).dialog("close");
                    if(isFormSubmission)
                    	$('form').submit();
                    else
                    	window.location = deletelink;
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            }
        });
		
		$("#submit-button").click(function() {
			
			isFormSubmission = true;
			
			$('#delete-dialog').dialog('open');
			return false;
			
		});
	});
	</script>
</body>
</html>
			