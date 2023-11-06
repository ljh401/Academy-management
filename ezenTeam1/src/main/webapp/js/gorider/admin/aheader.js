
 function goMain(){
	 location.href='/ezenTeam1/gorider/admin/amain.jsp';
 }
 function goMenu(to){
	 
	 if(to == 'R'){
		 location.href='aRequestList.jsp'
	 } else if(to == 'S'){
		 location.href='aServiceList.jsp'
	 } else if(to == 'V'){
		 location.href='aReviewList.jsp'
	 } else if(to == 'D'){
		 location.href='aDepositList.jsp'
	 } else if(to == 'B'){
		 location.href='board/aBoardList.jsp'
	 	 
	 } else{
		 location.href='aClosingList.jsp'

	 }
	 
	 
 }
Request();
function Request(){
	$.ajax({
      
            url : "/ezenTeam1/AdminController",   
            method : "get",
            data : {type : 3},      
           success : r=>{
			   console.log(r)
			   
			
			   document.querySelector('.hinfo').innerHTML=`신규요청 : ${r}건`;
			   
			   } ,
                          
   });
}
