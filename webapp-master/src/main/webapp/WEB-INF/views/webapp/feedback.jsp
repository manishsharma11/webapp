<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Feedback</title>

<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<!-- 	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
   <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- <link rel="stylesheet" type="text/css" href="/sts1/resources/assets/css/star_rating/star-rating.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="/sts1/resources/assets/css/star_rating/theme-krajee-fa.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="/sts1/resources/assets/css/star_rating/theme-krajee-svg.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="/sts1/resources/assets/css/star_rating/theme-krajee-uni.css"> -->

<link rel="stylesheet" type="text/css" href="<%=base_url%>/resources/assets/css/star_rating/star-rating.css">
<link rel="stylesheet" type="text/css" href="<%=base_url%>/resources/assets/css/star_rating/theme-krajee-fa.css">
<link rel="stylesheet" type="text/css" href="<%=base_url%>/resources/assets/css/star_rating/theme-krajee-svg.css">
<link rel="stylesheet" type="text/css" href="<%=base_url%>/resources/assets/css/star_rating/theme-krajee-uni.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

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
						<h3>Feedback</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				
				<c:if test="${status == true}">
					<div class="panel-heading">
						<h3><i class="icon-ok-sign" style="color:green"></i>
							Your Feedback Submitted Successfully...
						</h3>
					</div>
				</c:if>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div col-md-12>
							<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/feedback/submit_feedback" method="POST">
									<div class="form-group">
										<label class="col-md-2 control-label">Rate Us</label>
										<div class="col-md-12">
											<input id="input-1" name="rating" value="5" class="rating-loading">
										</div>
									</div>
											
									<div class="form-group">
										<div class="col-md-12">
											<textarea name="concern" cols="50" rows="5" placeholder="Your Concern" class="form-control required" pattern="^\+?[A-Za-z0-9\ ]+\*?$" title="Feedback must be characters with spaces">
											</textarea>
										</div>								
									</div>
									
									<div class="form-group">
										<div class="col-md-12">
											<label class="checkbox">
												<input type="checkbox" name="reason" value="Rude Driver">Rude Driver
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason" value="Not Safe">Not Safe
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason" value="AC Not Working">AC NotWorking
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason" value="Late Arrival">Late Arrival
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason" value="Unclean Vehicle">Unclean Vehicle
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason" value="Inconvenient Itenerary">Inconvenient Itenerary
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason"	value="App Issues">App Issues
											</label>
											<label class="checkbox">
												<input type="checkbox" name="reason" value="Others">Others
											</label>
										</div>
									</div>
									
									<div class="form-actions">
										<input type="submit" value="Submit Feedback"
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
	<script type="text/javascript"
	src="<c:url value="/resources/src/star_rating.js" />"></script>
	<script>
	$(document).on('ready', function(){
	   $('#input-1').rating({
		   step: 1,
	       starCaptions: {1: 'Very Poor', 2: 'Poor', 3: 'Ok', 4: 'Good', 5: 'Very Good'},
	       starCaptionClasses: {1: 'text-danger', 2: 'text-warning', 3: 'text-info', 4: 'text-primary', 5: 'text-success'}
		   
		   
		   
	   });
	});
	</script>
	
</body>
</html>
