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
				<div class="brand" ><a href="<spring:url value="/public/auth/questionnaireList" htmlEscape="true" />" style="text-decoration: none;"><fmt:message key="app.title" /></a></div>	
				 <ul class="nav navbar-nav navbar-right">
				        <%-- <li>
				        <a href="#">welcome ${userName}</a>
				        </li> --%>
				        <li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user icon-white"></i> <c:choose>  <c:when test="${model != null }"> ${model.userName}</c:when> <c:otherwise> ${userName }</c:otherwise></c:choose><span class="caret"></span></a>
				          <ul class="dropdown-menu" role="menu">
				            <li><a href="<spring:url value="/public/auth/editProfile" htmlEscape="true" />">Edit Profile</a></li>
				            <%-- <li><a href="<spring:url value="/admin/register" htmlEscape="true" />">Create Admin</a></li> --%>
				            <li><a href="<spring:url value="/public/auth/changePassword" htmlEscape="true" />">Change Password</a></li>
				            <li class="divider"></li>
				            <li><a href="<spring:url value="/logout" htmlEscape="true" />">Logout</a>
				            </li>
				          </ul>
				        </li>
			      </ul>	
	      </div>
		</div>
	</div>
	