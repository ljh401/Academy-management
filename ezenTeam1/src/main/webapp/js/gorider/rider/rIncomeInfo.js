
console.log('list연결');

//비로그인 리턴
if(loginState== false){
	alert('로그인이 필요합니다.');
	location.href='/ezenTeam1/gorider/rider/rlogin.jsp'
}


/*

	
	

*/ 

 //리스트출력
getIncomeInfo('AD');
function getIncomeInfo(type){
	console.log("type :: "+ type);
	if(type == 'AD'){ // 입금 된 목록
		document.querySelector('.btypeN').style.borderBottom  = "3px solid var(--dgray)";
		document.querySelector('.btypeN').style.backgroundColor  = "var(--dyellow)";
		document.querySelector('.btypeE').style.backgroundColor  = " #000";
		document.querySelector('.btypeE').style.borderBottom  = "3px solid #000";
		document.querySelector('.btypeE').style.color  = "var(--white)"
	}else if(type =='ND'){ // 입금 예정 목록 
		document.querySelector('.btypeE').style.borderBottom  = "3px solid var(--dgray)";
		document.querySelector('.btypeE').style.backgroundColor  = "var(--dyellow)";
		document.querySelector('.btypeN').style.backgroundColor  = " #000";
		document.querySelector('.btypeN').style.borderBottom  = "3px solid #000";
		document.querySelector('.btypeN').style.color  = "var(--white)"

	}
	console.log("getIncomeInfo");
	//아작스함수 (출력)호출

    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"get",
        data: {type : type }, // type이 'AD' , 'ND' 를 컨트롤러에게 보낸다.

        success: r => {
            console.log(r);
            
             let histroyBox = document.querySelector('.histroyBox');
             let html = ``;

             r.forEach( b => { console.log(b);
             html +=	`<ul class="hContBox">
	            	 		<li><span class="hTitleA">운행번호</span><span class="hContent">${b.sno}</span><li>
	            	 		<li><span class="hTitleA">운행일자</span><span class="hContent"> ${b.sdate}</span><li>`
            	 if(type == 'AD'){
            		 html += ` <li><span class="hTitleA">입금일자</span><span class="hContent"> ${b.ddate}</span><li>
            		 <li><span class="hTitleA">운행수입</span><span class="hContent">  ${b.spayment}원</span><li>`;
            	 } else{
             html+=`<li><span class="hTitleA">예정수입</span><span class="hContent">  ${b.spayment}원</span><li>`
            	 }
            	 html += `</ul>` ;
			

               });
             histroyBox.innerHTML = html;
			

            },
        error: e => { console.log("에러"+ e)}

    });

}

//상세보기
function onViewDetails(bno){
	console.log("onViewList");

}




console.log('js실행');

// 1.입금현황 함수
depositCount();
function depositCount(){
      $.ajax({
      
            url : "/ezenTeam1/AdminController",  
            method : "get",
            data : {type : 4},      
           success : r=>{  

			   console.log(r);
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
    			 			
					   
					   html += `<ul class="listContent rlistR">
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
                       
                  
                  html += `<ul class="listContent rlistR">
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
 
 
 
 
