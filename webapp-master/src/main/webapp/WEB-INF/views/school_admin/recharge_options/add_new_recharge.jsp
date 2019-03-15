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
						<li>Recharge Options List</li>

						<li>Add New Recharge</li>
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
						<h3>Recharge Options</h3>
						<span>Add Recharge</span>
					</div>
					
				</div>
				<c:if test="${message== 'recharge_exists' }">
				
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> This Recharge Option already Available.
					</div>
				
				</c:if>
				
				<div class="row" id="recharge_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Add New Recharge Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="add_recharge_action" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Select Driver<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											 </div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Select Route<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="route_name_id"
												class="col-md-12 select2 full-width-fix required"
												id="driver_id_name" style="border-color: grey;">
												<option></option>
												<c:forEach items="${routes }" var="route">
														<c:if test="${route.bus_licence_number eq 'none'}">
													<option value="<c:out value="${route.route_name}"></c:out>:<c:out value="${route.id}"></c:out>"><c:out value="${route.route_name}"></c:out></option>
														</c:if>
												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div> --%>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Recharge ID<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="id"  value="${rechargeOption.id}" class="form-control required" pattern="^\+?[0-9]+\*?$" id="id" title="Recharge ID Must be a valid Number without spaces">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Recharge Amount<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="recharge_amount" value="${rechargeOption.recharge_amount }" 	class="form-control required"  pattern="^\+?[0-9]+\*?$"  title="Recharge Amount Must be a valid Number without spaces">
										</div>
									</div>
									 
									<div class="form-group">
										<label class="col-md-3 control-label">Number of Credits Offered<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="num_credits_offered"  value="${rechargeOption.num_credits_offered }" class="form-control required" pattern="^\+?[0-9]+\*?$" title="Number of Credits Offered Must be a valid Number without spaces">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Enable<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="checkbox" name="enable" value="${rechargeOption.enabled }">
										</div>
									</div>
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/recharge_options/list">Cancel</a> <input
											type="submit" value="Add Recharge Option"
											class="btn btn-primary pull-right" id="add_new_recharge_action">
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