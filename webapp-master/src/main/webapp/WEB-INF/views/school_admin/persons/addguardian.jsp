<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			 	</script>

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
						<li>Subscribers</li>
						<li>Guardians</li>
						<li>Add Guardian</li>
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
						<h3>Guardian</h3>
						<span>Add Guardian</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
				</div>


				<!-- condition msg for gr no -->
				<c:if test="${error=='studentidExistError'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> <span> This Student ID already Registered, Please try another StudentId</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 



				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Guardian Form
								</h4>
							</div>
							
							
							<c:if test="${error eq 'nameexist'}">
				 <c:out value="${error }"></c:out>
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> <span> This Guardian Name already Registered, Please try another Name</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 
				
				<c:if test="${error eq 'mobileexist'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> <span> This Mobile Number already Registered, Please try another Contact Number</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 
				<c:if test="${error eq 'emailexist'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> <span> This Email already Registered, Please try another EmailAddress</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if>
							
							
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="addguardianaction" method="post">


									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										
											
											<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Guardian Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
														<label class="col-md-3 control-label">First Name<span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="first_name" minlength="1"
																class="form-control required"
																pattern="^\+?[A-Za-z\ ]+\*?$"
																title="Name must be characters with spaces"
																value="${studentEntity.first_name}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Last Name <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="last_name" minlength="1"
																class="form-control required"
																pattern="^\+?[A-Za-z\ ]+\*?$"
																title="Name must be characters with spaces"
																value="${studentEntity.last_name}">
														</div>
													</div>
													 
													<div class="form-group">
														<label class="col-md-3 control-label">Relation with Student<span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="relation" minlength="1"
																 class="form-control required"
																value="${studentEntity.relation}">
														</div>
													</div>
													 
													<!-- div class="form-group">
										<label class="col-md-3 control-label">Gender<span
											class="required">*</span></label>

										
											<div class="col-md-8 clearfix">
												<label class="radio">
													<input type="radio" name="gender" class="required uniform" value="male" >
														 Male
												</label> 
												<label class="radio">
													<input 	type="radio" name="gender" class="uniform" value="female">
														Female
												</label>
												 <label for="gender" class="has-error help-block"
													generated="true" style="display: none;"></label>
											</div>
									</div>		 -->
									
									<div class="form-group">
										<label class="col-md-3 control-label">Mobile
											Number<span class="required">*</span>
										</label>
										<div class="col-md-8 clearfix">
											<input type="text" name="mobile"
												class="form-control required" rangelength="8, 12"
												pattern="^\+?[0-9\-]+\*?$"
												title="phone number must be numbers with symbols(+,-) and range should between 8-12 " value="${studentEntity.mobile_number }">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Guardian Email<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<input type="text" name="email"
												class="form-control required email" value="${studentEntity.email}">
										</div>
									</div>							
												</div>
											
											
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
												<th >
													Email alerts 																										
												</th>
												
											</tr>
											<tr>
												<th class="checkbox-column">
													All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;														
													<input type="checkbox" class="uniform" name="email_all" >
												</th>
												
											</tr>
											
										</thead>
										<tbody>
										
											
											
											<tr>
												<td class="checkbox-column" >
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_noshow" id="checkbox1"> 
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_late" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_irregularities" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;
													&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_regularities" id="checkbox1">
												 </td>
												
											</tr>
										</tbody>
									</table>					
									</div>
									<div class="col-md-6">
								
										<table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
									
										<thead>
											<tr>
												<th>
													Sms alerts																											
												</th>
												
											</tr>
											<tr>
												<th class="checkbox-column">
													All	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;													
													<input type="checkbox" class="uniform" name="sms_all" >
												</th>
												
											</tr>
											
										</thead>
										<tbody>
										
											
											<tr>
												<td class="checkbox-column" >
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_noshow" id="checkbox1"> 
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_late" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_irregularities" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_regularities" id="checkbox1">
												 </td>												
											</tr>
										</tbody>
									</table>					
									</div>
									</div>
							</div>
											<!-- /Student Details  -->
										</div></div>

										 <div class="col-md-6">
											<div class="widget box">
											
											
											
											<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Student Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
														<label class="col-md-3 control-label">Commuter Name<span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="first_name" minlength="1"
																class="form-control required"
																 value="${fn}" readonly="readonly">
														</div>
											
														</div>
														<div class="form-group">
														<label class="col-md-3 control-label">Parent Name<span class="required">*</span></label>
														<div class="col-md-8 clearfix"><input type="text" name="father-name" minlength="1" class="form-control required" value="${father}" readonly="readonly">
																								
														</div>
														
														</div>
														
														
														
														
														
														
														</div></div></div>
									
									<!-- first row completed -->
									
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
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="street"
																class="form-control required" value="${address.street}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">City <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="city"
																class="form-control required" value="${address.city}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">State <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="state"
																class="form-control required" value="${address.state}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Postal <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="postal"
																class="form-control required" pattern="^[a-zA-Z0-9]*$"
												title="postal must be alphanumeric" value="${address.postal}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Select
															Country<span class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="country"
																class="col-md-12 select2 full-width-fix required"
																id="driver_rfid_number" style="border-color: grey;">
																<option selected="selected"><c:out
																		value="${address.country }"></c:out></option>
																<c:forEach items="${countries}" var="countries">
																	<option><c:out value="${countries}"></c:out></option>
																</c:forEach>
															</select> <label for="chosen1" generated="true"
																class="has-error help-block" style="display: none;"></label>
														</div>
													</div>
												</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>
					
						</div>			
									<c:if test="${message== 'fromHome-error' }">

										<div class="alert alert-danger fade in">
											<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
											There are no seats available in from home route: <b>${routeFromHome}</b>
										</div>

									</c:if>
									<c:if test="${message== 'fromSchool-error' }">

										<div class="alert alert-danger fade in">
											<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
											There are no seats available in from school route: <b>${routeFromSchool}</b>
										</div>

									</c:if>
									<c:if test="${message== 'oneSeat-error' }">

										<div class="alert alert-danger fade in">
											<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
											There is only one seat available in route: <b>${routeSame}</b>
										</div>

									</c:if>
									<c:if test="${message== 'bothEmpty-error' }">

										<div class="alert alert-danger fade in">
											<i class="" data-dismiss="alert"></i> <strong>Error!</strong>
											From-Home and From-School both should not be empty
										</div>

									</c:if>
									 
										 
						
					<div class="col-md-5">
						<div class="widget box">
							
						</div>
					</div>				
				

									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">
										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/persons/students">Cancel</a> <input
											type="submit" value="Add"
											class="btn btn-primary pull-right" id="add_new_driver_action">
									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>
					</div>

				</div>

			</div>
</body>
</html>