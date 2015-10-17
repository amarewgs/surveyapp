<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	 <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="../partials/styles.jsp" %>

</head>
<body>
	
	<%@ include file ="banner.jsp" %>
	<div id="body-container">
   <div id="body-content">	
    <section class="page container">
       <div class="row">
           <div class="span8">
               <div class="box">
               <div class="box-header">
                        <i class="icon-bookmark"></i>
                        <h5>Edit Profile</h5>
                    </div>
                   <div class="box-content">
                   <spring:url var = "action" value='/public/auth/updateProfile' />
					<form:form method="post" action="${action }" commandName="user">
					<form:hidden path="id" name="id" value="${id }"/>
					 <div class="container">	
						<div style="width: 90%; display: inline-block;">
							<form:errors path="*" cssClass="errorblock span5" element="div" />
						</div>	
						<div >
					    <p>
						    <form:label path="firstName">First Name</form:label>
						    <form:input path="firstName"></form:input>
						</p>
						<p>
						    <form:label path="lastName">Last Name</form:label>
						    <form:input path="lastName"></form:input>
						</p>	
						<p>
						    <form:label path="email">email</form:label>
						    <form:input path="email"></form:input>
						</p>						
				         <p>
					         <input class="btn btn-primary" type="submit" value="Submit"/>
					        
					         <!-- <a class="btn btn-primary"  href="#">Cancel</a> -->
					      </p>
					      </div>
					      </div>
					      
					</form:form>
					</div>
		   	</div>
		   	</div>
		   	
		</div>
		</section>
		<br/>
		<br/>		
		<%@ include file ="../partials/footer.jsp" %>
			
	</div>
</div>
</body>
</html>
