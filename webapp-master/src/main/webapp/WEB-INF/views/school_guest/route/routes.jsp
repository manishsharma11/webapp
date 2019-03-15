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


<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
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
						<li>Routes</li>
						
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
						<h3>Routes</h3>
						<span>Create,Update,Delete Routes</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Routes</div>
								<div class="value">
									<c:out value="${routes.size()}"></c:out>
								</div>




							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>


				<!--=== Normal ===-->
				<!-- <div class="col-md-12">
					<a href="add" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add Route</span>
					</a>
				</div> -->
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Routes List
								</h4>
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i
											class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>

							<div class="widget-content">
								<table
									class="table table-striped table-bordered table-hover table-checkable datatable">
									<thead>
										<tr>

											<th>Route Name</th>
											<th>Bus Licence Number</th>
											<th>Map</th>
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${routes}" var="route">
											
												<tr>
													<td>
														<a href="/sts/school_guest/stop/stops?route_id=<c:out value="${route.id }"></c:out>">
															<b><c:out value="${route.route_name }"></c:out></b>
														</a>
														
													</td>
													<td>
													
														<c:if test="${route.bus_licence_number == 'none' }">
															None
														</c:if>
														<c:if test="${route.bus_licence_number != 'none' }">
															<c:out value="${route.bus_licence_number }"></c:out>
														</c:if>
													</td>
													<td>
														<a href="#" target="popup"  onclick="window.open('/sts/school_guest/single_map?bus_id=none&route_id=<c:out value="${route.id }"></c:out>','name','width=600,height=400')"> 
															<i class="icon-map-marker"></i>&nbsp;Map
														</a>
													</td>
													<%-- <td>
														<a  href="updateroute?id=<c:out value="${route.id}"></c:out>" class="btn btn-sm btn-info"  > <i class=" icon-pencil"></i>&nbsp;Update</a>
														<c:if test="${route.bus_id== 'none' }">
															<a  href="removeroute?id=<c:out value="${route.id}"></c:out>"  class="btn btn-sm btn-danger" ><i class=" icon-trash"></i>&nbsp;Remove</a>
														</c:if>
													</td> --%>
												</tr>
											
											</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->
				<div class="alert alert-info fade in">
					<i class="icon-remove close" data-dismiss="alert"></i>
					<strong>Info!</strong> Routess assigned to buses are cannot be  deleted
				</div>
			</div>
			<!-- /.container -->

		</div>
	</div>



</body>
</html>