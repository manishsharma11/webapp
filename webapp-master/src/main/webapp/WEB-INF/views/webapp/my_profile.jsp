<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>My Profile</title>
<!-- Files to be loaded -->

<script type="text/javascript" src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>

<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>


<script type="text/javascript">
	function mouseoverPass1(obj) {
		var obj = document.getElementById('myPassword1');
		obj.type = "text";
	}
	function mouseoutPass1(obj) {
		var obj = document.getElementById('myPassword1');
		obj.type = "password";
	}
</script>

<script type="text/javascript">
	function mouseoverPass2(obj) {
		var obj = document.getElementById('myPassword2');
		obj.type = "text";
	}
	function mouseoutPass2(obj) {
		var obj = document.getElementById('myPassword2');
		obj.type = "password";
	}
</script>


</head>

<body>

	<!-- Include top navigation -->
	<%@include file="jsp-includes/top_navigation.jsp"%>

	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">

				<!-- Left Navigation -->
				<%@include file="jsp-includes/left_navigation.jsp"%>
			</div>
			
			<div id="divider" class="resizeable"></div>

		</div>
		<!-- /Sidebar -->

		<div id="content">
			<div class="container">
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>My Profile</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				
				<c:if test="${not empty error}">
					<div class="panel-heading">
						<h3><i class="icon-warning-sign" style="color:red"></i>
							<c:out value="${error}"></c:out>
						</h3>
					</div>
				</c:if>
				
				<c:if test="${not empty message}">
					<div class="panel-heading">
					<c:if test="${message == 'success'}">
						<h3><i class="icon-ok-sign" style="color:green"></i>
							Your details updated successfully!
						</h3>
					</c:if>
					<c:if test="${message == 'failure'}">
						<h3><i class="icon-warning-sign" style="color:red"></i>
							Failed to update your details. Please try after some time!
						</h3>
					</c:if>
					</div>
				</c:if>
				
		<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>My Profile
								</h4>
							</div>
						
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/commuter/user/profile/update" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Full Name :<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="name" class="form-control required has-success" value="${commuter.name}" pattern="^\+?[A-Za-z0-9\ ]+\*?$" title="Name should not contain any special characters">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Email ID :<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="email" name="email"
												class="form-control required email" value="${commuter.email}">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Mobile :<span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${commuter.mobile}"></c:out>
										</div>
									</div>
									
<!-- 									<div class="form-group"> -->
<!-- 										<label class="col-md-3 control-label">Password :<span -->
<!-- 											class="required"></span></label> -->
<!-- 										<div class="col-md-9"> -->
<%-- 											<input type="password" name="password" class="form-control" value="${commuter.password}"> --%>
<!-- 										</div> -->
<!-- 									</div> -->
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Password </label>
										<div class="col-md-9">
											<input type="password" name="password" value="${commuter.password}" id="myPassword1" class="form-control" rangelength="6, 14">
											<i class="icon-eye-open field-icon" onmouseover="mouseoverPass1();" onmouseout="mouseoutPass1();" ></i>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Confirm Password </label>
										<div class="col-md-9">
											<input type="password" name="cpassword" value="${commuter.password}" id="myPassword2" class="form-control" rangelength="6, 14" equalTo="[name='password']">
											<i class="icon-eye-open field-icon" onmouseover="mouseoverPass2();" onmouseout="mouseoutPass2();" ></i>	
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Gender :<span
											class="required"></span></label>
										<div class="col-md-9">
											<c:if test="${commuter.gender== 'M'}">
												<input type="radio" name="gender" value="M" checked="checked">&nbsp;&nbsp;Male &nbsp;&nbsp;
												<input type="radio" name="gender" value="F">&nbsp;&nbsp;Female<br>&nbsp;&nbsp;
											</c:if>
												
											<c:if test="${commuter.gender=='F'}">
												<input type="radio" name="gender" value="M">&nbsp;&nbsp;Male
												<input type="radio" name="gender" value="F" checked="checked">&nbsp;&nbsp;Female<br>
											</c:if>
										</div>
									</div>
								
									<div class="form-actions">
										<input type="submit" value="Update Profile"
											class="btn btn-success btn-block">
									</div>
								</form>
							</div>
							
						</div>
	
					</div>
		</div>
	</div>
</body>
<script></script>
</html>
