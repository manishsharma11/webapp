<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>PreBooking Confirmation</title>


<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<script>
     if (sessionStorage.getItem("boading_pass_visited")) {
          sessionStorage.removeItem("boading_pass_visited");
          window.location.reload(true); // force refresh page1
     }
</script>

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
						<h3>Booking Confirmation</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				
				
				<div class="col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Available Credits</div>
								<div class="value">
									<c:out value="${preBookingDetails.account.points_balance}"></c:out>
								</div>

							</div>

						</div>
						<!-- /.smallstat -->
					</div>
					
					<div class="col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Free Rides 
								</div>
								<div class="value">
									<c:out value="${preBookingDetails.account.free_rides_balance}"></c:out>
								</div>

							</div>

						</div>
						<!-- /.smallstat -->
					</div>
				
				
				
				

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
													
							
			<div class="row">
					<div class="col-md-12">
						<div class="widget-content">
						<font color="red">*Scroll the Table.</font>
						<div class="table-responsive">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>From</th>
										<th>To</th>										
										<th>Number of Seats</th>
										<th>Total Fare (<c:out value="${preBookingDTO.num_seats_choosen}"></c:out> * <c:out value="${preBookingDetails.fare.charged_fare}"></c:out> = <c:out value="${preBookingDetails.fare.charged_fare*preBookingDTO.num_seats_choosen}"></c:out>)</th>
									</tr>
								</thead>
								<tbody>
									<tr>
											<td><c:out value="${preBookingDetails.fare.from_stop}"></c:out></td>
											<td><c:out value="${preBookingDetails.fare.to_stop}"></c:out></td>											
											<td><c:out value="${preBookingDTO.num_seats_choosen}"></c:out></td>
											<td>
											<c:choose>
				
												<c:when test="${preBookingDetails.fare.charged_fare*preBookingDTO.num_seats_choosen <= preBookingDetails.account.points_balance}">
													<c:if test="${preBookingDTO.num_seats_choosen <= preBookingDetails.account.free_rides_balance}">
														<c:out value="${preBookingDTO.num_seats_choosen}"></c:out> Free Ride(s)
													</c:if>
												
													<c:if test="${preBookingDTO.num_seats_choosen > preBookingDetails.account.free_rides_balance}">
														<c:out value="${preBookingDetails.account.free_rides_balance}"></c:out> Free Ride(s) &
														<c:out value="${(preBookingDTO.num_seats_choosen - preBookingDetails.account.free_rides_balance) *  preBookingDetails.fare.charged_fare}"></c:out>
														 Credits
													</c:if>
												</c:when>
												
												<c:otherwise>
													<c:if test="${preBookingDTO.num_seats_choosen <= preBookingDetails.account.free_rides_balance}">
														<c:out value="${preBookingDTO.num_seats_choosen}"></c:out> Free Ride(s)
													</c:if>
													<c:if test="${preBookingDTO.num_seats_choosen > preBookingDetails.account.free_rides_balance}">
														<c:if test="${preBookingDetails.account.free_rides_balance != 0}">
															<c:out value="${preBookingDetails.account.free_rides_balance}"></c:out> Free Rides &
														</c:if>
														<c:out value="${(preBookingDTO.num_seats_choosen - preBookingDetails.account.free_rides_balance) *  preBookingDetails.fare.charged_fare}"></c:out>
														 Credits
													</c:if>
												</c:otherwise>
											
											</c:choose>
											
											</td>
									 </tr>
								</tbody>
							</table>
						</div>
						</div>

					</div>
				</div>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				<form class="form-horizontal row-border" id="validate-4" action="<%=appName%>/webapp/booking/bookNow">			
					<div class="form-group">
							<div>
									<div class="form-actions">
										<input type="checkbox" name="tc" data-rule-required="true" data-msg-required="Please Accept The Terms & Conditions to Book"> <label>I accept the Terms & Conditions</label> <br>
										<button type="submit" class="btn btn-success btn-block">Book Now</button>
									</div>				
							</div>
					</div>
				</form>
	
						</div>
						<!-- /Validation Example 1 -->
					</div>
				</div>
			</div>

		</div>
	</div>
	
</body>
</html>
