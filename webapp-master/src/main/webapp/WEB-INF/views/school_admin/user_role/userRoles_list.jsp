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
    $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
        	 var roleId=$(this).find(' input[type="hidden"] ').val();
         if(key=="edit"){        	            
             window.location="changeroleOfUsers?id="+roleId;
         }
         if(key=="delete"){        	         	 
        	 window.location="deletenewRole?id="+roleId;
         }         
        },
        items: {
            "edit": {name: "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update Role</i><br>", icon: "edit1"},
            
            "delete": {name: "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete Role</i>", icon: "delete"},            
        }
    });
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    })
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
        selector: '.context-menu-two', 
        callback: function(key, options) {
        	 var roleId=$(this).find(' input[type="hidden"] ').val();
         if(key=="edit"){        	
        	 window.location="changeroleOfUsers?id="+roleId;
         }
        },
        items: {
        	 "edit": {name: "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update Role</i><br>", icon: "edit1"},      
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
						<li>UserRoles</li>
						<li>Roles List</li>
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
						<h3>Users List</h3>
						<span>Create,Update,Delete Users List</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
					<div class="col-sm-6 col-md-3">
						<div class="statbox widget box box-shadow">
							<div class="widget-content">
								<div class="visual green">
									<i class="icos-wifi-signal"></i>
								</div>
								<div class="title">Total Roles</div>
								<div class="value">
									<c:out value="${Roles.size()}"></c:out>
								</div>
							</div>
						</div>
						<!-- /.smallstat -->
					</div>
					<!-- /.col-md-3 -->
				</div>
				<!--=== Normal ===-->
				<c:if test="${login_role  == 'ROLE_ADMIN' }">
				<div class="col-md-12">
					<a href="addnewRole" class="btn btn-warning"> <i
						class="icon-plus"></i> <span>Add Role</span>
					</a>					
					<!-- <div class="btn btn-danger" id="deleteButton"> <i
						class="icon-trash"></i> <span>Delete</span>
					</div>	 -->
				</div>	
				</c:if>							
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Roles List: 									
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
											<th>Role Name</th>																																
										</tr>
									</thead>
									<tbody>
									<c:set value="0" var="x"></c:set>
										<c:forEach items="${Roles}" var="role">											
											<tr class="context-menu-one box menu-1">																							
											 <td class="checkbox-column">
												<input type="checkbox" class="uniform" id="singleCheckId">
												<input type="hidden" value="${role.role_id}" id="roleIdHidden">
												</td>	  											
												<td><c:set value="${x+1 }" var="x"></c:set>
												<c:out value="${x}"></c:out>
												<%-- <input type="hidden" value="${role.id}" id="roleIdHidden"> 			 --%>									
												</td>
												<td>
												<%-- <c:choose>
												<c:when test="${role.role eq 'ROLE_ADMIN'}">
													<c:out value="Admin"></c:out>
												</c:when>
												<c:otherwise>
												<c:out value="Guest"></c:out>
												</c:otherwise>
												</c:choose> --%>
												<c:out value="${role.role_name}"></c:out>
												</td>												
											</tr>
											
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /Normal -->
			</div>
			<!-- /.container -->
		</div>
	</div>
</body>
</html>