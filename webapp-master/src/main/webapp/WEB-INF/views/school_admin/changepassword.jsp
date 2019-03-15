<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<title>Dashboard</title>
	<script type="text/javascript" src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/project_js_file/changepassword.js" />"></script>
	<!-- Include CSS files -->
	<%@ include file="jsp-includes/css_files.jsp" %>

	<!-- Include JS files -->
	<%@ include file="jsp-includes/js_files.jsp" %>
	
	<script type="text/javascript">			
	 </script>
</head>

<body>

	<!-- Include top navigation -->
	<%@include file="jsp-includes/top_navigation.jsp" %>
	
	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">
				<!-- Left Navigation -->
				<%@include file="jsp-includes/left_navigation.jsp" %>
			</div>
			<div id="divider" class="resizeable"></div>			
		</div>
		<!-- /Sidebar -->

		<div id="content">
			<div class="container">
				<!-- Breadcrumbs line -->
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li>
							<i class="icon-home"></i>
							Dashboard
						</li>
						<li >
							Change Password
						</li>						
					</ul>

					<ul class="crumb-buttons">
						<li>
							<a href="#">
								<i class="icon-calendar"></i>
								${date}
							</a>
						</li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">
					
					<div class="page-title">
						<h3>${login_name}</h3>
						<span>Hello Admin!</span>
					</div>		
					<br>
					<div class="col-sm-4 col-md-1">
					
					</div>
				</div>
				<!-- /Page Header -->
				
 				<!--    Commuters List  -->
 				
				<!--=== Managed Tables ===-->

				<!-- Change Password  -->
			
				<div class="col-sm-6 col-md-3" style="width:600px;">
							<div class="statbox widget box box-shadow">
								<div class="widget-content">
									
									<div class="title">Change Password</div>
									
									<br><br>
									
								<div id="current_password" >
										<label class="col-md-4 control-label">Current Password:</label>
										<div class="col-md-6">
											
											<input type="password" id="c_password" class="form-control required" >
											
											<font color="brown" id="c_password_error" ></font>
											<br>
											<div  id="loading" style="display: none;">												
											</div>											
										</div>
										
										<br><br><br><br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn btn-primary" id="unlock">
											&nbsp;&nbsp;&nbsp;Unlock&nbsp;&nbsp;&nbsp;
										</button>
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/profile">Cancel</a>
										<br><br><br>
									
								</div>
																
								<div id="new_password" style="display: none;">
								
										<label class="col-md-4 control-label">New Password:</label>
										<div class="col-md-6">
											
											<input type="password" id="c_password_new" class="form-control required" >
											
											<font color="brown" id="c_password_error_new" ></font>
											<br>											
										</div>
										<label class="col-md-4 control-label">Repeat Password:</label>
										<div class="col-md-6">
											
											<input type="password" id="c_password_new_repeat" class="form-control required">
											
											<font color="brown" id="c_password_error_new_repeat" ></font>
											<br>											
										</div>
										
										<br><br><br><br>
										<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn btn-primary" id="change_password">
											&nbsp;&nbsp;&nbsp;Change Password&nbsp;&nbsp;&nbsp;
										</button>
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/profile">Cancel</a>
								
										<br><br>
										<p id="changepasswordstatus"></p>
								</div>
								<div id="password_changed_successfully" style="text-align: center;display: none;">
								
									<p><b>Password has been changed......</b></p>
								
								</div>
								</div>								
							</div> <!-- /.smallstat -->
					</div> <!-- /.col-md-3 -->					
			</div>
			<!-- /.container -->
		</div>
	</div>		
</body>
</html>

