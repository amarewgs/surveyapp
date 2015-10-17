<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	 <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
	<title><fmt:message key="app.title" /></title>
	<%@ include file ="partials/styles.jsp" %>
	<c:url value="/libs/js/password-strength-meter.js" var="passwordmeter" />
	<script type="text/javascript" src="${passwordmeter}"></script>	
	
	<script type="text/javascript">
	    // <![CDATA[
	    (function($){
	
	        $(document).ready( function() {
	
	            function check_pass_strength () {
	
	                var pass = $('#password').val();
	                var pass2 = $('#verifypassword').val();
	                var user = "";
	                //var user = $('#user_login').val();
	                $('#pass-strength-result').removeClass('short bad good strong');
	                if ( ! pass ) {
	                    $('#pass-strength-result').html( pwsL10n.empty );
	                    return;
	                }
	
	                var strength = passwordStrength(pass, user, pass2);
	
	                if ( 2 == strength )
	                    $('#pass-strength-result').addClass('bad').html( pwsL10n.bad );
	                else if ( 3 == strength )
	                    $('#pass-strength-result').addClass('good').html( pwsL10n.good );
	                else if ( 4 == strength )
	                    $('#pass-strength-result').addClass('strong').html( pwsL10n.strong );
	                else if ( 5 == strength )
	                    $('#pass-strength-result').addClass('short').html( pwsL10n.mismatch );
	                else
	                    $('#pass-strength-result').addClass('short').html( pwsL10n.short );
	
	            }
	
	            $('#password, #verifypassword').val('').keyup( check_pass_strength );
	        });
	    })(jQuery);
	
	    pwsL10n = {
	        empty: "Strength indicator",
	        short: "Very weak",
	        bad: "Weak",
	        good: "Medium",
	        strong: "Strong",
	        mismatch: "Mismatch"
	    }
	    try{convertEntities(pwsL10n);}catch(e){};
	    // ]]>
	</script>

</head>
<body>	
	<%@ include file ="partials/banner.jsp" %>
	<div id="body-container">
   <div id="body-content">	
   <%@ include file="partials/menu.jsp" %>
    <section class="page container">
       <div class="row">
           <div class="span13">
               <div class="box">
               <div class="box-header">
                        <i class="icon-bookmark"></i>
                        <h5>Change Password</h5>
                    </div>
                   <div class="box-content">
                   <spring:url var = "action" value='/admin/updatePassword' />
					<form:form method="post" action="${action }" commandName="changePasswordForm">
					 <div class="container">	
						<div >
						<div style="width: 80%; display: inline-block;">
							<form:errors path="*" cssClass="errorblock span5" element="div" />
						</div>	
						<form:hidden path="userId" name="userId" value="${id }"/>
						 <p>
					        <form:label path="oldPassword">old password</form:label>
							<form:input path="oldPassword" type="password" id="oldPassword"></form:input>
						</p>				
						 <p>
					        <form:label path="password">new password</form:label>
							<form:input path="password" type="password" id="password"></form:input>
						</p>
						 <p>
					        <label for="confirmPassword">new confirm password</label>
							<form:input path="confirmPassword" type="password" id="verifypassword"/>
						</p>
						<div style="width: 100%; display: inline-block; padding: 0px 0px 10px;">
						 <div id="pass-strength-result" class="span3">Strength indicator</div>
						 </div>
				         <p>
					         <input class="btn btn-primary" type="submit" value="Submit"/>
					        
					         <a class="btn btn-primary"  href="<spring:url value="/admin/index" htmlEscape="true" />">Cancel</a>
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
		<%@ include file ="partials/footer.jsp" %>
			
	</div>
</div>
</body>
</html>
