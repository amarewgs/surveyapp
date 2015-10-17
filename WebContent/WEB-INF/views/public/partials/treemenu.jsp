<%@ include file="/WEB-INF/views/include.jsp"%>
	
<script type="text/javascript" language="javascript" class="init">

	$(document).ready(function() {
		$("#menuTreeview").treeview({ speed: "fast", collapsed: false});
		});
</script>
	
 <div class="span4" > 	
     <div id="mainTreeMenu">  
       <div class="box" style=" " >
            <div class="box-header " style="position: fixed; width: 248px;">
                <i class="icon-bookmark"></i>
                <h5>Navigation Menu</h5>
            </div>          
            	<ul id="menuTreeview" class="filetree">
            		<li>
            			<span class="folder"><a href="<spring:url value="/public/auth/userCampaigns" htmlEscape="true" />">Campaigns</a></span>
							<ul style="">
								<c:forEach items="${campaigns}" var="campaign" varStatus="campaignRow">
									<li id="${ campaign.campaignId}"> 
										<span class="file"><a href="<spring:url value="/public/auth/questionnaires/${campaign.campaignId }" htmlEscape="true" />"> <c:out value="${campaign.name}" /> Questionnaires</a></span>
										
										<ul  style="">
											<c:forEach items="${campaign.questionnaires}" var="questionnaire" varStatus="questionnaireRow">
												<li id="${ questionnaire.groupId}"> 
													<span class="file"><a href="<spring:url value="/public/auth/questionnaire/${questionnaire.groupId }/${campaign.campaignId }" htmlEscape="true" />"><c:out value="${questionnaire.categoryName}" /> Questions</a></span>											
													
													<ul >
														<c:forEach items="${questionnaire.questions}" var="question" varStatus="questionRow">
															<li id="${ question.id}"> 
																<span><a href="<spring:url value="#" htmlEscape="true" />" id=""><span><c:out value="${question.name}" /></span></a></span>
															</li>
														</c:forEach>
													</ul>
												</li>											
											</c:forEach>
										</ul>
							
									</li>										
								</c:forEach>
							</ul>
            		</li>
			  </ul>  
		</div>
	</div>
</div>