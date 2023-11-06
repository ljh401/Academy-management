console.log('js실행');

// 라이더 입금내역 js 

// 1.입금현황 함수
depositCount();
function depositCount(){
      $.ajax({
      
            url : "/ezenTeam1/AdminController",  
            method : "get",
            data : {type : 4},      
           success : r=>{  

			   console.log(r)
			   let listbox = document.querySelector('.listbox');
			   let html=``;
			   html =`<ul class="listTitle rlistR">
			                <li class="col15">R번호</li>
			                <li class="col15">S번호</li>
			                <li class="col25">서비스일자</li>
			                <li class="col20">입금액</li>
			                <li class="col25">입금</li>
           				</ul>`;
           		for(let i=0; i<r.length; i++){
					  		let rno = r[i].rno; // rno 변수 설정
  				 	  		let sno = r[i].sno; // rid 변수 설정
    			 			let sdate = r[i].sdate; // rdate 변수 설정
    			 			let spayment = r[i].spayment; // rid 변수 설정
    			 			
					   
					   html+=`<ul class="listContent rlistR">
					                 <li class="col15">${rno}</li>
					                <li class="col15">${sno}</li>
					                <li class="col25">${sdate}</li>
					                <li class="col20">${spayment}</li>
					                <li class="col25"><button class="btnPd6" onclick="deposit(${rno}, ${sno},${spayment})" type="button" >입금</button></li>
           						</ul>`;
				   }
			   		listbox.innerHTML=html;
			   } ,
			})
            console.log(r)
            let listbox = document.querySelector('.listbox');
            let html=``;
            html =`<ul class="listTitle rlistR">
                         <li class="col15">R번호</li>
                         <li class="col15">S번호</li>
                         <li class="col25">서비스일자</li>
                         <li class="col20">입금액</li>
                         <li class="col25">입금</li>
                       </ul>`;
                 for(let i=0; i<r.length; i++){
                       let rno = r[i].rno; // rno 변수 설정
                          let sno = r[i].sno; // rid 변수 설정
                       let sdate = r[i].sdate; // rdate 변수 설정
                       let spayment = r[i].spayment; // rid 변수 설정
                       
                  
                  html+=`<ul class="listContent rlistR">
                                <li class="col15">${rno}</li>
                               <li class="col15">${sno}</li>
                               <li class="col25">${sdate}</li>
                               <li class="col20">${spayment}</li>
                               <li class="col25"><button class="btnPd6" onclick="deposit(${rno}, ${sno},${spayment})" type="button" >입금</button></li>
                             </ul>`;
               }
                  listbox.innerHTML=html;
 			}
 
 
 function deposit(rno, sno, spayment){
   console.log('deposit() 실행')
   console.log('rno : '+ rno);
   console.log('sno :'+ sno)
   console.log('spayment : '+ spayment)
   
    $.ajax({
            url : "/ezenTeam1/AdminServiceControoler",    
            method : "post",
            data : {rno : rno, sno:sno, spayment:spayment},      
           success : r=>{
            console.log(r)
            alert('입금 성공했습니다.')
            } ,

                          
   });
}
function deposit(rno, sno, spayment){
   console.log('deposit() 실행')
   console.log('rno :: '+ rno);
   console.log('sno :: '+ sno)
   console.log('spayment :: '+ spayment)
   
    $.ajax({
            url : "/ezenTeam1/AdminServiceControoler",    
            method : "post",
            data : {rno : rno, sno:sno, spayment:spayment},      
           success : r=>{
            console.log(r)
            } ,
                          
   });
}