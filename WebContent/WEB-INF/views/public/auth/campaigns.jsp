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
<%@ include file ="banner.jsp" %>
<div id="body-container">
   <div id="body-content">
		    <section class="page container">
		       <div class="row">
		           <div class="span15">
		               <div class="box" style="display:inline-block; width: 100%">
			                   <div class="box-header">
			                       <i class="icon-bookmark"></i>
			                       <h5>Your Campaigns</h5>
			                   </div>	
			                   <div class="box-content">
			                   
								<c:if test="${null != userCampaigns }">
									<div style="width: 100%; display: inline-block;">
										<form:errors path="*" cssClass="errorblock span5" element="div" />
									</div>
									<table id="questionTable" class="stripe table table-hover table-bordered tablesorter">
										<thead>
											<tr>
										      <th>Name</th>
										      <th>Description</th>
										      <th>Target</th>
										      <th>Active</th>
								    		</tr>
								 		</thead>
							   			<tbody>
											<c:forEach items="${userCampaigns}" var="userCampaign" varStatus="userCampaignRow">
												<tr> 
													<td>
														<a href="<spring:url value='/public/auth/questionnaires/${userCampaign.campaign.id }' htmlEscape="true"/>"> <c:out value="${userCampaign.campaign.name}" /></a>
													</td>
													<td>
														<c:out value="${userCampaign.campaign.description}" />
													</td>
													<td>
														<c:out value="${userCampaign.campaign.asset.name }"/>
													</td>
													<td>
														<c:out value="${userCampaign.campaign.active ? 'YES' : 'NO'}" />
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</c:if>
									<c:if test="${null == userCampaigns }">
										No Campaigns Found
									</c:if>
									</div>
							</div>
						</div>
					</div>
				</section>
		</div>
	</div>	
</body>
</html>
			