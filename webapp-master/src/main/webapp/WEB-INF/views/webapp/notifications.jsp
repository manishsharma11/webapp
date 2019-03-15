<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Notifications</title>


<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>



</head>
<body>

	<!-- Include top navigation -->
	<%@include file="jsp-includes/top_navigation.jsp"%>
	
	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">

				<!-- Left Navigation -->
				<%@include file="jsp-includes/left_navigation.jsp"%>

			</div>
			<div id="divider" class="resizeable"></div>

		</div>
		<!-- /Sidebar -->

		<div id="content">
			<div class="container">
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Notifications</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
							
			 <div class="row">
					<div class="col-md-12">
						<div class="widget-content">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head table-responsive">
								<thead class="thead-inverse">
									<tr>
										<th>S.No</th>
										<th>Notification</th>										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${notifications}" var="notification"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td>
												<label><c:out value="${notification.title}"></c:out></label>
												<div class="well well-sm">
												<c:out value="${notification.message}"></c:out>
												</div>
												 At <fmt:formatDate type="both" value="${notification.created_at}" />
											</td>
									   </tr>

									</c:forEach>

								</tbody>
							</table>
						
						</div>

					</div>
				
				</div>
			</div>

		</div>
	</div>
	
</body>
</html>
