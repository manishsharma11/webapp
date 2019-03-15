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
						<li>Bus</li>
						<li>Bus Stops List</li>
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
						<h3>Bus Stops List</h3>
						<span>Create,Update,Delete Bus stops List</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Stops</div>
								<div class="value">
									<c:out value="${stops.size()}"></c:out>
								</div>




							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>


				<!--=== Normal ===-->
				<%-- <div class="col-md-12">
					<a href="addstop?route_id=<c:out value="${route_id}"></c:out>" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add Stop</span>
					</a>
					
				</div> --%>
				
				
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Stops List for Route: 
									<font color="sienna"><c:out value="${route.route_name}"></c:out></font>
									
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

											<th>Stop Number</th>
											<th>Stop Name</th>
											<th>Expected Time Towards School</th>
											<th>Expected Time Towards Home</th>
											<th>Latitude</th>
											<th>Longitude</th>
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${stops }" var="stop">
											
												<tr>
													<td><c:out value="${stop.stop_number}"></c:out></td>
													<td><c:out value="${stop.stop_name}"></c:out></td>
													<%-- <td><c:out value="${stop.expected_time_towards_school}"></c:out></td> --%>
													<%-- <td><c:out value="${stop.expected_time_towards_home}"></c:out></td> --%>
													<td>
													<fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${stop.hoursTowardsSchool}" />
													<c:out value=":"/>
													<fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${stop.minutesTowardsSchool}" />
													</td>
													
													<td>
													<fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${stop.hoursTowardsHome}" />
													<c:out value=":"/>
													<fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${stop.minutesTowardsHome}" />
													</td>
													<td><c:out value="${stop.lattitude}"></c:out></td>
													<td><c:out value="${stop.longitude}"></c:out></td>
													
													<%-- <td>
														<a  href="updatestop?stop_id=<c:out value="${stop.id}"></c:out>&route_id=<c:out value="${route_id}"></c:out>" class="btn btn-sm btn-info"  > <i class=" icon-pencil"></i>&nbsp;Update</a>
														<a  href="deletestop?stop_id=<c:out value="${stop.id}"></c:out>&route_id=<c:out value="${route_id}"></c:out>"  class="btn btn-sm btn-danger" ><i class=" icon-trash"></i>&nbsp;Remove</a>
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
			</div>
			<!-- /.container -->

		</div>
	</div>



</body>
</html>