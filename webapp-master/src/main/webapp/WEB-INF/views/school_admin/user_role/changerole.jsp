<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/project_js_file/capsLock.js" /> "></script> 
<script>
	$(document).ready(function() {
    /* 
    * Bind to capslockstate events and update display based on state 
    */
    //alert("hi");
    $(window).bind("capsOn", function(event) {
    	
        if ($("#Passwd:focus").length > 0) {
            $("#capsWarning").show();
        }
    });
    $(window).bind("capsOff capsUnknown", function(event) {
        $("#capsWarning").hide();
    });
    $("#Passwd").bind("focusout", function(event) {
        $("#capsWarning").hide();
    });
    $("#Passwd").bind("focusin", function(event) {
        if ($(window).capslockstate("state") === true) {
            $("#capsWarning").show();
        }
    });

    /* 
    * Initialize the capslockstate plugin.
    * Monitoring is happening at the window level.
    */
    $(window).capslockstate();

});
</script>

<script type="text/javascript">
	
</script>
</head>
<body>
	<!-- Include top navigation -->
	<%@include file="../jsp-includes/top_navigation.jsp"%>

	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">

				<!-- Left Navigation -->
				<%@include file="../jsp-includes/left_navigation.jsp"%>

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
						<li> Roles</li>						
						<li>Update Role</li>
					</ul>

					<ul class="crumb-buttons">

						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->				
				
				<%-- <c:if test="${error_message=='latAndLongDup'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong>  latitude and longitude are
							already exists....
						</div>
				</c:if> 
				
				<c:if test="${error_message=='stopDup'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Stop name already exists.Please provide unique stop name...... 
						</div>
				</c:if>  --%>
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Roles</h3>
						<span>Change Role</span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>
				</div>


				<div class="row" id="role_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Role Form
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="updatenewRoleAction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Role Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="login_id"
												class="form-control required" maxlength="20"
												 
												  value="${rolename.role_name}">												
										</div>										
									</div>
									<c:if test="${error=='RoleNameError'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip1">
												<strong>Error!</strong> Role Name already exist please try another name.
											</div>
										</c:if>
									 
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/roles/getrole">Cancel</a>
										<input type="submit" value="update Role"
											class="btn btn-primary pull-right">
									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>