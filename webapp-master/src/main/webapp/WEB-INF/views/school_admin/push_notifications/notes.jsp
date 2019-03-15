<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
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
$(function(){
	$('#deleteButton').attr('disabled','disabled');	
});
</script>

<script type="text/javascript">

	$(function(){
		var count=0;
   $(document).on('click','#singleCheckId',function(){	  
      if($(this).prop('checked')) {    	 
    	  $('#deleteButton').removeAttr('disabled');
    	  count++;
	}   
      if(!($(this).prop('checked'))) {    	     	 
    	  count--;
    	 // alert("unchecked");
	}       	    	 
       else {    	  
      }
      if(count==0)
  	{
  	   $('#deleteButton').attr('disabled','disabled');	  
  	}
   });
 });
</script>

<script type="text/javascript">

$(function(){
	 var count=0;
   $(document).on('click','#allSelectId',function(){
	 
      if($(this).prop('checked')) {    	 
    	  //alert(count++);
    	  count++;
    	  $('#deleteButton').removeAttr('disabled');    	  
	}          	
      if(!($(this).prop('checked'))) {
     // alert(count--);
      count--;
     }      
     	if(count==0)
    	{
     	//alert("is count==0");
    	   $('#deleteButton').attr('disabled','disabled');	  
    	}     	
   });
 });
</script>

<script type="text/javascript">
	
	var sel=false;
	var ids = [];
		 $(document).on('click','#deleteButton',function(){	
		//alert("delete button");							
		$("td.checkbox-column").each(function(){
			// alert('foreach');								 
			// alert($('input.uniform', this).is(':checked'));		
					if($('input.uniform', this).is(':checked')){
					//alert("if");
				        sel = true;	//set to true if there is/are selected row				    
				       var rfids = $('#stopIdHidden',this).val();
				       // alert(routeId);
				       ids.push(rfids);				    	
				      // alert(ids);					    	
		}		
	});
		if(sel==true){
		$.ajax({
    		url : "removeAllStopsByStopIds",
    		type : "POST",
    		data : "stopIds="
    				+ ids,
    		success : function(
    				result) {    					    			
    		}
    		});
		}
		ids=null;
		// location.reload(true);
		document.location.reload(true);
		//location.href = "./trips";		
 });			 		 
</script> 


	
<script type="text/javascript" class="showcase">
$(function(){
   
   $("#send").click(function(){

   		var ids = [];
   		$("#box2View option").each(function(){
  			console.log($(this).val());
   			ids.push($(this).val());
		});
   		var title = $("#push_title").val().trim();
   		var message = $("#push_message").val().trim();
		if(ids.length == 0){
			alert("Please select atleast one commuter");
		} else if( title.length == 0){
			alert("Please enter a title for push notification");			
		} else if( message.length == 0){
			alert("Please type some message");
		} else {
			//ids.toString();
			window.location.href = "send?data="+ids.toString()+":"+title+":"+message;
		}
   });
   
   $("#send_sms").click(function(){

  		var ids = [];
  		$("#box2View option").each(function(){
  			console.log($(this).val());
  			ids.push($(this).val());
		});
  		var message = $("#sms_message").val().trim();
		
  		if(ids.length == 0){
			alert("Please select atleast one commuter");		
		} else if( message.length == 0){
			alert("Please type some message");
		} else {
			//ids.toString();
			window.location.href = "send_sms?data="+ids.toString()+":"+message;
		}
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
						<li>Notifications</li>
						
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
						<h3>Push Notifications</h3>
						<span>Emails , SMS</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
					<div class="col-sm-6 col-md-3">
						
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
				<div class="col-md-12">
					<a href="addRole" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add User</span>
					</a>					
					<!-- <div class="btn btn-danger" id="deleteButton"> <i
						class="icon-trash"></i> <span>Delete</span>
					</div>	 -->
				</div>	
				</c:if>							
				
					<!--=== Feeds (with Tabs) ===-->
					<div class="col-md-12">
						<div class="widget">
							
							<div class="widget-content">
								<div class="tabbable tabbable-custom">
									<ul class="nav nav-tabs">
										<li class="active"><a href="#tab_feed_1" data-toggle="tab">Email Notifications</a></li>
										<li><a href="#tab_feed_2" data-toggle="tab">Sms Notifications</a></li>
										<li><a href="#tab_feed_3" data-toggle="tab">App Notifications</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane active" id="tab_feed_1">
											<div class="scroller" data-height="290px" data-always-visible="1" data-rail-visible="0">
													App Notifications.......
											</div>
										</div> <!-- /#tab_feed_1 -->

										<div class="tab-pane" id="tab_feed_2">
												<div class="scroller" data-height="450px" data-always-visible="1" data-rail-visible="0">
													
												</div>
										</div>
										<div class="tab-pane" id="tab_feed_3">
											<div class="scroller" data-height="450px" data-always-visible="1" data-rail-visible="0">
													
													
													<div class="widget-content clearfix">
														<!-- Left box -->
														<div class="left-box">
															<input type="text" id="box1Filter" class="form-control box-filter" placeholder="Commuters list"><button type="button" id="box1Clear" class="filter">x</button>
															<select id="box1View" multiple="multiple" class="multiple">
																<c:forEach items="${commuters }" var="commuter">
																		<option value="${commuter.commuter_id}"> ${commuter.name} - ${commuter.mobile} - ${commuter.commuter_id}</option>
																</c:forEach>
															</select>
															<span id="box1Counter" class="count-label"></span>
															<select id="box1Storage"></select>
														</div>
														<!--left-box -->

														<!-- Control buttons -->
														<div class="dual-control">
															<button id="to2" type="button" class="btn">&nbsp;&gt;&nbsp;</button>
															<button id="allTo2" type="button" class="btn">&nbsp;&gt;&gt;&nbsp;</button><br>
															<button id="to1" type="button" class="btn">&nbsp;&lt;&nbsp;</button>
															<button id="allTo1" type="button" class="btn">&nbsp;&lt;&lt;&nbsp;</button>
														</div>
														<!--control buttons -->

														<!-- Right box -->
														<div class="right-box">
															<input type="text" id="box2Filter" class="form-control box-filter" placeholder="Filter entries..."><button type="button" id="box2Clear" class="filter">x</button>
															<select id="box2View" multiple="multiple" class="multiple">
																
															</select>
															<span id="box2Counter" class="count-label"></span>
															<select id="box2Storage"></select>
														</div>
														<!--right box -->
													</div>
													<label class="col-md-2 control-label">Title:</label>
                                                    <input id="push_title" name="title" type="text"></input>
                                                    </br>
													<label class="col-md-2 control-label">Message:</label>
													
													<textarea id="push_message" rows="5" cols="5" name="textarea" style="overflow:scroll;" class="auto form-control"></textarea>
													</br>
													<button class="btn btn-info" id="send">Send</button>
												</div>
											</div> <!-- /.scroller -->
										</div> <!-- /#tab_feed_1 -->
									</div> <!-- /.tab-content -->
								</div> <!-- /.tabbable tabbable-custom-->
							</div> <!-- /.widget-content -->
						</div> <!-- /.widget .box -->
					</div> <!-- /.col-md-6 -->
					<!-- /Feeds (with Tabs) -->
				<!-- /Normal -->
			</div>
			<!-- /.container -->
		</div>
	</div>
</body>
</html>