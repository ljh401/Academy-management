console.log('list연결');


 //리스트출력
onList('N',0);
function onList(btype, limit){
	console.log("btype :: "+ btype);
	if(btype == 'N'){
		document.querySelector('.btypeN').style.borderBottom  = "3px solid var(--dgray)";
		document.querySelector('.btypeE').style.borderBottom  = "none"
	}else if(btype =='E'){
		document.querySelector('.btypeN').style.borderBottom  = "none"
		document.querySelector('.btypeE').style.borderBottom  = "3px solid var(--dgray)";

	}
	console.log("onList");
	//아작스함수 (출력)호출

    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"get",
        data: {type : btype , btarget:'U',limit : limit },

        success: r => {
            console.log(r);

             let bContBox = document.querySelector('.bContBox');
             let html = ``;
             r.forEach( b => {
             html +=	`<div class="btitle">
             				[${btype == 'N'? '공지' : '이벤트' }]
             				<a href="mBoardView.jsp?bno=${ b.bno }&bt='U'">${b.btitle}</a>
             				<span>${b.bdate}</span>
             			</div>`;

               });
               bContBox.innerHTML = html;


            },
        error: e => { console.log("에러"+ e)}

    });

}

//상세보기
function onViewDetails(bno){
	console.log("onViewList");

}




























