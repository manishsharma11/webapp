<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>My Wallet</title>



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
						<h3>My Wallet</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
				
				
				<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Available Credits</div>
								<div class="value">
									<c:out value="${acc.points_balance}"></c:out>
								</div>

							</div>

						</div>
						<!-- /.smallstat -->
					</div>
					
					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Available Free Rides</div>
								<div class="value">
									<c:out value="${acc.free_rides_balance}"></c:out>
								</div>

							</div>

						</div>
						<!-- /.smallstat -->
					</div>
					
					</div>
					<c:if test="${status == 'NOT_ENOUGH_BALANCE'}">
					<div class="panel-heading">
						<h3><i class="icon-warning-sign" style="color:red"></i>
							Booking Failed
						</h3>
						<span>Not Enough Credits Please Recharge Your Account</span>
					</div>
				</c:if>
					
					
				<div class="row">
					<div class="col-md-12">
						<div class="widget-content">
						<font color="red">*Scroll the Table.</font>
							<div class="table-responsive">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Recharge Amount</th>										
										<th>Credits</th>
										<th>Recharge Now</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="recharge_option"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${recharge_option.recharge_amount}"></c:out></td>											
											<td><c:out value="${recharge_option.num_credits_offered}"></c:out></td>
									   		<td>
									   		<form action="<%=appName%>/webapp/transaction/payUMoney" method="post">
									   			<input type="hidden" name="amount_selected" value="${recharge_option.recharge_amount}">
									   			<input type="hidden" name="recharge_options_id" value="${recharge_option.id}">
									   			
									   			<label for="submit${recharge_option.id}" class="btn btn-success">Proceed to Pay <i class="icon-angle-right"></i></label>
										 		<input id="submit${recharge_option.id}" type="submit" value="Proceed to Pay" class="hidden" />	
									   		</form>
									   		</td>
									   </tr>

									</c:forEach>

								</tbody>
							</table>
						</div>
						</div>

					</div>
				</div>
				
					<div  class="col-md-3 col-md-offset-1">
						<a href="<%=appName%>/webapp/transaction/my_transactions"><button type="button" class="btn btn-success">Transaction History <span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span></button></a>
					</div>
				
			</div>

		</div>
	</div>
	
</body>
</html>
