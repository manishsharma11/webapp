<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Dashboard</title>
<!-- Files to be loaded -->

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>

<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<!-- <script type="text/javascript">

function openWindow(){
		
	window.open('bus?bus_id=', 'popup_name','height=' + 600 + 
			',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
}

</script> -->
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
				<!-- Breadcrumbs line -->
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li><i class="icon-home"></i> Dashboard</li>
						<li><i class="icon-user"></i> Profile</li>

					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Profile</h3>

					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>




				</div>






				<!--=== Inline Tabs ===-->
				<div class="row">
					<div class="col-md-12">
						<!-- Tabs-->
						<div class="tabbable tabbable-custom tabbable-full-width">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_overview"
									data-toggle="tab">Overview</a></li>
								<!-- <li><a href="#tab_edit_account" data-toggle="tab">Edit
										Account</a></li> -->
							</ul>
							<div class="tab-content row">
								<!--=== Overview ===-->
								<div class="tab-pane active" id="tab_overview">
									
									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-7">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Admin Profile
													</h4>
												</div>
												<div class="widget-content">
													<form class="form-horizontal row-border" id="validate-2" 	action="changepassword" method="get">
													
														<div class="form-group">
															<label class="col-md-3 control-label">Admin Name:
															</label>
															<div class="col-md-9">
																<label  style="color: sienna;"  class="col-md-3"><c:out value="${admin_profile.admin_name }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Login Id:
															</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${admin_profile.login_id}"></c:out></label>
															</div>
														</div>
														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Latitude:</label>
															<div class="col-md-9">
																<label  style="color: sienna;"  class="col-md-3"><c:out value="${admin_profile.latitude }"></c:out></label>
															</div>
														</div> --%>
														
														 <!-- <div class="form-actions">
															<input type="submit" value="Update Login Password"
																class="btn btn-primary pull-right">
														</div> -->
													</form>
												</div>
											</div>
											<!-- /Validation Example 1 -->
										</div>
									</div>
									<!-- /Edit Account -->	
		


								</div>
								<!-- /Overview -->

								<!--=== Edit Account ===-->
<%-- 								<div class="tab-pane" id="tab_edit_account">
									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-7">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Admin Profile
													</h4>
												</div>
												<div class="widget-content">
													<form class="form-horizontal row-border" id="validate-1" 	action="updateprofile" method="post">
														<input type="hidden" name="id" value="${id}">
														<input type="hidden" name="u_id" value="46dgh346546dfghb456b54">
														<div class="form-group">
															<label class="col-md-3 control-label">Admin Name
																<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="admin_name" minlength="3"
																	class="form-control required"
																	pattern="^\+?[A-Za-z\ ]+\*?$"
																	value="<c:out value="${admin_profile.admin_name }"></c:out>"
																	title="Name must be characters with spaces minimum 3 characters">
															</div>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Change Login Id:<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="login_id"
																	class="form-control required"
																	pattern="^\+?[A-Za-z0-9]+\*?$"
																	value="<c:out value="${admin_profile.login_id}"></c:out>"
																	title="Login Id must be alpha numberic keys only">
															</div>
														</div>
														

														<div class="form-group">
															<label class="col-md-3 control-label">Change Password <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="password" name="password"
																value="<c:out value="${admin_profile.password }"></c:out>"
																	class="form-control required" minlength="5">
															</div>
														</div>
														
														<div class="form-actions">
															<input type="submit" value="Update"
																class="btn btn-primary pull-right">
														</div>
													</form>
												</div>
											</div>
											<!-- /Validation Example 1 -->
										</div>
									</div> --%>
									<!-- /Edit Account -->
								</div>
								<!-- /.tab-content -->
							</div>
							<!--END TABS-->





						</div>
					</div>
					<!-- /Page Header -->

					<!--    Students List  -->

					<!--=== Managed Tables ===-->


					<!-- /Normal -->
				</div>
				<!-- /.container -->


			</div>
		</div>
	</div>



</body>
</html>