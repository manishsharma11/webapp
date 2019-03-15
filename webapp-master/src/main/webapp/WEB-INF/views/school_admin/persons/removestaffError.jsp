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
	
	 $("#bus_id").change(function(){
			$("#loading").show();
	        $.ajax({
	        	
	        	url:"getAllStopsByBusId",
	        	type:"POST",
	        	data:"bus_id="+$(this).val(),
	        	success:function(result){
	        		
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
						<li>Subscribers</li>
						<li>Staff</li>
						<li>Delete Staff</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				<c:if test="${error=='allocated'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
						Sorry you can not delete staff because staff allocated to <b><c:out
								value="${name}" /></b> student
					</div>
				</c:if>
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Staff</h3>
						<span>Delete Staff</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Staff Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="deletestaffaction" method="post">
									
									<input type="hidden" name="staff_rfid" value="<c:out value="${staff.staff_rfid }"></c:out>">
									<input type="hidden" name="id" value="<c:out value="${staff.id }"></c:out>">
									<input type="hidden" name="bus_licence_number" value="<c:out value="${staff.bus_licence_number }"></c:out>">
										
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">RFID Number :<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.staff_rfid }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Current Bus :<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.bus_licence_number }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Current Stop Name:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.stop_name }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Staff ID:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.staff_id }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Staff Name:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.staff_name }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Staff Mobile:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.staff_mobile }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Staff E-mail:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.staff_email }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Street:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.street }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">City:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.city }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">State:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.state }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Zip:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.zip }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Country:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<label class="control-label" style="color: sienna"> <c:out
													value="${staff.country }"></c:out>
											</label> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									
									<%-- <div class="form-group">
										<label class="col-md-5 ">Address:
										<span class="required"><c:out value="${staff.address }"></c:out></span></label>
									</div> --%>

									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/persons/staff">Back</a> 
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											
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