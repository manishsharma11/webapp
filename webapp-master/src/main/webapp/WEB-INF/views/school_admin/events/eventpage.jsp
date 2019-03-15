<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />">
	
</script>

<!-- Include CSS files -->

<%@include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>


<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#events").click(
								function(event) {
									event.preventDefault();

									var busno = $("#events").find(":selected")
											.val();
									busno = $.trim(busno);
									//alert(busno);
									if (busno == "one") {
										if ($("#div_id").is(":visible"))
											$("#div_id").fadeOut(1000);
										else
											$("#div_id").fadeIn(1000);

										$("#div_bus").fadeOut(1000);
										$("#div_type").fadeOut(1000);
										$("#div_trip").fadeOut(1000);
										$("#div_sub").fadeIn(1000);
										$("#div_msg").fadeIn(1000);
										$("#email_id").prop("disabled", false);
									}
									if (busno == "all") {
										$("#div_id").fadeOut(1000);
										$("#div_bus").fadeOut(1000);
										$("#div_type").fadeOut(1000);
										$("#div_trip").fadeOut(1000);
										$("#div_sub").fadeIn(1000);
										$("#div_msg").fadeIn(1000);
										$("#autocomplete-example").prop(
												"disabled", false);
										$("#msg").prop("disabled", false);
									} else if (busno == "select") {

										$("#div_id").fadeOut(1000);
										$("#div_bus").fadeOut(1000);
										$("#div_type").fadeOut(1000);
										$("#div_trip").fadeOut(1000);
										$("#div_sub").fadeOut(1000);
										$("#div_msg").fadeOut(1000);
									}

									if (busno == "bus") {
										$("#div_id").fadeOut(1000);
										$("#div_sub").fadeOut(1000);
										$("#div_msg").fadeOut(1000);

										$("#div_bus").fadeIn(1000);
										$("#div_type").fadeIn(1000);
										$("#div_trip").fadeIn(1000);

										$("#email_id").prop("disabled", true);
										$("#buses").prop("disabled", false);
										$("#trip").prop("disabled", true);
										$("#autocomplete-example").prop(
												"disabled", true);
										$("#msg").prop("disabled", true);
										//$("#tripnames").prop("disabled", false);
									} else {

										$("#names").val("");
										$("#buses").prop("disabled", true);
										$("#trip").prop("disabled", true);
										$("#names").prop("disabled", true);
									}
								});

						$("#buses").click(function(event) {
							event.preventDefault();

							var busno = $("#buses").find(":selected").val();
							busno = $.trim(busno);

							$("#trip").prop("disabled", false);
							$("#trip").attr("disabled", false);

						});

						$("#trip")
								.change(
										function() {
											var busid = $("#buses").find(
													":selected").val();

											if (busid == "select") {
												alert("please Select Bus First");
											}

											else {

												var busno = "";
												busno = busno + $(this).val();

												//alert(busno);

												if (busno == "null") {
													$("#names").empty();
													$("#names").prop(
															"disabled", true);
													$("#autocomplete-example")
															.prop("disabled",
																	true);
												}

												else {

													$
															.ajax({

																url : "getnames",
																type : "POST",
																data : "busno="
																		+ busno
																		+ "&busid="
																		+ busid,
																success : function(
																		result) {

																	result = $
																			.trim(result);
																	//alert(busno);

																	if (result == "") {

																		$(
																				"#names")
																				.empty();
																		alert("there is No "
																				+ busno
																				+ " trip for "
																				+ busid);

																	} else {
																		//alert(result);
																		var list = result
																				.split("+");
																		var append = "";
																		//$("#selecttrips").empty();

																		for (var i = 0; i < list.length; i++) {
																			if (list[i] != "") {
																				//var data1=list[i].split(":");

																				append = append
																						+ "<option value='"+list[i]+"'> "
																						+ list[i]
																						+ " </option>";
																			}
																			//append=append+"<option value="+list[i]+" > "+list[i]+" </option>";
																		}

																		// alert(append);
																		$(
																				"#names")
																				.prop(
																						"disabled",
																						false);
																		$(
																				"#names")
																				.empty();
																		$(
																				"#names")
																				.append(
																						append);
																	}
																},

																error : function(
																		error,
																		status) {
																	window
																			.alert("there are No trips for this Bus");
																}

															});
												}
											}
										});

						$("#names").click(
								function(event) {

									var names = "";
									names = names
											+ $(this).find(':selected').val();
									if (names == "") {
										$("#autocomplete-example").prop(
												"disabled", true)
									} else {
										$("#div_sub").fadeIn(1000);
										$("#div_msg").fadeIn(1000);
										$("#autocomplete-example").prop(
												"disabled", false);
									}
								});

						$("#msg").on(
								"keyup",
								function() {
									var oldVal = "";

									var currentVal = $(this).val();
									if (currentVal == oldVal) {
										//alert("finish");
										$("#add_new_driver_action").prop(
												"disabled", true);
										return; //check to prevent multiple simultaneous triggers
									} else {
										$("#add_new_driver_action").prop(
												"disabled", false);
									}

								});
						$("#autocomplete-example").on("keyup", function() {
							var oldVal = "";

							var currentVal = $(this).val();
							if (currentVal == oldVal) {
								//alert("finish");
								$("#msg").prop("disabled", true);
								return; //check to prevent multiple simultaneous triggers
							} else {
								$("#msg").prop("disabled", false);
								var bus = $("#events").find(":selected").val();

							}

						});

						$("#email_id").on(
								"input propertychange paste",
								function() {

									var email = $("#email_id").val();
									//alert(email);

									if (validateEmail(email)) {

										$("#msg").prop("disabled", false);
										$("#autocomplete-example").prop(
												"disabled", false);
									} else {
										$("#autocomplete-example").prop(
												"disabled", true);
									}

								});

					});

	function validateEmail(sEmail) {
		var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
		var email = document.getElementById("email_id").value;
		//alert("js "+email);
		var data = email.split(",");
		//alert(data[0]);
		var res = true;
		for (i = 0; i < data.length; i++) {

			if (filter.test(data[i])) {

				//alert("true");
				res = true;
			} else {
				res = false;
			}

		}
		return res;
	}
	function validateEmails(field, delimiter) {
		var delimiter = delimiter || ';';
		var filter = /([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+>?$/;
		var error = 0;
		//alert("new");
		// Create an array by splitting the field along the delimiter
		var aEmails = field.value.split(';');

		// For each of the emails
		for (index = 0; index < aEmails.length; index++) {
			// Trim spaces from the ends
			aEmails[index] = (aEmails[index].replace(/^\s+/, '')).replace(
					/\s+$/, '');
			// Check whether an email is present
			if (aEmails[index] != '' && aEmails[index].search(filter) == -1)
				error = 1;
		}

		// Update the value of the field
		field.style.backgroundColor = (error == 1) ? 'FFFFCC' : 'FFFFFF';
	}
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
						<li>Email Events</li>

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
						<h3>Email Events</h3>
						<span>Compose</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>
				<c:if test="${error_message=='noMatching'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>No
							match found...........</strong>

					</div>
				</c:if>
				<!--=== Page Header ===-->
				<br> <br>
				<c:if test="${result=='success'}">
					<div class="alert alert-success fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Success!
						</strong> <span>Email Sent Successfully.</span>
					</div>
					<style>
#home_error {
	border-color: green;
}
</style>
				</c:if>
				<c:if test="${result=='failure'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!
						</strong> <span>Email sending got Failed.</span>
					</div>
					<style>
#home_error {
	border-color: red;
}
</style>
				</c:if>

				<c:if test="${result==''}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!
						</strong> <span>Email sending got Failed</span>
					</div>
					<style>
#home_error {
	border-color: red;
}
</style>
				</c:if>




				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-11">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Compose Email
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="send" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">To <span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="events"
												class="col-md-12 select2 full-width-fix required"
												id="events" style="border-color: grey;">
												<option value="select">Select Recipients</option>
												<option value="all">All Users</option>
												<option value="bus">Specific Bus/Trip Users</option>
												<option value="one">One or More Users</option>

											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<!-- adding driver ID code -->

									<div class="form-group" id="div_id" style="display: none;">
										<label class="col-md-3 control-label">Email ID<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="email_id"
												class="form-control required" id="email_id"
												placeholder="Enable if Selected one or More Option"
												disabled="disabled"> <span class="help-block">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Add Emails
												Separated By commas</span>

										</div>

									</div>

									<div class="form-group" id="div_bus" style="display: none;">
										<label class="col-md-3 control-label">Select Bus<span
											class="required">*</span></label>
										<div class="col-md-9">
											<select name="buses"
												class="col-md-12 select2 full-width-fix required" id="buses"
												disabled="disabled">

												<option value="select">Select Bus</option>
												<c:forEach items="${allbuses }" var="bus">
													<option value="${bus.bus_licence_number }">${bus.bus_licence_number}</option>

												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group" id="div_type" style="display: none;">
										<label class="col-md-3 control-label">Select Trip Type
											<span class="required">*</span>
										</label>
										<div class="col-md-9">


											<select id="trip"
												class="col-md-12 select2 full-width-fix required" multiple
												size="6" name="triptype" placeholder="Seelct Bus First"
												disabled="disabled">
												<option value="Pick Up">Pick Up Trip</option>
												<option value="Drop Off">Drof Off Trip</option>
											</select>

										</div>
									</div>



									<div class="form-group" id="div_trip" style="display: none;">
										<label class="col-md-3 control-label">Select Trip
											Names <span class="required">*</span>
										</label>
										<div class="col-md-9">
											<select id="names"
												class="select2-select-02 col-md-12 full-width-fix" multiple
												size="6" name="tnames" disabled="disabled">
												<option value=""></option>
											</select> <span class="help-block">Select atleast One Trip Name
												& Use Ctrl key for Mutiple Names when gets Enable</span>
										</div>
									</div>
									<div class="form-group" id="div_sub" style="display: none;">
										<label class="col-md-3 control-label">Subject <span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="sub" class="form-control required"
												id="autocomplete-example" disabled="disabled"
												placeholder="Type Subject First to Compose"
												onmousedown="sub()">

										</div>
									</div>
									<div class="form-group" id="div_msg" style="display: none;">
										<label class="col-md-3 control-label">Body <span
											class="required">*</span></label>
										<div class="col-md-9">
											<textarea rows="5	" cols="5" name="msg"
												class="form-control required"
												placeholder="Compose Your Message to Send"
												disabled="disabled" id="msg"></textarea>
										</div>
									</div>


									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-sm btn-warning"
											href="/sts/school_admin/emails/events">Cancel</a> <input
											type="submit" value="Send" class="btn btn-primary pull-right"
											id="add_new_driver_action" disabled="disabled"
											onclick="sub()" onsubmit="sub()">
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