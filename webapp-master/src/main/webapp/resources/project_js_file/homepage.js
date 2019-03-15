$(document).ready(function() {

	setInterval(function() {
		
		$.ajax({
			
			url:"homepage/getHomePageData",
			type:"POST",
			success:function(result){
				//alert(result);
				var o=$.parseJSON(result);
				for(var i=0;i<o.length;i++){
					
				}
			},
			error:function(){
				
			}
			
		});
	}, 9000);
	
});