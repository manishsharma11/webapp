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
						<li>Feedback</li>
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
						<h3>Feedback</h3>
						<span></span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Feedbacks</div>
								<div class="value">
									<c:out value="${recordsCount}"></c:out>
								</div>




							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>

				<div class="row">
					<div class="col-md-12">

						<div class="widget-content">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>#</th>
										<th>ID</th>
										<th>Commuter ID</th>
										<th>Rating Points</th>										
										<th>Reason</th>
										<th>Concern</th>
										<th>Trip ID</th>
										<th>Booking ID</th>										
										<th>Created At</th>													
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${feedbackList}" var="feedback"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${feedback.feedback_id}"></c:out></td>
											<td><c:out value="${feedback.commuter_id}"></c:out></td>											
											<td><c:out value="${feedback.rating_points}"></c:out></td>
											<td><c:out value="${feedback.reason}"></c:out></td>
											<td><c:out value="${feedback.concern}"></c:out></td>
											<td><c:out value="${feedback.trip_id}"></c:out></td>
											<td><c:out value="${feedback.booking_id}"></c:out></td>											
										    <td><c:out value="${feedback.created_at}"></c:out></td>																					
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
														<li class="active"><a href="list?offset=&limit=">${i }</a></li>
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