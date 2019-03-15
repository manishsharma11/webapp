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
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

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
						<li>Commuter</li>

						<li>Refund</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					
				</div>
				<br>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Make Refund
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="refundaction" method="post">
									<input type="hidden" name="id" value="${comm.commuter_id }" class="form-control  required digits" >
									
									<div class="form-group">
										<label class="col-md-4 control-label">
											Commuter Name:
										</label>
										<div class="col-md-8 ">
											${comm.name }
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">
											Commuter Phone:
										</label>
										<div class="col-md-8 ">
											${comm.mobile }
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Refund Points <span class="required">*</span></label>
										<div class="col-md-9">
											<div>
												<input type="text" name="refund_points" value="0" class="form-control  required digits" >
											</div>
										</div>
									</div>
									 
									<div class="form-group">
										<label class="col-md-3 control-label">Refund Free rides <span class="required">*</span></label>
										<div class="col-md-9">
											<div>
												<input type="text" name="refund_free_rides" value="0" class="form-control required digits" >
											</div>
										</div>
									</div>
									 
									<div class="form-group">
										<label class="col-md-3 control-label">Refund Reason:</label>
										<div class="col-md-9"><textarea rows="3" cols="5" name="refund_reason" class="form-control required"></textarea></div>
									</div>

									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										 <input
											type="submit" value="Submit"
											class="btn btn-primary pull-right" id="add_new_driver_action">
									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>


				</div>
			</div>
</body>
</html>