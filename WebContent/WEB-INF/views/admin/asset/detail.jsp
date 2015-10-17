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
	    <%@ include file="../partials/target_menu.jsp" %>	
			<section class="page container">
				<div class="row">
				<%@ include file="../partials/treemenu.jsp" %>
					<div class="span12">
						<div class="box" style="display: inline-block;">
							<div class="box-header">
								<i class="icon-bookmark"></i>
								<h5>Target Detail</h5>
								<div style="float: right;">
									<a href="<spring:url value="/admin/asset/list" htmlEscape="true" />" class="btn btn-small btn-remove">
			                            <i class="btn-icon-only icon-remove"></i>
			                        </a>
									<a id="delete_link" data-href="<spring:url value="/admin/asset/delete/${asset.assetId }" htmlEscape="true" />" data-toggle="modal" onclick="openDeleteDialog(this)" class="btn btn-small trash">
			                            <i class="btn-icon-only icon-trash"></i>
			                        </a>									
			                        <a href="<spring:url value="/admin/asset/form/${asset.assetId }" htmlEscape="true" />" class="btn btn-small btn-edit">
			                            <i class="btn-icon-only icon-edit"></i>
			                        </a>
								</div>
							</div>
							<div class="box-content">
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Name</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${asset.name}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Description</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${asset.description}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Active</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${question.active ? 'Yes' : 'No'}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Target Type</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${asset.targetType.name}</label>
										</div>
									</div>
								</div>
								<div class="form form-horizontal" style="display: inline-block; margin-bottom: 4px; width: 90%; vertical-align: top;">
									<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
										<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">Status</label> 
										<div class="" style="display: inline-block; width: 50%; float: none;">
											<label class="span4" style="">${asset.status.name}</label>
										</div>
									</div>
								</div>
								
								<fieldset>
									<legend>Target Attributes</legend>
									<c:forEach items="${assetTemplate.sectionAttributes}" var="sectionAttribute" varStatus="sectionAttributeRow">
										<legend style="font-size: 13px;">${sectionAttribute.name} Attributes</legend>
										<c:forEach items="${sectionAttribute.attributes}" var="attribute" varStatus="attributeRow">
										<div class="control-group " style="width: 100%;display: inline-block; margin-bottom: 0;">
											<label style="width: 15%; display: inline-block; vertical-align: top; margin-bottom: 0" class="control-label">${attribute.label }</label> 
											<div class="" style="display: inline-block; width: 50%; float: none;">
												<label class="span4" style="">${attribute.value}</label>
											</div>
										</div>
										</c:forEach>
									</c:forEach>
								</fieldset>
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
				