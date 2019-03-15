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
<script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage_new.js" />"></script>
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
						<li>Current Running Buses</li>
					</ul>
					<ul class="crumb-buttons">
						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					<!-- <div class="page-title">
						<h4>Current Running Buses</h4>
						<span>Create,Update,Delete Schools List</span>
					</div> -->
					<br>

					<!--=== Statboxes ===-->

					<div class="col-sm-3 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<div id="buses_running_ontime"></div>
								</div>
								<div class="title">Buses On Time</div>
								<div>
								<%-- <img src="<c:url value="/resources/greenbus.jpg" />" /> --%>
								</div>
							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->

					<div class="col-sm-5 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual yellow">
									<div id="buses_running_late"></div>
								</div>
								<div class="title">Buses Late</div>


							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->

					<div class="col-sm-5 col-md-3 hidden-xs">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual red">
									<div id="buses_running_verylate"></div>
								</div>
								<div class="title">Buses very Late</div>

							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->

					<div class="col-sm-5 col-md-3 hidden-xs">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual cyan">
									<div id="buses_running_outofservice"></div>
								</div>
								<div class="title">Buses Out of Service</div>
								<!-- <span class="photo"><img src="/bus.png" alt="" /></span> -->
						
							</div>
						</div>
						
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<!-- /.row -->
				<!-- /Statboxes -->
				

				<!--=== Normal ===-->
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Current Running Buses
								</h4>
								
							</div>
							<div class="widget-content" >
								
									<div class="alert alert-info fade in" style="text-align: center;display: none;" id="no_services">
										
										<strong >Info!</strong> Bus services  not started yet....
									</div>
									<div align="center" id="loading" style="display: none;">
											
												<img alt="loading_image" src="<c:url value="/resources/loading_icon.gif" />">&nbsp;
												<font color="sienna" size="3">please wait,fetching  information from database...</font>
									</div>							
								<table class="table table-striped table-bordered table-hover table-checkable" style="overflow: scroll;">
									<thead>
									<br/>
										<tr>
 
											<th>Bus</th>
											<th>Current Stop</th>
											<th>Arrived Time</th>
											<th>Users In Bus</th>
											<th>Driver Info</th>
											<th>Status</th>
											<th>Map</th>
										</tr>
									</thead>
											
									<tbody>
											
										
										<!-- <tr>

											<td><a onclick=" openWindow()" href="#"> Bus_3245 </a></td>
											<td>32 john street</td>
											<td>10:38 AM</td>
											<td>4</td>
											<th><a href="#"> Sujeeth </a></th>
											<td><span class="label label-warning">Late</span></td>
											<th><a href="#"> <i class="icon-map-marker"></i>&nbsp;Map
											</a></th>
										</tr> -->

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->
	

			</div>
		</div>
		<!-- /Page Header -->

		<!--    Students List  -->

		<!--=== Managed Tables ===-->


		<!-- /Normal -->
	</div>
	<!-- /.container -->

											<!-- Modal dialog -->
											<div class="modal fade" id="myModal1">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															
															<h4 class="modal-title">Driver Information:</h4>
														</div>
														<div class="modal-body">
															
															<h5>
																Board Time:&nbsp;&nbsp;<font color="sienna" id="board_time"></font>
																
															</h5>
															<br>
															<h5>
																&nbsp;&nbsp;&nbsp;&nbsp;Exit Time:&nbsp;&nbsp;<font color="sienna" id="exit_time"></font>
															</h5>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
															
														</div>
													</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
											</div><!-- /.modal -->
</body>
</html>