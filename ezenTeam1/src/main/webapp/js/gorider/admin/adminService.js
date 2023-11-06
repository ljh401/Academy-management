// 서비스 이용 현황 함수
 ServiceStatus();
function ServiceStatus(){
	    $.ajax({
      
            url : "/ezenTeam1/AdminServiceControoler",   
            method : "get",
            data : {type : 1},      
           success : r=>{ console.log(r);
			   let listbox =document.querySelector('.listbox');
			   let html =`<ul class="listTitle rlistR">
               						 <li class="col15">R번호</li>
                					<li class="col15">S번호</li>
                					<li class="col10">★</li>
               						<li class="col40">리뷰</li>
                					<li class="col20">서비스일자</li>
          					 </ul>`;
          					 
          					 for(let i=0; i<r.length; i++){
								let rno = r[i].rno; // rno 변수 설정
  				 				let sno = r[i].sno; // rid 변수 설정
    			 				let spoint = r[i].spoint; // rdate 변수 설정
    			 				let sreview = r[i].sreview; // rid 변수 설정
    			 				let sdate = r[i].sdate; // rdate 변수 설정
								    html += `
						               	 <ul class="listContent rlistR">
               								<li class="col15">${rno}</li>
							                <li class="col15">${sno}</li>
							                <li class="col10">${spoint}</li>
							                <li class="col40">${sreview}</li>
							                <li class="col20">${sdate}</li>
           								</ul>`;
							   }
						listbox.innerHTML = html;
			   } , 
			   error : e =>{ console.log(e);}                          
   });
   
}


































