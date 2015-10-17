<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="partials/styles.jsp" %>
	<% response.setHeader("pragma", "no-cache");              
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
response.setHeader("Expires", "0");  %>
</head>
<body>
 <div id="body-container">
            <div id="body-content">
		<%@ include file ="partials/banner.jsp" %>
	
		
		<div class="container">	

		<div class="signin-row row">
                <div class="span4"></div>
                <div class="span8">
                    <div class="container-signin">
                        <legend>Please Login</legend>                        	
						<c:if test="${not empty param.error}">
						    <div class="error">
						        Your login attempt was not successful, try again.<br />
						        <%=((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage()%>
						    </div>
						</c:if>
                        <form action="<spring:url value="/j_spring_security_check" htmlEscape="true" />" method="post" id="loginForm" class="form-signin" autocomplete="off">
                            <div class="form-inner">
                                <div class="input-prepend">
                                    
                                    <span class="add-on" rel="tooltip" title="Username or E-Mail Address" data-placement="top"><i class="icon-envelope"></i></span>
                                    <input type="text" class="span4" id="j_username" name="j_username">
                                </div>

                                <div class="input-prepend">
                                    
                                    <span class="add-on"><i class="icon-key"></i></span>
                                    <input type="password" class="span4" id="j_password" name="j_password">
                                </div>
                                <label class="checkbox" for="remember_me">Remember me
                                    <input type="checkbox" id="remember_me">
                                </label>
                            </div>
                            <footer class="signin-actions">
                                <input class="btn btn-primary" type="submit" id="submit" value="Login">
                                &nbsp;&nbsp;<a class="btn btn-primary" href="<spring:url value="/public/register" htmlEscape="true" />">register</a>
                            </footer>
                        </form>
                    </div>
                </div>
                <div class="span3"></div>
            </div>
		</div>
	</div>
	</div>
</body>
</html>