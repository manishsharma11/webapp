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
						<li><i class="icon-user"></i> Preferences</li>

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
						<h3>Preferences</h3>

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
														<i class="icon-reorder"></i> Admin Preferences
													</h4>
												</div>
												<div class="widget-content">
													<form class="form-horizontal row-border" id="validate-2" 	action="changepassword" method="get">
													
														
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number:
															</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.number }"></c:out></label>
															</div>
														</div>
														
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSchool Coordinates</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Latitude:</label>
															<div class="col-md-9">
																<label  style="color: sienna;"  class="col-md-3"><c:out value="${adminPreferences.latitude }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Longitude:</label>
															<div class="col-md-9">
																<label  style="color: sienna;"  class="col-md-3"><c:out value="${adminPreferences.longitude }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSMS Gateway</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number (For alerts):
															</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.numberForAlers }"></c:out></label>
															</div>
														</div>
														
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEmail Proxy</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Serverhost <span
																class="required">*</span></label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.smtpServerHost }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Port <span
																class="required">*</span></label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.smtpPort }"></c:out></label>
															</div>
														</div>

														<div class="form-group">
															<label class="col-md-3 control-label">Email:</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.e_mail }"></c:out></label>
															</div>
														</div>

														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Email Password: </label>
															<div class="col-md-9">
																<label style="color: sienna;"  class="col-md-3"><c:out value="${admin_profile.password }"></c:out></label>
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
														<i class="icon-reorder"></i> Admin Preferences
													</h4>
												</div>
												<div class="widget-content">
													<form class="form-horizontal row-border" id="validate-1" 	action="updatepreferences" method="post">
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
															<label class="col-md-3 control-label">Phone
																Number<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="number"
																	class="form-control required"
																	pattern="^\+?[0-9\-]+\*?$"
																	value="<c:out value="${adminPreferences.number }"></c:out>"
																	title="phone number must be numbers with symbols(+,-)">
															</div>
														</div>
														<div class="form-group">
														<font color="sienna">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSchool Coordinates</p></b>
															</font>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Latitude<span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="latitude"
																	value="<c:out value="${adminPreferences.latitude }"></c:out>"
																	class="form-control required" range="-90, +90">
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Longitude<span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="longitude"
																value="<c:out value="${adminPreferences.longitude }"></c:out>"
																	class="form-control required" range="-90, +90">
															</div>
														</div>
														
														<div class="form-group">
														<font color="sienna">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSMS Gateway</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number (For Alerts)<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="numberForAlers"
																	class="form-control required"
																	pattern="^\+?[0-9\-]+\*?$"
																	value="<c:out value="${adminPreferences.numberForAlers }"></c:out>"
																	title="phone number must be numbers with symbols(+,-)">
															</div>
														</div>
														
														<div class="form-group">
														<font color="sienna">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEmail Proxy</p></b>
															</font>
														</div>

														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Serverhost <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="smtpServerHost"
																	class="form-control required" value="<c:out value="${adminPreferences.smtpServerHost }"></c:out>">
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Port <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="smtpPort"
																	class="form-control required" value="<c:out value="${adminPreferences.smtpPort }"></c:out>">
															</div>
														</div>

														<div class="form-group">
															<label class="col-md-3 control-label">Email <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="e_mail"
																	value="<c:out value="${adminPreferences.e_mail }"></c:out>"
																	class="form-control required email" >
															</div>
														</div>

														<div class="form-group">
															<label class="col-md-3 control-label">Password <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="password" name="password"
																value="<c:out value="${adminPreferences.password }"></c:out>"
																	class="form-control required" minlength="5">
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Confirm
																Password <span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="password" name="admin_pass_re"
																value="<c:out value="${admin_profile.password }"></c:out>"
																	class="form-control required" minlength="5"
																	equalTo="[name='password']">
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