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

<script type="text/javascript">


		$(document).ready(function(){
			
			
			 
			
			
			 
			 
			
			$("#fetch_bus").click(function(event){
				event.preventDefault();
				
				var date=$("#date_select").val();
				 
				 
				if(date==""){
					
					alert("Please select a date");
				}
				else{
					//alert(date);
					 $.ajax({
						
						 url:"getstudents",
						 
						 type:"POST",
						 data:"date="+date,
						  
						 success:function(result)
						 {
							// alert("success");
							 
							 result=$.trim(result);
							 var append="";
							// $("#chosen1").empty();
							 var list=result.split("+");
							 for(var i=0;i<list.length;i++)
								 {
								 append=append+"<option value="+list[i]+" > "+list[i]+" </option>";
								 }
							 
							 $("#chosen1").prop("disabled", false);
							 $("#chosen1").empty();
							 $("#chosen1").append(append);
							 $("#bus_div").show();
						 },
						 error: function ( xhr, status, error) {
					          alert(  " //status: " + status + " //Error: "+error );

					        }
						 
					 });
					 
					 
					 
					 
				}
			});
			
  	 
			
			$("#chosen1").click(function(event){
				event.preventDefault();
				
				var date=$("#date_select").val();
				 
				 date=$.trim(date);
				 
				 var name=$("#chosen1").find(":selected").text();
					name=$.trim(name);
				 //alert(name+"  "+date);
					
					$.ajax({
						url:"getstudreport",
						type:"POST",
						data:"name="+name+"&date="+date,
						 
					success:function(result)
					{
						
						
					  
						 result=$.trim(result);
						 var result1=result.split("*");
							var list=result1[0].split("+");
							var list1=result1[1].split("+");
							//alert(list1); 
							var row="";
							var row1="";
							for (var i = 0; i <list.length; i++) {
								if(list[i] !="")
								{
									var data1=list[i].split("/");
					             /* row =row+ "<tr> <td>" + [i+1] + "</td><td>" + obj[i].date + "</td><td>" + obj[i].driver_id + "</td><td>" + obj[i].driver_name + "</td><td>" + obj[i].busid + "</td><td>" + obj[i].in_bound + "</td><td>" + obj[i].out_bound + "</td><td>" + obj[i].trip_id + "</td></tr>"; */
									row =row+ "<tr> <td>" + [i+1] + "</td><td>" + data1[8] + "</td><td>" + data1[0] + "</td><td>" + data1[1] + "</td><td>" + data1[2] +
									"</td><td>" + data1[3] + "</td><td>" + data1[4] + "</td><td>" + data1[5] + "</td><td>" + data1[6] + "</td><td>" + data1[7] + "</td></tr>";
									
									 
					        } 
								
							}
							if(list==null||list=="")
							{
							row=row+"<tr class=odd>  <td class=dataTables_empty colspan=9> No Results Found </td>  </tr>"
							}
							for (var i = 0; i <list1.length; i++) {
								if(list1[i] !="")
								{
									var data2=list1[i].split("/");
					              
									//alert(data2)
									row1 =row1+ "<tr> <td>" + [i+1] + "</td><td>" + data2[0] + "</td><td>" + data2[1] + "</td><td>" + data2[2] + "</td><td>" + data2[3] +
									"</td><td>" + data2[4] + "</td></tr>";
					        } 
								 
							}
							if(list1==null||list1=="")
							{
							row1=row1+"<tr class=odd>  <td class=dataTables_empty colspan=9> No Results Found </td>  </tr>"
							}
						//line to clear data 
						$("#data").empty();
						$("#pdata").empty();
						
						 $("#data").append(row);
						 $("#pdata").append(row1);
						
						 
						
						 
						
					}
					 
					});
					
			});
			
		 
			
			
			
		});

</script>


<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

<style type="text/css">
tr.active td {
	background-color: #428bca;
}
/* 	tr:hover {
		    color: #261F1D !important;
		    background-color: #E5C37E !important;
		} */
</style>
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


						<li>Reports</li>
						<li>Students</li>
					</ul>

					<ul class="crumb-buttons">
						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				<c:if test="${error_message=='noMatching'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>No
							match found...........</strong>

					</div>
				</c:if>
				<!--=== Page Header ===-->
				<br>
				<br>

				<div class="widget-content">
					<form class="form-horizontal" action="#">

						<div class="form-group">

							<label class="col-md-1 control-label">Select Date <span
								class="required">*</span></label>
							<div class="col-md-2 clearfix">
								<input type="text" name="regular"
									class="form-control datepicker" id="date_select">

							</div>
							<div style="display: none;" id="bus_div">




								<label class="col-md-1 control-label">Select Student <span
									class="required">*</span></label>
								<div class="col-md-2 clearfix">
									<select name="chosen1" id="chosen1"
										class="col-md-3 select2 full-width-fix required"
										disabled="disabled">
										<option></option>

									</select> <label for="chosen1" generated="true"
										class="has-error help-block" style="display: none;"></label>

								</div>


								<!-- <label class="col-md-1 control-label">Select Trip <span
													class="required">*</span></label>
												<div class="col-md-3 clearfix">
													<select name="chosen1" id="selecttrip"
														class="col-md-3 select2 full-width-fix required"
														disabled="disabled">
														 <option></option>
													</select> <label for="chosen1" generated="true"
														class="has-error help-block" style="display: none;"></label>
												</div> -->
							</div>
							<button class="btn btn-sm btn-primary" id="fetch_bus">Fetch</button>
						</div>
				</div>


				<!-- Table Data -->
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Student Report:
								</h4>
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i
											class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>

							<div class="widget-content">
								<table
									class="table table-striped table-bordered table-hover table-checkable  "
									id="data1">
									<thead>
										<tr>
											<!-- <th class="checkbox-column" >
												<input type="checkbox" class="uniform" id="allSelectId" >	
											</th> -->
											<th>S.No</th>
											<!-- <th>Route Name</th> -->
											<th>Gr No.</th>
											<th>Commuter Name</th>
											<th>Boarding Stop</th>
											<th>Boarding Time</th>
											<th>Exit Stop</th>
											<th>Exit Time</th>
											<th>Trip Type</th>
											<th>Trip Name</th>
											<th>Bus ID</th>
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody id="data">
										<tr class="odd">
											<td valign="top" colspan="10" class="dataTables_empty">No
												data available in table</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				
				<br><br><br><br><br>
				
				<!-- Student record for plexus mediator -->
					<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Student Plexus Mediator Report:
								</h4>
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i
											class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>

							<div class="widget-content">
								<table
									class="table table-striped table-bordered table-hover table-checkable  "
									id="data2">
									<thead>
										<tr>
											<!-- <th class="checkbox-column" >
												<input type="checkbox" class="uniform" id="allSelectId" >	
											</th> -->
											<th>S.No</th>
											<!-- <th>Route Name</th> -->
											 
											<th>Recipient Name</th>
											<th>Phone Number</th>
											<th>Date</th>
											<th>Status</th>
											<th>Message</th>
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody id="pdata">
										<tr class="odd">
											<td valign="top" colspan="7" class="dataTables_empty">No
												data available in table</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				
				
				<!--  -->
			</div>
		</div>


		</form>
	</div>


</body>
</html>