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
		
	  $(document).ready(function(){
		  
		 
		  $("#route_id").change(function(){
				$("#loading").show();
		        $.ajax({
		        	
		        	url:"getAllStopsByBusId",
		        	type:"POST",
		        	data:"route_id="+$(this).val(),
		        	success:function(result){
		        		//alert(result);
		        		var obj = JSON.parse( result );
		        		$("#stop_id").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			data=data+"<option value="+obj[i].id+">"+obj[i].stop_name+"</option>";
		        		}
		        		$("#stop_id").append(data);
		        		$("#loading").hide();
		        	}
		        });
		    });
		  
		  
	  });
	  
	  $(document).ready(function() {
		    $('#selec_all').click(function(event) {  //on click 
		        if(this.checked) { // check select status
		            $('.checkbox1').each(function() { //loop through each checkbox
		                this.checked = true;  //select all checkboxes with class "checkbox1"               
		            });
		        }else{
		            $('.checkbox1').each(function() { //loop through each checkbox
		                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
		            });         
		        }
		    });
		    
		});
			
	</script>


<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
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
						<li>Add New Student</li>
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
						<h3>Student</h3>
						<span>Add Student</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Student Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-499" action="addstudentaction" method="post">


									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Student Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
														<label class="col-md-3 control-label">First Name <span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${student.first_name }"></c:out>
														</div>														
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Last Name <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${student.last_name }"></c:out>
														</div>														
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Student GR Number <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${student.gr_number }"></c:out>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-3 control-label">Select RFID
															Number <span class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${student.rfid_number }"></c:out>
														</div>														
													</div>
													<div class="form-group">
										<label class="col-md-3 control-label">Gender<span
											class="required">:</span>
										</label>

										<c:if test="${student.gender=='male' }">
											<div class="col-md-9" style="color: sienna">
													<c:out value="${student.gender }"></c:out>
											</div>
										</c:if>
										<c:if test="${student.gender=='female' }">
											<div class="col-md-9" style="color: sienna">
													<c:out value="${student.gender }"></c:out>
											</div>
										</c:if>
									</div>	
									
									<div class="form-group">
														<label class="col-md-3 control-label">Grade <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${student.student_grade }"></c:out>
														</div>														
													</div>
																	
												</div>
											</div>
											<!-- /Student Details  -->
										</div>

										<!--=== Validation Example 2 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Parent Contact Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
										<label class="col-md-3 control-label">First Name<span
											class="required">:</span></label>
											<div class="col-md-9" style="color: sienna">
													<c:out value="${parent.first_name }"></c:out>
											</div>
										
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Last Name<span
											class="required">:</span></label>
											<div class="col-md-9" style="color: sienna">
													<c:out value="${parent.last_name }"></c:out>
											</div>									
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Mobile
											Number<span class="required">:</span>
										</label>
										<div class="col-md-9" style="color: sienna">
													<c:out value="${parent.mobile }"></c:out>
											</div>										
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Parent Email<span
											class="required">:</span></label>
										<div class="col-md-9" style="color: sienna">
													<c:out value="${parent.email }"></c:out>
											</div>										
									</div>

												
												</div>
											</div>
										</div>
										<!-- /Parent Contact Details -->
									</div>
									<!-- first row completed -->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> From Home
													</h4>
												</div>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-3 control-label">Bus<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${homeBus.bus_licence_number}"></c:out>
														</div>

													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Route<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${homeRoute.route_name }"></c:out>
														</div>
													
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Trip<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${homeTrip.trip_name }"></c:out>
														</div>
													
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Stop<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${homeStop.stop_name }"></c:out>
														</div>

													</div>
																									
												</div>
											</div>
											<!-- /From Home -->
										</div>

										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> From School
													</h4>
												</div>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-3 control-label">Bus<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${schoolBus.bus_licence_number }"></c:out>
														</div>

													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Route<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${schoolRoute.route_name}"></c:out>
														</div>
													
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Trip<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${schoolTrip.trip_name }"></c:out>
														</div>
													
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Stop<span
															class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${schoolStop.stop_name}"></c:out>
														</div>

													</div>
																									
												</div>
											</div>
											<!-- /From Home -->
										</div>
										<!-- /Validation Example 2 -->
									</div>
					<div class="row">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4><i class="icon-reorder"></i> Address </h4>
							</div>
							<%-- --%>
							<div class="widget-content">
													<div class="form-group">
														<label class="col-md-3 control-label">Street <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${address.street }"></c:out>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">City <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${address.city }"></c:out>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">State <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${address.state }"></c:out>
														</div>														
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Postal <span
															class="required">:</span></label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${address.postal }"></c:out>
														</div>														
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Select
															Country<span class="required">:</span>
														</label>
														<div class="col-md-9" style="color: sienna">
															<c:out value="${address.country }"></c:out>
														</div>														
													</div>
												</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>		
					
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4><i class="icon-reorder"></i> Alerts </h4>
							</div>
							<%-- --%>
							<div class="widget-content">
								
								<div class="row">
										
									<div class="col-md-6">
								
										<table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
									
										<thead>
											<tr>
												<th class="checkbox-column">
													Email alerts													
												</th>
												
												
											</tr>
											
											<tr>
												<th class="checkbox-column">
													All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													
													<c:choose>
												        <c:when test="${emailAlert.all_alerts == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
													
												</th>
												
												
											</tr>
											
										</thead>
										<tbody>
										
											
											<tr>
												<td class="checkbox-column" >
													
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${emailAlert.no_show == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose> 
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${emailAlert.late == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${emailAlert.irregularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;										
													<c:choose>
												        <c:when test="${emailAlert.regularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_regularities" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_regularities"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
										</tbody>
									</table>					
									</div>
									<div class="col-md-6">
								
										<table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
									
										<thead>
											<tr>
												<th class="checkbox-column">
													Sms alerts													
												</th>
												
											</tr>
											<tr>
												<th class="checkbox-column">
													All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														
													<c:choose>
												        <c:when test="${smsAlert.all_alerts == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												</th>
												
											</tr>
											
										</thead>
										<tbody>
										
											
											<tr>
												<td class="checkbox-column" >
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${smsAlert.no_show == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${smsAlert.late == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${smsAlert.irregularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;												
													<c:choose>
												        <c:when test="${smsAlert.regularities == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_regularities" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_regularities"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
										</tbody>
									</table>					
									</div>
									</div>
							</div>
						</div>
					</div>			
				</div>

									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">
										<strong>Error!</strong> Please fill all the fields which are
										Marked :
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/<%=request.getContextPath()%>/school_admin/persons/students">Back</a> 
									</div>									
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>


				</div>

			</div>
</body>
</html>