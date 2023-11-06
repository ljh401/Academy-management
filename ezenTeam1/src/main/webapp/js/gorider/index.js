
console.log('index.js 연결')

//1. 메인화면 공지사항/ 이벤트 출력
onNList('N',5);
onNList('E',5);
function onNList(btype, limit){
	console.log("onNList");

      let html = ``;

	if(btype == 'N'){

		$.ajax({
	        url:"/ezenTeam1/BoardController",
	        method:"get",
	        data: {type : btype , btarget:'U',limit : limit },

	        success: r => {
	            console.log(r);
	            console.log(r.length);
	            let btN = document.querySelector('.btN');
	            if(r.length > 0){

		             	r.forEach( b => {
		             		html +=	`<ul class="mbCont">
		             				 <li class="col70 mbContTitle"><a href="mBoardView.jsp?bno=${ b.bno }&bt=U">${b.btitle}</a><</a></li>
		             				 <li class="col20 mbContDate">${b.bdate}</li>
		             				</ul>`;

		               });//f end
		             	btN.innerHTML = html;
	            } else {
	            	btN.innerHTML = `<ul class="mbCont">
										<li class="col70 mbContTitle">등록된 공지사항이 없습니다.</li>
				 						<li class="col20 mbContDate"></li>
					 			</ul>`;
	            }

	        },
	        error: e => { console.log("에러"+ e)}

	    });

	} else if(btype == 'E'){

		$.ajax({
	        url:"/ezenTeam1/BoardController",
	        method:"get",
	        data: {type : btype , btarget:'U',limit : limit },

	        success: r => {
	            console.log(r);

	             let btE = document.querySelector('.btE');
	             if(r.length > 0){
	            	 r.forEach( b => {
	            	 html +=	`<ul class="mbCont">
	            		 			<li class="col70 mbContTitle"><a href="mBoardView.jsp?bno=${ b.bno }&bt=U">${b.btitle}</a></li>
             				 		<li class="col20 mbContDate">${b.bdate}</li>
             					</ul>`;
	            	 });//f end
	            	 btE.innerHTML = html;
	             } else {
	            	 btE.innerHTML = `<ul class="mbCont">
     		 							<li class="col70 mbContTitle">등록된 이벤트가 없습니다.</li>
 				 						<li class="col20 mbContDate"></li>
	            		 			</ul>`;
	             }

	            },
	        error: e => { console.log("에러"+ e)}

	    });

	}



}
