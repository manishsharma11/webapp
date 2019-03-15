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
						<li>Students</li>
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
						<h3>Students</h3>
						<span>Create,Update,Delete Students</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Students</div>
								<div class="value">
									<c:out value="${students.size()}"></c:out>
								</div>




							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>
				

				<!--=== Normal ===-->
				<!-- <div class="col-md-12">
					<a href="students/add" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add Student</span>
					</a>
				</div> -->
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Students
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

											<th>Commuter Name</th>
											<th>RFID Number</th>
											<th>Bus</th>
											<th>Route Name</th>
											<th>Stop Name</th>
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody>
										
										<c:forEach items="${students }" var="student">
										<tr>
											<td>
												<a href="viewstudent?id=<c:out value="${student.id }"></c:out>">
													<c:out value="${student.student_name }"></c:out>
												</a>
												
											</td>
											<td><c:out value="${student.rfid_number }"></c:out></td>
											<td><c:out value="${student.bus_licence }"></c:out></td>
											<td><c:out value="${student.route_name }"></c:out></td>
											<td><c:out value="${student.stop_name }"></c:out></td>
											
											<%-- <td>
												<a class="btn btn-sm btn-info" href="students/updatestudent?id=<c:out value="${student.id}" />"><i class=" icon-pencil"></i>&nbsp;Update </a> 
												<a class="btn btn-sm btn-danger" href="students/removestudent?id=<c:out value="${student.id}" />"><i class=" icon-trash"></i>&nbsp;Remove</a>
												
											</td> --%>
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
											marked *
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
					<!-- <div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
															<button type="button" class="btn btn-primary" id="button_add">Add</button>
														</div> -->
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>

</body>
</html>