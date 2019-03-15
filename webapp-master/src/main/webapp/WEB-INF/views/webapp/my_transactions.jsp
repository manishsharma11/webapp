<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lan="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>My Transactions</title>


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
						<h3>My Transactions</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>


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
										<th>Transaction Action</th>										
<!-- 									<th>Transaction Status</th> -->
										<th>Transaction Type</th>
										<th>Credits Added</th>
										<th>Credits Deducted</th>
										<th>Credits Balance</th>				
										<th>Free Rides Added</th>
										<th>Free Rides Deducted</th>
										<th>Free Rides Balance</th>
										<th>Transaction Time</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${transactionsList}" var="transaction"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${transaction.transaction_action}"></c:out></td>											
<!-- 											<td> -->
<%-- 												<c:if test="${transaction.transaction_status == '1' }"> --%>
<!-- 													<span class="label label-success">Success</span> -->
<%-- 												</c:if>  --%>
<%-- 												<c:if test="${transaction.transaction_status == '2' }"> --%>
<!-- 													<span class="label label-warning">Failed</span> -->
<%-- 												</c:if> --%>
<!-- 											</td> -->
											<td>
												<c:if test="${transaction.transaction_type == '1' }">
													<span class="label label-success">Credit</span>
												</c:if> 
												<c:if test="${transaction.transaction_type == '2' }">
													<h5><span class="label label-warning">Debit</span></h5>
												</c:if>
											</td>
											<td><c:out value="${transaction.points_added}"></c:out></td>
											<td><c:out value="${transaction.points_deducted}"></c:out></td>
											<td><c:out value="${transaction.points_balance}"></c:out></td>
											<td><c:out value="${transaction.free_rides_added}"></c:out></td>
											<td><c:out value="${transaction.free_rides_deducted}"></c:out></td>
											<td><c:out value="${transaction.free_rides_balance}"></c:out></td>
									   		<td><fmt:formatDate type="both" value="${transaction.created_at}" /></td>
									   </tr>

									</c:forEach>

								</tbody>
							</table>
						 </div>
						</div>

					</div>
				</div>
				<!-- /Normal -->
			</div>
			<!-- /.container -->

		</div>
	</div>
	
</body>
</html>