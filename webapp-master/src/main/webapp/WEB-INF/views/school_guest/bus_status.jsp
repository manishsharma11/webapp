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
<script type="text/javascript"
	src="<c:url value="/resources/project_js_file/bus_status.js" />"></script>
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<script type="text/javascript">

function openWindow(){
		
	window.open('bus', 'popup_name','height=' + 600 + 
			',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
}

</script>
</head>

<body>
	<br>
	<input type="hidden" id="bus_id" value="<c:out value="${bus_id }" ></c:out> "> 
	<div id="container">
		<div class="widget-content">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th id="bus_lice_number">
							
						</th>
						<th id="bus_status">
							
						</th>
						
						<th id="bus_current_stop">
							
						</th>
						<th id="bus_current_arrived">
							
						</th>
					</tr>
				</thead>
				
			</table>
		</div>

		<!--=== Normal ===-->
		<div class="row">
			<div class="col-md-12">
				<div class="widget box">
					<div class="widget-header">
						<h4>
							<i class="icon-reorder"></i>Bus :&nbsp;&nbsp;&nbsp;&nbsp;
						</h4>
					</div>
					<div class="widget-content">
						<table
							class="table table-striped table-bordered table-hover table-checkable"
							id="output">
							<thead>
										<tr>    
										    <th>S.No</th>											
											<th>Commuter Name</th>										
											<th colspan="4">	
												<c:if test="${session_to == 'morning' }">
													Towards School
												</c:if>
												<c:if test="${session_to == 'evening' }">
													Towards Home
												</c:if>
											</th>			
										</tr>
										<tr>
												<th></th>
												<th></th>	
												<th>Board Stop</th>
												<th>Board Time</th>
												<th>Exit Stop</th>
												<th>Exit Time</th>
										</tr>
									</thead>							
									<tbody id="tbody_students">
									</tbody>
						</table>
					</div>
					<br></br>
					<div align="center" id="loading" style="display: none;">
											
							<img alt="loading_image" src="<c:url value="/resources/loading_icon.gif" />">&nbsp;
							<font color="sienna" size="3">please wait,fetching  information from database...</font>
					</div>	
				</div>
			</div>
		</div>
		<!-- /Normal -->
		<input type="hidden" value="<c:out value="${session }"></c:out>" id="session">
	</div>
</body>
</html>
