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
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#bus_id")
								.change(
										function() {
											$("#loading").show();
											$
													.ajax({

														url : "getAllStopsByBusId",
														type : "POST",
														data : "bus_id="
																+ $(this).val(),
														success : function(
																result) {

															var obj = JSON
																	.parse(result);
															$("#stop_id")
																	.empty();
															var data = "<option></option>";
															for (var i = 0; i < obj.length; i++) {
																//alert(obj[i].stop_name);
																data = data
																		+ "<option value="+obj[i].id+">"
																		+ obj[i].stop_name
																		+ "</option>";
															}
															$("#stop_id")
																	.append(
																			data);
															$("#loading")
																	.hide();
														}
													});
										});

					});

	$(document).ready(function() {
		$('#selec_all').click(function(event) { //on click 
			if (this.checked) { // check select status
				$('.checkbox1').each(function() { //loop through each checkbox
					this.checked = true; //select all checkboxes with class "checkbox1"               
				});
			} else {
				$('.checkbox1').each(function() { //loop through each checkbox
					this.checked = false; //deselect all checkboxes with class "checkbox1"                       
				});
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
						<li>Students</li>
						<li>View Student</li>
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
						<h3>Student</h3>
						<span>View Student</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Student Information
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="deletestudentaction" method="post">


									<div class="form-group">


										<label class="col-md-3 control-label"> Bus:</label>
										<div class="col-md-9 clearfix">
											<c:out value="${student.bus_licence }"></c:out>
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>

										<label class="col-md-3 control-label"> Route Name:</label>
										<div class="col-md-9 clearfix">
											<c:out value="${student.route_name }"></c:out>
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>


										<label class="col-md-3 control-label">Stop Name:</label>
										<div class="col-md-9 clearfix">
											<c:out value="${student.stop_name }"></c:out>
											<label for="chosen2" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Commuter Name :</label>
										<div class="col-md-9">
											<c:out value="${student.student_name }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Student ID:</label>
										<div class="col-md-9">
											<c:out value="${student.student_number }"></c:out>
										</div>
									</div>

									<div class="form-group">

										<label class="col-md-3 control-label"> RFID Number:</label>
										<div class="col-md-9 clearfix">
											<c:out value="${student.rfid_number }"></c:out>
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Gender:</label>



										<div class="col-md-9">
											<c:out value="${student.gender }"></c:out>
										</div>


									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Teacher Name:</label>
										<div class="col-md-9">
											<c:out value="${student.teacher_name }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Teacher ID:</label>
										<div class="col-md-9">
											<c:out value="${student.teacher_id }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Home Room
											Assignment <span class="required">*</span>
										</label>
										<div class="col-md-9">
											<c:out value="${student.homeroom_assignment }"></c:out>
										</div>
									</div>
									<!-- <div class="form-group">
																					<label class="col-md-6 control-label">Emergency Contact Details<span class="required">*</span></label>
																					
																				</div> -->
									<div class="form-group">
										<font color="sienna"> <b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEmergency
													Contact Details</p></b>
										</font>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.e_name }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Phone
											Number<span class="required">*</span>
										</label>
										<div class="col-md-9">
											<c:out value="${student.e_number }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Email<span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.e_mail }"></c:out>
										</div>
									</div>
									<%-- <div class="form-group">
																					<label class="col-md-3 control-label">Home Address<span class="required">*</span></label>
																					<div class="col-md-9">
																						<c:out value="${student.home_address }"></c:out>
																					</div>
																				</div> --%>
									<div class="form-group">
										<font color="sienna"> <b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSecondary
													Contact</p></b>
										</font>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.secondary_e_name }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Phone
											Number<span class="required">*</span>
										</label>
										<div class="col-md-9">
											<c:out value="${student.secondary_e_number }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Email<span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.secondary_e_mail }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<font color="sienna"> <b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAddress</p></b>
										</font>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Street <span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.street }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">City <span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.city }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">State <span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.state }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Zip <span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.zip }"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Country <span
											class="required">*</span></label>
										<div class="col-md-9">
											<c:out value="${student.country }"></c:out>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Select Alerts</span></label>
										<div class="col-md-9">
											<c:if test="${student.ch1=='on' }">
												<label class="checkbox"><input type="checkbox"
													name="ch1" class="uniform" checked="checked"
													disabled="disabled"> send text if emergency
													condition exists</label>
											</c:if>
											<c:if test="${student.ch1=='off' }">
												<label class="checkbox"><input type="checkbox"
													name="ch1" class="uniform" disabled="disabled">
													send text if emergency condition exists</label>
											</c:if>
											<c:if test="${student.ch2=='on' }">
												<label class="checkbox"><input type="checkbox"
													name="ch2" class="uniform" checked="checked"
													disabled="disabled"> send text if child does not
													arrive at school</label>
											</c:if>
											<c:if test="${student.ch2=='off' }">
												<label class="checkbox"><input type="checkbox"
													name="ch2" class="uniform" disabled="disabled">
													send text if child does not arrive at school</label>
											</c:if>
											<c:if test="${student.ch3=='on' }">
												<label class="checkbox"><input type="checkbox"
													name="ch3" class="uniform" checked="checked"
													disabled="disabled"> send text if bus is late</label>
											</c:if>
											<c:if test="${student.ch3=='off' }">
												<label class="checkbox"><input type="checkbox"
													name="ch3" class="uniform" disabled="disabled">
													send text if bus is late</label>
											</c:if>

											<label for="sport" class="has-error help-block"
												generated="true" style="display: none;"></label>
										</div>
										<input type="hidden"
											value="<c:out value="${student.rfid_number }"></c:out>"
											name="rfid_number_old"> <input type="hidden"
											value="<c:out value="${student.id }"></c:out>" name="id">
									</div>
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_guest/persons/students">Back</a>

									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>


				</div>
			</div>
		</div>
</body>
</html>