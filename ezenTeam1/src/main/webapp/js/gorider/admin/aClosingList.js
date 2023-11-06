console.log('js실행')
servicestatus();
function servicestatus(){
	$.ajax({
      
            url : "/ezenTeam1/AdminServiceControoler",   
            method : "get",   
            data : {type : 3},      
           success : r =>{console.log(r)} ,       
                 
             
   });
}