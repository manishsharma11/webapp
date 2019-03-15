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
						<li>Update Stop</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				<c:if test="${error=='greater'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Time towards home should be greater than the Time towards school.......
						</div>
				</c:if> 
				
				<c:if test="${error=='langitudeError'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong>  latitude and longitude are
							already exists....
						</div>
				</c:if> 
				<c:if test="${error=='errorPreviousSchool'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Expected Time Towards School of current stop timings should be greater than previous stop (according to priority) timings
						</div>
				</c:if> 
				
				<c:if test="${error=='errorPreviousHome'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Expected Time Towards Home of current timings should be less than previous stop (according to priority) timings
						</div>
				</c:if> 
				
				<c:if test="${error=='errorNextSchool'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Expected Time Towards School of current stop timings should be greater than next stop (according to priority) timings
						</div>
				</c:if> 
				
				<c:if test="${error=='errorNextHome'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Expected Time Towards Home of current timings should be greater than next stop (according to priority) timings
						</div>
				</c:if> 
					<c:if test="${error=='duplicateStop'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Stop name already exists.Please provide unique stop name......
						</div>
				</c:if> 
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Bus Stops</h3>
						<span>Update Stop</span>
					</div>
					
					<%-- <c:if test="${error=='greater'}">
						<span style="color: #f00;"> Time towards home should be greater than the Time towards school.......</span>
					</c:if>
				
					<c:if test="${error=='langitudeError'}">
						<span style="color: #f00;"> latitude and longitude are
							already exist....</span>
					</c:if>
					
					<c:if test="${error=='errorPreviousSchool'}">
						<span style="color: #f00;"> Expected Time Towards School of current stop timings should be greater than previous stop timings
						  </span>
					</c:if>
					<c:if test="${error=='errorPreviousHome'}">
						<span style="color: #f00;">Expected Time Towards Home of current timings should be less than previous stop
						  </span>
					</c:if>
					
					
					
					<c:if test="${error=='errorNextSchool'}">
						<span style="color: #f00;"> Expected Time Towards School of current stop timings should be greater than next stop timings
						  </span>
					</c:if>
					<c:if test="${error=='errorNextHome'}">
						<span style="color: #f00;">Expected Time Towards Home of current timings should be greater than next stop </span>
					</c:if>
					
					<c:if test="${error=='duplicateStop'}">
						<span style="color: #f00;">Stop name already exist.Please provide unique stop name...... </span>
					</c:if> --%>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-8">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Stop Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="updatestopaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Current Bus Stop Sequence:</label>
										<div class="col-md-9">
											<label class="control-label"><c:out value="${stop.stop_number}"></c:out></label>
											
											<input type="hidden" name="current_stop_number" class="form-control required" value="<c:out value="${stop.stop_number}"></c:out>">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Stop Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="stop_name" 	class="form-control required"  value="${stop_name}"/>
											<input type="hidden" name="route_id" 	class="form-control required" value="<c:out value="${route_id }"></c:out>">
											<input type="hidden" name="stop_id" 	class="form-control required" value="<c:out value="${stop.id }"></c:out>">
										</div>
									</div>
									
				<c:if test="${error=='duplicateStop'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Stop name already exists.Please provide unique stop name (according to priority) timings
						 </font>
						</div>
						
				</c:if>  
									<div class="form-group">
										<label >
											&nbsp;Current Time  Towards School
												<span class="required">
													<c:out value="${stop.expected_time_towards_school }"></c:out>
												</span>
											</label>
										<br>
										<label class="col-md-3 control-label">Expected Time Towards School</label>
										<%-- <div class="col-md-3">
										
											
											<input type="text" name="expected_time_hour_towards_school" class="form-control required digits"  placeholder="hour" value="<c:out value="${expected_time_towards_school[0] }"></c:out>" range="1, 12">
											
										</div> --%>
										<div class="col-md-3 clearfix">
											<select name="expected_time_hour_towards_school"
												class="col-md-8 select2 full-width-fix">
												<%-- <option selected="selected"><c:out value="${expected_time_towards_school[0]}"></c:out></option> --%>
												<%-- <option selected="selected">${expected_time_hours_towards_school}</option>  --%>
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${expected_time_hours_towards_school}" /></option>
												
												
													<c:forEach begin="00" end="23" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
										<%-- <div class="col-md-3">
											<input type="text" name="expected_time_min_towards_school" class="form-control required digits"  placeholder="minute" value="<c:out value="${expected_time_towards_school[1] }"></c:out>" range="0, 59">
											
										</div> --%>
										
										<div class="col-md-3 clearfix">
											<select name="expected_time_min_towards_school"
												class="col-md-8 select2 full-width-fix">
												<%-- <option selected="selected"><c:out value="${expected_time_towards_school[1] }"></c:out></option> --%>
												<%-- <option selected="selected" >${expected_time_mins_towards_school}</option> --%>
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${expected_time_mins_towards_school}" /></option>
												 
													<c:forEach begin="00" end="59" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
									</div>
									
				<c:if test="${error=='errorPreviousSchool'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards School of current stop timings should be greater than previous stop (according to priority) timings
						 </font>
						</div>
				</c:if>  
				<c:if test="${error=='errorNextSchool'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards School of current stop timings should be greater than next stop (according to priority) timings
						 </font>
						</div>
				</c:if> 
									<div class="form-group">
										<label >
											&nbsp;Current Time Towards Home
												<span class="required">
													<c:out value="${stop.expected_time_towards_home }"></c:out>
												</span>
											</label>
										<br>
										<label class="col-md-3 control-label">Expected Time Towards Home</label>
										<%-- <div class="col-md-3">
											<input type="text" name="expected_time_hour_towards_home" class="form-control required digits"  placeholder="hour" value="<c:out value="${expected_time_towards_home[0] }"></c:out>" range="1, 12">
											
										</div> --%>
										<div class="col-md-3 clearfix">
											<select name="expected_time_hour_towards_home"
												class="col-md-8 select2 full-width-fix">
												<%-- <option selected="selected"><c:out value="${expected_time_towards_home[0]}"></c:out></option> --%>
												<%-- <option selected="selected">${expected_time_hours_towards_home}</option> --%> 
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${expected_time_hours_towards_home}" /></option>
												
													<c:forEach begin="00" end="23" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
										
										
										<div class="col-md-3 clearfix">
											<select name="expected_time_min_towards_home"
												class="col-md-8 select2 full-width-fix">
												<%-- <option selected="selected"><c:out value="${expected_time_towards_home[1] }"></c:out></option> --%>
												<%-- <option selected="selected">${expected_time_mins_towards_home}</option> --%> 
												<option selected="selected"><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${expected_time_mins_towards_home}" /></option>
													<c:forEach begin="00" end="59" varStatus="num">
														<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
													</c:forEach>
											
											</select> 
										</div>
									</div>
				<c:if test="${error=='greater'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Time towards home should be greater than the Time towards school.......
						 </font>
						</div>
						
				</c:if>  
				
				<c:if test="${error=='errorPreviousHome'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards Home of current timings should be less than previous stop (according to priority) timings
						 </font>
						</div>		
				</c:if> 
				
				
				<c:if test="${error=='errorNextHome'}">
						<div >
						<i data-dismiss="alert"></i>
						<font color="sienna">
						 Expected Time Towards Home of current timings should be greater than next stop (according to priority) timings
						 </font>
						</div>		
				</c:if> 
									
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Latitude<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="latitude" class="form-control required" value="${lattitude }" range="-90, +90">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Longitude<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="longitude" class="form-control required" value="<c:out value="${longitude }"></c:out>" range="-90, +90">
										</div>
									</div>
									
				<c:if test="${error=='langitudeError'}">
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
											href="/sts/school_admin/stop/stops?route_id=<c:out value="${route_id}"></c:out>">Cancel</a> <input
											type="submit" value="Update"
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