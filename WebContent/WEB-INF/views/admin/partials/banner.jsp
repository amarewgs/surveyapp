<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- CONVENCION DE NOMBRES: Particula "To" antes como prefijo de nombre, significa que su funcionamiento va a ser ajustado -->
<!-- por alguna accion dentro del código. -->

<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
			 <button class="btn btn-navbar" data-toggle="collapse" data-target="#app-nav-top-bar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
				<div class="brand" >
					<a href="<spring:url value="/admin/index" htmlEscape="true" />" style="text-decoration: none;">
						<fmt:message key="app.title" />
					</a>
				</div>	
                    <div id="app-nav-top-bar" class="nav-collapse">				
				
                        <%-- <ul class="nav">                            
                           <li class="dropdown">
                               <a href="#" class="dropdown-toggle" data-toggle="dropdown">Configurations
                                   <b class="caret hidden-phone"></b>
                               </a>
                               <ul class="dropdown-menu">
	                           		<li>
		                               	<a href="<spring:url value="/admin/attribute/list" htmlEscape="true" />">Attribute
		                               	</a>
	                               	</li>
                                   <li>
                                       <a href="<spring:url value="/admin/section/list" htmlEscape="true" />">Section
                                   	</a>
                                   </li>                                   
                                   <li>
                                       <a href="<spring:url value="/admin/template/list" htmlEscape="true" />">Template</a>
                                   </li>
                                   <li>
                                        <a href="<spring:url value="/admin/status/list" htmlEscape="true" />">Status</a>
                                   </li>
                                   <li>
                                        <a href="<spring:url value="/admin/assetType/list" htmlEscape="true" />">Target Type</a>
                                   </li>
                                   <li>
                                        <a href="<spring:url value="/admin/asset/list" htmlEscape="true" />">Target</a>
                                   </li>
                               </ul>
                           </li>
                            
                        </ul> --%>
				
						 <ul class="nav navbar-nav navbar-right">
						        <%-- <li>
						        <a href="#">welcome ${userName}</a>
						        </li> --%>
						        <li class="dropdown">
						          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user icon-white"></i> <c:choose>  <c:when test="${model != null }"> ${model.userName}</c:when> <c:otherwise> ${userName }</c:otherwise></c:choose><span class="caret"></span></a>
						          <ul class="dropdown-menu" role="menu">
						            <li><a href="<spring:url value="/admin/editProfile" htmlEscape="true" />">Edit Profile</a></li>
						            <li><a href="<spring:url value="/admin/users" htmlEscape="true" />">All Users</a></li>
						            <li><a href="<spring:url value="/admin/changePassword" htmlEscape="true" />">Change Password</a></li>
						            <li class="divider"></li>						            
						            <li><a href="<spring:url value="/logout" htmlEscape="true" />">Logout</a>
						            </li>
						          </ul>
						        </li>
					      </ul>	
			      </div>
	      </div>
		</div>
	</div>
	