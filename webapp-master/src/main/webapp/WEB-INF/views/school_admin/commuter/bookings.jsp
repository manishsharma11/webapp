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
						<li>Commuters</li>
						<li>List</li>
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
						<h3>Booking History</h3>
						<span>Create,Update,Delete</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>

					
					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Bookings</div>
								<div class="value">
									<c:out value="${bookings.size()}"></c:out>
								</div>




							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>

				<p style="font-size:18px;">
					Commuter Name: <font color="sienna"> ${comm.name }</font>
					&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="refund?id=${comm.commuter_id}" class="btn btn-warning">Refund</a>
				</p>
				
				
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							

							<div class="widget-content">
								<table
									class="table table-striped table-bordered table-hover table-checkable datatable">
									<thead>
										<tr>
											<th>From Stop</th>
											<th>To Stop</th>
											<th>Actual Fare</th>
											<th>Charged Fare</th>
											<th>BookingTravel Date</th>
											<th>Booking Time</th>
											<th>Seats Booked</th>
											<th>status</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${bookings }" var="commuter">
												<tr>
													<td><c:out value="${commuter.from_stop}"></c:out></td>
													<td><c:out value="${commuter.to_stop}"></c:out></td>
													<td><c:out value="${commuter.actual_fare}"></c:out></td>
													<td><c:out value="${commuter.charged_fare}"></c:out></td>
													<td><c:out value="${commuter.booking_travel_date}"></c:out></td>
													<td><c:out value="${commuter.booking_time}"></c:out></td>
													<td><c:out value="${commuter.num_seats_booked}"></c:out></td>
													<td><c:out value="${commuter.status}"></c:out></td>
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