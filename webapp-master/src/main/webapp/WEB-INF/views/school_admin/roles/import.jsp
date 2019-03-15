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
						<li>Import to DB</li>						
						 
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
				</c:if> --%>
				
				<c:if test="${fileerror=='yes'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> File Format is not supported Please select csv file only...... 
						</div>
				</c:if>  
				<c:if test="${rfiderror=='yes'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Quit!</strong> <c:out value="${rows }"></c:out>Rows Inserted and Due to Rfid Shortage Importing rows didnot continue further ...... 
						</div>
				</c:if>  
				<c:if test="${importerror=='yes'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Quit!</strong> <c:out value="${rows }"></c:out>
						</div>
				</c:if>  
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Import </h3>
						<span>From CSV file</span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>
				</div>


				<div class="row" id="role_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Import Form
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="execute" method="post" enctype="multipart/form-data" commandname="FORM">
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Select File<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="file" name="file"
												class="form-control required">												
										</div>										
									</div>
									
									<!-- <div class="form-group">
										<label class="col-md-3 control-label">Operation Type<span class="required">*</span></label>
										<div class="col-md-9">
											<select name="op" class="form-control required">
											<option value="select">Select</option>
											<option value="insert">Insert</option>
											<option value="update">Update</option>
											 </select>
										</div>
									
									</div> -->
									
									
									
													
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/import">Cancel</a>
										<input type="submit" value="Execute"
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