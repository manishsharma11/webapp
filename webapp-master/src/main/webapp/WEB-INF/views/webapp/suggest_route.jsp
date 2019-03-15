<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Suggest Route</title>

<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places" type="text/javascript"></script>
<script type="text/javascript">
   function initialize1() {
      var input = document.getElementById('searchTextField1');
      var autocomplete = new google.maps.places.Autocomplete(input);
   }
   google.maps.event.addDomListener(window, 'load', initialize1);
   
   function initialize2() {
	      var input = document.getElementById('searchTextField2');
	      var autocomplete = new google.maps.places.Autocomplete(input);
	   }
	   google.maps.event.addDomListener(window, 'load', initialize2);
</script>

<!-- Files to be loaded -->

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script type="text/javascript">
	$(function(){
	    $('#t1').clockface();  
	});
	
	$(function(){
	    $('#t2').clockface();  
	});
</script>



<link href="<c:url value="/resources/assets/css/clockface.css" />" rel="stylesheet" type="text/css" />	

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
						<h3>Suggest Route</h3>
						<span>which you want!</span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				
				
				<c:if test="${status== true}">
					<div class="panel-heading">
						<h3><i class="icon-ok-sign" style="color:green"></i>
								Thank You for your valuable Suggestion
						</h3>
					</div>	
				</c:if>
				
				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Suggest A Route
								</h4>
							</div>
							
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/route/suggest_route" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">From :<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="from" class="form-control required" id="searchTextField1" size="50" placeholder="Enter a location" autocomplete="on"">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">To :<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="to"
												class="form-control required" id="searchTextField2" size="50" placeholder="Enter a location" autocomplete="on">
										</div>
									</div>
									
									<div class="form-group">
											<label class="col-md-3 control-label">Morning :<span
												class="required">*</span></label>
											<div class="col-md-9">
												<input type="text" name="morning" id="t1" value="8:30 AM" size="50" data-format="hh:mm A" class="input-small form-control required">
											</div>
									</div>
									
									<div class="form-group">
											<label class="col-md-3 control-label">Evening :<span
												class="required">*</span></label>
											<div class="col-md-9">
												<input type="text" name="evening" id="t2" value="6:30 PM" size="50" data-format="hh:mm A" class="input-small form-control required">
											</div>
									</div>
							
									<div class="form-actions">
										<input type="submit" value="Submit"
											class="btn btn-success btn-block">
									</div>
								</form>
							</div>
					</div>
						<!-- /Validation Example 1 -->
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<script type="text/javascript" src="<c:url value="/resources/clockface.js" />">
	</script>


</body>
</html>
