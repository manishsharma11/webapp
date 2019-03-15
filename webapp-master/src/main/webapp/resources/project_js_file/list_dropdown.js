/**
 * 
 */
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
             window.location="drivers/removedriver?id="+va;
         }
        },
        items: {
            "edit": {name: "<i class='icon-pencil'>&nbsp;&nbsp;&nbsp;Update</i><br>", icon: "edit1"},
            
            "delete": {name: "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete</i>", icon: "delete"},   
            
            "view": {name: "<i class='icon-trash '>&nbsp;&nbsp;&nbsp;Delete</i>", icon: "view"},   
        }
    });
    
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    })
});