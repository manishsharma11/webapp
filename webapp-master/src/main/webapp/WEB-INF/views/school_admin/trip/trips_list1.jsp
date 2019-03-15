<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	src="<c:url value="/resources/jquery-1.11.0.min.js" />">
	
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//alert("ready");
						/* search box code model4 started */
						$("#search_button")
								.click(
										function() {
											var search_trips = $(
													"#search_trips_id").val();
											search_trips = $.trim(search_trips);
											var searchOption = $(
													"#search_searchOption")
													.val();
											var regx = /^[A-Za-z0-9]+$/;
											if (search_trips == ""
													|| !regx.test(search_trips)) {
												$("#search_rfid_number").css(
														"border-color", "red");
												$(
														"#rfid_number_status_error501")
														.show();

												$("#rfid_number_status_error")
														.hide();
												$("#rfid_number_status_success")
														.hide();
											} else {
												$
														.ajax({
															url : "${pageContext.request.contextPath}/school_admin/trip/search",
															type : "POST",
															data : 'search_trips='
																	+ search_trips
																	+ '&searchOption='
																	+ searchOption,
															success : function(
																	result) {
																//alert(result);	
																window.location.href = result;
															}
														});
											}//else
											/* 	return true; */
										});
						/* search box model4 code ended */

						$("tbody tr").bind("contextmenu", function(e) {
							$('table tr').removeClass("active");
							$(this).addClass("active");
						});
						$("tbody tr").click(function(e) {
							$('table tr').removeClass("active");
							$(this).addClass("active");
						});
					});
</script>

<script type="text/javascript">
	$(function() {
		$('#deleteButton').attr('disabled', 'disabled');
	});

	$(document).ready(
			function() {

				$("#original_table tbody tr td a").click(
						function() {

							var row_index = $(this).parent().parent().index();
							console.log(row_index);

							$("#modal_table").empty();
							var data = "<tr><td>Trip Name:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(2)').text()
									+ "</td></tr>"
									+ "<tr><td>Trip Type:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(3)').text()
									+ "</td></tr>"
									+ "<tr><td>Trip Runnig Date:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(4)').text()
									+ "</td></tr>"
									+ "<tr><td>Route Name:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(5)').text()
									+ "</td></tr>"
									+ "<tr><td>Bus ID:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(6)').text()
									+ "</td></tr>"
									+ "<tr><td>School Time:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(7)').text()
									+ "</td></tr>"
									+ "<tr><td>Trip Start Time:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(8)').text()
									+ "</td></tr>"
									+ "<tr><td>Trip End Time:</td><td>"
									+ $(this).parent().parent()
											.find('td:eq(9)').text()
									+ "</td></tr>";
							$("#modal_table").append(data);
						});
			})
</script>

<script type="text/javascript">
	var count = 0;
	$(function() {

		$(document).on('click',
				'#original_table tbody tr td inout[type=checkbox]', function() {
					alert("clicked");
					/*if ($(this).prop('checked')) {
						$('#deleteButton').removeAttr('disabled');
						count++;
						//alert(count);
					}
					if (!($(this).prop('checked'))) {
						count--;
						//alert(count);
						if ($('#allSelectId').is(':checked')) {
							//alert("all");					
							//$('#allSelectId').trigger("click");					
						}
					}
					

					if (count == 0) {
						//alert("count is 0");
						$('#deleteButton').attr('disabled', 'disabled');
					}*/

				});
	});

	$(function() {
		$(document).on('click', '#allSelectId', function() {

			if ($(this).prop('checked')) {
				//alert("select all checked");
				//$('#deleteButton').removeAttr('disabled');
				$("td.checkbox-column").each(function() {

					if ($('input.uniform', this).is(':checked')) {
						//alert(count);
						count++;
					}
				});
				if (count != 0)
					$('#deleteButton').removeAttr('disabled');
				else
					$('#deleteButton').attr('disabled', 'disabled');
			}
			if (!($(this).prop('checked'))) {
				//alert("unchecked");					
				$('#deleteButton').attr('disabled', 'disabled');
			}
		});
	});
</script>


<script type="text/javascript">
	var sel = false;
	var ids = [];
	$(document).on('click', '#deleteButtonAction', function() {
		//alert("delete button");							
		$("td.checkbox-column").each(function() {
			if ($('input.uniform', this).is(':checked')) {
				if (!($('input.uniform', this).is(':disabled'))) {
					sel = true; //set to true if there is/are selected row				    
					var routeId = $('#tripIdHidden', this).val();
					ids.push(routeId);
					// alert(ids);		
				}
			}
		});
		$.ajax({

			url : "removeAllTripsByTripIds",
			type : "POST",
			data : "tripIds=" + ids,
			success : function(result) {

				window.location.href = result;
			}
		});
		ids = null;

	});
</script>


<script type="text/javascript" class="showcase">
	$(function() {
		$
				.contextMenu({
					selector : '.context-menu-one',
					callback : function(key, options) {
						var va = $(this).find(' input[type="hidden"] ').val();
						if (key == "edit") {
							window.location = "updateTrip1?id=" + va;
						}
						if (key == "delete") {
							window.location = "deleteTrip1?id=" + va;
						}
						if (key == "view") {
							window.location = "viewTrip?id=" + va;
						}
					},
					items : {
						"edit" : {
							name : "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>",
							icon : "edit1"
						},

						"delete" : {
							name : "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete</i>",
							icon : "delete"
						},

					/* "view": {name: "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>", icon: "view"}, */
					}
				});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		});
		$("tbody tr").bind("contextmenu", function(e) {
			$('table tr').removeClass("active");
			$(this).addClass("active");

		});
		$("tbody tr").click(function(e) {
			$('table tr').removeClass("active");
			$(this).addClass("active");
		});
	});
</script>


<script type="text/javascript" class="showcase">
	$(function() {
		$
				.contextMenu({
					selector : '.context-menu-two',
					callback : function(key, options) {
						var va = $(this).find(' input[type="hidden"] ').val();

						if (key == "view") {
							window.location = "viewTrip?id=" + va;
						}
						if (key == "edit") {
							window.location = "updateTrip?id=" + va;
						}
					},
					items : {

						"view" : {
							name : "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>",
							icon : "view"
						},
						"edit" : {
							name : "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>",
							icon : "edit1"
						},
					}
				});

		$('.context-menu-two').on('click', function(e) {
			console.log('clicked', this);
		});
		$("tbody tr").bind("contextmenu", function(e) {
			$('table tr').removeClass("active");
			$(this).addClass("active");

		});
		$("tbody tr").click(function(e) {
			$('table tr').removeClass("active");
			$(this).addClass("active");
		});
	});
</script>

<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

<style type="text/css">
tr.active td {
	background-color: #428bca;
}
/* 	tr:hover {
		    color: #261F1D !important;
		    background-color: #E5C37E !important;
		} */
</style>
<%
	// Set refresh, autoload time as 5 seconds
	response.setIntHeader("Refresh", 30);
%>
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
						<li>Trips</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<c:if test="${error_message=='noMatching'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>
							No match found</strong>

					</div>
				</c:if>
				<!--=== Page Header ===-->
				<div class="page-header">


					<br>
					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>

								<div class="title">Total Trips</div>
								<div class="value">
									<c:out value="${trips.size()}"></c:out>
								</div>


							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
					<div class="col-md-12">
						<a href="addTrip1" class="btn btn-primary"> <i
							class="icon-plus"></i> <span>Add Trip</span>
						</a>

					</div>
				</c:if>
				<br />

				<div class="col-md-12" align="right">
					<a data-toggle="modal" href="#myModal4" class="btn btn-success"
						id="rfid_create_click"> <i class="icon-search"></i> <span>
							Search</span>
					</a>
				</div>
				<br />

				<div class="row">
					<div class="col-md-12">

						<div class="widget-content">
							<table id="original_table"
								class="table table-striped table-bordered table-hover table-checkable ">
								<thead>
									<tr>

										<th>S.No</th>
										<th>Trip Id</th>
										<th>Trip Detail Id</th>
										<th>Trip Name</th>
										<th>Trip Running Date</th>
										<th>Trip Start Time</th>
										<th>Trip End Time</th>
										<th>Total Seats (Capacity)</th>
										<th>Total Seats (Allocated)</th>
										<th>Seats Filled</th>
										<th>Status</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:set value="0" var="x"></c:set>
									<c:forEach items="${trips }" var="trip">
										<tr class="context-menu-one box menu-1">
											<input type="hidden"
												value="<c:out value="${trip.id}"></c:out>" />
											<%-- 											<c:choose>
												<c:when test="${login_role  == 'ROLE_ADMIN' }">
													<c:choose>
														<c:when test="${trip.seats_filled == 0}">

															
														</c:when>
														<c:otherwise>
															<tr class="context-menu-two box menu-1">
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose> --%>
											<!-- <tr class="context-menu-one box menu-1"> -->
											<%-- 											<c:choose>
												<c:when test="${trip.seats_filled == 0}">
													<td class="checkbox-column"><input type="checkbox"
														id="singleCheckId" class="uniform"> <input
														type="hidden" value="${trip.id}" id="tripIdHidden">
													</td>
												</c:when>
												<c:otherwise> --%>

											<%-- 												</c:otherwise>
											</c:choose> --%>


											<td><c:set var="x" value="${x+1 }"></c:set> <c:out
													value="${x }"></c:out></td>
											<td style="background-color:LightGoldenRodYellow">
											<a href="/sts/school_admin/bookings/list?trip_id=${trip.id}">${trip.id}</a></td>
											<td><c:out value="${trip.trip_detail_id}"></c:out></td>
											<td><c:out value="${trip.tripDetail.trip_name}"></c:out></td>
											<td><c:out value="${trip.trip_running_date}"></c:out></td>
											<td><c:out
													value="${trip.tripDetail.trip_start_time_hours}:${trip.tripDetail.trip_start_time_minutes}"></c:out></td>
											<td><c:out
													value="${trip.tripDetail.trip_end_time_hours}:${trip.tripDetail.trip_end_time_minutes}"></c:out></td>
											<td><c:out value="${trip.tripDetail.bus.bus_capacity}"></c:out></td>
											<td><c:out value="${trip.tripDetail.bus.bus_allotted}"></c:out></td>
											<td><c:out value="${trip.seats_filled}"></c:out></td>
											<td><c:if test="${trip.enabled == true}">
													<span class="label label-success">Enabled</span>
												</c:if> <c:if test="${trip.enabled == false}">
													<span class="label label-warning">Disabled</span>
												</c:if></td>
											<td style="background-color:Lavender"><a href="cancelTrip1?id=${trip.id}">Cancel Trip</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="row">
								<div class="table-footer">
									<div class="col-md-4" style="overflow: scroll;">
										Showing ${offset } to ${limit } of ${recordsCount } entries
										<ul class="pagination">

											<c:forEach var="i" begin="1"
												end="${recordsCount /  recordsPerPage}">
												<c:choose>
													<c:when test="${ i == (limit /  recordsPerPage)}">
														<!-- li class="active"><a href="trips?offset=&limit=">${i }</a></li-->
														<li class="active"><a href="trips?offset=&limit=">${((i -1) * recordsPerPage) +1}</a></li>
													</c:when>
													<c:otherwise>
														<li><a
															href="trips1?offset=${((i -1) * recordsPerPage) +1}&limit=${i *recordsPerPage} ">${i }</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- /Normal -->
				<div class="alert alert-info fade in">
					<i data-dismiss="alert"></i> <strong>Info!</strong> Cannot delete
					trips with assigned Commuters
				</div>
			</div>
			<!-- /.container -->
		</div>
	</div>

	<!-- search model4 started -->
	<div class="modal fade" id="myModal4">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Search form:</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">

						<label class="col-md-5 control-label">Search By: <span
							class="required">*</span></label>

						<div class="col-md-7 clearfix">
							<select name="searchOption" id="search_searchOption"
								class="col-md-5 select2 full-width-fix required"
								style="border-color: grey;">

								<!-- 	<option>select</option> -->
								<option value="trip_name">Trip Name</option>
								<option value="trip_running_date">Trips Running on</option>
								<option value="seats_filled">Seats Filled</option>

							</select>
						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter: <span
							class="required">*</span></label>
						<div class="col-md-6">
							<input type="text" name="search_trips" id="search_trips_id"
								class="form-control" style="width: 300px;">
						</div>
						<br>

						<!-- <div id="loading" style="display: none;">
							<h3>Fetching data from DataBase please wait</h3>
						</div> -->
						<br>


						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							Please enter valid search key.....
						</div>

						<div class="alert alert-danger fade in"
							id="rfid_number_status_error2" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							some problem occured... please try to reload page again...
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="search_button">Search</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- search model4 ended -->

	<!-- Modal dialog -->
	<div class="modal fade" id="myModal5">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Delete Form</h4>
				</div>
				<div class="modal-body">Are you sure you want delete?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						id="deleteButtonAction">Yes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- Modal dialog -->
	<div class="modal fade" id="show_trip">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Tip Details</h4>
				</div>
				<div class="modal-body">
					<!--=== Labels ===-->
					<div class="widget box hidden-xs">

						<div class="widget-content no-padding">
							<table class="table table-striped" id="modal_table">

								<tbody>

								</tbody>
							</table>
						</div>
					</div>
					<!-- /Labels -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>