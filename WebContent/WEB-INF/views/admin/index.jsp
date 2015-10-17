<%@ include file="/WEB-INF/views/include.jsp"%>
<html lang="es">
<head>
	<meta charset="utf-8">
 	<meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="partials/styles.jsp" %>
	<% response.setHeader("pragma", "no-cache");              
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
	response.setHeader("Expires", "0");  %>
</head>
<body>
	<!-- <div class="page container"> -->
		<%@ include file ="partials/banner.jsp" %>
	      	<div id="body-container">
	            <div id="body-content">	
              	<%@ include file="partials/menu.jsp" %>
				    <section class="page container">
				       	<div class="row">
						  <%@ include file="partials/treemenu.jsp" %>
				            <div class="span12">
				                <div class="box">
				                    <div class="box-header">
				                        <i class="icon-bookmark"></i>
				                        <h5>QUICK ITEM MENU</h5>
				                    </div>
				                    <div class="box-content">
				                        <div class="btn-group-box">
				                            <button class="btn" onclick="window.location='<spring:url value="/admin/questionnaire/list" htmlEscape="true" />'"><i class="icon-dashboard icon-large"></i><br/>Survey</button>
				                            <button class="btn" onclick="window.location='<spring:url value="/admin/users" htmlEscape="true" />'"><i class="icon-user icon-large"></i><br/>Account</button>
				                            <button class="btn" onclick="window.location='<spring:url value="/admin/question/list" htmlEscape="true" />'"><i class="icon-search icon-large"></i><br/>Questions</button>
				                            <button class="btn" onclick="window.location='<spring:url value="/admin/questionnaire/list" htmlEscape="true" />'"><i class="icon-list-alt icon-large"></i><br/>Questionnaire</button>
				                            <button class="btn" onclick="window.location='<spring:url value="/admin/campaign/list" htmlEscape="true" />'"><i class="icon-bar-chart icon-large"></i><br/>Campaign</button>
				                        </div>
				                    </div>
				               </div>
				           </div>
						</div>
					</section>
				</div>
			</div>
		<!-- </div> -->
</body>
</html>