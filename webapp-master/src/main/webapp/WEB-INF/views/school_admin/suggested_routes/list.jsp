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
	src="<c:url value="/resources/jquery-1.11.0.min.js" />">
</script>

<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

<script type="text/javascript">
	src="<c:url value="/resources/jquery-1.11.0.min.js" />">
</script>

<script type="text/javascript">

	$(document).ready(function(){
		//alert("ready");
		/* search box code model4 started */
		$("#search_button").click(function(){			
			var search_sr=$("#search_sr_id").val();						
			search_sr=$.trim(search_sr);			
			var searchOption=$("#search_searchOption").val();						
			var regx = /^[A-Za-z0-9]+$/;					
			if(search_sr==""||!regx.test(search_sr)){				
				$("#search_rfid_number").css("border-color","red");
				$("#rfid_number_status_error501").show();
				
				$("#rfid_number_status_error").hide();
				$("#rfid_number_status_success").hide();						
			}					
			else{					
				 $.ajax({				
					url:"${pageContext.request.contextPath}/school_admin/suggested_routes/search",
					type:"POST",
					data:'search_sr='+search_sr+'&searchOption='+searchOption,
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
						<li>Suggested Routes</li>
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
						<h3>Suggested Routes</h3>
						<span></span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Suggested Routes</div>
								<div class="value">
									<c:out value="${recordsCount}"></c:out>
								</div>

							</div>

						</div>
						<!-- /.smallstat -->

					</div>

					<!-- /.col-md-3 -->

				</div>
				<div class="col-md-12" align="right">
					<a data-toggle="modal" href="#myModal4" class="btn btn-success"
						id="rfid_create_click"> <i class="icon-search"></i> <span>
							Search</span>
					</a>
				</div> <br/>

				<div class="row">
					<div class="col-md-12">

						<div class="widget-content">
							<table
								class="table table-hover table-striped table-bordered table-highlight-head">
								<thead>
									<tr>
										<th>#</th>
										<th>ID</th>
										<th>Commuter ID</th>
										<th>From</th>										
										<th>To</th>
										<th>Start Time Hour</th>
										<th>Start Time Minutes</th>
										<th>End Time Hour</th>
										<th>End Time Minutes</th>
									    <th>Created At</th>																		
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${suggestedRoutes}" var="suggestedRoute"
										varStatus="index">

										<tr>
											<td><c:out value="${index.index+1}"></c:out></td>
											<td><c:out value="${suggestedRoute.id}"></c:out></td>
											<td><c:out value="${suggestedRoute.commuter_id}"></c:out></td>											
											<td><c:out value="${suggestedRoute.from_stop}"></c:out></td>
											<td><c:out value="${suggestedRoute.to_stop}"></c:out></td>
											<td><c:out value="${suggestedRoute.start_time_hour}"></c:out></td>
											<td><c:out value="${suggestedRoute.start_time_minutes}"></c:out></td>
											<td><c:out value="${suggestedRoute.end_time_hour}"></c:out></td>
											<td><c:out value="${suggestedRoute.end_time_minutes}"></c:out></td>
										    <td><c:out value="${suggestedRoute.created_at}"></c:out></td>																					
										</tr>

									</c:forEach>

								</tbody>
							</table>
							<div class="row">
								<div class="table-footer">
									<div class="col-md-4" style="overflow: scroll;">
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
															href="list?offset=${((i -1) * recordsPerPage) +1}&limit=${i *recordsPerPage} ">${i }</a></li>
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

						<label class="col-md-5 control-label">Search By: <span
							class="required">*</span></label>

						<div class="col-md-7 clearfix">
							<select name="searchOption" id="search_searchOption"
								class="col-md-5 select2 full-width-fix required"
								style="border-color: grey;">

								<!-- 	<option>select</option> -->

								<option value="from_stop">From Stop</option>
								<option value="to_stop">To Stop</option>
																 
							</select>
						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter: <span
							class="required">*</span></label>
						<div class="col-md-6">
							<input type="text" name="search_sr" id="search_sr_id"
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