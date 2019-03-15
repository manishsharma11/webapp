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
		//alert("ready");
		/* search box code model4 started */
		$("#search_button").click(function(){			
			var search_commuter=$("#search_commuter_id").val();						
			search_commuter=$.trim(search_commuter);			
			var searchOption=$("#search_searchOption").val();						
			var regx = /^[A-Za-z0-9]+$/;					
			if(search_commuter==""||!regx.test(search_commuter)){				
				$("#search_rfid_number").css("border-color","red");
				$("#rfid_number_status_error501").show();
				
				$("#rfid_number_status_error").hide();
				$("#rfid_number_status_success").hide();					
			}
			else{					
				 $.ajax({				
					url:"${pageContext.request.contextPath}/school_admin/commuter/search",
					type:"POST",
					data:'search_commuter='+search_commuter+'&searchOption='+searchOption,
					 success:function(result){
						//alert(result);	
						 window.location.href = result;
					}
				}); 
			}//else
		/* 	return true; */
		});
		/* search box model4 code ended */	
		
		$("tbody tr").bind("contextmenu",function(e){
			  $('table tr').removeClass("active");
		        $(this).addClass("active");	       
	    });
		$("tbody tr").click(function(e){
			  $('table tr').removeClass("active");
		        $(this).addClass("active");	       
	    });
	});
	
</script>

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
						<li>Commuters</li>
						<li>List</li>
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
						<h3>Commuters List</h3>
						<span>Create,Update,Delete</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Commuters</div>
								<div class="value">
									<c:out value="${recordsCount}"></c:out>
								</div>

							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>

				<c:if test="${message == 'refund_success' }">

					<font color="green" size="4">Refund is successfull.......</font>
				</c:if>
				<c:if test="${message == 'refund_failure' }">
					<font color="green" size="4">Refund is failed: <c:out value="${failure_message}"/></font>
				</c:if>
				<p></p>

<!-- Not Verified Commuters Search Button Starting -->
				<div>
					<input type="hidden" name="search_commuter" id="search_commuter_id"
						value="0" class="form-control" style="width: 300px;">
				</div>
				<div>
					<input type="hidden" name="searchOption" id="search_searchOption"
						value="status" class="form-control" style="width: 300px;">
				</div>
				<br>
				<div class="col-md-12" align="left">
					<button type="button" class="btn btn-danger" id="search_button">
						Not Verified Commuters</button>
				</div>
<!-- Not Verified Commuters Search Button Ending -->			

	<div class="col-md-12" align="right">
					<a data-toggle="modal" href="#myModal4" class="btn btn-success"
						id="rfid_create_click"> <i class="icon-search"></i> <span>
							Search</span>
					</a>
				</div>
				
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget-content">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>#</th>
										<th>Commuter Id</th>										
										<th>Name</th>
										<th>Email</th>
										<th>Mobile</th>
										<th>Gender</th>
										<th>Status</th>
										<th>Active</th>
										<th>Create At</th>
										<th>Verified At</th>
										<th>Referral Code used</th>										
										<th style="width: 190px;">Options</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${commuterList }" var="commuter"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${commuter.commuter_id}"></c:out></td>											
											<td><c:out value="${commuter.name}"></c:out></td>
											<td><c:out value="${commuter.email}"></c:out></td>
											<td><c:out value="${commuter.mobile}"></c:out></td>
											<td><c:out value="${commuter.gender}"></c:out></td>
											<td>
												<c:if test="${commuter.status == '0' }">
													<span class="label label-warning">Not Verified</span>
												</c:if> 
												<c:if test="${commuter.status == '1' }">
													<span class="label label-success">Verified</span>
												</c:if> 
												
											</td>
											<td>
												<c:if test="${commuter.active == '0' }">
													<span class="label label-success">Active</span>
												</c:if> 
												<c:if test="${commuter.active == '1' }">
													<span class="label label-warning">Blocked</span>
												</c:if> 
												<c:if test="${commuter.active == '2' }">
													<span class="label label-danger">Suspended</span>
												</c:if> 
											</td>
											<td><c:out value="${commuter.created_at}"></c:out></td>
											<td><c:out value="${commuter.verified_at}"></c:out></td>
											<td><c:out value="${commuter.referral_code_used}"></c:out></td>											
											<td>
												<div class="btn-group">
													<button class="btn dropdown-toggle" data-toggle="dropdown">
														<i class="icol-cog"></i> Settings <span class="caret"></span>
													</button>
													<ul class="dropdown-menu">
														<li><a
															href="edit?id=<c:out value="${commuter.commuter_id}"></c:out>">Edit</a></li>
														
														<c:if test="${login_role == 'ROLE_ADMIN' }">
															<li><a
																href="delete?id=<c:out value="${commuter.commuter_id}"></c:out>">Delete</a></li>
														</c:if>

														<li><a
															href="transactionhistory?id=<c:out value="${commuter.commuter_id}"></c:out>">Transaction
																History</a></li>
														<li><a
															href="bookinghistory?id=<c:out value="${commuter.commuter_id}"></c:out>">Booking
																History</a></li>
														<li><a
															href="refund?id=<c:out value="${commuter.commuter_id}"></c:out>">Refund</a></li>
													</ul>
												</div>
											</td>
										</tr>

									</c:forEach>

								</tbody>
							</table>
							<div class="row">
								<div class="table-footer">
									<div class="col-md-8" style="overflow: scroll;">
										Showing ${offset } to ${limit } of ${recordsCount } entries
										<ul class="pagination">

											<c:forEach var="i" begin="1"
												end="${recordsCount /  recordsPerPage}">
												<c:choose>
													<c:when test="${ i == (limit /  recordsPerPage)}">
														<li class="active"><a href="list?offset=&limit=">${i }</a></li>
													</c:when>
													<c:otherwise>
														<li><a
															href="list?offset=${((i -1) * recordsPerPage) +1}&limit=${i*recordsPerPage}">${i}</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							<br>
						</div>

					</div>
				</div>
				<!-- /Normal -->
			</div>
			<!-- /.container -->

		</div>
	</div>

	<!-- search model4 started -->
	<div class="modal fade" id="myModal4">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Search form:</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<!-- col-mod-5 to col-mod-8 -->
						<label class="col-md-5 control-label">Search By: <span
							class="required">*</span></label>

						<div class="col-md-7 clearfix">
							<select name="searchOption" id="search_searchOption"
								class="col-md-5 select2 full-width-fix required"
								style="border-color: grey;">

								<!-- 	<option>select</option> -->

								<option value="commuter_id">Commuter ID</option>
								<option value="name">Name</option>
								<option value="email">Email ID</option>
								<option value="mobile">Mobile Number</option>
								<option value="referral_code_used">Referral Code Used</option>
								<option value="referral_code_belongs_to">Referral Code
									Belongs To</option>
								<option value="gender">Gender</option>
								<option value="status">Status</option>
								<option value="created_at">Date of Creation</option>

							</select>
							

						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter: <span
							class="required">*</span></label>
						<div class="col-md-6">
							<input type="text" name="search_commuter" id="search_commuter_id"
								class="form-control" style="width: 300px;">
						</div>



						<br>

						<!-- <div id="loading" style="display: none;">
							<h3>Fetching data from DataBase please wait</h3>
						</div> -->
						<br>


						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							Please enter valid search key.....
						</div>

						<div class="alert alert-danger fade in"
							id="rfid_number_status_error2" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							some problem occured... please try to reload page again...
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="search_button">Search</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- search model4 ended -->

</body>
</html>