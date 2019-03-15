<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Available Shuttles</title>
<!-- Files to be loaded -->

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
	

<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

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
<!-- 				Breadcrumbs line -->
<!-- 				<div class="crumbs"> -->
<!-- 					<ul id="breadcrumbs" class="breadcrumb"> -->
<!-- 						<li><i class="icon-home"></i>Easy Commute</li> -->
<!-- 						<li>Book Now</li> -->
<!-- 						<li>Available Shuttles</li> -->
<!-- 					</ul> -->

<!-- 					<ul class="crumb-buttons"> -->


<%-- 						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date} --%>
<!-- 						</a></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 				/Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Available Shuttles List</h3>
						<span>Choose Your Shuttle</span><br>
					</div>

				</div>
				
				<div>
				<ul class="list-group">
				  <li class="list-group-item list-group-item-warning"><label class=col-md-3>From :  </label><c:out value="${from_stop}"></c:out></li>
				  <li class="list-group-item list-group-item-info"><label class=col-md-3>To   :  </label><c:out value="${to_stop}"></c:out></li>
				</ul>
					
				</div> 
				<div class="well well-sm">
					<label class=col-md-3>Number of Seats Selected :  </label><c:out value="${num_seats_choosen}"></c:out>
				</div>
				
				
				<c:choose>
				<c:when test="${empty trips}">				
					<h3><i class="icon-exclamation-sign" style="color:red"></i>
								Sorry! No Shuttles Available Now
					</h3>
				</c:when>				
				<c:otherwise>
				<div class="row">
					<div class="col-md-12">
						<div class="widget-content">
						<font color="red">*Scroll the Table.</font>
						<div class="table-responsive">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Shuttle Type</th>										
										<th>Pick Up Time</th>
										<th>Trip Running Date</th>
										<th>Proceed To Booking</th>										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${trips}" var="trip"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${trip.vehicle_model}"></c:out></td>											
											<td><c:out value="${trip.pickup_stop_time} ${trip.pickup_stop_time_ampm}"></c:out></td>
											<td><fmt:formatDate type="date" value="${trip.trip_running_date}" /></td>
											<td>
												<c:choose>
													<c:when test="${trip.active == true && trip.seats_available != 0}">
														<form action="<%=appName%>/webapp/booking/pre_booking_details" method="post">
												   			<input type="hidden" name="trip_id" value="${trip.trip_id}">
<!-- 												   			<button type="submit" class="btn btn-success">Proceed to Booking</button> -->		
												   				
														<c:choose>
															<c:when test="${num_seats_choosen <= trip.seats_available}">
													   			<label for="submit${index.index+1}" class="btn btn-success">Proceed to Booking</label>
										 					    <input id="submit${index.index+1}" type="submit" value="GO" class="hidden" />
										 					</c:when>    
										 					<c:otherwise>	    
																<button class="btn btn-success btn-notification" data-layout="top" data-type="error" data-text="Number of selected seats is more than available seats!">Proceed to Booking</button>
															</c:otherwise>
														</c:choose>	
											
												   			<c:if test="${trip.seats_available < 4}">
												   				<span class="label label-info">Only <c:out value="${trip.seats_available}"></c:out> Seats Available!</span>
												   			</c:if>
											   			</form>
													</c:when>
													<c:when test="${trip.active == true && trip.seats_available == 0}">
														<button type="button" class="btn btn-info" disabled>Seats Not Available!</button>
													</c:when>
													<c:when test="${trip.active == false}">
														<button type="button" class="btn btn-default" disabled>Trip Ended!</button>
													</c:when>
												</c:choose>
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
							<br>
						</div>

					</div>
				</div>
				</c:otherwise>
				
				</c:choose>
				<!-- /Normal -->
			</div>
			<!-- /.container -->

		</div>
	</div>
	<script type="text/javascript"
	src="<c:url value="/resources/plugins/bootbox/bootbox.js" />"></script>
	</body>
</html>