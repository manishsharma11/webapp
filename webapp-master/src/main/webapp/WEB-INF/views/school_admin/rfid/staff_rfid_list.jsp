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
$(function(){
	$('#deleteButton').attr('disabled','disabled');	
});
</script>

<script type="text/javascript">
	var count = 0;
	$(function() {

		$(document).on('click', '#singleCheckId', function() {
			if ($(this).prop('checked')) {
				$('#deleteButton').removeAttr('disabled');
				count++;
				//alert(count);
			}
			if (!($(this).prop('checked'))) {
				count--;
				//alert(count);
				if ($('#allSelectId').is(':checked')) {
					//alert("all");					
					//$('#allSelectId').trigger("click");					
				}
			}
			

			if (count == 0) {
				//alert("count is 0");
				$('#deleteButton').attr('disabled', 'disabled');
			}

		});
	});

	$(function() {
		$(document).on('click', '#allSelectId', function() {

			if ($(this).prop('checked')) {
				//alert("select all checked");
				//$('#deleteButton').removeAttr('disabled');
				$("td.checkbox-column").each(function() {

					if ($('input.uniform', this).is(':checked')) {
						//alert(count);
						count++;
					}
				});
				if(count!=0)
					$('#deleteButton').removeAttr('disabled');
				else
					$('#deleteButton').addAttr('disabled');
			}
			if (!($(this).prop('checked'))) {
				//alert("unchecked");					
				$('#deleteButton').attr('disabled', 'disabled');
			}
		});
	});
</script>


<script type="text/javascript">
	
	var sel=false;
	var ids = [];
		 $(document).on('click','#deleteButtonAction',function(){	
		//alert("delete button");							
		$("td.checkbox-column").each(function(){
			// alert('foreach');								 
			// alert($('input.uniform', this).is(':checked'));		
					if($('input.uniform', this).is(':checked')){
						if (!($('input.uniform', this).is(':disabled'))) {
					//alert("if");
				        sel = true;	//set to true if there is/are selected row				    
				       var rfids = $('#rfidIdHidden',this).val();
				       // alert(routeId);
				       ids.push(rfids);				    	
				      // alert(ids);					    	
						}
		}		
	});				
		$.ajax({

    		url : "removeAllStaffRFIDSByTripIds",
    		type : "POST",
    		data : "rfids="
    				+ ids,
    		success : function(
    				result) {    		
    			window.location.href = result;
    		}
    		});
		ids=null;
		
			
		// location.reload(true);
		//document.location.reload(true);
		//location.href = "./trips";		
 });			 		 
</script>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#button_add").click(function(){
			
			var rfid_number=$("#rfid_number").val();
			rfid_number=$.trim(rfid_number);
			var regx = /^[A-Za-z0-9]+$/;
			if(rfid_number.length<10||rfid_number.length>20){
				$("#rfid_number_status_error111").show();
				return false;
			}
			if(rfid_number==""  ||!regx.test(rfid_number)){
				$("#rfid_number").css("border-color","red");
				$("#rfid_number_status_error111").show();
				$("#rfid_number_status_error1").show();
				$("#rfid_number_status_error").hide();
				$("#rfid_number_status_success").hide();
			}
			else{
				$("#rfid_number").css("border-color","green");
				$("#rfid_number_status_success").show();
				$("#rfid_number_status_error1").hide();
				$("#rfid_number_status_error2").hide();
				$("#loading").show();
				$.ajax({
					
					url:"${pageContext.request.contextPath}/school_admin/rfid/staff/add",
					type:"POST",
					data:"rfid_number="+rfid_number,
					success:function(result){
						result=$.trim(result);
						if(result=="rfid_inserted"){
							$("#rfid_number_status_error").hide();
							$("#rfid_number_status_error1").hide();
							$("#rfid_number_status_success").hide();
							$("#loading").hide();
							document.location.reload(true);
						}
						if(result=="rfid_notinserted"){
							$("#rfid_number_status_error2").show();
							$("#rfid_number_status_error1").hide();
							$("#rfid_number_status_success").hide();
							$("#loading").hide();
						}
						if(result=="rfid_exists"){
							$("#rfid_number_status_error").show();
							$("#rfid_number_status_error1").hide();
							$("#rfid_number_status_success").hide();
							$("#loading").hide();
						}
					}
				});
			}
		});
		 $("#button_update").click(function(){
			
			var rfid_number=$("#rfid_number1").val();
			rfid_number=$.trim(rfid_number);
			var regx = /^[A-Za-z0-9]+$/;
			if(rfid_number=="" || !regx.test(rfid_number)){
				$("#rfid_number1").css("border-color","red");
				$("#rfid_number_status_success1").hide();
				$("#rfid_number_status_error14535").hide();
				$("#rfid_number_status_error11").show();
				$("#rfid_number_status_error21").hide();
			}
			else{
				$("#rfid_number1").css("border-color","green");
				$("#rfid_number_status_success1").show();
				$("#rfid_number_status_error11").hide();
				$("#rfid_number_status_error21").hide();
				$("#loading1").hide();
				$.ajax({
					
					url:"${pageContext.request.contextPath}/school_admin/rfid/staff/update",
					type:"POST",
					data:"current_rfid="+$("#rfid_number12").val()+"&new_rfid="+rfid_number,
					success:function(result){
						result=$.trim(result);
						if(result=="rfid_inserted"){
							$("#rfid_number_status_error1").hide();
							$("#rfid_number_status_error11").hide();
							$("#rfid_number_status_success1").hide();
							$("#loading1").hide();
							document.location.reload(true);
						}
						if(result=="rfid_notinserted"){
							$("#rfid_number_status_error21").show();
							$("#rfid_number_status_error11").hide();
							$("#rfid_number_status_success1").hide();
							$("#loading1").hide();
						}
						if(result=="rfid_exists"){
							$("#rfid_number_status_error14535").show();
							$("#rfid_number_status_error11").hide();
							$("#rfid_number_status_success1").hide();
							$("#loading1").hide();
						}
					}
				});
			}
		}); 
		$("#rfid_create_click").click(function(){
			$("#rfid_number").val("");
			$("#rfid_number").css("border-color","grey");
			$("#rfid_number_status_error1").hide();
			$("#rfid_number_status_error").hide();
			$("#rfid_number_status_success").hide();
			$("#loading").hide();
		});
		
		$("tr td a").click(function(event){
			
			$("#loading1").hide();
			$("#loading13245").hide();
			var id=$(this).attr("id");
			if(id=="edit_rfid_number_click"){
				$("#rfid_number_status_success1").hide();
				$("#rfid_number_status_error14535").hide();
				$("#rfid_number_status_error11").hide();
				$("#rfid_number_status_error21").hide();
				$("#rfid_number1").css("border-color","grey");
				$("#rfid_number1").val($(this).attr("value"));
				$("#rfid_number12").val($(this).attr("value"));
				//alert($(this).attr("value"));
			}
			if(id=="delete_rfid_number_click"){
				
				$("#rfid_number12456").val($(this).attr("value"));
				$("#rfid_number12_delete").empty().append($(this).attr("value"));
				//alert($(this).attr("value"));
			}
		}); 
		
		$("#button_delete").click(function(){
			
			//alert("delete");
			var rfid_number=$("#rfid_number12456").val();
			//alert(rfid_number);
				$("#loading13245").show();
				$.ajax({
					
					url:"${pageContext.request.contextPath}/school_admin/rfid/staff/delete",
					type:"POST",
					data:"rfid_number="+rfid_number,
					success:function(result){
						result=$.trim(result);
						if(result=="rfid_deleted"){
							
							document.location.reload(true);
						}
						if(result=="rfid_notdeleted"){
							$("#loading13245").show().empty().append("<font color='red'>Some error occured please try again</font>");;
						}
						
					}
				});
			
		});
		
		/* search box code model4 started */
		$("#search_button_add").click(function(){
			
			//alert("search");
			var rfid_number=$("#search_rfid_number").val();			
			rfid_number=$.trim(rfid_number);
			//alert(rfid_number);
			var regx = /^[A-Za-z0-9]+$/;
			
			
			if(rfid_number==""||!regx.test(rfid_number)){				
				$("#search_rfid_number").css("border-color","red");
				$("#rfid_number_status_error501").show();
				
				$("#rfid_number_status_error").hide();
				$("#rfid_number_status_success").hide();						
			}
			else{	
				 $.ajax({
					
					url:"${pageContext.request.contextPath}/school_admin/rfid/staff/search",
					type:"POST",
					data:"rfid_number="+rfid_number,
					 success:function(result){
						//alert(result);	
						 window.location.href = result;
					}
				}); 
			}//else
		/* 	return true; */
		});
		/* search box model4 code ended */
		
		
	});
	
</script>

<!-- <script type="text/javascript" class="showcase">
$(function(){
    $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
        	
        	 var va=$(this).find(' input[type="hidden"] ').val();
         if(key=="edit"){        	
             window.location="drivers/updatedriver?id="+va;
         }
         if(key=="delete"){        	         	 
             window.location="drivers/removedriver?id="+va;
         }
         if(key=="view"){        	         	 
             window.location="driver/viewdriver?id="+va;
         }
        },
        items: {
            "edit": {name: "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>", icon: "edit1"},
            
            "delete": {name: "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete</i>", icon: "delete"},
            
            "view": {name: "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>", icon: "view"},
        }
    });
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    })
});
    </script> -->


<script type="text/javascript" class="showcase">
$(function(){
    $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
        	 var va=$(this).find(' input[type="hidden"] ').val();
        	//alert(va);
        	/* $("#edit_icon").attr({
			    "href" : "#myModal2",
			    "data-toggle" : "modal"
			  }); */
          if(key=="edit"){   
        	  
        	  
        	  //data-toggle='modal' href='#myModal2'
        	  $("#rfid_number1").val(va);
        	  $("#rfid_number12").val(va);
        	  jQuery("#edit_rfid_number_click").trigger('click');
        	 /*  $("#edit_icon").prop("data-toggle","modal");
        	  $("#edit_icon").prop("href","#myModal2"); */
             //window.location="drivers/updatedriver?id="+va;
         }
         if(key=="delete"){
        	 
        	 $("#rfid_number12456").val(va);
        	 $("#rfid_number12_delete").empty().append(va);
        	 jQuery("#delete_rfid_number_click").trigger('click');
             //window.location="drivers/removedriver?id="+va;
         }
         if(key=="view"){        	         	 
            // window.location="driver/viewdriver?id="+va;
         }  
        },
      
        items: {
        	 "edit": {name: "<i  class='icon-pencil' id='edit_icon'>&nbsp;&nbsp;&nbsp;Update&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>", icon: "edit1"},
             
             "delete": {name: "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete</i>", icon: "delete"},
             
             /* "view": {name: "<i class='icon-eye-open '>&nbsp;&nbsp;&nbsp;View</i>", icon: "view"}, */
        }
    });
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    });
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
<style type="text/css">
tr.active td {
	background-color: #428bca;
}
/* 	tr:hover {
		    color: #261F1D !important;
		    background-color: #E5C37E !important;
		} */
</style>
<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
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
						<li>RFID</li>
						<li>Staff RFID</li>
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
							match found... </strong>
					</div>
					<style>
#home_error {
	border-color: red;
}
</style>
				</c:if>
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Staff RFID</h3>
						<span>Create,Update,Delete Staff's RFID</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>

					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Staffs RFIDs</div>
								<div class="value">
									<c:out value="${staff_rfid_list.size()}" />
								</div>

								<!-- Store temp School ID for deleting school -->
								<input type="hidden" id="store_schoolid" value="null">
							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
					<div class="col-md-8">
						<a data-toggle="modal" href="#myModal1" class="btn btn-primary"
							id="rfid_create_click"> <i class="icon-plus"></i> <span>Add
								RFID</span>
						</a> <a data-toggle="modal" href="#myModal5" class="btn btn-danger"
							id="deleteButton"> <i class="icon-trash"></i> <span>Delete</span>
						</a>

					</div>
				</c:if>
				<div class="col-md-12" align="right">
					<a data-toggle="modal" href="#myModal4" class="btn btn-success"
						id="rfid_create_click"> <i class="icon-search"></i> <span>
							Search By RFID</span>
					</a> <a data-toggle="modal" href="#myModal2"
						id="edit_rfid_number_click"> </a> <a data-toggle="modal"
						href="#myModal3" id="delete_rfid_number_click"></a>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Staff RFID List
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
									class="table table-striped table-bordered table-hover table-checkable datatable">
									<thead>
										<tr>
											<th class="checkbox-column"><input type="checkbox"
												class="uniform" id="allSelectId"></th>
											<th>RFID Number</th>
											<th>Allotted To</th>
											<th>Allotted Date</th>
											<th>Status</th>
											<!-- <th style="width:170px;">Options</th> -->
										</tr>
									</thead>
									<tbody>

										<c:forEach var="rfid" items="${staff_rfid_list}">
											<c:choose>
												<c:when test="${login_role  == 'ROLE_ADMIN' }">
													<c:if test="${rfid.available==0}">
														<tr class="context-menu-one box menu-1">
													</c:if>
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>
											<!-- <tr class="context-menu-one box menu-1"> -->
											<c:choose>
												<c:when test="${rfid.available==0}">
													<td class="checkbox-column"><input type="checkbox"
														class="uniform" id="singleCheckId"> <input
														type="hidden" value="${rfid.rfid_number}"
														id="rfidIdHidden"></td>
												</c:when>
												<c:otherwise>
													<td class="checkbox-column"><input type="checkbox" id="singleCheckId"
														class="uniform" disabled="disabled"> <input
														type="hidden" value="${rfid.rfid_number}"
														id="tripIdHidden"></td>
												</c:otherwise>
											</c:choose>


											<td><c:out value="${rfid.rfid_number}" /></td>
											<td><c:out value="${rfid.allocated_person_name}" /></td>
											<td><c:out value="${rfid.allocated_time}" /></td>
											<c:if test="${rfid.available==0}">
												<td><span class="label label-success">Available</span></td>
											</c:if>
											<c:if test="${rfid.available!=0}">
												<td><span class="label label-warning">Not
														Available</span></td>
											</c:if>
											<%--<c:if test="${rfid.available=='yes'}">
													 <td>
														<a  data-toggle="modal" href="#myModal2" class="btn btn-sm btn-info" value="<c:out value="${rfid.rfid_number}" />"  id="edit_rfid_number_click" > <i class=" icon-pencil"></i>&nbsp;Update</a>
														<a data-toggle="modal" href="#myModal3" class="btn btn-sm btn-danger" value="<c:out value="${rfid.rfid_number}" />" id="delete_rfid_number_click"><i class=" icon-trash"></i>&nbsp;Delete</a>
													</td>	 									
												</c:if>
												<c:if test="${rfid.available!='yes'}">
														<td></td>								
												</c:if>--%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->
				<div class="alert alert-info fade in">
					<i class="icon-remove close" data-dismiss="alert"></i> <strong>Info!</strong>
					"RFIDs which are assigned to staff cannot be updated and deleted".
				</div>
			</div>
			<!-- /.container -->

		</div>
	</div>
	<div class="modal fade" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Add new Staff RFID form:</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-md-5 control-label">Enter RFID Number: <span
							class="required">*</span></label>
						<div class="col-md-12">
							<input type="text" name="rfid_number" id="rfid_number"
								class="form-control" style="width: 300px;">
						</div>
						<br>
						<div id="loading" style="display: none;">
							<h3>Fetching data from Database please wait...</h3>
						</div>
						<div class="alert alert-success fade in"
							id="rfid_number_status_success" style="display: none;">

							<strong>Notice:</strong>Inserting into database please wait
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error" style="display: none;">

							<strong>Error!</strong> RFID Number allready Registered...
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error1" style="display: none;">

							<strong>Error!</strong>RFID number must be in an Alpha-Numeric
							Pattern.
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error111" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>RFID
							length should be between 10-20 digits.
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error2" style="display: none;">

							<strong>Error!</strong> some problem occured... please try to
							reload page again...
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="button_add">Add</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade" id="myModal2">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Edit Staff RFID form:</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-md-8 control-label">Enter RFID Number:
							(current RFID) <span class="required">*</span>
						</label> <br>
						<div class="col-md-8">
							<input type="text" name="rfid_number" id="rfid_number1"
								class="form-control" style="width: 250px;"> <input
								type="text" name="rfid_number" id="rfid_number12"
								class="form-control required" style="display: none;">
						</div>
						<br>
						<div id="loading1" style="display: none;">
							<h3>Fetching data from db please wait...</h3>
						</div>
						<br> <br>
						<div class="alert alert-success fade in"
							id="rfid_number_status_success1" style="display: none;">

							<strong>Notice:</strong> inserting into database please wait
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error14535" style="display: none;">

							<strong>Error!</strong> RFID Number allready Registered...
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error11" style="display: none;">

							<strong>Error!</strong>RFID number must be in an Alpha-Numeric
							Pattern.
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error21" style="display: none;">

							<strong>Error!</strong> some problem occured... please try to
							reload page again...
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="button_update">Update</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade" id="myModal3">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Remove Staff RFID form:</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-md-8 control-label">Are you confirm to
							Delete below RFID?</label> <br></br>
						<div class="col-md-8">

							<button class="btn" id="rfid_number12_delete">Default</button>
							<input type="text" name="rfid_number" id="rfid_number12456"
								class="form-control required" style="display: none;">
						</div>
						<br>
						<div id="loading1" style="display: none;">
							<h3>Fetching data from db please wait...</h3>
						</div>
						<div class="alert alert-success fade in"
							id="rfid_number_status_success1" style="display: none;">

							<strong>Notice:</strong> inserting into database please wait
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error1" style="display: none;">

							<strong>Error!</strong> RFID Number allready Registered....
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error11" style="display: none;">

							<strong>Error!</strong> RFID number must be in an Alpha-Numeric
							Pattern.
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error21" style="display: none;">

							<strong>Error!</strong> some problem occured... please try to
							reload page again...
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="button_delete">Delete</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- search model4 started -->
	<div class="modal fade" id="myModal4">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Search By RFID form:</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-md-5 control-label">Enter RFID Number: <span
							class="required">*</span></label>
						<div class="col-md-12">
							<input type="text" name="search_rfid_number"
								id="search_rfid_number" class="form-control"
								style="width: 300px;">
						</div>
						<br>
						<div id="loading" style="display: none;">
							<h3>Fetching data from db please wait...</h3>
						</div>
						<div class="alert alert-success fade in"
							id="rfid_number_status_success" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Notice:</strong>
							inserting into database please wait
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							RFID number entered already exists...
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							RFID number must be in an Alpha-Numeric Pattern.
						</div>
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error111" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							RFID length should be between 10-20 digits.
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
					<button type="button" class="btn btn-primary"
						id="search_button_add">Search</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- search model4 ended -->

	
</body>
</html>