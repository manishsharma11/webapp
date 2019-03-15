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
						
						<li>Buses</li>
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
						<h3>Buses</h3>
						<span>Create,Update,Delete Buses</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Buses</div>
								<div class="value">
									<c:out value="${buses.size()}"></c:out>
								</div>
							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>
				<!--=== Normal ===-->
				<!-- <div class="col-md-12">
					<a href="add" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add Bus</span>
					</a>
				</div> -->
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Buses List
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

											<th>Bus Id</th>
											<th>Route Name</th>
											<th>Bus Make and Model</th>
											<th>Bus Capacity</th>
											<th>Alloted</th>
											<th>Driver Name</th>
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${buses }" var="bus">
											 <tr>
												<td>
													
														<c:out value="${bus.bus_licence_number}"></c:out>
												
													
												</td>
												<td>
													<a href="/sts/school_guest/stop/stops?route_id=<c:out value="${bus.route_id}"></c:out>">
														<b><c:out value="${bus.route_name}"></c:out></b>
													</a>
														
												
													
												</td>
												<td><c:out value="${bus.bus_make_model}"></c:out></td>
												<td><c:out value="${bus.bus_capacity}"></c:out></td>
												<td>
												<a href="/sts/school_guest/persons/busStudents?bus_licence_number=<c:out value="${bus.bus_licence_number}"></c:out>">
												<c:out value="${bus.noOfStudents}"></c:out>
												</a>
												</td>
												<td><c:out value="${bus.driver_name}"></c:out></td>
												<%-- <td>
													<a  href="updatebus?id=<c:out value="${bus.id}"></c:out>" class="btn btn-sm btn-info"  > <i class=" icon-pencil"></i>&nbsp;Update</a>
													<a  href="removebus?id=<c:out value="${bus.id}"></c:out>"  class="btn btn-sm btn-danger" ><i class=" icon-trash"></i>&nbsp;Remove</a>
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
</body>
</html>