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
				 <%-- <c:out value="${error }"></c:out>
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
				</c:if>  --%>
				 

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Guardian Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="updateguardianaction" method="post">
									<input type="hidden" value="${students.id }" name="id">
									<input type="hidden" value="${sname.id }" name="sid" >

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
																value="${students.first_name}">
														</div>
													</div>
													
													<div>
										<c:if test="${error=='nameexist'}">
											<div class="alert alert-danger" align="center"
												id="prev_trip1">
												<strong>Error!</strong> This Guardian Name already Registered, Please try another Name
											</div>
										</c:if>
									</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Last Name <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="last_name" minlength="1"
																class="form-control required"
																pattern="^\+?[A-Za-z\ ]+\*?$"
																title="Name must be characters with spaces"
																value="${students.last_name}">
														</div>
													</div>
													 
													<div class="form-group">
														<label class="col-md-3 control-label">Relation with Student<span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="relation" minlength="1"
																 class="form-control required"
																value="${students.relation}">
														</div>
													</div>
												 
									
									<div class="form-group">
										<label class="col-md-3 control-label">Mobile
											Number<span class="required">*</span>
										</label>
										<div class="col-md-8 clearfix">
											<input type="text" name="mobile"
												class="form-control required" rangelength="8, 12"
												pattern="^\+?[0-9\-]+\*?$"
												title="phone number must be numbers with symbols(+,-) and range should between 8-12 " value="${students.mobile_number }">
										</div>
									</div>
									<c:if test="${error=='mobileexist'}">
											<div class="alert alert-danger" align="center"
												id="prev_trip1">
												<strong>Error!</strong> This Guardian MobileNumber already Registered, Please try another Contact Number
											</div>
										</c:if>
									<div class="form-group">
										<label class="col-md-3 control-label">Guardian Email<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<input type="text" name="email"
												class="form-control required email" value="${students.email}">
										</div>
									</div>	
									<c:if test="${error=='emailexist'}">
											<div class="alert alert-danger" align="center"
												id="prev_trip1">
												<strong>Error!</strong> This Guardian Email already Registered, Please try another EmailAddress
											</div>
										</c:if>						
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
													<c:choose>
													
														<c:when test="${email.all_alerts=='on' }">
															<input type="checkbox" class="uniform" name="email_all" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="email_all">
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
													
														<c:when test="${email.no_show=='on' }">
															<input type="checkbox" class="uniform" name="email_noshow" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="email_noshow">
													</c:otherwise>
													
													</c:choose>		
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
													
														<c:when test="${email.late=='on' }">
															<input type="checkbox" class="uniform" name="email_late" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="email_late">
													</c:otherwise>
													
													</c:choose>		
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;
													<c:choose>
													
														<c:when test="${email.irregularities=='on' }">
															<input type="checkbox" class="uniform" name="email_irregularities" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="email_irregularities">
													</c:otherwise>
													
													</c:choose>		
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;
													&nbsp;&nbsp;&nbsp;
													<c:choose>
													
														<c:when test="${email.regularities=='on' }">
															<input type="checkbox" class="uniform" name="email_regularities" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="email_regularities">
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
												<th>
													Sms alerts																											
												</th>
												
											</tr>
											<tr>
												<th class="checkbox-column">
													All	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;													
													<c:choose>
													<c:when test="${sms.all_alerts=='on' }">
															<input type="checkbox" class="uniform" name="sms_all" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="sms_all">
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
													<c:when test="${sms.no_show=='on' }">
															<input type="checkbox" class="uniform" name="sms_noshow" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="sms_noshow">
													</c:otherwise>
													
													</c:choose>	
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
													
														<c:when test="${sms.late=='on' }">
															<input type="checkbox" class="uniform" name="sms_late" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="sms_late">
													</c:otherwise>
													
													</c:choose>		
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<c:choose>
													
														<c:when test="${sms.irregularities=='on' }">
															<input type="checkbox" class="uniform" name="sms_irregularities" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="sms_irregularities">
													</c:otherwise>
													
													</c:choose>		
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
													
														<c:when test="${sms.regularities=='on' }">
															<input type="checkbox" class="uniform" name="sms_regularities" checked="checked">
														</c:when>
													<c:otherwise>
														<input type="checkbox" class="uniform" name="sms_regularities">
													</c:otherwise>
													
													</c:choose>		
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
																 value="${sname.getFirst_name()} &nbsp; ${sname.getLast_name()}" readonly="readonly">
														</div>
											
														</div>
														<div class="form-group">
														<label class="col-md-3 control-label">Parent Name<span class="required">*</span></label>
														<div class="col-md-8 clearfix"><input type="text" name="father-name" minlength="1" class="form-control required" value="${fname}" readonly="readonly">
																								
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
									<%-- <c:if test="${error=='nameexist'}">
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
				
				<c:if test="${error=='mobileexist'}">
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
				<c:if test="${error=='emailexist'}">
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
 --%>
									 
										 
						
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
											href="/sts/school_admin/persons/students/viewguardian?id=${sname.id }">Cancel</a> <input
											type="submit" value="Update"
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