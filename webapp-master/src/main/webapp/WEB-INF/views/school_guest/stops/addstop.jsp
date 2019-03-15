<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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


<!-- <script type="text/javascript">
$(document).ready(function(){

	$("#add_new_driver_action").click(function(event){
	
		var h1=$('#h1').val();
		var m1=$('#m1').val();
		var ap1=$('#ap1').val();
		var h2=$('#h2').val();
		var m2=$('#m2').val();
		var ap2=$('#ap2').val();
		//alert("hiii    "+h1+"   "+m1+"   "+ap1+"   "+h2+ "   "+m2+"  "+ap2);
		if(ap1==ap2){
			if(h1<h2){
				$("#loading").hide();
			}else{
				$("#loading").show();	
				event.preventDefault();
			}
		}
		if(ap1!=ap2){
			//alert("both are not same");	
			//event.preventDefault();
		}
	});
});

</script> -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#add_new_driver_action").click(function(event) {
			var h1 = $('#h1').val();
			var h11 = h1.trim();
			var m1 = $('#m1').val();

			var ap1 = $('#ap1').val();
			var h2 = $('#h2').val();
			var h22 = h2.trim();
			var m2 = $('#m2').val();
			var ap2 = $('#ap2').val();
			/* alert("hiii    "+h1+"   "+m1+"   "+ap1+"   "+h2+ "   "+m2+"  "+ap2); */

			//ampm changed to default started
			/* if(ap1.trim()==ap2.trim()){
				if(h11<h22)
				{
					
					$("#loading").hide();
				}else if(m1.trim()<m2.trim())
				{
					
					$("#loading").hide();
				}else
				{
					
					$("#loading").show();	
					event.preventDefault();
				}
				
			} */

			//ampm changed to default ended
			/* if(ap1.trim()=="PM"&&ap2.trim()=="AM"){
				alert("am pm");
				$("#loading1").show();	
				event.preventDefault();
			} */
			var latti = $('#latti').val();
			var langi = $('#langi').val();

			if (latti.trim() == langi.trim()) {
				$("#loading2").show();
				event.preventDefault();
			}

		});
	});
</script>
<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
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
						<li>Buses</li>
						<li>Stops</li>
						<li>Add New Stop</li>
					</ul>

					<ul class="crumb-buttons">

						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				<c:if test="${error_message=='greater'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Time towards home should be greater than the Time towards school.......
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 
				
				<c:if test="${error_message=='latitudeError'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong>  latitude and longitude are
							already exists....
						</div>
				</c:if> 
				<c:if test="${error_message=='errorPreviousSchool'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Expected Time Towards School of current stop timings should be greater than previous stop (according to priority) timings
						</div>
				</c:if> 
				
				<c:if test="${error_message=='errorPreviousHome'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Expected Time Towards Home of current timings should be less than previous stop (according to priority) timings
						</div>
				</c:if> 
				
				<c:if test="${error_message=='errorNextSchool'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Expected Time Towards School of current stop timings should be greater than next stop (according to priority) timings
						</div>
				</c:if> 
				
				<c:if test="${error_message=='errorNextHome'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Expected Time Towards Home of current timings should be greater than next stop (according to priority) timings
						</div>
				</c:if> 
				<c:if test="${error_message=='duplicateStop'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Stop name already exists.Please provide unique stop name...... 
						</div>
				</c:if> 

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Bus Stops</h3>
						<span>Add Stop</span>
					</div>
					
					
	
					
					<%-- <c:if test="${error_message=='greater'}">
						<span style="color: #f00;"> Time towards home should be greater than the Time towards school.......</span>
					</c:if> --%>
					<%-- <c:if test="${error_message=='latitudeError'}">
						<span style="color: #f00;"> latitude and longitude are
							already exist....</span>
					</c:if> --%>
					<%-- <c:if test="${error_message=='errorPreviousSchool'}">
						<span style="color: #f00;"> Expected Time Towards School of current stop timings should be greater than previous stop timings
						  </span>
					</c:if> --%>
					<%-- <c:if test="${error_message=='errorPreviousHome'}">
						<span style="color: #f00;">Expected Time Towards Home of current timings should be less than previous stop
						  </span>
					</c:if> --%>
					
					
					
					<%-- <c:if test="${error_message=='errorNextSchool'}">
						<span style="color: #f00;"> Expected Time Towards School of current stop timings should be greater than next stop timings
						  </span>
					</c:if> --%>
					
					<%-- <c:if test="${error_message=='errorNextHome'}">
						<span style="color: #f00;">Expected Time Towards Home of current timings should be greater than next stop </span>
					</c:if> --%>

				<%-- 	<c:if test="${error_message=='duplicateStop'}">
						<span style="color: #f00;">Stop name already exist.Please provide unique stop name...... </span>
					</c:if> --%>
					
					<br>
					<div class="col-sm-4 col-md-1"></div>


				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Stop Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="addstopaction" method="get">

									<div class="form-group">
										<label class="col-md-3 control-label">Select Bus Stop
											Sequence:<span class="required">*</span>
										</label>
										<div class="col-md-9 clearfix">
											<select name="stop_number"
												class="col-md-8 select2 full-width-fix required">
												<option></option>
												<c:if test="${stops == 0 }">
													<option>1</option>
												</c:if>
												<c:if test="${stops != 0 }">
													<c:forEach begin="1" end="${stops+1 }" varStatus="num">
														<option>${num.index}</option>
													</c:forEach>
												</c:if>

											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Stop Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="stop_name"
												class="form-control required" id="driver_name"
												value="${stopsEntity.stop_name}"> <input
												type="hidden" name="route_id" class="form-control required"
												value="<c:out value="${route_id }"></c:out>">
										</div>
									</div>
				<c:if test="${error_message=='duplicateStop'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Stop name already exists.Please provide unique stop name...
						 </font>
						</div>
						
				</c:if>  
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Expected Time
											Towards School<span class="required">*</span>
										</label>
										
										
										<div class="col-md-3 clearfix">
											<select name="expected_time_hour_towards_school"
												class="col-md-8 select2 full-width-fix required">
												<%-- <option selected="selected" >${currentStopHoursTowardsSchool}</option> --%>
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${currentStopHoursTowardsSchool}" /></option>
												
													<c:forEach begin="0" end="23" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
										
										
										
										
										<div class="col-md-3 clearfix">
											<select name="expected_time_min_towards_school"
												class="col-md-8 select2 full-width-fix required">
												<%-- <option selected="selected" >${currentStopMinutesTowardsSchool}</option> --%>
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${currentStopMinutesTowardsSchool}" /></option>
												
													<c:forEach begin="0" end="59" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
										
										
										

									</div>
									
				<c:if test="${error_message=='errorPreviousSchool'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards School of current stop timings should be greater than previous stop (according to priority) timings
						 </font>
						</div>
				</c:if>  
				<c:if test="${error_message=='errorNextSchool'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards School of current stop timings should be greater than next stop (according to priority) timings
						 </font>
						</div>
				</c:if> 
									<div id="loading" style="display: none;">
										<p>
											<font color="sienna">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSchool
												time should be less then towards home</font>
										</p>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Expected Time
											Towards Home<span class="required">*</span>
										</label>
										
										<div class="col-md-3 clearfix">
											<select name="expected_time_hour_towards_home"
												class="col-md-8 select2 full-width-fix required" id="home_error">
												<%-- <option selected="selected" >${currentStopHoursTowardsHome}</option> --%>
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${currentStopHoursTowardsHome}" /></option>
												
													<c:forEach begin="0" end="23" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
										
 										<div class="col-md-3 clearfix">
											<select name="expected_time_min_towards_home"
												class="col-md-8 select2 full-width-fix required">
												<!-- <option></option> -->
												<%-- <option selected="selected" >${currentStopMinutesTowardsHome}</option> --%> 
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${currentStopMinutesTowardsHome}" /></option>
													<c:forEach begin="0" end="59" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
									</div>
				
									
				 
				 <c:if test="${error_message=='greater'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Time towards home should be greater than the Time towards school.......
						 </font>
						</div>
						
				</c:if>  
				
				<c:if test="${error_message=='errorPreviousHome'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards Home of current timings should be less than previous stop (according to priority) timings
						 </font>
						</div>		
				</c:if> 
				
				
				<c:if test="${error_message=='errorNextHome'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards Home of current timings should be greater than next stop (according to priority) timings
						 </font>
						</div>		
				</c:if> 
									
									<div class="form-group">
										<label class="col-md-3 control-label">Latitude<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="latitude"
												class="form-control required" range="-90, +90"
												value="${lattitude}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Longitude<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="longitude"
												class="form-control required" range="-90, +90"
												value="${longitude}">
										</div>
									</div>
				<c:if test="${error_message=='latitudeError'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 latitude and longitude are	already exists....
						 </font>
						</div>
						
				</c:if>  
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/stop/stops?route_id=<c:out value="${route_id}"></c:out>">Cancel</a>
										<input type="submit" value="Add Stop"
											class="btn btn-primary pull-right" id="add_new_driver_action">
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

</body>
</html>