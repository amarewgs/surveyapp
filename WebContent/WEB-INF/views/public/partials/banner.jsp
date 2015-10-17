<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- CONVENCION DE NOMBRES: Particula "To" antes como prefijo de nombre, significa que su funcionamiento va a ser ajustado -->
<!-- por alguna accion dentro del código. -->

<div class="navbar navbar-fixed-top" role="navigation">
		<div class="navbar-inner">
			<div class="container">
				<div class="brand" ><a href="<spring:url value="/public/auth/questionnaireList" htmlEscape="true" />" style="text-decoration: none;"><fmt:message key="app.title" /></a></div>	
				 <ul class="nav navbar-nav navbar-right">
				        <%-- <li>
				        <a href="#">welcome ${userName}</a>
				        </li> --%>
			      </ul>	
	      </div>
		</div>
	</div>