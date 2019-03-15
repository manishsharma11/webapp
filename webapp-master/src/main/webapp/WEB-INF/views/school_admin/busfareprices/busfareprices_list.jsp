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
	src="<c:url value="/resources/jquery-1.11.0.min.js" />">
	
</script>

<script type="text/javascript">
	$(function() {
		$('#deleteButton').attr('disabled', 'disabled');
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
		$("td.checkbox-column").each(function(){
			
			
			if( $(this).find("input[type='checkbox']:checked").val() == 'on'){
				
				if($(this).parent().find("#id").val() != null)
					 ids.push($(this).parent().find("#id").val());
			}
			
			
		});	
		
		//alert(ids);
		 $.ajax({
			url : "removbusFarePriceEntitiesById",
			type : "POST",
			data : "ids=" + ids,
			success : function(result) {
				window.location.href = result;
			}
		});
 
		ids = null;
		// location.reload(true);
		//document.location.reload(true);
		//location.href = "./trips";		
	});
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
											var search_bus = $("#search_bus_id")
													.val();
											search_bus = $.trim(search_bus);
											var searchOption = $(
													"#search_searchOption")
													.val();
											var regx = /^[A-Za-z0-9]+$/;
											if (search_bus == ""
													|| !regx.test(search_bus)) {
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
															url : "${pageContext.request.contextPath}/school_admin/bus/search",
															type : "POST",
															data : 'search_bus='
																	+ search_bus
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

<script type="text/javascript" class="showcase">
	$(function() {
		$
				.contextMenu({
					selector : '.context-menu-one',
					callback : function(key, options) {
						var va = $(this).find(' input[type="hidden"] ').val();
						if (key == "edit") {
							window.location = "update?id=" + va;
						}
						if (key == "delete") {
							window.location = "removebus?id=" + va;
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

					/*  "view": {name: "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>", icon: "view"}, */
					}
				});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		})
	});
</script>


<script type="text/javascript" class="showcase">
	$(function() {
		$
				.contextMenu({
					selector : '.context-menu-two',
					callback : function(key, options) {
						var va = $(this).find(' input[type="hidden"] ').val();
						if (key == "edit") {
							window.location = "update?id=" + va;
						}
					},
					items : {
						"edit" : {
							name : "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>",
							icon : "edit1"
						},
					}
				});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		})
	});
</script>


<script type="text/javascript" class="showcase">
	$(function() {
		$.contextMenu({
			selector : '.context-menu-three',
			callback : function(key, options) {
				var va = $(this).find(' input[type="hidden"] ').val();
			},
		});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		})
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

						<li>Buses Fare Prices</li>
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
						<h3>Buses Fare Prices</h3>
						<span>Create,Update,Delete Buses</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
					<div class="col-md-8">
						<a href="add" class="btn btn-primary"> <i class="icon-plus"></i>
							<span>Add Bus</span>
						</a> <a data-toggle="modal" href="#myModal5" class="btn btn-danger"
							id="deleteButton"> <i class="icon-trash"></i> <span>Delete</span>
						</a>
					</div>
				</c:if>
				<!--=== Normal ===-->

				<div class="col-md-8">
					<a href="busfareprices_list/add" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add</span>
					</a> <a data-toggle="modal" href="#myModal5" class="btn btn-danger"
						id="deleteButton"> <i class="icon-trash"></i> <span>Delete</span>
					</a>
				</div>

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
									<i class="icon-reorder"></i>Buses Fare Prices
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
											<th class="checkbox-column"><input type="checkbox"
												class="uniform" id="allSelectId"></th>
											<th>S.No</th>
											<th>Source Stop</th>
											<th>Dest Stop</th>
											<th>Auto Fare</th>
											<th>Bus Fare</th>
											<th>Our Fare (Actual fare)</th>
											<th>Our Fare (Charged fare)</th>
											<th>Discounted fare Enabled</th>											
											<th>Our Fare (Discounted fare)</th>
											<th>Distance(km)</th>
											<th>Time duration(mins/hours)</th>											
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody>
										<c:set var="x" value="0"></c:set>
										<c:forEach items="${busFarePriceEntities }" var="bus">
											<tr class="context-menu-two box menu-1" data-toggle="collapse">
												<td class="checkbox-column"><input type="checkbox"
													class="uniform" id="singleCheckId"> <input
													type="hidden" value="${bus.fare_id }" id="id" name="id">
												</td>

												<td><c:set value="${x+1 }" var="x"></c:set> <c:out
														value="${x }"></c:out></td>
												<%-- <td><a class="accordion-toggle" data-toggle="collapse" href="#collapseOne">
										<c:out value="${bus.bus_licence_number}"></c:out> </a></td>
											<div id="collapseOne" class="panel-collapse collapse">
											<div class="panel-body">
												 Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
											</div>
										</div> --%>

												<td><c:out value="${bus.source_stop.stop_name}"></c:out>(<c:out value="${bus.source_stop.id}"></c:out>)</td>
												<td><c:out value="${bus.dest_stop.stop_name}"></c:out>(<c:out value="${bus.dest_stop.id}"></c:out>)</td>
												<td><c:out value="${bus.auto_fare}"></c:out></td>
												<td><c:out value="${bus.bus_fare}"></c:out></td>
												<td><c:out value="${bus.actual_fare}"></c:out></td>
												<td><c:out value="${bus.charged_fare}"></c:out></td>
												<td><c:out value="${bus.discounted_fare_enabled}"></c:out></td>												
												<td><c:out value="${bus.discounted_fare}"></c:out></td>																																			
												<td><c:out value="${bus.distance}"></c:out></td>
												<td><c:out value="${bus.time_duration}"></c:out></td>												
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->
<!-- 				<div class="alert alert-info fade in">
					<i data-dismiss="alert"></i> <strong>Info!</strong> Bus assigned to
					students, Staffs,trips are cannot be deleted
				</div> -->
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

								<option value="bus_licence_number">Bus Licence Number</option>
								<option value="driverId">Driver Name</option>
								<option value="bus_capacity">Capacity</option>


							</select>
						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter: <span
							class="required">*</span></label>
						<div class="col-md-6">
							<input type="text" name="search_bus" id="search_bus_id"
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


</body>
</html>