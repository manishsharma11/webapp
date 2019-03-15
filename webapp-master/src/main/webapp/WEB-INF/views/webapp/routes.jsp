<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Routes</title>


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
						<h3>Routes</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Routes
								</h4>
							</div>
							
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover datatable">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Routes</th>
										</tr>
									</thead>
									
									<tbody>
										  <c:forEach items="${routeStops}" var="routeStop" varStatus="index">
											<tr class="context-menu-two box menu-1" id="${index.index+1}">
												<td><c:out value="${index.index+1}"></c:out></td>
												<td>
													<div class="container">
													  
													  <div class="panel-group">
													    <div class="panel panel-default">
													      <div class="panel-heading">
													        <h4 class="panel-title">
													          <a data-toggle="collapse" href="#collapse<c:out value="${index.index+1}"></c:out>"><c:out value="${routeStop.route_name}"></c:out>
													          <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span></a>
													        </h4>
													      </div>
													      
														      <div id="collapse<c:out value="${index.index+1}"></c:out>" class="panel-collapse collapse"><br>
														       <c:forEach items="${routeStop.stops}" var="stop">
														        <ul>
														          <li><c:out value="${stop.stop_name}"></c:out></li>
														      	</ul>
														         </c:forEach>
														      </div>
														  
													    </div>
													  </div>
													</div>
																		
													</td>
											</tr>
										  </c:forEach>
									</tbody>
								</table>
							</div>
							
						</div>
						<!-- /Validation Example 1 -->
					</div>
				</div>
			</div>

		</div>
	</div>
	
</body>
</html>
