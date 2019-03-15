<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Boarding Pass</title>


<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
	
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<script>
     sessionStorage.setItem("boading_pass_visited", "True");
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
						<h3>Boarding Pass</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>	
				<c:if test="${status == 'success'}">
					<div class="panel-heading">
						<h3><i class="icon-ok-sign" style="color:green"></i>
							Booking Successful...
						</h3>
					</div>
				</c:if>
			<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/booking/cancelBooking" method="post">
									<c:choose>
										<c:when test="${booking.booking_status == 'RECEIVED' || booking.booking_status =='ACCEPTED'}">
										<div class="form-group">
												<button type="button" class="btn btn-success btn-block">
												<label>
												<font size="5">
												EC <c:out value="${booking.booking_id}"></c:out>
												</font>
												</label>
												</button>
										</div>
										</c:when>
									
										<c:otherwise>
											<div class="form-group">
												<button type="button" class="btn btn-default btn-block" disabled>
												<label>
												<font size="5">
												EC <c:out value="${booking.booking_id}"></c:out>
												</font>
												</label>
												</button>
										</div>
										</c:otherwise>
									</c:choose>
									<div class="form-group">
										<label class="col-md-3 control-label">From :<span
											class="required"></span></label>
										<div class="col-md-9">
											<c:out value="${booking.from_stop}"></c:out>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">To :<span
											class="required"></span></label>
										<div class="col-md-9">
											<c:out value="${booking.to_stop}"></c:out>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Seats :<span
											class="required"></span></label>
										<div class="col-md-9">
											<c:out value="${booking.num_seats_booked}"></c:out>
										</div>
									</div>
									
									<div class="form-group">
										<c:choose>
												<c:when test="${booking.charged_fare == 0 && booking.charged_free_rides != 0}">
													<label class="col-md-3 control-label">Deducted Free Rides :<span
													class="required"></span></label>
													<div class="col-md-9">
														<c:out value="${booking.charged_free_rides}"></c:out>
													</div>
												</c:when>
												
												<c:when test="${booking.charged_fare != 0 && booking.charged_free_rides == 0}">
													<label class="col-md-3 control-label">Charged Fare :<span
													class="required"></span></label>
													<div class="col-md-9">
														<c:out value="${booking.charged_fare}"></c:out>
													</div>
												</c:when>
												
												<c:otherwise>
													<label class="col-md-3 control-label">Deducted Free Rides :<span
													class="required"></span></label>
													<div class="col-md-3">
														<c:out value="${booking.charged_free_rides}"></c:out>
													</div>
												
													<label class="col-md-3 control-label"> And Charged Fare :<span
													class="required"></span></label>
													<div class="col-md-3">
														<c:out value="${booking.charged_fare}"></c:out>
													</div>
												</c:otherwise>
										
										</c:choose>
										
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Travel Date :<span
											class="required"></span></label>
										<div class="col-md-9">
											<c:out value="${booking.booking_travel_date_time}"></c:out>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Status :<span
											class="required"></span></label>
										<div class="col-md-9">
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
													<span class="label label-warning">Cancelled No Refund</span>
												</c:if> 
											<c:if test="${booking.booking_status == 'CANCELLED_REFUNDED' }">
													<span class="label label-warning">Cancelled Refund</span>
												</c:if> 
											<c:if test="${booking.booking_status == 'CANCELLED_PARTIAL_REFUNDED' }">
													<span class="label label-warning">Cancelled Partially Refund</span>
												</c:if> 
											<c:if test="${booking.booking_status == 'EXPIRED' }">
													<span class="label label-danger">Expired</span>
												</c:if>
										</div>
									</div>
								<c:if test="${booking.booking_status == 'RECEIVED' || booking.booking_status =='ACCEPTED'}">
									<div class="form-group">
<!-- 										<label class="col-md-3 control-label">Status :<span -->
<!-- 											class="required"></span></label> -->
										<div class="col-md-9">
													<div class="form-actions">
														<input type="hidden" name="booking_id" value="${booking.booking_id}">
														<input type="checkbox" name="confirm_cancel" data-rule-required="true" data-msg-required="Sure to Cancel Booking? Please check this box">
														<button type="submit" class="btn btn-success">Cancel Booking</button>
													</div>
												
										</div>
									</div>
								</c:if> 
								</form>
							</div>
							
						</div>
	
					</div>
		</div>
				
			</div>

		</div>
	</div>
	
</body>
</html>