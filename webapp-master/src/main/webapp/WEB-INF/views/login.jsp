<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<%
	String appName = request.getContextPath();
%>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<title>EasyCommute Web App Login</title>
	<!-- Favicon -->
	<link href="<c:url value="/resources/assets/img/icons/led/src/dashboard.png" />" rel="icon" type="image/x-icon" />
	<!--=== CSS ===-->
	
	<!-- Bootstrap -->
	<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />

	<!-- Theme -->
	<link href="<c:url value="/resources/assets/css/main.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/assets/css/plugins.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/assets/css/responsive.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/assets/css/icons.css" />" rel="stylesheet" type="text/css" />

	<!-- Login -->
	<link href="<c:url value="/resources/assets/css/login.css" />" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" href="<c:url value="/resources/assets/css/fontawesome/font-awesome.min.css" /> " />
	<!--[if IE 7]>
		<link rel="stylesheet" href="assets/css/fontawesome/font-awesome-ie7.min.css">
	<![endif]-->

	<!--[if IE 8]>
		<link href="assets/css/ie8.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>

	<!--=== JavaScript ===-->
	


	<script type="text/javascript" src="<c:url value="/resources/assets/js/libs/jquery-1.10.2.min.js" /> "></script>
	
	<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/assets/js/libs/lodash.compat.min.js" />" ></script>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="assets/js/libs/html5shiv.js"></script>
	<![endif]-->


	<!-- Beautiful Checkboxes -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/uniform/jquery.uniform.min.js" />"></script>

	<!-- Form Validation -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/validation/jquery.validate.min.js" />"></script>

	<!-- Slim Progress Bars -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/nprogress/nprogress.js" />"></script>

	<!-- App -->
	<script type="text/javascript" src="<c:url value="/resources/assets/js/login.js" />"></script>

<script type="text/javascript">
	function mouseoverPass(obj) {
		var obj = document.getElementById('mobile');
		obj.type = "text";
	}
	function mouseoutPass(obj) {
		var obj = document.getElementById('mobile');
		obj.type = "password";
	}
</script>


<script>	
	$(document).ready(function(){
		"use strict";
		Login.init(); // Init login JavaScript
	});
	</script>

<script type="text/javascript">
        $(function () {
        	 $("#error").hide();
        	 $("#error1").hide();
            var isShiftPressed = false;
            var isCapsOn = null;
            $("#Passwd").bind("keydown", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = true;
                }
            });
            $("#Passwd").bind("keyup", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode == 16) {
                    isShiftPressed = false;
                }
                if (keyCode == 20) {
                    if (isCapsOn == true) {
                        isCapsOn = false;
                        $("#error").hide();
                    } else if (isCapsOn == false) {
                        isCapsOn = true;
                        $("#error").show();
                    }
                }
            });
            $("#Passwd").bind("keypress", function (e) {
                var keyCode = e.keyCode ? e.keyCode : e.which;
                if (keyCode >= 65 && keyCode <= 90 && !isShiftPressed) {
                    isCapsOn = true;
                    $("#error").show();
                } else {
                    $("#error").hide();
                }
            });
    		$('#login-password-btn').click(function (e) {
    			e.preventDefault(); // Prevent redirect to #

    			// Hide login form
    			$('.login-password-form').slideUp(550, function() {
    				// Finished, so show register form
    				$('.login-otp-form').slideDown(550);
    				$('.login-otp-form').hide();
    				$(".errorblock").empty();
    				$('.login-password-form').show();
    			});
    		});
    		$('#login-otp-btn').click(function (e) {
    			e.preventDefault(); // Prevent redirect to #

    			// Hide login form
    			$('.login-otp-form').slideUp(550, function() {
    				// Finished, so show register form
    				$('.login-password-form').slideDown(550);
    				$('.login-password-form').hide();
    	            $(".login-otp-form").find(".form-actions").hide();
    				$('.input-with-otp').hide();
    				$(".errorblock").empty();
    				$('.login-otp-form').show();
    			});
    		});

    		// Click on "Back"-button
    		$('.back').click(function (e) {
    			e.preventDefault(); // Prevent redirect to #

    			// Hide register form
    			$('.register-form').slideUp(350, function() {
    				// Finished, so show login form
    				$('.login-form').slideDown(350);
    				$('.sign-up').show();
    			});
    		});
    		
    		$('.regenerate_btn').click(function (e) {
    			e.preventDefault(); // Prevent redirect to #

    			// Hide register form
    			$('.register-form').slideUp(350, function() {
    				// Finished, so show login form
    				$('.login-form').slideDown(350);
    				$('.sign-up').show();
    			});
    		});
    		
    		$('#generate_otp_btn').click(function (e) {

    			    var mobile = $(".login-otp-form").find('input[name="mobile"]').val();
    			    var url = '<%=appName%>/webapp/commuter/regenrateOTP';
    			    //alert(mobile);
    			    var newForm = jQuery('<form>', {
    			        'action': url,
    			        'target': '_top',
    			        'method': 'post'
    			    }).append(jQuery('<input>', {
    			        'name': 'mobile',
    			        'value': mobile,
    			        'type': 'hidden'
    			    }));
    			    
    			    var formData = {
    			            'mobile'              : mobile
    			        };
    			    //newForm.submit();
/*      			    newForm.submit(function (ev) {
    			        $.ajax({
    			            type: newForm.attr('method'),
    			            url: newForm.attr('action'),
    			            data: newForm.serialize(),
    			            success: function (data) {
    			                alert('ok');
    			                $(".login-otp-form").find('$message').val("An OTP is sent to your mobile number "+mobile);
    			            }
    			        });
    			    }); */
    			    //e.preventDefault(); // avoid to execute the actual submit of the form.
     			    $.ajax({
    		            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
    		            url         : url, // the url where we want to POST
    		            data        : formData // our data object
    		            //dataType    : 'json', // what type of data do we expect back from the server
    		            //encode          : true
    		        })
    		            // using the done promise callback
    		            .done(function(data) {
    		                // log data to the console so we can see
    		                console.log(data); 
    		                //alert('ok');
    		                // here we will handle errors and validation messages
		    				$('.input-with-otp').show();
		    				$('#generate_otp_btn').text("Regenerate OTP");
		    	            $(".login-otp-form").find(".form-actions").show();
			                $(".login-otp-form").find('.message').val("An OTP is sent to your mobile number "+mobile);
    		            });
    		});
        });
</script>


	<style type="text/css">
	#footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    right: 15%;
    }
    
    #button {
    /*  border:1px #333 solid;    */
   /*  padding:10px; */
    /*background-color:Blue;*/
    border-color:black;
   -webkit-border-radius:40px;
    -moz-border-radius:40px;  
   /*  border-radius:40px; */
}
    
	</style>

</head>

<body class="login" >
	
	<!-- Login Box -->
	<div class="box">
		<div class="content">
			<!-- Login Formular -->
			<form class="form-vertical login-password-form" name='f' id="validate-4"
									action="<%=appName%>/webapp/commuter/login_process" method="post">
				<!-- Title -->
				
				<div align="right"  class="form-title">
				<%-- <img src="<c:url value="/resources/Logo A copy.jpg" />" align="bottom"/> --%>
				<img src="<c:url value="/resources/icons_webapp/logo.png" />" /> 
				</div>
				<h3 class="form-title">Easy Commute Web Booking Portal</h3>
				<!-- Error Message -->
				<div class="alert fade in alert-danger" style="display: none;">
					<i class="icon-remove close" data-dismiss="alert"></i>
					Please Enter Mobile no. and Password.
				</div>
				<c:if test="${not empty error}">
				<div class="errorblock">
					<div class="alert fade in alert-danger" >
						<i class="icon-remove close" data-dismiss="alert"></i>
						Authentication failed. 
						<c:out value="${error}"/>
					</div>					
				</div>
			    </c:if>
				<!-- Input Fields -->
				<div class="form-group">
					<!--<label for="username">Username:</label>-->
					<div class="input-icon">
						<i class="icon-user"></i>
						<input type="tel" name="mobile" class="form-control required" placeholder="Mobile Number" maxlength="10" pattern="[0-9]{10}" autofocus="autofocus" title="Please Enter Your Registered Mobile Number" />
					</div>
				</div>
				<div class="form-group">
					<!--<label for="password">Password:</label>-->
					<div class="input-icon">
						<i class="icon-eye-open" onmouseover="mouseoverPass();" onmouseout="mouseoutPass();" ></i>
						<input type="password" name="password" id="mobile" class="form-control required" placeholder="Password" maxlength="14" pattern=".{6,14}" title="Please Enter Your Password (should be of 6-14 Characters length)">									
					</div>
				</div>
	
	            <div class="form-actions"> <label class="checkbox pull-left">
<!-- 	            	<div class="checker"> -->
<!-- 	            	   <span class="checked"><input type="checkbox" class="uniform" name="remember_me" data-rule-required="true" data-msg-required="Please enter the received OTP."></span> -->
<!-- 	                </div> <label>Remember me</label>  -->
	             &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" class="submit btn btn-primary pull-right"> Log In <i class="icon-angle-right"></i> </button> 
	            </div>
	            
<!-- 			    <div class="inner-box"> <div class="content"> <i class="icon-remove close hide-default"></i> 
			      <a href="#" class="forgot-password-link">Forgot Password?</a> 
			    </div> -->
	
				<div class="inner-box">
					<span id="login-otp-btn"><a href="#" role="button">Forgot Password? Log In with OTP</a></span>
				</div>
				<input type="hidden" name="signin_flag" id="signin_flag" value="password" />						
				
			</form>
			<!-- /Login Formular -->
			
			
			<!-- /LoginWithOTP Formular -->
			<form class="form-vertical login-otp-form" name='f' action="<%=appName%>/webapp/commuter/login_process" method="post" style="display: none;">
				<!-- Title -->
				
				<div align="right"  class="form-title">
				<%-- <img src="<c:url value="/resources/Logo A copy.jpg" />" align="bottom"/> --%>
				<img src="<c:url value="/resources/icons_webapp/logo.png" />" /> 
				</div>
				<h3 class="form-title">Easy Commute Web Booking Portal</h3>
				<!-- Error Message -->
				<div class="alert fade in alert-danger" style="display: none;">
					<i class="icon-remove close" data-dismiss="alert"></i>
					Please Enter Mobile no. and OTP.
				</div>
				<div id="message" class="alert fade in alert-success" style="display: none;">
					<i class="icon-remove close" data-dismiss="alert"></i>
					<!-- message -->
				</div>
				<c:if test="${not empty error}">
				<div class="errorblock">
					<div class="alert fade in alert-danger" >
						<i class="icon-remove close" data-dismiss="alert"></i>
						Authentication failed. 
						<c:out value="${error}"/>
					</div>					
				</div>
			</c:if>
				<!-- Input Fields -->
				<div class="form-group">
					<!--<label for="username">Username:</label>-->
					<div class="input-icon">
						<i class="icon-user"></i>
						<input type="tel" name="mobile" class="form-control required" placeholder="Mobile Number" maxlength="10" pattern="[0-9]{10}" autofocus="autofocus" title="Please enter exactly 10 digit Mobile number"/>
					</div>
				</div>
				<div class="form-group input-with-otp">
					<!--<label for="password">Password:</label>-->
					<div class="input-icon">
						<i class="icon-lock"></i>
						<input type="tel" name="otp" id="Passwd" class="form-control required" placeholder="Type In OTP Sent To Your Mobile" maxlength="6" pattern="[0-9]{6}" title="Please enter the received OTP." />						
					</div>
				</div>
				
	            <div class="form-actions"> <label class="checkbox pull-left">
<!-- 	            	<div class="checker"> -->
<!-- 	            	   <span class="checked"><input type="checkbox" class="uniform" name="remember_me" data-rule-required="true" data-msg-required="Please enter the received OTP."></span> -->
<!-- 	                </div> <label>Remember me</label>  -->
	               &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" class="submit btn btn-primary pull-right"> Log In <i class="icon-angle-right"></i> </button> 
	            </div>
			
				<a href="#" class="btn btn-default" id="generate_otp_btn"><i class="icon-repeat"></i> Generate OTP</a>
				<br>
				<div class="inner-box">
					<span id="login-password-btn"><a href="#" role="button">Login with Password</a></span>
				</div>
				
				<input type="hidden" name="signin_flag" id="signin_flag" value="otp" />						
				
			</form>
			
			<!-- /LoginWithOTP Formular -->
			</div>
							<br/>

				<a href=<c:url value="/webapp/commuter/register" /> class="btn btn-success btn-lg btn-block">New User? Register Now</a>
<!-- 				<div class="inner-box"> <div class="content"> <i class="icon-remove close hide-default"></i> <a href="#" class="forgot-password-link">Forgot Password?</a> <form class="form-vertical forgot-password-form hide-default" action="login.html" method="post" novalidate="novalidate"> <div class="form-group"> <div class="input-icon"> <i class="icon-envelope"></i> <input type="text" name="email" class="form-control" placeholder="Enter Mobile no." data-rule-required="true" data-msg-required="Please enter your Mobile no."> </div> </div> <button type="submit" class="submit btn btn-default btn-block"> Reset your Password </button> </form> <div class="forgot-password-done hide-default"> <i class="icon-ok success-icon"></i> <span>Great. We have sent you an SMS.</span> </div> </div> </div> -->
				
	</div>
	<!-- /Login Box -->
	
	<!-- <div class="footer"  >
	 <div  class="container">
	  <h3 ><font color="white">@CopyRight 2015, EasyCommute. All Rights Reserved</font></h3>
	</div>  
	</div> -->
</body>
</html>
