<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Register</title>
<!-- Files to be loaded -->

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>

<!-- Include common files -->
<%@ include file="./webapp/jsp-includes/common_files.jsp"%>

	<%
	String appName = app_context;
    %>
</head>

<body>

	<div id="container">
		
		<div id="content">
			<div class="container">
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Register</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>

		<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Register
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/commuter/registerCommuter" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Name<span
											class="required">*</span></label>
										<div class="col-md-9">	
											<input type="text" name="name"
												class="form-control required" placeholder="Enter Your Name" pattern="^\+?[A-Za-z0-9\ ]+\*?$" title="Name should not contain any special characters">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Email<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="email"
												class="form-control required email" placeholder="Enter Your Email">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Mobile<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="tel" name="mobile"
												class="form-control required" placeholder="Enter your Mobile Number without +91 or 0" maxlength="10" pattern="[0-9]{10}" title="Please enter exactly 10 digits">
												<span class="help-block">8123456789</span>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Gender<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="radio" name="gender" value="M" checked="checked">&nbsp;Male &nbsp;&nbsp;
											<input type="radio" name="gender" value="F">&nbsp;Female<br>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Referral/Promo Code</label>
										<div class="col-md-9">
											<input type="text" name="referral_code"
												class="form-control" placeholder="Enter Referral/Promo Code here">
										</div>
									</div>
									
									<div class="form-actions">
										<input type="submit" value="Register"
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