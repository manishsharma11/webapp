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
		$("#rfid_number_status_error501").hide();
		//alert("ready");
		/* search box code model4 started */
		$("#addEmail_click").click(function(){	
			$("#rfid_number_status_error501").hide();
		});
		$("#email_add_button").click(function(){	
			//$("#rfid_number_status_error501").hide();
			//alert("email_add");
			var email=$("#email_id").val();				
			email=$.trim(email);
			
			
			
			//alert(email);						
			var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
		   				
			if(!(pattern.test(email))){				
				$("#search_rfid_number").css("border-color","red");
				$("#rfid_number_status_error501").show();											
			}		
			
			else{	
				
				 $.ajax({				
					url:"${pageContext.request.contextPath}/school_admin/addEmails",
					type:"POST",
					data:'email='+email,
					 success:function(result){
						//alert(result);	
						// document.location.reload(true);
						 window.location.href = result;
					}
				}); 
			}//else
		/* 	return true; */
		});
		/* search box model4 code ended */		
	});
	
</script>



<script type="text/javascript" class="showcase">
$(function(){
    $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
        	 var va=$(this).find(' input[type="hidden"] ').val();
        /*  if(key=="edit"){      
        	
        	 } */
         if(key=="delete"){        	         	 
        	 //alert(va);
             window.location="deleteEmail?id="+va;
         }
        },
        items: {
            /* "edit": {name: '<a data-toggle="modal" href="#myModal2"  >Edit</a><br>', icon: "edit1"}, */
            
            "delete": {name: "Delete", icon: "delete"},                       
        }
    });
    
    $('.context-menu-one').on('click', function(e){
    	
        console.log('clicked', this);
    });
    $('.context-menu-one').bind("contextmenu",function(e){
    	 var va=$(this).find(' input[type="hidden"] ').val();
       $("#email_id_edit").val(va);
    });
});
    </script>
	

<!-- Include CSS files -->
<%@ include file="jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="jsp-includes/js_files.jsp"%>

<!-- <script type="text/javascript">

function openWindow(){
		
	window.open('bus?bus_id=', 'popup_name','height=' + 600 + 
			',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
}

</script> -->
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
				<!-- Breadcrumbs line -->
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li><i class="icon-home"></i> Dashboard</li>
						<li><i class="icon-user"></i> Preferences</li>

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
						<h3>Preferences</h3>

					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>

				</div>




				<!--=== Inline Tabs ===-->
				<div class="row">
					<div class="col-md-12">
						<!-- Tabs-->
						<div class="tabbable tabbable-custom tabbable-full-width">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_overview"
									data-toggle="tab">Overview</a></li>
									<c:if test="${login_role  == 'ROLE_ADMIN' }">
								<li><a href="#tab_edit_account" data-toggle="tab">Edit
										Account</a></li>
										</c:if>
							</ul>
							<div class="tab-content row">
								<!--=== Overview ===-->
								<div class="tab-pane active" id="tab_overview">
									
									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-7">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Admin Preferences
													</h4>
												</div>
												<div class="widget-content">
													<form class="form-horizontal row-border" id="validate-2" 	action="changepassword" method="get">
													
														
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number:
															</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.number }"></c:out></label>
															</div>
														</div>
														
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSchool Coordinates</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Latitude:</label>
															<div class="col-md-9">
																<label  style="color: sienna;"  class="col-md-3"><c:out value="${adminPreferences.latitude }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Longitude:</label>
															<div class="col-md-9">
																<label  style="color: sienna;"  class="col-md-3"><c:out value="${adminPreferences.longitude }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSMS Gateway</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number (For alerts):
															</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.numberForAlers }"></c:out></label>
															</div>
														</div>
														
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEmail Proxy</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Serverhost</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.smtpServerHost }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Port </span></label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.smtpPort }"></c:out></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">From Mail </label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.fromEmail }"></c:out></label>
															</div>
														</div>
														

														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Email:</label>
															<div class="col-md-9">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${adminPreferences.e_mail }"></c:out></label>
															</div>
														</div> --%>

														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Email Password: </label>
															<div class="col-md-9">
																<label style="color: sienna;"  class="col-md-3"><c:out value="${admin_profile.password }"></c:out></label>
															</div>
														</div> --%>
														<div class="form-group">
														<font color="#000000">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspTo Emails</p></b>
															</font>
														</div>
														
														<c:forEach items="${emails}" var="emailId">
														<div class="form-group">
															<label class="col-md-3 control-label"></label>
															<div class="col-md-9 context-menu-one box menu-1" oncontextmenu="setemail()">
																<label  style="color: sienna;" class="col-md-3"><c:out value="${emailId.email }"></c:out></label>
																<input type="hidden" value="${emailId.id}" >
															</div>
														</div>
														
														</c:forEach>

														<div class="form-group">
														<div class="col-md-12" align="center">
															<a data-toggle="modal" href="#myModal1" class="btn btn-primary" id="addEmail_click">
															 <i class="icon-plus"></i> <span>Add Email</span>
															</a>
														</div>
														</div>
														

													</form>
												</div>
											</div>
											<!-- /Validation Example 1 -->
										</div>
									</div>
									<!-- /Edit Account -->	

								</div>
								<!-- /Overview -->

								<!--=== Edit Account ===-->
								
								<div class="tab-pane" id="tab_edit_account">
									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-7">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Admin Preferences
													</h4>
												</div>
												<div class="widget-content">
													<form class="form-horizontal row-border" id="validate-1" 	action="updatepreferences" method="post">
														<input type="hidden" name="id" value="${id}">
														<input type="hidden" name="u_id" value="46dgh346546dfghb456b54">
														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Admin Name
																<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="admin_name" minlength="3"
																	class="form-control required"
																	pattern="^\+?[A-Za-z\ ]+\*?$"
																	value="<c:out value="${admin_profile.admin_name }"></c:out>"
																	title="Name must be characters with spaces minimum 3 characters">
															</div>
														</div> --%>
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="number"
																	class="form-control required"
																	pattern="^\+?[0-9\-]+\*?$"
																	value="<c:out value="${adminPreferences.number }"></c:out>"
																	title="phone number must be numbers with symbols(+,-)">
															</div>
														</div>
														<div class="form-group">
														<font color="sienna">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSchool Coordinates</p></b>
															</font>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Latitude<span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="latitude"
																	value="<c:out value="${adminPreferences.latitude }"></c:out>"
																	class="form-control required" range="-90, +90">
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">Longitude<span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="longitude"
																value="<c:out value="${adminPreferences.longitude }"></c:out>"
																	class="form-control required" range="-90, +90">
															</div>
														</div>
														
														<div class="form-group">
														<font color="sienna">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSMS Gateway</p></b>
															</font>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">Phone
																Number (For Alerts)<span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="text" name="numberForAlers"
																	class="form-control required"
																	pattern="^\+?[0-9\-]+\*?$"
																	value="<c:out value="${adminPreferences.numberForAlers }"></c:out>"
																	title="phone number must be numbers with symbols(+,-)">
															</div>
														</div>
														
														<div class="form-group">
														<font color="sienna">
															<b><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
															&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEmail Proxy</p></b>
															</font>
														</div>

														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Serverhost <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="smtpServerHost"
																	class="form-control required" value="<c:out value="${adminPreferences.smtpServerHost }"></c:out>">
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-3 control-label">SMTP Port <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="smtpPort"
																	class="form-control required" value="<c:out value="${adminPreferences.smtpPort }"></c:out>">
															</div>
														</div>
														
														<div class="form-group">
															<label class="col-md-3 control-label">From Email <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="fromEmail"
																	value="<c:out value="${adminPreferences.fromEmail }"></c:out>"
																	class="form-control required email" >
															</div>
														</div>

														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Email <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="text" name="e_mail"
																	value="<c:out value="${adminPreferences.e_mail }"></c:out>"
																	class="form-control required email" >
															</div>
														</div> --%>

														<div class="form-group">
															<label class="col-md-3 control-label">Password <span
																class="required">*</span></label>
															<div class="col-md-9">
																<input type="password" name="password"
																value="<c:out value="${password}"></c:out>"
																	class="form-control required" minlength="5">
															</div>
														</div>
														<%-- <div class="form-group">
															<label class="col-md-3 control-label">Confirm
																Password <span class="required">*</span>
															</label>
															<div class="col-md-9">
																<input type="password" name="admin_pass_re"
																value="<c:out value="${admin_profile.password }"></c:out>"
																	class="form-control required" minlength="5"
																	equalTo="[name='password']">
															</div>
														</div> --%>

														<div class="form-actions">
															<input type="submit" value="Update"
																class="btn btn-primary pull-right">
														</div>
													</form>
												</div>
											</div>
											<!-- /Validation Example 1 -->
										</div>
									</div>
									<!-- /Edit Account -->
								</div>
								
								<!-- /.tab-content -->
							</div>
							<!--END TABS-->
						</div>
					</div>
					<!-- /Page Header -->

					<!--    Commuters List  -->

					<!--=== Managed Tables ===-->


					<!-- /Normal -->
				</div>
				<!-- /.container -->
			</div>
		</div>
	</div>

<!-- email model1 started -->
	<div class="modal fade" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Add Email form:</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">						
						
						<label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-4 control-label">Enter Email: <span
							class="required">*</span>
						</label>
						<div class="col-md-6">
							<input type="text" name="email"
								id="email_id" class="form-control required email" style="width: 300px;">
						</div><br> <br>
						
						<br><br>
						
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" >
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							Please enter valid email.....
						</div>						
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="email_add_button">Add</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- email model1 ended -->


<!-- email edit model2 started -->
	<div class="modal fade" id="myModal2">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Email Edit form....:</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">						
						
						<label for="chosen1" generated="true"
							class="has-error help-block" style="display: none;"></label> <label
							class="col-md-4 control-label">Enter Email: <span
							class="required">*</span>
						</label>
						<div class="col-md-6">
							<input type="text" name="email"
								id="email_id_edit" class="form-control required email" style="width: 300px;">
						</div><br> <br>
						
						<br><br>
						
						<div class="alert alert-danger fade in"
							id="rfid_number_status_error501" >
							<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
							Please enter valid email.....
						</div>						
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="email_add_button">Add</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- edit model2 ended -->
	<input type="hidden" value="" id="email_edit">
</body>
</html>