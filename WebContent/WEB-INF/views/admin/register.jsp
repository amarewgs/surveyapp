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

			
			
			function checkInputs() {
				if ($("input[type=text]").filter(function () {
					return this.value.length > 4;
					if(this.value.length < 4)
						$('.errorblock').append($(this).attr('name') + " must be atleast 4 chars")
				}));
			}
			$(document).ready(function(){
				$("#submit-button").click(function() {
					if(checkInputs()) {
						$('form').submit();
					} else {
						
					}
					return false;
				});
			});
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
		                        <h5>Register New Admin</h5>
		                    </div>
		                   <div class="box-content">
		                   <spring:url var = "action" value='/admin/new/save' />
							<form:form method="post" action="${action }" commandName="userForm.user">
								<div class="container span8">	
								<div style="width: 90%; display: inline-block;">
									<form:errors path="*" cssClass="errorblock span5" element="div" />
								</div>	
								<div >
							    <p>
								    <form:label path="firstName">First Name</form:label>
								    <form:input path="firstName" min-length="5"></form:input>
								</p>
								<p>
								    <form:label path="lastName">Last Name</form:label>
								    <form:input path="lastName" min-length="5"></form:input>
								</p>	
								<p>
								    <form:label path="email" >email</form:label>
								    <form:input path="email" min-length="10" type="email"></form:input>
								</p>			        
								<legend>Login Information</legend>
		
							        <p>
							        <form:label path="userName">user name</form:label>
									<form:input path="userName" min-length="5"></form:input>
									<form:errors path="userName" class="error"/>
								</p>
								 <p>
							        <form:label path="password">password</form:label>
									<form:input path="password" type="password" id="password" min-length="6"></form:input>
								</p>
								 <p>
							        <form:label path="confirmPassword">confirm password</form:label>
									<form:input path="confirmPassword" type="password" id="verifypassword" min-length="6"/>
								</p>
								<div style="width: 100%; display: inline-block; padding: 0px 0px 10px;">
								 <div id="pass-strength-result" class="span3">Strength indicator</div>
								 </div>
								 </div>
						         <p>
							         <input class="btn btn-primary" type="submit" value="Submit" id="submit-button"/>
							        
							         <a class="btn btn-primary"  href="<spring:url value="/admin/users" htmlEscape="true" />">Cancel</a>
							      </p>
							      </div>
							      
							</form:form>
							</div>
				   	</div>
				   	</div>
				   	
				</div>
				</section>
		<br/>
			
	</div>
</div>

</body>
</html>
