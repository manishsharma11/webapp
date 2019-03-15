<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="cache-control" content="private, must-revalidate, max-age=0">
<meta http-equiv="expires" content="0">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Book Now</title>
<!-- Files to be loaded -->

<script type="text/javascript" src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
	
<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<script type="text/javascript" src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>

<script type="text/javascript">
	$(document).ready(
		function() {

		//$("#from_stop_id").empty();

	// whichever works
	$("option:selected").removeAttr("selected");
	$("option:selected").prop("selected", false);
		
    $("#to_stop_id").prop("disabled", true);

	$("#from_stop_id").change(
		function() {

		$("#loading").show();
		//alert($(this).val());

		$("#to_stop_id").prop("disabled", true);
		var from_stop_id = $(this).val();
		var app_context = '<%=app_context%>';
		var url='';
		if(app_context){
			url = url+app_context+"/stops/to_stops/"+from_stop_id;
		}else{
			url = url+"/stops/to_stops/"+from_stop_id;
		}
        $.ajax({
			url : url,
			type : "GET",
			data : "",
			success : function(result) {
				//alert(result);
				var obj = result;//JSON.parse(result);
				if(obj == 'undefined'){
					
				} else {
					var data = "<option></option>";

					for (var i = 0; i < obj.length; i++) {
						var d = obj[i].stop_id;//+ ";;"+ obj[i].stop_name;
						if(d != 'undefined'){
							data = data
							+ "<option value=\""+d+"\">"
							+ obj[i].stop_name
							+ "</option>";
						}
					}
					$("#to_stop_id").prop("disabled", false);
					$("#to_stop_id").empty();
					//alert($("#route_css_id").val());
					//$("#to_stop_id").append("<option>Select</option>").change();
					$("#to_stop_id").append(data);
					$("#loading").hide();
			   }
			}
		});
	  });
	});
</script>
  
<!-- <input id="alwaysFetch" type="hidden" />
<script>
    setTimeout(function () {
        var el = document.getElementById('alwaysFetch');
        el.value = el.value ? location.reload() : true;
    }, 0);
</script> -->

<script type="text/javascript">    
// If persisted then it is in the page cache, force a reload of the page.
        window.onpageshow =function(event){
            if(event.persisted){        
                document.body.style.display ="none";        
                location.reload();
            }
        };
</script>

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
						<h3>Book Now</h3>
						<span></span>
					</div>
			
					<div class="col-sm-4 col-md-1"></div>

				</div>
		
		<c:if test="${not empty error}">
				<div class="errorblock">
					<div class="alert fade in alert-danger" >
						<i class="icon-remove close" data-dismiss="alert"></i>
						<c:out value="${error}"/>
					</div>					
				</div>
		</c:if>
		
		<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Book Now
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="<%=appName%>/webapp/booking/available_shuttles" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Pick Up Point<span class="required">*</span></label>
											<div class="col-md-9 clearfix">
												<select id="from_stop_id" name="from_stop_id" class="col-md-12 select2 full-width-fix required" style="border-color:grey;">
													<option></option>
														<c:forEach items="${from_list}" var="from_stop">
															<option value="<c:out value="${from_stop.stop_id }"></c:out>">
															     <c:out value="${from_stop.stop_name }"></c:out>
															</option>
														</c:forEach>
												</select>
											<label for="from_stop_id" generated="true" class="has-error help-block" style="display:none;"></label>
											</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Drop off Point<span class="required">*</span></label>
											<div class="col-md-9 clearfix">
												<select id="to_stop_id" name="to_stop_id" class="col-md-12 select2 full-width-fix required" style="border-color:grey;">
													<option></option>
												</select>
											<label for="to_stop_id" generated="true" class="has-error help-block" style="display:none;"></label>
											</div>
									</div>
									
									<div class="form-group">
										  <label class="col-md-3 control-label">How Many of You Will Commute<span class="required">*</span></label>
										  
										  <div class="btn-group col-md-9 clearfix" data-toggle="buttons">
							                <label class="btn btn-default active">
							                    <input type="radio" name="num_seats" value="1" checked="checked"/> Just Me
							                </label> 
							                <label class="btn btn-default">
							                    <input type="radio" name="num_seats" value="2" /> +1
							                </label> 
							                <label class="btn btn-default">
							                    <input type="radio" name="num_seats" value="3" /> +2
							                </label> 
							                <label class="btn btn-default">
							                    <input type="radio" name="num_seats" value="4" /> +3
							                </label>
							            </div>
										  
									</div>
									
									
									<div class="form-actions">
										<input type="submit" value="Search Shuttles"
											class="btn btn-success btn-block" id="add_new_driver_action">
									</div>
								</form>
							</div>
						</div>
	
					</div>
				</div>
	</div>
	</div>
	
</body>
</html>
