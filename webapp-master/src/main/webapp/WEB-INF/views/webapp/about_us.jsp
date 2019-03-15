<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>About Us</title>


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
						<h3>About Us</h3>
						
					</div>

				</div>
					
					 <div class="panel-group">
					    <div class="panel panel-default">
					      <div class="panel-heading"><h3>Easy Commute - Affordable Bus Shuttle Service</h3></div>
					      	<div class="panel-body">
								<div class="col-md-12 col-md-offset-4">
									<img src="<c:url value="/resources/icons_webapp/logo_crop.png" />"/>
								</div>
								<p>
									<c:out value="${aboutus.about_us_desc}"></c:out>
								</p>

							</div>
					    </div>
					    <div class="panel panel-default">
					      <div class="panel-heading"><h4>Contact Details</h4></div>
					      
					      <div class="panel-body">
					      	<label>Phone : </label>
					      	<c:out value="${aboutus.customer_care_number}"></c:out>
					      </div>
					      
					      <div class="panel-body">
					      	<label>Email : </label>
					      	<a href="mailto:<c:out value="${aboutus.customer_care_email}"></c:out>" target="_top"><c:out value="${aboutus.customer_care_email}"></c:out></a>				      	
					      </div>
					      
					      <div class="panel-body">
					      	<label>WhatsApp : </label>
					      	<c:out value="${aboutus.whatsapp_care_number}"></c:out>
					      </div>
					    </div>
					    <div class="panel panel-default">
					      <div class="panel-heading">Developed by Avogadro Technology Solutions Pvt. Ltd.</div>
					    </div>
  					</div>
				</div>

		</div>
	</div>
	
	
</body>
</html>