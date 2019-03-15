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
						<li>Users</li>
						<li>Drivers</li>
						<li>Update Driver</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Driver</h3>
						<span>Update Driver</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>
				<c:if test="${message=='driver_add_failure' }">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> The driver [ <c:out value="${driver_name }"></c:out> ] failed to add. Please try again..
					</div>
				
				</c:if>
				
															<div class="row" id="driver_form_add">
																<!--=== Validation Example 1 ===-->
																<div class="col-md-6">
																	<div class="widget box">
																		<div class="widget-header">
																			<h4><i class="icon-reorder"></i>Driver Form</h4>
																		</div>
																		<div class="widget-content">
																			<form class="form-horizontal row-border" id="validate-4" action="updatedriveraction" method="post">
																				
																				<div class="form-group">
																					<label class="col-md-3 control-label">Current RFID<span class="required">*</span></label>
																					<div class="col-md-9 clearfix">
																						<c:out value="${driver.driver_rfid_number }"></c:out>
																						<label for="chosen1" generated="true" class="has-error help-block" style="display:none;"></label>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Change RFID?</label>
																					<div class="col-md-9 clearfix">
																						<select name="driver_rfid_number" class="col-md-12 select2 full-width-fix" id="driver_rfid_number" style="border-color:grey;">
																							<option></option>
																							<c:forEach items="${drivers_rfid_list }" var="rfid">
																							
																								<option><c:out value="${rfid.rfid_number }"></c:out></option>
																							
																							</c:forEach>
																						</select>
																						<label for="chosen1" generated="true" class="has-error help-block" style="display:none;"></label>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Driver Name<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="driver_name" class="form-control required" id="driver_name" pattern="^\+?[A-Za-z\ ]+\*?$" value="<c:out value="${driver.driver_name }"></c:out>">
																						<input type="hidden" name="driver_id" class="form-control required"  value="<c:out value="${driver.id}"></c:out>">
																						<input type="hidden" name="driver_rfid_number_old" class="form-control required"  value="<c:out value="${driver.driver_rfid_number}"></c:out>">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Phone Number [8-12] <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="phone_number" class="form-control required" rangelength="8, 12" pattern="^\+?[0-9\-]+\*?$" value="<c:out value="${driver.phone_number }"></c:out>">
																					</div>
																				</div>
																				
																				<div class="alert alert-danger fade in" id="input_errors" style="display: none;">
																		
																					<strong>Error!</strong> Please fill all the fields which are marked *
																				</div>
																				
																				<%-- <div class="form-group">
																					<label class="col-md-3 control-label">Address<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="address" class="form-control required" id="driver_address" value="<c:out value="${driver.address }"></c:out>">
																					
																					</div>
																				</div> --%>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Street <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="street" class="form-control required" value="<c:out value="${driver.street }"></c:out>">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">City <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="city" class="form-control required" value="<c:out value="${driver.city }"></c:out>">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">State <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="state" class="form-control required" value="<c:out value="${driver.state }"></c:out>">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Zip <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="zip" class="form-control required" value="<c:out value="${driver.zip }"></c:out>">
																					</div>
																				</div>
																				<%-- <div class="form-group">
																					<label class="col-md-3 control-label">Country <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="country" class="form-control required" value="<c:out value="${driver.country }"></c:out>">
																					</div>
																				</div> --%>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Select Country<span
																					class="required">*</span></label>
																					<div class="col-md-9 clearfix">
																					<select name="country"
																						class="col-md-12 select2 full-width-fix required"
																						id="driver_rfid_number" style="border-color: grey;">
																						<option></option>
																						<c:forEach items="${countries}" var="countries">

																						<option><c:out value="${countries}"></c:out></option>

																							</c:forEach>
																						</select> <label for="chosen1" generated="true"
																								class="has-error help-block" style="display: none;"></label>
																						</div>
																						</div>


																				<div class="form-actions">
																					<a type="button" class="btn btn-default" href="/sts/school_admin/persons/drivers">Cancel</a>
																					<input type="submit" value="Update" class="btn btn-primary pull-right" id="add_new_driver_action">
																				</div>
																			</form>
																		</div>
																	</div>
																	<!-- /Validation Example 1 -->
																</div>
																									
														
			</div>
		</div>

		
</body>
</html>