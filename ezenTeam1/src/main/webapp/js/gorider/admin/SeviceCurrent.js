getServiceUsageStatus();
function getServiceUsageStatus(){
	    $.ajax({
      
            url : "/ezenTeam1/AdminServiceControoler",   
            method : "get",
            data : {type : 2},      
           success : r=>{
			   console.log(r);
			      let listbox =document.querySelector('.listbox');
			  	   let html =`<ul class="listTitle rlistR">
                					<li class="col20">현상태</li>
                					<li class="col20">S번호</li>
                					<li class="col20">M번호</li>
                					<li class="col20">R번호</li>
                					<li class="col20">요청일</li>
           						</ul>`;
           				for(let i=0; i<r.length; i++){
							 
  				 				let sno = r[i].sno; // rid 변수 설정
    			 				let mno = r[i].mno; // rdate 변수 설정
    			 				let rno = r[i].rno; // rid 변수 설정
    			 				let sdate = r[i].sdate; // rdate 변수 설정
    			 				let sfromla = r[i].sfromla;
    			 				let stola = r[i].stola;
    			 				 let status = '';

								    if (sno && !rno) {
								        status = '요청상태';
								    } else if (sno && rno && !sfromla) {
								        status = '대기상태';
								    } else if (sno && rno && sfromla && !stola) {
								        status = '진행상태';
								    } else if (sno && rno && sfromla && stola) {
								        status = '종료상태';
								    }
							      html += `<ul class="listContent rlistR">
									                <li class="col20">${status}</li>
									                <li class="col20">${sno}</li>
									                <li class="col20">${mno}</li>
									                <li class="col20">${rno}</li>
									                <li class="col20">${sdate}</li>
									            </ul>`;
						 			  }
						listbox.innerHTML = html;
			   } ,
                          
   });
   
}