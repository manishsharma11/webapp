<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Booking Status</title>


<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<input id="alwaysFetch" type="hidden" />
<script>
    setTimeout(function () {
        var el = document.getElementById('alwaysFetch');
        el.value = el.value ? location.reload() : true;
    }, 0);
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
						<h3>Booking Status</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Booking Status
								</h4>
							</div>
							
			<div>
			
				<c:if test="${status== 'failure'}">
					<div class="panel-heading">
						<h3><i class="icon-warning-sign" style="color:red"></i>
								Booking Failed...
						</h3>
					</div>					
				</c:if>
				
				<c:if test="${status== 'booking_cancelled'}">
					<div class="panel-heading">
						<h3><i class="icon-ok-sign" style="color:green"></i>
								Booking Succesfully Cancelled...
						</h3>
					</div>	
				</c:if>
				
				<c:if test="${status== 'booking_cancellation_failed'}">
					<div class="panel-heading">
						<h3><i class="icon-warning-sign" style="color:red"></i>
								Booking Cancellation Failed..
						</h3>
					</div>	
				</c:if>
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