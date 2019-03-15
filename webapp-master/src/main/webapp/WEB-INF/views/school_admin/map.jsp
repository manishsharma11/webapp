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
<script src="http://maps.google.com/maps/api/js?sensor=true"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/project_js_file/map_data.js" />"></script>
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>
<!-- Google Maps -->
<!-- Google Maps -->


<script type="text/javascript">
	function openWindow() {

		window
				.open(
						'bus',
						'popup_name',
						'height='
								+ 600
								+ ',width='
								+ 900
								+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
	}
</script>

<style type="text/css">
.gmnoprint img {
	max-width: none;
}
</style>
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
				<!-- Breadcrumbs line -->
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li><i class="icon-home"></i> Dashboard</li>
						<li>Map</li>

					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					<!-- <div class="page-title">
						<h4>Current Running Buses</h4>
						<span>Create,Update,Delete Schools List</span>
					</div> -->
					<br>

				</div>
				<%-- <div align="center" id="loading" style="display: none;">
											
					<img alt="loading_image" src="<c:url value="/resources/loading_icon.gif" />">&nbsp;
					<font color="sienna" size="3">please wait,fetching  information from database...</font>
				</div>	 --%>
				<div id="no_buses_running" style="display: none;">
					
					<div class="alert alert-info fade in" align="center">
									
									<strong>Info!</strong>No Bus service at this time
					</div>
				
				</div> 
				<div id="map-canvas" style="width: 900px; height: 480px;" class="gmnoprint img"></div>
				
			</div>

	  <input type="hidden" value="<c:out value="${admin.school_longitude }"></c:out>" id="long">
	  <input type="hidden" value="<c:out value="${admin.school_latitude }"></c:out>" id="lat">

			<!-- <script type="text/javascript">
				var data4="<div style='width:300px;height:60px;'>No of Students:<b> 4</b><br>"+
						  "Current Location: <font color=sienna>Gloucester,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-success'>On Time</span></div>";
				var data3="<div style='width:300px;height:60px;'>No of Students:<b> 14</b><br>"+
						  "Current Location: <font color=sienna>Dunderosa Golf Course,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-warning'>Late</span></div>";		  
				var data2="<div style='width:300px;height:60px;'>No of Students:<b> 22</b><br>"+
						  "Current Location: <font color=sienna>Mcdee St john's Road,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-danger'>Very Late</span></div>";
				var data1="<div style='width:300px;height:60px;'>No of Students:<b> 16</b><br>"+
						  "Current Location: <font color=sienna>Gloucester,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-warning'>Late</span></div>";
						
				
				var locations = [
									[ data4, 45.4314, -75.6019, 4 ],
									[ data3, 45.4414, -75.7719, 3 ] ,
									[ data2, 45.4914, -75.8019, 2 ] ,
									[ data1, 45.5014, -75.8619, 1 ] ,
								];

				var map = new google.maps.Map(document.getElementById('map'), {
					zoom : 10,
					center : new google.maps.LatLng(45.4214, -75.6919),
					mapTypeId : google.maps.MapTypeId.ROADMAP
				});

				var infowindow = new google.maps.InfoWindow();

				var marker, i;

				for (i = 0; i < locations.length; i++) {
					marker = new google.maps.Marker({
						position : new google.maps.LatLng(locations[i][1],
								locations[i][2]),
						map : map,
						icon:"<c:url value="/resources/bus-marker-icon2.png" />"
					});

					google.maps.event.addListener(marker, 'mouseover', (function(
							marker, i) {
						return function() {
							infowindow.setContent(locations[i][0]);
							infowindow.open(map, marker);
						}
					})(marker, i));
					google.maps.event.addListener(marker, 'mouseout', function() {
						infowindow.close();
					});
				}
			</script> -->
		</div>
		<!-- /.container -->
	</div>
</body>
</html>