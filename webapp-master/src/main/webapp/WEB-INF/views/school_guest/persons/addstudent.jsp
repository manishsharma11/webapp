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
						<c:if test="${message=='error'}" var="message">
							<div class="alert alert-danger fade in">
								<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
								Sorry you can not add student.Bus capacity exceeding....
							</div>
						</c:if>

					</div>
					<div></div>
					<br>
					<div class="col-sm-4 col-md-1"></div>

				</div>


				<div class="row" id="driver_form_add">
																<!--=== Validation Example 1 ===-->
																<div class="col-md-7">
																	<div class="widget box">
																		<div class="widget-header">
																			<h4><i class="icon-reorder"></i>Student Form</h4>
																		</div>
																		<div class="widget-content">
																			<form class="form-horizontal row-border" id="validate-4" action="addstudentaction" method="post">
																				
									<div class="form-group">											
										<label class="col-md-3 control-label">Select Bus<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="route_id" 
												class="col-md-12 select2 full-width-fix required"
												id="route_id" style="border-color: grey;">
												<option></option>
												<c:forEach items="${buses }" var="bus" >

													<option value="<c:out value="${bus.route_id }"></c:out>"><c:out
															value="${bus.bus_licence_number }" ></c:out></option>

												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>

										<span style="color: sienna; display: none;" id="loading">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											Fetching stops list from database please wait... </span> <label
											class="col-md-3 control-label">Select Stop<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="stop_id"
												class="col-md-12 select2 full-width-fix required"
												id="stop_id" style="border-color: grey;">
												<option></option>

											</select> <label for="chosen2" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Commuter Name <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="student_name" minlength="3" class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$" title="Name must be characters with spaces minimum 3 characters" >
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Commuter ID <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="student_number" class="form-control required " >
																					</div>
																				</div>
																				<div class="form-group">
																				<label class="col-md-3 control-label">Select RFID Number <span class="required">*</span></label>
																					<div class="col-md-9 clearfix">
																						<select name="rfid_number" class="col-md-12 select2 full-width-fix required">
																							<option></option>
																							<c:forEach items="${rfids }" var="rfid">
																								
																								<option value="<c:out value="${rfid.rfid_number }"></c:out>"><c:out value="${rfid.rfid_number }"></c:out></option>
																							</c:forEach>
																						</select>
																						<label for="chosen1" generated="true" class="has-error help-block" style="display:none;"></label>
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Gender<span class="required">*</span></label>
																					<div class="col-md-9">
																						<label class="radio"><input type="radio" name="gender" class="required uniform" value="male"> Male</label>
																						<label class="radio"><input type="radio" name="gender" class="uniform"  value="female"> Female</label>
																						<label for="gender" class="has-error help-block" generated="true" style="display:none;"></label>
																					</div>
																				</div>
																				<!-- <div class="form-group">
																					<label class="col-md-3 control-label">Teacher Name <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="teacher_name" class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$" title="Name must be characters with spaces">
																					</div>
																				</div> -->
																				
									<div class="form-group">
										<label class="col-md-3 control-label">Select Teacher <span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="teacher_name"
												class="col-md-12 select2 full-width-fix required" style="border-color: grey;">
												<option></option>
												<c:forEach items="${staffList }" var="staffList">
														
													<option><c:out value="${staffList.staff_name}"></c:out></option>
														
												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
																				
																				
																				<div class="form-group">
																					<label class="col-md-3 control-label">Teacher ID <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="teacher_id" class="form-control required">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Home Room Assignment <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="homeroom_assignment" class="form-control required">
																					</div>
																				</div>
																				<!-- <div class="form-group">
																					<label class="col-md-6 control-label">Emergency Contact Details<span class="required">*</span></label>
																					
																				</div> -->
									<div class="form-group">
										<font color="sienna">
											<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEmergency Contact Details</p></b>
										</font>
									</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Person Name<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="e_name" class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$" title="Name must be characters with spaces">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Person Phone Number<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="e_number" class="form-control required" rangelength="8, 12" pattern="^\+?[0-9\-]+\*?$" title="phone number must be numbers with symbols(+,-) and range should between 8-12 ">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Person Email<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="e_mail" class="form-control required email">
																					</div>
																				</div>
																				
									<div class="form-group">
										<font color="sienna">
											<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSecondary Contact</p></b>
										</font>
									</div>
																				
																				
																				<div class="form-group">
																					<label class="col-md-3 control-label">Person Name<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="secondary_e_name" class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$" title="Name must be characters with spaces">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Person Phone Number<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="secondary_e_number" class="form-control required" rangelength="8, 12" pattern="^\+?[0-9\-]+\*?$" title="phone number must be numbers with symbols(+,-) and range should between 8-12 ">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Person Email<span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="secondary_e_mail" class="form-control required email">
																					</div>
																				</div>
																				
																				<!-- <div class="form-group">
																					<label class="col-md-3 control-label">Home Address<span class="required">*</span></label>
																					<div class="col-md-9">
																						<textarea name="home_address" class="form-control required"></textarea>
																					</div>
																				</div> -->
									<div class="form-group">
										<font color="sienna">
											<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAddress</p></b>
										</font>
									</div>

																				<div class="form-group">
																					<label class="col-md-3 control-label">Street <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="street" class="form-control required">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">City <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="city" class="form-control required">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">State <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="state" class="form-control required">
																					</div>
																				</div>
																				<div class="form-group">
																					<label class="col-md-3 control-label">Zip <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="zip" class="form-control required">
																					</div>
																				</div>
																				<!-- <div class="form-group">
																					<label class="col-md-3 control-label">Country <span class="required">*</span></label>
																					<div class="col-md-9">
																						<input type="text" name="country" class="form-control required">
																					</div>
																				</div> -->
									

																					<div class="form-group">
																					<label class="col-md-3 control-label">Select Country<span class="required">*</span></label>
																					<div class="col-md-9 clearfix">
																						<select name="country" class="col-md-12 select2 full-width-fix required" id="driver_rfid_number" style="border-color:grey;">
																							<option></option>
																							<c:forEach items="${countries}" var="countries">
																							
																								<option><c:out value="${countries}"></c:out></option>
																							
																							</c:forEach>
																						</select>
																						<label for="chosen1" generated="true" class="has-error help-block" style="display:none;"></label>
																					</div>
																				</div>
																				
																				
																				
																				<div class="form-group">
																					<label class="col-md-3 control-label">Select Alerts</span></label>
																					<div class="col-md-9">
																						<label class="checkbox"><input type="checkbox" name="ch1" class="uniform"> send text if emergency condition exists</label>
																						<label class="checkbox"><input type="checkbox" name="ch2"  class="uniform"> send text  if child does not arrive at school</label>
																						<label class="checkbox"><input type="checkbox" name="ch3"  class="uniform"> send text  if bus is late</label>
																						<label for="sport" class="has-error help-block" generated="true" style="display:none;"></label>
																					</div>
																					
																					
																				</div>
																				<div class="alert alert-danger fade in" id="input_errors" style="display: none;">
																		
																					<strong>Error!</strong> Please fill all the fields which are marked *
																				</div>
																				<div class="form-actions">
																					<a type="button" class="btn btn-default" href="/sts/school_admin/persons/students">Cancel</a>
																					<input type="submit" value="Add" class="btn btn-primary pull-right" id="add_new_driver_action">
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