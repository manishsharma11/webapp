/**
 * 
 */
$(document).ready(function(){
		
	$("#unlock").click(function(){
		
		var c_pass=$("#c_password").val();
		var c_uname=$("#userCssId").val();
		if(c_pass==""){
			
			$("#c_password").css("border-color","brown");
			$("#c_password_error").empty().append("<font color='red'>Please enter current password..</font>");
		}
		else{
			
			$("#c_password").css("border-color","grey");
			$("#c_password_error").empty().append("<font color='sienna'>Authenticating please wait....</font>");	
			$.ajax({
				type: "GET",
				url: 'verifypasswordOfUser',
				cache: false,
				data:"password="+c_pass+ "&uname=" + c_uname,
				success: function(response){
					response=$.trim(response);
					if(response=="ok"){
						$("#c_password_error").empty().append("<font color='green'>Please enter current password..</font>");
						$("#current_password").hide();
						$("#new_password").show();
						
					}
					else{
						$("#c_password_error").empty().append("<font color='red'>Authentication failed.wrong password.</font>");
					
					}
				}
			});
		}
	});
	
	$("#change_password").click(function(){
		var c_password_new=$("#c_password_new").val();
		var c_password_new_repeat=$("#c_password_new_repeat").val();
		var c_password_new_check=false,c_password_new_repeat_check=false;
		if(c_password_new==""){
			$("#c_password_new").css("border-color","brown");
			$("#c_password_error_new").empty().append("<font color='red'>Please enter new password..</font>");
			c_password_new_check=false;
		}
		else{
			$("#c_password_new").css("border-color","grey");
			$("#c_password_error_new").empty().append("<font color='green'>ok</font>");
			c_password_new_check=true;
		}
		if(c_password_new_repeat==""){
			$("#c_password_new_repeat").css("border-color","brown");
			$("#c_password_error_new_repeat").empty().append("<font color='red'>Please repeat new password</font>");
			c_password_new_repeat_check=false;
		}
		else if(c_password_new_repeat!=c_password_new){
			$("#c_password_new_repeat").css("border-color","brown");
			$("#c_password_error_new_repeat").empty().append("<font color='red'>passwords should be matched..</font>");
			c_password_new_repeat_check=false;
		}
		else{
			$("#c_password_new_repeat").css("border-color","grey");
			$("#c_password_error_new_repeat").empty().append("<font color='green'>ok</font>");
			c_password_new_repeat_check=true;
		}
		if(c_password_new_repeat_check==false || c_password_new_check==false){
			$("#changepasswordstatus").empty();
		}
		else{
			
			$("#changepasswordstatus").empty().append("<font color='black'>Please wait...</font>");
			$.ajax({
				type: "GET",
				url: 'changepasswordrequestOfUsers',
				cache: false,
				data:"c_pass="+$("#c_password").val()+"&c_newpass="+c_password_new,
				success: function(response){
					response=$.trim(response);
					if(response=="ok"){
						$("#current_password").hide();
						$("#new_password").hide();
						$("#password_changed_successfully").show();
						$("#changepasswordstatus").empty().append("<font color='green'>Password has been changed</font>");
						//window.location.reload();
					}
					else{
						//alert("something went wrong ... please try again");
						$("#changepasswordstatus").empty().append("<font color='red'>something went wrong ... please try again</font>");
					}
				}
			});
		}
	});
});