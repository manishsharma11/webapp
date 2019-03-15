<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Verification</title>
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
						<h3>Verification</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>

				<c:if test="${message == 'Verification failed'}">
					<div class="panel-heading">
						<h3><i class="icon-warning-sign" style="color:red"></i>
							Verification Failed!
						</h3>
						<span>Please check that you have typed OTP correctly.</span>
					</div>
				</c:if>

		<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Verification
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/commuter/verifyCommuter" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Verify OTP<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="tel" name="otp"
												class="form-control required" placeholder="Type In OTP Sent To Your Mobile" 
												maxlength="6" pattern="[0-9]{4,6}" title="Please enter OTP Sent to your Mobile">
										</div>
									</div>
											
									<div class="form-actions">
										<input type="submit" value="Verify"
											class="btn btn-success btn-block">
									</div>
								</form>
							</div>
							
							<div>
								&nbsp<label>Haven't Received OTP Yet!</label><br>
								<form action="<%=appName%>/webapp/commuter/regenrateOTP" method="POST">
									    <label for="mySubmit" class="btn btn-success"><i class="icon-repeat"></i>  Regenerate OTP</label>
									    <input id="mySubmit" type="submit" value="GO" class="hidden" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
	</div>
	</div>
</body>
</html>