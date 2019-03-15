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
	$(function() {
		$('#deleteButton').attr('disabled', 'disabled');
	});
</script>

<script type="text/javascript">
$(document).ready(function(){
   								 $("form").submit(function(event){
   									 var $form=$(this).closest('form');
													event.preventDefault();
													$('#confirm').modal({ backdrop: 'static', keyboard: false })
        											.one('click', '#cancel', function(event){
        												$("form").submit();
        												
        											});
   								 });
});
</script>


<script type="text/javascript">
	var count = 0;
	$(function() {

		$(document).on('click', '#singleCheckId', function() {
			if ($(this).prop('checked')) {
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
			}

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
				if(count!=0)
					$('#deleteButton').removeAttr('disabled');
				else
					$('#deleteButton').addAttr('disabled');
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
			// alert('foreach');								 
			// alert($('input.uniform', this).is(':checked'));		
			if ($('input.uniform', this).is(':checked')) {
				//alert("if");
				sel = true; //set to true if there is/are selected row				    
				var driverHiddenId = $('#driverHiddenId', this).val();
				// alert(routeId);
				ids.push(driverHiddenId);
				// alert(ids);					    	
			}
		});
		
			$.ajax({
				url : "removeAllDriversById",
				type : "POST",
				data : "driverIds=" + ids,
				success : function(result) {
					window.location.href = result;
				}
			});
			ids = null;
	
		//location.reload(true);
		//document.location.reload(true);
		//location.href = "./trips";		
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
							window.location = "drivers/updatedriver?id=" + va;
						}
						if (key == "delete") {
							window.location = "drivers/removedriver?id=" + va;
						}
						if (key == "view") {
							window.location = "driver/viewdriver?id=" + va;
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

						"view" : {
							name : "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>",
							icon : "view"
						},
					}
				});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		});
	});
	
	function confirmCancellation(){
		//alert("Are you sure want to cancel")
		
		var txt;
var r = confirm("Are you sure want to cancel!");
alert(r);
if (r == true) {
    txt = "Cancellating booking..";
    
    return true;
} else {
    txt = "You pressed Cancel!";
    return false;
}
		
		
	}
</script>
<style type="text/css">
tr.active td {
	background-color: #428bca;
}
/* 	tr:hover {
		    color: #261F1D !important;
		    background-color: #E5C37E !important;
		} */
</style>


<script type="text/javascript" class="showcase">
	$(function() {
		$
				.contextMenu({
					selector : '.context-menu-two',
					callback : function(key, options) {

						var va = $(this).find(' input[type="hidden"] ').val();
						if (key == "edit") {
							window.location = "drivers/updatedriver?id=" + va;
						}
						/*  if(key=="delete"){        	         	 
						     window.location="drivers/removedriver?id="+va;
						 } */
						if (key == "view") {
							window.location = "driver/viewdriver?id=" + va;
						}
					},
					items : {
						"edit" : {
							name : "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>",
							icon : "edit1"
						},

						"view" : {
							name : "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>",
							icon : "view"
						}
					}
				});

		$('.context-menu-two').on('click', function(e) {
			console.log('clicked', this);
		});
	});
</script>
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
						<li>Commuters</li>
						<li>Bookings</li>
					</ul>
					<ul class="crumb-buttons">
						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				<c:if test="${error_message=='noMatching'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>No
							match found...........</strong>
					</div>
				</c:if>
				<!--=== Page Header ===-->
				<div class="page-header">
					<div class="page-title">
						<h3>Bookings</h3>
						<span>Grouped by Trip Name and Travel Date</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Bookings</div>
								<div class="value">
									<c:out value="${bookings.size()}"></c:out>
								</div>
							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<c:if test="${message=='driver_add_success' }">
					<div class="alert alert-success fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Success!</strong>
						The driver [
						<c:out value="${driver_name }"></c:out>
						] has been successfully added.
					</div>
				</c:if>

				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
					<div class="col-md-8">
						<a href="drivers/add" class="btn btn-primary"> <i
							class="icon-plus"></i> <span>Add</span>
						</a>
						<a data-toggle="modal" href="#myModal5" class="btn btn-danger" id="deleteButton">
							<i class="icon-trash"></i> <span>Delete</span>
						</a>
						<!-- 
					<a href="drivers/add" class="btn btn-info"> <i
						class="icon-edit"></i> <span>Edit</span>
					</a>
					
					<a href="drivers/add" class="btn btn-danger"> <i
						class="icon-trash"></i> <span>Delete</span>
					</a> -->
					</div>
				</c:if>
				<div class="col-md-12" align="right">
					<a data-toggle="modal" href="#myModal4" class="btn btn-success"
						id="rfid_create_click"> <i class="icon-search"></i> <span>
							Search</span>
					</a>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Bookings List
								</h4>
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i
											class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>

							<div class="widget-content">
								<table
									class="table table-striped table-bordered table-hover table-checkable datatable">
									<thead>
										<tr>
											<th>S.No</th>
										    <th>Booking ID</th>
										    <th>Name</th>
											<th>Mobile</th>
											<th>Gender</th>
                                            <th>Pickup Stop</th>
											<th>Drop off Stop</th>
											<th>Booking Status</th>
											<th>Booking Travel Date</th>
											<th>No. of Seats Booked</th>
											<th>Trip Name</th>
											<th>Trip Type</th>
									<!--    <th>Bus ID</th>
											<th>Actual Fare</th>
											<th>Charged Fare</th>
											<th>Charged Free Rides</th>
											<th>Expiry Date</th>  -->
											<th>Cancellation Date</th>
									<!--	<th>Message Sent for Pickup</th>
											<th>Message Sent for Dropoff</th> -->
											<th>Booking Time</th>										
											<th>Commuter ID</th>
									<!--	<th>From Stop ID</th>
											<th>To Stop ID</th>
											<th>Trip ID</th>  -->
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${bookings}" var="booking"
										varStatus="index">

										<tr class="context-menu-two box menu-1">
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${booking.id}"></c:out></td>
											<td><c:out value="${booking.commuter_name}"></c:out></td>
											<td><c:out value="${booking.mobile}"></c:out></td>
											<td><c:out value="${booking.gender}"></c:out></td>
											<td><c:out value="${booking.from_stop_name}"></c:out></td>
											<td><c:out value="${booking.to_stop_name}"></c:out></td>
											<td>
											<c:if test="${booking.status == '0' }">
													<span class="label label-success">Received</span>
												</c:if> 
											<c:if test="${booking.status == '1' }">
													<span class="label label-success">Accepted</span>
												</c:if> 
											<c:if test="${booking.status == '2' }">
													<span class="label label-danger">Rejected</span>
												</c:if> 
											<c:if test="${booking.status == '3' }">
													<span class="label label-warning">Cancelled No Refund</span>
												</c:if> 
											<c:if test="${booking.status == '4' }">
													<span class="label label-warning">Cancelled Refund</span>
												</c:if> 
											<c:if test="${booking.status == '5' }">
													<span class="label label-warning">Cancelled Partially Refund</span>
												</c:if> 
											<c:if test="${booking.status == '6' }">
													<span class="label label-danger">Expired</span>
												</c:if> 
											</td>
											<td><c:out value="${booking.booking_travel_date}"></c:out></td>
											<td><c:out value="${booking.num_seats_booked}"></c:out></td>
											<td><c:out value="${booking.trip_name}"></c:out></td>
											<td><c:out value="${booking.trip_type}"></c:out></td>
								<!--        <td><c:out value="${booking.bus_id}"></c:out></td>
											<td><c:out value="${booking.actual_fare}"></c:out></td>
											<td><c:out value="${booking.charged_fare}"></c:out></td>
											<td><c:out value="${booking.charged_free_rides}"></c:out></td>
											<td><c:out value="${booking.booking_expirary_date}"></c:out></td> -->
											<td><c:out value="${booking.booking_cancellation_date}"></c:out></td>
								<!--		<td><c:out value="${booking.message_sent_for_pickup}"></c:out></td>
											<td><c:out value="${booking.message_sent_for_dropoff}"></c:out></td> -->
											<td><c:out value="${booking.booking_time}"></c:out></td>
											<td><c:out value="${booking.commuter_id}"></c:out></td>
								<!--		<td><c:out value="${booking.from_stop}"></c:out></td>
											<td><c:out value="${booking.to_stop}"></c:out></td>
											<td><c:out value="${booking.trip_id}"></c:out></td> -->
											
										<c:if test="${booking.status == '0' || booking.status == '1'}">

											<td>
													<form action="cancel_booking" method="get">
														<input type="hidden" id="formValueId" name="id" value="${booking.id}">
														<input type="submit" value="Cancel Booking">
														</form>
		
													</td>
										
										</c:if>
												<!-- <form>
													<input type="text" id="formValueId" name="valueId" /> <input
														type="button" id="myButton" />
												</form>
												JavaScript: $(document).ready(function() {
												$('#myButton').click(function() {
												foo($('#formValueId').val()); }); }); -->

											</tr>
									</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->


				<div id="confirm" class="modal fade">
					<div class="modal-body">Are you sure?</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-primary"
							id="cancel">Cancel Booking</button>
						<button type="button" data-dismiss="modal" class="btn">Not
							Cancel</button>
					</div>
				</div>









				<div class="alert alert-info fade in">
					<i class="icon-remove close" data-dismiss="alert"></i> <strong>Info!</strong>	
				<!--		Bookings from Today...		 -->		
		 		<c:if test="${show_isCancelled}">
						<c:if test="${isCancelled}">
							<font size= "5" color = "#DC143C">Successfully Cancelled Booking with ID: <c:out value="${id}"></c:out> or it is Expired.</font>
						</c:if> 
						
						<c:if test="${! isCancelled}">
							<font size= "5" color = "#FFD700">Failed Booking Cancellation with ID: <c:out value="${id}"></c:out>!!</font>
						</c:if>
					</c:if>
					<c:if test="${!show_isCancelled}">
						Bookings From Today ordered by Trip Name..
					</c:if>  
				</div>  
				
			</div>
			<!-- /.container -->

		</div>
	</div>

	<div class="modal fade" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-body">
					<!--=== Page Content ===-->
					<div class="row" id="driver_form_add" style="display: none">
						<!--=== Validation Example 1 ===-->
						<div class="col-md-12">
							<div class="widget box">
								<div class="widget-header">
									<h4>
										<i class="icon-reorder"></i>Add New Driver Form
									</h4>
								</div>
								<div class="widget-content">
									<form class="form-horizontal row-border" id="driver_form"
										action="#" name="pForm" method="post">

										<div class="form-group">
											<label class="col-md-3 control-label">Select RFID<span
												class="required">*</span></label>
											<div class="col-md-9 clearfix">
												<select name="driver_rfid_number"
													class="col-md-12 select2 full-width-fix required"
													id="driver_rfid_number" style="border-color: grey;">
													<option>hmm</option>

												</select> <label for="chosen1" generated="true"
													class="has-error help-block" style="display: none;"></label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">Driver Name<span
												class="required">*</span></label>
											<div class="col-md-9">
												<input type="text" name="req1" class="form-control required"
													id="driver_name">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">Phone Number
												[8-12] <span class="required">*</span>
											</label>
											<div class="col-md-9">
												<input type="text" name="range1"
													class="form-control required digits" id="phone_number">
											</div>
										</div>

										<div class="alert alert-danger fade in" id="input_errors"
											style="display: none;">

											<strong>Error!</strong> Please fill all the fields which are
											Marked *
										</div>
										<div class="form-actions">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Cancel</button>
											<input type="submit" value="Add Driver"
												class="btn btn-primary pull-right"
												id="add_new_driver_action">
										</div>
									</form>
								</div>
							</div>
							<!-- /Validation Example 1 -->
						</div>

					</div>
					<div id="loading" style="display: none">

						<div class="alert alert-info fade in">

							<strong>Info!</strong> Please wait fetching data from database
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>

	<!-- search model4 started -->
	<div class="modal fade" id="myModal4">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Search Form:</h4>
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

								<option value="rfid_number">RFID</option>
								<option value="driver_name">Name</option>

							</select>
						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter search key: <span
							class="required">*</span></label>
						<div class="col-md-6">
							<input type="text" name="searchkey" id="search_rfid_number"
								class="form-control" style="width: 300px;">
						</div>
						<br>
<!-- 
						<div id="loading" style="display: none;">
							<h3>Fetching data from DataBase please wait</h3>
						</div> -->
						<br>


						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							Please enter valid search key
						</div>

						<div class="alert alert-danger fade in"
							id="rfid_number_status_error2" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							 Oh! Some problem occurred, Please try to reload page again
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary"
						id="search_button_add">Search</button>
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

</body>
</html>