$(document).ready(function(){
	setInterval(function() {
		$.ajax({
			url:"/sts/school_guest/homepage/getNotifications",
			type:"POST",
			success:function(result){
				$("#add_notifications").empty();
				var obj=JSON.parse(result);
				//add_notifications
				for (var i=0;i<obj.length;i++){
					//alert(obj[i].message_type);
				var append=	'<li > '+
							'<div class="col-left">';
							if(obj[i].message_type=="bus_started"){
								append=append+'<span class="label label-success"><i class="icon-plus"></i></span>';
							}
							if(obj[i].message_type=="bus_arrived_to_stop"){
								
								append=append+'<span class="label label-info"><i class="icon-envelope"></i></span>';
							}
							if(obj[i].message_type=="bus_arrived"){
								
								append=append+'<span class="label label-info"><i class="icon-envelope"></i></span>';
							}
							if(obj[i].message_type=="bus_verylate"){
								append=append+'<span class="label label-danger"><i class="icon-warning-sign"></i></span>';
							}
							if(obj[i].message_type=="bus_late"){
								append=append+'<span class="label label-danger"><i class="icon-warning-sign"></i></span>';
							}
							if(obj[i].message_type=="bus_ontime"){
								append=append+'<span class="label label-info"><i class="icon-bullhorn"></i></span>';
							}
							
						append=append+	'</div>'+
							'<div class="col-right with-margin">'+
								
									'<span class="message">'+obj[i].notification+'</span>'+
							'</div>'+
						'</li>';
						$("#add_notifications").append(append);
				}
			}
		});
	}, 3000);
	
});