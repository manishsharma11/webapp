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
															 <c:out value="${studentEntity.first_name}"></c:out>
																 
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Last Name <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															 
																<c:out value="${studentEntity.last_name}"></c:out>
														</div>
													</div>
													 
													<div class="form-group">
														<label class="col-md-3 control-label">Relation with Student<span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<c:out value="${studentEntity.relation}"></c:out>
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
											<c:out value="${studentEntity.mobile_number}"></c:out>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Guardian Email<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<c:out value="${studentEntity.email}"></c:out>
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
															<c:out value="${sname}"></c:out>
														</div>
											
														</div>
														<div class="form-group">
														<label class="col-md-3 control-label">Parent Name<span class="required">*</span></label>
														<div class="col-md-8 clearfix"><c:out value="${father}"></c:out>
																								
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
															<c:out value="${address.street}"></c:out>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">City <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<c:out value="${address.city}"></c:out>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">State <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<c:out value="${address.state}"></c:out>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Postal <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<c:out value="${address.postal}"></c:out>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">
															Country<span class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<c:out value="${address.country}"></c:out> <label for="chosen1" generated="true"
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
										<a type="button" class="btn btn-info"
											href="/sts/school_admin/persons/students/viewguardian?id=${student.id}&fn=${student.first_name } ${student.last_name }">Back</a>  
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