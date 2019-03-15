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

$(document).ready(function(){
	$("#search_button").click(function(){
		
		
		var search_route=$("#search_route_id").val();						
		search_route=$.trim(search_route);			
		var searchOption=$("#search_searchOption").val();						
		var regx = /^[A-Za-z0-9]+$/;					
		if(search_route==""||!regx.test(search_route)){				
			$("#search_rfid_number").css("border-color","red");
			$("#rfid_number_status_error501").show();				
			$("#rfid_number_status_error").hide();
			$("#rfid_number_status_success").hide();						
		}		
		
		else{	
			
			 $.ajax({
			
				url:"${pageContext.request.contextPath}/school_admin/stop/search",
				type:"POST",
				data:'rfid_number='+search_route+'&searchOption='+searchOption,
				 success:function(result){
					//alert(result);	
					 window.location.href = result;
				}
			}); 
		}//else
	})
	
	
});


</script>


<script type="text/javascript">
	var sel = false;
	var ids = [];
	$(document).on('click', '#deleteButtonAction', function() {

		$("td.checkbox-column").each(function() {
		if( $(this).find("input[type='checkbox']:checked").val() == 'on'){
				
				if($(this).parent().find("#stopIdHidden").val() != null)
					 ids.push($(this).parent().find("#stopIdHidden").val());
			}
		});		
			$.ajax({

				url : "removeAllStopsByStopIds",
				type : "POST",
				data : "stopIds=" + ids,
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
						var stop_id = $(this).find(' input[type="hidden"] ')
								.val();
						if (key == "edit") {
							window.location = "updatestop?id=" + stop_id;
						}
						if (key == "delete") {
							window.location = "deletestop?id=" + stop_id;
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
					}
				});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		})
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
						var stop_id = $(this).find(' input[type="hidden"] ')
								.val();
						if (key == "edit") {
							window.location = "updatestop?id=" + stop_id;
						}
						/*  if(key=="delete"){        	         	 
							 window.location="deletestop?id="+stop_id;
						 }    */
					},
					items : {
						"edit" : {
							name : "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>",
							icon : "edit1"
						},

					/*  "delete": {name: "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete</i>", icon: "delete"},   */
					}
				});

		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		})
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
						<li>Bus</li>
						<li>Bus Stops List</li>
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
						<h3>Bus Stops List</h3>
						<span>Create,Update,Delete Bus stops List</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Stops</div>
								<div class="value">
									<c:out value="${stops.size()}"></c:out>
								</div>
							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
					<div class="col-md-8">
						<a href="addstop" class="btn btn-primary"> <i
							class="icon-plus"></i> <span>Add Stop</span>
						</a>
						<a data-toggle="modal" href="#myModal5" class="btn btn-danger" id="deleteButton">
							<i class="icon-trash"></i> <span>Delete</span>
						</a>
						 
					 
				 
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
									<i class="icon-reorder"></i>Stops List:
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
											<th>Stop Id</th>
											<th>Stop Name</th>
											<th>Display Name</th>
											<th>Latitude</th>
											<th>Longitude</th>
											<th>Geofence</th>
											<th>Enabled</th>
											<th>Shortcode</th>
											<th>Stop type</th>											
											<th>Helptext</th>
											<th>Tags</th>																															
										</tr>
									</thead>
									<tbody>
										<c:set var="row" value="context-menu-one box menu-1" />
										<c:set var="x" value="0"></c:set>
										<c:forEach items="${stops }" var="stop">

											<c:if test="${login_role  == 'ROLE_ADMIN' }">
												<c:if test="${stop.isAssigned  == '0'}">
													<c:set var="row" value="context-menu-one box menu-1" />
												</c:if>
											</c:if>

											<c:if test="${login_role  == 'ROLE_ADMIN' }">
												<c:if test="${stop.isAssigned  !='0'}">
													<c:set var="row" value="context-menu-two box menu-1" />
												</c:if>
											</c:if>

											<tr class="${row}">
												<c:if test="${login_role  == 'ROLE_ADMIN' }">
													<c:if test="${stop.isAssigned  == '0'}">
														<td class="checkbox-column"><input type="checkbox"
															class="uniform" id="singleCheckId"> <input
															type="hidden" value="${stop.id}" id="stopIdHidden">
														</td>
													</c:if>
												</c:if>

												<c:if test="${login_role  == 'ROLE_ADMIN' }">
													<c:if test="${stop.isAssigned  != '0'}">
														<td><input type="checkbox" class="uniform"
															id="singleCheckId" disabled="disabled"> <input
															type="hidden" value="${stop.id}" id="stopIdHidden">
														</td>
													</c:if>
												</c:if>
												<c:if test="${login_role  != 'ROLE_ADMIN' }">
													<c:if test="${stop.isAssigned  == '0'}">
														<td class="checkbox-column"><input type="checkbox"
															class="uniform" id="singleCheckId"> <input
															type="hidden" value="${stop.id}" id="stopIdHidden">
														</td>
													</c:if>
												</c:if>

												<c:if test="${login_role  != 'ROLE_ADMIN' }">
													<c:if test="${stop.isAssigned  != '0'}">
														<td class="checkbox-column"><input type="checkbox" class="uniform"
															id="singleCheckId" disabled="disabled"> <input
															type="hidden" value="${stop.id}" id="stopIdHidden">
														</td>
													</c:if>
												</c:if>

												<%--<c:if test="${login_role  != 'ROLE_ADMIN' }">
												<tr>
													<td class="checkbox-column"><input type="checkbox"
														class="uniform" id="singleCheckId"> <input
														type="hidden" value="${stop.id}" id="stopIdHidden">
													</td>
											</c:if> --%>
												<td><c:set var="x" value="${x+1}"></c:set>
												<c:out value="${x}"></c:out></td>
												<td><c:out value="${stop.id}"></c:out></td>
												<td><c:out value="${stop.stop_name}"></c:out></td>
												<td><c:out value="${stop.display_name}"></c:out></td>
												<td><c:out value="${stop.latitude}"></c:out></td>
												<td><c:out value="${stop.longitude}"></c:out></td>
												<td><c:out value="${stop.geofence}"></c:out></td>
												<td>
													<c:if test="${stop.enabled  == 'true' }">
														<span class="label label-success">Enabled</span>
													</c:if>
													<c:if test="${stop.enabled  == 'false' }">
														<span class="label label-warning">Disabled</span>
													</c:if>
												</td>
												<td><c:out value="${stop.shortcode}"></c:out></td>
												<td><c:out value="${stop.stop_type}"></c:out></td>												
												<td><c:out value="${stop.help_text}"></c:out></td>
												<td><c:out value="${stop.tags}"></c:out></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->
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
								<option value="stop_name">Stop Name</option>
								 

							</select>
						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter String: <span
							class="required">*(Case Sensitive)</span></label>
						<div class="col-md-6">
							<input type="text" name="search_route" id="search_route_id"
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
				<div class="modal-body">Delete selected stop, are you sure?</div>
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