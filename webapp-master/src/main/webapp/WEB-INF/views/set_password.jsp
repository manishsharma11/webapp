<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Set Password</title>
<!-- Files to be loaded -->

<%-- <script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script> --%>

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

<!-- Include common files -->
<%@ include file="./webapp/jsp-includes/common_files.jsp"%>
<script>
	
$(document).ready(function(){
   jQuery('#set_password_form').validate({
	    rules : {
	    	password : {
	            required: true,
	            minlength : 6
	        },
	        cpassword : {
	            required: true,
	            minlength : 6,
	            equalTo : "#password"
	        }
	    },
	    // messages
	    password: {
	    	password: {
	            required: "What is your password?",
	            minlength: "Your password must contain 6 or more characters"
	        },
	        cpassword: {
	            required: "You must confirm your password",
	            minlength: "Your password must contain 6 or more characters",
	            passwordMatch: "Your Passwords Must Match" // custom message for mismatched passwords
	        }
	    }
	    });
	});
</script>
<%
	String appName = request.getContextPath();
%>
</head>

<body>

	<div id="container">
		
		<div id="content">
			<div class="container">
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Set Password</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				
				<c:if test="${message == 'failed'}">
					<div class="panel-heading">
						<h3><i class="icon-warning-sign" style="color:red"></i>
							Unable Set Password! Please Try Again
						</h3>
					</div>					
				</c:if>
				
		<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Set Password
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/commuter/setPassword" method="post">
									
									<div class="form-group">
										<label class="col-md-3 control-label">Password <span class="required">*</span></label>
										<div class="col-md-9">
											<input type="password" name="password" class="form-control required" rangelength="6, 14">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Confirm Password <span class="required">*</span></label>
										<div class="col-md-9">
											<input type="password" name="cpassword" class="form-control required" rangelength="6, 14" equalTo="[name='password']">
										</div>
									</div>
																					
									<div class="form-actions">
										<input type="submit" value="Set Password"
											class="btn btn-success btn-block">
									</div>
								</form>
							</div>
						</div>
	
					</div>
				</div>
	</div>
	</div>
</body>
</html>