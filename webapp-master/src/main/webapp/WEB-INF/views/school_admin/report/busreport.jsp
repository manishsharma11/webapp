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
  <%-- <script type="text/javascript" src="<c:url value="/resources/plugins/flot/jquery.flot.pie.min.js"/>"></script> --%>
	<%-- <script type="text/javascript" src="<c:url value="/resources/plugins/easy-pie-chart/jquery.easy-pie-chart.min.js"/>"></script> --%>
	<%-- <script type="text/javascript" src="<c:url  value="/resources/assets/js/demo/charts/chart_pie.js"/>"></script>   --%>
	 <%--  <script type="text/javascript" src="<c:url  value="/resources/assets/js/demo/charts/chart_donut.js"/>"></script>   --%>
	 <script type="text/javascript" src="<c:url value="/resources/assets/js/demo/charts/d3pie.min.js"></c:url>"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js" charset="utf-8"></script>
	 
	 <script>

</script>
<script type="text/javascript">


		$(document).ready(function(){
			 
		
			
			$("#fetch_bus").click(function(event){
				event.preventDefault();
				
				var from=$("#from").val();
				var to=$("#to").val();
				 //alert(trip+"  "+date);
				 from=$.trim(from);
				 to=$.trim(to);
				if(from=="" || to=="")
					{
					alert("please select Dates");
					}
				 
				else
					{
					$.ajax({
						url:"getbusreport",
						type:"POST",
						data:"from="+from+"&to="+to,
						 
					success:function(result)
					{
						
						result=$.trim(result);
						var obj1=result.split("+");
						//alert(obj1);  
						  var row="";
						 if(obj1==null||obj1==""){
							 
							  row=row+"<tr class=odd > <td class=dataTables_empty colspan=6> No Results Found </td>  </tr>";  
							  $("#pieChart").empty();
					 
						 }
						 else
							 {
						 var obj2=obj1[0].split("/")
						 //alert("else "+obj2)
						  $("#pieChart").empty();
							for (var i = 0; i <obj2.length; i++) {
								if(obj2[i] !=""){
									var obj=obj2[i].split(":");
					             row =row+ "<tr> <td>" + [i+1] + "</td><td>"  +obj[0]+  "</td><td>" + obj[1] + "</td><td>" + obj[2]+ "</td><td>" + obj[3] + "</td><td>" + obj[4] + "</td></tr>";
					           
					        } 
							 
							 }
							var obj3=obj1[1].split("*")
							//alert(obj3);
							var t=obj3[0];
							//alert(t);
							if(obj3[0]!=""){
								 $("#pieChart").empty();
							var pie = new d3pie("pieChart", {
								"header": {
									"title": {
										"text": "Bus Status",
										"fontSize": 24,
										"font": "open sans"
									},
									"subtitle": {
										"text": "A full pie chart to Show Status of Buses between Given Dates.",
										"color": "#999999",
										"fontSize": 12,
										"font": "open sans"
									},
									"titleSubtitlePadding": 9
								},
								"footer": {
									"color": "#999999",
									"fontSize": 10,
									"font": "open sans",
									"location": "bottom-left"
								},
								"size": {
									"canvasWidth": 590,
									"pieInnerRadius": "37%",
									"pieOuterRadius": "94%"
								},
								"data": {
									"sortOrder": "value-desc",
									"content": [
										{
											"label": "On-Time",
											"value":  parseInt(t),
											"color": "#0c6197"
										},
										{
											"label": "Late",
											"value":  parseInt(obj3[1]),
											"color": "#830909"
										},
										{
											"label": "Very Late",
											"value":  parseInt(obj3[2]),
											"color": "#e65414"
										}
									]
								},
								"labels": {
									"outer": {
										"pieDistance": 32
									},
									"inner": {
										"hideWhenLessThanPercentage": 3
									},
									"mainLabel": {
										"fontSize": 11
									},
									"percentage": {
										"color": "#ffffff",
										"decimalPlaces": 0
									},
									"value": {
										"color": "#adadad",
										"fontSize": 11
									},
									"lines": {
										"enabled": true
									},
									"truncation": {
										"enabled": true
									}
								},
								"effects": {
									"pullOutSegmentOnClick": {
										"effect": "none",
										"speed": 400,
										"size": 8
									}
								},
								"misc": {
									"gradient": {
										"enabled": true,
										"percentage": 100
									}
								},
								"callbacks": {
									"onMouseoutSegment": null,
									"onClickSegment": null
								}
							});
							}
							else
								{
								alert("else");
								$("#pieChart").empty();
								}
							 }
						//line to clear data 
						$("#data").empty();
						 //$("#pieChart").empty();
						 $("#data").append(row);
						
					   $("#excel").prop("disabled",false);
						 $("#pdf").prop("disabled",false);  
						 
						
					}
					 
					});
					}
			});
			 

			$("#excel").click(function(e) {
				 window.open('data:application/vnd.ms-excel,'  + encodeURIComponent($('#jqxgrid').html()));
			});
			
			
			  $("#pdf").click(function(event) {
				/*  window.open('data:application/asd.pdf,' + encodeURIComponent($('#jqxgrid').html())); */
				
			 var date=$("#date_select").val();
				var trip=$("#selecttrip").val();
				 //alert("pdf export");
				 trip=$.trim(trip);
				 date=$.trim(date);
				
			   
			         var url = "report.htm";
			         alert("run");
			         $.ajax({

			                type: "GET", url: url,
			                data:"trip="+trip+"&date="+date,
			                
			                success: function(response){
			                 
			                     window.open(url);
			                      
			                     alert("pdf");
			                    
			                },
			                   error: function(error, status){
			                     window.alert("Problem retrieving PDF.\nThe error status is: " + status);
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
					<!-- <form class="form-horizontal" action="#"> -->

					<div class="form-group">
						<label class="col-md-1 control-label">From Date <span
							class="required">*</span></label>
						<div class="col-md-2 clearfix">
							<input type="text" name="regular" class="form-control datepicker"
								id="from">

						</div>
						
						
						<label class="col-md-1 control-label">To Date <span
							class="required">*</span></label>
						<div class="col-md-2 clearfix">
							<input type="text" name="regular" class="form-control datepicker"
								id="to">

						</div>
						<!-- <div style="display: none;" id="bus_div">

							<label class="col-md-1 control-label">Select Bus <span
								class="required">*</span></label>
							<div class="col-md-2 clearfix">
								<select name="chosen1" id="chosen1"
									class="col-md-3 select2 full-width-fix required"
									disabled="disabled">
									<option></option>

								</select> <label for="chosen1" generated="true"
									class="has-error help-block" style="display: none;"></label>
							</div>
							<label class="col-md-1 control-label">Select Trip <span
								class="required">*</span></label>
							<div class="col-md-3 clearfix">
								<select name="chosen1" id="selecttrip"
									class="col-md-3 select2 full-width-fix required"
									disabled="disabled">
									<option></option>
								</select> <label for="chosen1" generated="true"
									class="has-error help-block" style="display: none;"></label>
							</div>
						</div> -->
						<button class="btn btn-sm btn-primary" id="fetch_bus">Fetch</button>
						<br>
						<br>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					</div>





				</div>


				<!-- Table Data -->
				<div class="row">


					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>

									<i class="icon-reorder"></i>Buses Report:
								</h4>

								<!-- <a href="/sts/school_admin/reports/report.htm">Show Pdf</a>  -->
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i
											class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>

							<div class="widget-content" id="jqxgrid">
								<table
									class="table table-striped table-bordered table-hover table-checkable  "
									id="data1">

									<thead>
										<tr>
											<!-- <th class="checkbox-column" >
												<input type="checkbox" class="uniform" id="allSelectId" >	
											</th> -->
											<th id="head1">S.No</th>
											<!-- <th>Route Name</th> -->
											<th id="head">BusLicence No.</th>
											<th id="head">Trip Name</th>
											<th id="head">Date</th>
											<th id="head">Status</th>
											<th id="head">Driver</th>
											 
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody id="data">
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
			</div>
			<!-- pie chart data -->
			  <div class="widget box">
							<div class="widget-header">
								<h4><i class="icon-reorder"></i> Pie Chart!!!</h4>
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>
							<div class="widget-content">
								<!-- <div id="chart_pie" class="chart" style="padding: 0px; position: relative;"> -->
								
								 <div id="pieChart"></div>
								   
  
						</div>
					</div>  
					<!-- end -->
					
					
			<!-- Donut  -->
					
					<!--  <div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4><i class="icon-reorder"></i> Donut Chart</h4>
								<div class="toolbar no-padding">
									<div class="btn-group">
										<span class="btn btn-xs widget-collapse"><i class="icon-angle-down"></i></span>
									</div>
								</div>
							</div>
							<div class="widget-content">
								<div id="chart_donut" class="chart" style="padding: 0px; position: relative;">

								
							</div>
						</div>
					</div> -->
					
					<!-- End -->
					
					
		</div>


		<!-- </form> -->
	</div>




</body>
</html>