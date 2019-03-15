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
				        sel = true;	//set to true if there is/are selected row				    
				       var driverHiddenId = $('#staffHiddenId',this).val();				       
				       ids.push(driverHiddenId);				    	
				      // alert(ids);					    	
		}		
	});
		
		$.ajax({
    		url : "removeAllStaffsById",
    		type : "POST",
    		data : "staffsIds="
    				+ ids,
    		success : function(
    				result) {    		
    			window.location.href = result;
    		}
		
    		});
		ids=null;		
			
		//document.location.reload(true);
 });			 		 
</script> 



<script type="text/javascript">

	$(document).ready(function(){
		//alert("ready");
		/* search box code model4 started */
		$("#search_button_add").click(function(){
			
			alert("search");
			var rfid_number=$("#search_rfid_number").val();						
			rfid_number=$.trim(rfid_number);			
			var searchOption=$("#search_searchOption").val();			
			alert(searchOption);			
			var regx = /^[A-Za-z0-9]+$/;					
			if(rfid_number==""||!regx.test(rfid_number)){				
				$("#search_rfid_number").css("border-color","red");
				$("#rfid_number_status_error501").show();
				
				$("#rfid_number_status_error").hide();
				$("#rfid_number_status_success").hide();						
			}		
			else{	
				//alert("else");
				 $.ajax({
					//alert("else");
					url:"${pageContext.request.contextPath}/school_admin/persons/staff/search",
					type:"POST",
					data:'rfid_number='+rfid_number+'&searchOption='+searchOption,
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
    
<script type="text/javascript" class="showcase">
$(function(){
    $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
        	 var va=$(this).find(' input[type="hidden"] ').val();
         if(key=="edit"){        	
             window.location="staff/updatestaff?id="+va;
         }
         if(key=="delete"){        	         	 
             window.location="staff/deletestaff?id="+va;
         }
         if(key=="view"){        	         	 
             window.location="staff/viewstaff?id="+va;
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
						<li>Subscribers</li>
						<li>Staff</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				<c:if test="${error=='busCapacityExceed'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Sorry you can not add staff.Bus capacity exceeding
						</div>
				</c:if> 
				<c:if test="${error=='allocated'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Sorry you can not delete staff because staff allocated to <b><c:out value="${name}"/></b>  Student
						</div>
				</c:if>

				<c:if test="${error_message=='noMatching'}">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>No
							match found...........</strong>

					</div>
				</c:if>

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Staff</h3>
						<span>Create,Update,Delete Staff</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Staffs</div>
								<div class="value">
									<c:out value="${staffs.size()}"></c:out>
								</div>
							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>			
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
				<div class="col-md-8">
					<a href="staff/add" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add Staff</span>
					</a>		
					<a data-toggle="modal" href="#myModal5" class="btn btn-danger" id="deleteButton"> <i
						class="icon-trash"></i> <span>Delete</span>
					</a>			
				</div>
				</c:if>
				<div class="col-md-12" align="right">
					
					<a data-toggle="modal" href="#myModal4" class="btn btn-success" id="rfid_create_click" >
						<i class="icon-search"></i> <span> Search</span>
					</a>
				</div>
				
				
				<!-- <div class="form-group" >
					<label class="col-md-2 control-label"> Appended </label>
					<div class="col-md-5" align="right">
						<div class="input-group">
							<input type="text" class="form-control"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">Go!</button>
							</span>
						</div>
					</div>
				</div> -->

				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Staffs List
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
											<th class="checkbox-column">
												<input type="checkbox" class="uniform" id="allSelectId">	
											</th>
											<th>S.No</th>
											<th>Staff Name</th>
											<th>RFID Number</th>
											<th>Staff Id</th>
											<th>Phone Number</th>
											<th colspan="2">From Home</th>
											<th colspan="2">From School</th>
											
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
										<tr>
										<th colspan="6"></th>										
										<th>Bus</th>
										<th>Stop</th>
										<th>Bus</th>
										<th>Stop</th>
											
											<!-- <th style="width: 190px;">Options</th> -->
										</tr>
									</thead>
									<tbody>
										<c:set value="0" var="x"></c:set>
										<c:forEach items="${staffs}" var="staff">
										
											<c:choose>
												<c:when test="${login_role  == 'ROLE_ADMIN' }">
													<tr class="context-menu-one box menu-1">
												</c:when>
												<c:otherwise>
													<tr>
												</c:otherwise>
											</c:choose>	

											<!-- <tr class="context-menu-one box menu-1"> -->
												<td class="checkbox-column"><input type="checkbox"
													class="uniform" id="singleCheckId">
												<input type="hidden" value="${staff.id}" id="staffHiddenId">
												</td>
												<td><c:set value="${x+1 }" var="x"></c:set>
												<c:out value="${x  }"></c:out>
												<td><b><c:out value="${staff.full_name}"></c:out></b> 												
												</td>
												<td><c:out value="${staff.rfid_number}"></c:out></td>
												<td><c:out value="${staff.staff_id}"></c:out></td>
												<td><c:out value="${staff.mobile_number}"></c:out></td>
												<td><c:out value="${staff.bus_licence_number_home }"></c:out></td>
											<td><c:out value="${staff.stop_name_home }"></c:out></td>
											<td><c:out value="${staff.bus_licence_number_school }"></c:out></td>
											<td><c:out value="${staff.stop_name_school }"></c:out></td>		
							
												<%-- <td>																			
													<a class="btn btn-sm btn-info" href="staff/updatestaff?id=<c:out value="${staff.id}" />&bus_licence_number=<c:out value="${staff.bus_licence_number}" />"><i class=" icon-pencil"></i>&nbsp;Update </a> 
													<c:forEach items="${students}" var="student">
													<c:if test="${student.rfid_number ne staff.staff_rfid}">
													<a class="btn btn-sm btn-danger" href="staff/deletestaff?id=<c:out value="${staff.id}" />&staff_rfid=<c:out value="${staff.staff_rfid}" />"><i class=" icon-trash"></i>&nbsp;Remove</a>
													</c:if>
													</c:forEach>
												</td> --%>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								
							</div>
						</div>
					</div>
				</div>
				
			</div>
			<!-- /.container -->
			
		</div>
	</div>
	
	<div class="modal fade" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-body">
					<!--=== Page Content ===-->
					<div class="row" id="driver_form_add" style="display: none">
						<!--=== Validation Example 1 ===-->
						<div class="col-md-12">
							<div class="widget box">
								<div class="widget-header">
									<h4>
										<i class="icon-reorder"></i>Add New Driver Form
									</h4>
								</div>
								<div class="widget-content">
									<form class="form-horizontal row-border" id="driver_form"
										action="#" name="pForm" method="post">

										<div class="form-group">
											<label class="col-md-3 control-label">Select RFID<span
												class="required">*</span></label>
											<div class="col-md-9 clearfix">
												<select name="driver_rfid_number"
													class="col-md-12 select2 full-width-fix required"
													id="driver_rfid_number" style="border-color: grey;">
													<option>hmm</option>

												</select> <label for="chosen1" generated="true"
													class="has-error help-block" style="display: none;"></label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">Driver Name<span
												class="required">*</span></label>
											<div class="col-md-9">
												<input type="text" name="req1" class="form-control required"
													id="driver_name">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label">Phone Number
												[8-12] <span class="required">*</span>
											</label>
											<div class="col-md-9">
												<input type="text" name="range1"
													class="form-control required digits" id="phone_number">
											</div>
										</div>

										<div class="alert alert-danger fade in" id="input_errors"
											style="display: none;">

											<strong>Error!</strong> Please fill all the fields which are
											Marked *
										</div>
										<div class="form-actions">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Cancel</button>
											<input type="submit" value="Add Driver"
												class="btn btn-primary pull-right"
												id="add_new_driver_action">
										</div>
									</form>
								</div>
							</div>
							<!-- /Validation Example 1 -->
						</div>

					</div>
					<div id="loading" style="display: none">

						<div class="alert alert-info fade in">

							<strong>Info!</strong> Please wait fetching data from database
						</div>
					</div>					
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>

	<!-- search model4 started -->
	<div class="modal fade" id="myModal4">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Search Form:</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">

						<label class="col-md-5 control-label">Search By: <span
							class="required">*</span></label>

						<div class="col-md-7 clearfix">
							<select name="searchOption" id="search_searchOption"
								class="col-md-5 select2 full-width-fix required"
								style="border-color: grey;">

								<!--  <option>select</option>  -->

								<option value="rfid_number">RFID</option>
								<option value="full_name">StaffName</option>
								<option value="mobile_number">Phone Number</option>
							</select>
						</div>
						<br> <br> <label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-5 control-label">Enter search key: <span
							class="required">*(Case Sensitive)</span></label>
						<div class="col-md-6">
							<input type="text" name="search_rfid_number"
								id="search_rfid_number" class="form-control"
								style="width: 300px;">
						</div>
						<br>

						<!-- <div id="loading" style="display: none;">
							<h3>Fetching data from DataBase please wait</h3>
						</div> --><br>

						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							Please enter valid search key
						</div>

						<div class="alert alert-danger fade in"
							id="rfid_number_status_error2" style="display: none;">
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							 oh! Some problem occurred, Please try to reload page again
						</div
						>
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
	
	<!-- Modal dialog -->
	<div class="modal fade" id="myModal5">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Delete Form</h4>
				</div>
				<div class="modal-body">Are you sure you want delete?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						id="deleteButtonAction">Yes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>