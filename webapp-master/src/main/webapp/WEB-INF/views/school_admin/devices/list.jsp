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

<script type="text/javascript" class="showcase">
$(function(){
    $.contextMenu({
        selector: '.context-menu-two', 
        callback: function(key, options) {
        	 var va=$(this).find(' input[type="hidden"] ').val();
         if(key=="update"){        	
             window.location="update?id="+va;
         }
         else if(key == "delete"){
        	 window.location="delete?id="+va;
         }
        },
        items: {
            "update": {name: "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>", icon: "update"},  
            "delete": {name: "<i class='icon-trash'>&nbsp;&nbsp;&nbsp;Delete</i><br>", icon: "delete"},
        }
    });
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    })
});
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
						<li>Devices</li>
						<li>List</li>
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
						<h3>Devices List</h3>
						<span>Create,Update,Delete</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Devices</div>
								<div class="value">
									<c:out value="${recordsCount}"></c:out>
								</div>




							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>

				<div class="col-md-8">
						<a href="add" class="btn btn-primary"> <i class="icon-plus"></i>
							<span>Add Device</span>
						</a>
						
				</div>
				<br></br>

				<div class="row">
					<div class="col-md-10">



						<div class="widget-content">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>#</th>
										<th>Device ID</th>
										<th>Vehicle ID</th>
										<th>Vehicle Number</th>										
										<th>Hardware Device ID</th>
										<th>Created At</th>
										<th>Updated At</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${devices }" var="device"
										varStatus="index">

										<tr class="context-menu-two box menu-1" data-toggle="collapse">

											<td>
												<input type="hidden" value="${device.ec_device_id}" id="busIdHidden" />
												<c:out value="${index.index+1}"></c:out>
											</td>
											<td><c:out value="${device.ec_device_id}"></c:out></td>
											<td><c:out value="${device.vehicle_id}"></c:out></td>
											<td><c:out value="${device.vehicle_number}"></c:out></td>
											<td><c:out value="${device.hw_device_id}"></c:out></td>
											<td><c:out value="${device.created_at}"></c:out></td>
											<td><c:out value="${device.updated_at}"></c:out></td>
											
											
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
														<li class="active">${i }</li>
													</c:when>
													<c:otherwise>
														<li><a
															href="list?offset=${((i -1) * recordsPerPage) +1}&limit=${i *recordsPerPage} ">${i }</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							<br>
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