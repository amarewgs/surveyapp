<%@ include file="/WEB-INF/views/include.jsp"%>
	
<script type="text/javascript" language="javascript" class="init">

	$(document).ready(function() {
		$("#menuTreeview").treeview({ 
			//animated: "fast",
	        collapsed: true,
	        //unique: true, 
	        speed: "fast", 
	        persist: "cookie"
	        });
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
            			<span class="folder"> &nbsp; Attributes</span>
            			<ul style="">
            					<li><span class="file"><a href="<spring:url value="/admin/attribute/list" htmlEscape="true" />">Display All</a></span></li>
            					<li>
            						<span class="folder"><a href="<spring:url value="/admin/attribute/listByActive/true" htmlEscape="true" />"> By Active</a></span>
            					
	            					<ul style="">
										<c:forEach items="${attributes}" var="attribute" varStatus="attributeRow">
											<c:if test="${ attribute.active == true}">
												<li id="${ attribute.id}"> 
													<span class="file"><a href="<spring:url value="/admin/attribute/detail/${attribute.id }" htmlEscape="true" />"> <c:out value="${attribute.label}" /></a></span>
												</li>	
											</c:if>									
										</c:forEach>
									</ul>
								</li>
								
								<li><span class="folder"><a href="<spring:url value="/admin/attribute/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
									<ul style="">
										<c:forEach items="${attributes}" var="attribute" varStatus="attributeRow">
											<c:if test="${ attribute.active == false}">
												<li id="${ attribute.id}"> 
													<span class="file"><a href="<spring:url value="/admin/attribute/detail/${attribute.id }" htmlEscape="true" />"> <c:out value="${attribute.label}" /></a></span>
												</li>	
											</c:if>									
										</c:forEach>
									</ul>
								</li>
							</ul>
            		</li>
            		<li>
            			<span class="folder"> &nbsp; Sections</span>
            				<ul style="">
           					<li><span class="file"><a href="<spring:url value="/admin/section/list" htmlEscape="true" />">Display All</a></span></li>
           					<li>
           						<span class="folder"><a href="<spring:url value="/admin/section/listByActive/true" htmlEscape="true" />"> By Active</a></span>
           					
            					<ul style="">
									<c:forEach items="${sections}" var="section" varStatus="sectionRow">
										<c:if test="${ section.active == true}">
											<li id="${ section.id}"> 
												<span class="file"><a href="<spring:url value="/admin/section/detail/${section.id }" htmlEscape="true" />"> <c:out value="${section.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/section/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${sections}" var="section" varStatus="sectionRow">
										<c:if test="${ section.active == false}">
											<li id="${ section.id}"> 
												<span class="file"><a href="<spring:url value="/admin/section/detail/${attribute.id }" htmlEscape="true" />"> <c:out value="${section.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
						</ul>
            		</li>
            		<li>
            			<span class="folder"> &nbsp; Templates</span>
            			<ul style="">
	          				<li><span class="file"><a href="<spring:url value="/admin/template/list" htmlEscape="true" />">Display All</a></span></li>
							<li>
	          						<span class="folder"><a href="<spring:url value="/admin/template/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${templates}" var="template" varStatus="templateRow">
										<c:if test="${ template.active == true}">
											<li id="${ template.id}"> 
												<span class="file"><a href="<spring:url value="/admin/template/detail/${template.id }" htmlEscape="true" />"> <c:out value="${template.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/template/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${templates}" var="template" varStatus="templateRow">
										<c:if test="${ template.active == false}">
											<li id="${ template.id}"> 
												<span class="file"><a href="<spring:url value="/admin/template/detail/${template.id }" htmlEscape="true" />"> <c:out value="${template.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
							  </ul>
						</li>
					</ul>
            		</li>
            		<li>
            			<span class="folder"> &nbsp; Status</span>
            			  <ul style="">
            				<li><span class="file"><a href="<spring:url value="/admin/status/list" htmlEscape="true" />">Display All</a></span></li>
            					
           					<li>
	          					<span class="folder"><a href="<spring:url value="/admin/status/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${statuses}" var="status" varStatus="statusRow">
										<c:if test="${ status.active == true}">
											<li id="${ status.id}"> 
												<span class="file"><a href="<spring:url value="/admin/status/detail/${status.id }" htmlEscape="true" />"> <c:out value="${status.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/status/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${statuses}" var="status" varStatus="statusRow">
										<c:if test="${ status.active == false}">
											<li id="${ status.id}"> 
												<span class="file"><a href="<spring:url value="/admin/status/detail/${status.id }" htmlEscape="true" />"> <c:out value="${status.name}" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
						</ul>
            		</li>
            		<li>
            			<span class="folder"> &nbsp; Target Types</span>
            			<ul style="">
           					<li><span class="file"><a href="<spring:url value="/admin/assetType/list" htmlEscape="true" />">Display All</a></span></li>
           					<li>
	          					<span class="folder"><a href="<spring:url value="/admin/assetType/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${targetTypes}" var="targetType" varStatus="targetTypeRow">
										<c:if test="${ targetType.active == true}">
											<li id="${ targetType.id}"> 
												<span class="file"><a href="<spring:url value="/admin/assetType/detail/${targetType.id }" htmlEscape="true" />"> <c:out value="${targetType.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/assetType/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${targetTypes}" var="targetType" varStatus="targetTypeRow">
										<c:if test="${ targetType.active == false}">
											<li id="${ targetType.id}"> 
												<span class="file"><a href="<spring:url value="/admin/assetType/detail/${targetType.id }" htmlEscape="true" />"> <c:out value="${targetType.name}" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
						</ul>
            		</li>
            		<li>
            			<span class="folder"> &nbsp; Targets</span>
            			<ul style="">
            				<li><span class="file"><a href="<spring:url value="/admin/asset/list" htmlEscape="true" />">Display All</a></span></li>
            				<li>
	          					<span class="folder"><a href="<spring:url value="/admin/asset/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${targets}" var="target" varStatus="targetRow">
										<c:if test="${ target.active == true}">
											<li id="${ target.id}"> 
												<span class="file"><a href="<spring:url value="/admin/asset/detail/${target.id }" htmlEscape="true" />"> <c:out value="${target.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/asset/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${targets}" var="target" varStatus="targetRow">
										<c:if test="${ target.active == false}">
											<li id="${ target.id}"> 
												<span class="file"><a href="<spring:url value="/admin/asset/detail/${targetType.id }" htmlEscape="true" />"> <c:out value="${target.name}" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
							</ul>
            		</li>
            		<li>
            			<span class="folder">&nbsp;Campaigns</span>
							<ul style="">
							<li><span class="file"><a href="<spring:url value="/admin/campaign/list" htmlEscape="true" />">Display All</a></span></li>
							<li>
	          					<span class="folder"><a href="<spring:url value="/admin/campaign/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${campaignMenues}" var="campaign" varStatus="campaignRow">
										<c:if test="${ campaign.active == true}">
											<li id="${ campaign.id}"> 
												<span class="file"><a href="<spring:url value="/admin/campaign/detail/${campaign.id }" htmlEscape="true" />"> <c:out value="${campaign.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/campaign/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${campaignMenues}" var="campaign" varStatus="campaignRow">
										<c:if test="${ campaign.active == false}">
											<li id="${ campaign.id}"> 
												<span class="file"><a href="<spring:url value="/admin/campaign/detail/${campaign.id }" htmlEscape="true" />"> <c:out value="${campaign.name}" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
							</ul>
            		</li>
					<li>	<span class="folder">&nbsp;Questionnaires</span>
							<ul  style="">
							<li><span class="file"><a href="<spring:url value="/admin/questionnaire/list" htmlEscape="true" />">Display All</a></span></li>
							<li>
	          					<span class="folder"><a href="<spring:url value="/admin/questionnaire/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${questionGroups}" var="questionnaire" varStatus="questionnaireRow">
										<c:if test="${ questionnaire.active == true}">
											<li id="${ questionnaire.id}"> 
												<span class="file"><a href="<spring:url value="/admin/questionnaire/detail/${questionnaire.id }" htmlEscape="true" />"> <c:out value="${questionnaire.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/questionnaire/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${questionGroups}" var="questionnaire" varStatus="questionnaireRow">
										<c:if test="${ questionnaire.active == false}">
											<li id="${ questionnaire.id}"> 
												<span class="file"><a href="<spring:url value="/admin/questionnaire/detail/${questionnaire.id }" htmlEscape="true" />"> <c:out value="${questionnaire.name}" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
								
								<%-- <c:forEach items="${questionnaires}" var="questionnaire" varStatus="questionnaireRow">
									<li id="${ questionnaire.groupId}"> 
										<span><a href="<spring:url value="/admin/questionnaire/detail/${questionnaire.groupId }" htmlEscape="true" />"><c:out value="${questionnaire.categoryName}" /></a></span>											
									</li>											
								</c:forEach> --%>
							</ul>
					</li>
					<li><span class="folder">&nbsp;Questions</span>
						<ul >
							<li><span class="file"><a href="<spring:url value="/admin/question/list" htmlEscape="true" />">Display All</a></span></li>
							<li>
	          					<span class="folder"><a href="<spring:url value="/admin/question/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${questions}" var="question" varStatus="questionRow">
										<c:if test="${ question.active == true}">
											<li id="${ question.id}"> 
												<span class="file"><a href="<spring:url value="/admin/question/detail/${question.id }" htmlEscape="true" />"> <c:out value="${question.name}" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/question/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${questions}" var="question" varStatus="questionRow">
										<c:if test="${ question.active == false}">
											<li id="${ question.id}"> 
												<span class="file"><a href="<spring:url value="/admin/question/detail/${question.id }" htmlEscape="true" />"> <c:out value="${question.name}" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
							<%-- <c:forEach items="${questions}" var="question" varStatus="questionRow">
								<li id="${ question.id}"> 
									<span><a href="<spring:url value="/admin/question/detail/${question.id }" htmlEscape="true" />" id=""><span><c:out value="${question.name}" /></span></a></span>
								</li>
							</c:forEach> --%>
						</ul>
					</li>
					<li><span class="folder">&nbsp;Users</span>
						<ul >
							<li><span class="file"><a href="<spring:url value="/admin/users" htmlEscape="true" />">Display All</a></span></li>
							
							<li>
	          					<span class="folder"><a href="<spring:url value="/admin/user/listByActive/true" htmlEscape="true" />"> By Active</a></span>
	          					
	           					<ul style="">
									<c:forEach items="${users}" var="user" varStatus="userRow">
										<c:if test="${ user.active == true}">
											<li id="${ user.id}"> 
												<span class="file"><a href="<spring:url value="/admin/user/form/${user.id }" htmlEscape="true" />"> <c:out value="${user.firstName } ${user.lastName }" /></a></span>
											</li>	
										</c:if>									
									</c:forEach>
								</ul>
							</li>
							
							<li><span class="folder"><a href="<spring:url value="/admin/user/listByActive/false" htmlEscape="true" />"> By Inactive</a></span>
								<ul style="">
									<c:forEach items="${users}" var="user" varStatus="userRow">
										<c:if test="${ user.active == false}">
											<li id="${ user.id}"> 
												<span class="file"><a href="<spring:url value="/admin/user/form/${user.id }" htmlEscape="true" />"> <c:out value="${user.firstName } ${user.lastName }" /></a></span>
											</li>	
										</c:if>									
										</c:forEach>
								  </ul>
							</li>
							
							<%-- <c:forEach items="${users}" var="user" varStatus="userRow">
								<li id="${ user.id}"> 
									<span class="file"><a href="<spring:url value="/admin/users/form/${user.id }" htmlEscape="true" />" id=""><span><c:out value="${user.firstName } ${user.lastName }" /></span></a></span>
								</li>
							</c:forEach> --%>
						</ul>
					</li>
					<%-- <li><span class="folder"><a href="<spring:url value="/admin/users/questionnaireResults" htmlEscape="true" />">User Responses</a></span>
					</li> --%>
			  </ul>  
		</div>
	</div>
</div>