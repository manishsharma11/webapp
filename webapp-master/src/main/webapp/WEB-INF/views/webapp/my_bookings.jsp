<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>My Bookings</title>


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
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>My Bookings</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				<c:choose>
				<c:when test="${empty bookings}">				
					<h3><i class="icon-exclamation-sign" style="color:red"></i>
								You Didn't Have Any Bookings!
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
										<th>From</th>										
										<th>To</th>
										<th>Number of <br>Seats Booked</th>
										<th>Deducted</th>
										<th>Booking Travel Date</th>					
										<th>Booking Status</th>
										<th>Details</th>
										
									</tr>
								</thead>
					
								<tbody>
									<c:forEach items="${bookings}" var="booking"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${booking.from_stop}"></c:out></td>											
											<td><c:out value="${booking.to_stop}"></c:out></td>
											<td><c:out value="${booking.num_seats_booked}"></c:out></td>
											<td>
												<c:choose>
													<c:when test="${booking.charged_fare != 0 && booking.charged_free_rides == 0}">
														<c:out value="${booking.charged_fare}"></c:out> Credits
													</c:when>
													<c:when test="${booking.charged_fare == 0 && booking.charged_free_rides != 0}">
														<c:out value="${booking.charged_free_rides}"></c:out> Free Ride(s)
													</c:when>
													<c:otherwise>
														<c:out value="${booking.charged_free_rides}"></c:out> Free Ride(s) & <c:out value="${booking.charged_fare}"></c:out> Credits
													</c:otherwise>
												</c:choose>
											</td>
											<td><c:out value="${booking.booking_travel_date_time}"></c:out></td>
											<td>
												<c:if test="${booking.booking_status == 'RECEIVED' }">
													<span class="label label-success">Received</span>
												</c:if> 
												<c:if test="${booking.booking_status == 'ACCEPTED' }">
													<span class="label label-success">Accepted</span>
												</c:if> 
												<c:if test="${booking.booking_status == 'REJECTED' }">
													<span class="label label-danger">Rejected</span>
												</c:if> 
												<c:if test="${booking.booking_status == 'CANCELLED_NOREFUND' }">
													<span class="label label-warning">Cancelled & No Refund</span>
												</c:if> 
												<c:if test="${booking.booking_status == 'CANCELLED_REFUNDED' }">
													<span class="label label-warning">Cancelled & Refunded</span>
												</c:if> 
												<c:if test="${booking.booking_status == 'CANCELLED_PARTIAL_REFUNDED' }">
													<span class="label label-warning">Cancelled & Partially Refunded</span>
												</c:if> 
												<c:if test="${booking.booking_status == 'EXPIRED' }">
													<span class="label label-danger">Expired</span>
												</c:if>		
											</td>
											<td>
												<form action="<%=appName%>/webapp/booking/boarding_pass" method="post">
										   			<input type="hidden" name="booking_id" value="${booking.booking_id}">
										   			<c:choose>
											   			<c:when test="${booking.booking_status == 'RECEIVED' || booking.booking_status == 'ACCEPTED'}">
											   				<input type="submit" value="Go To Boarding Pass" class="btn btn-sm btn-success">
											   			</c:when>
											   			<c:otherwise>
											   				<input type="submit" value="Go To Boarding Pass" class="btn btn-sm btn-default">
											   			</c:otherwise>
										   			</c:choose>
										   		</form>
											</td>
									   </tr>

									</c:forEach>

								</tbody>
							</table>
						</div>
						</div>

					</div>
				</div>
				</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>
	
</body>
</html>
