console.log('list연결');

//비로그인 리턴
if(loginState== false){
	alert('로그인이 필요합니다.');
	location.href='/ezenTeam1/gorider/rider/rlogin.jsp'
}
 //리스트출력
onList('N',0);
function onList(btype, limit){
	console.log("btype :: "+ btype);
	if(btype == 'N'){
		document.querySelector('.btypeN').style.borderBottom  = "3px solid var(--dgray)";
		document.querySelector('.btypeN').style.backgroundColor  = "var(--dyellow)";
		document.querySelector('.btypeE').style.backgroundColor  = " #000";
		document.querySelector('.btypeE').style.borderBottom  = "3px solid #000";
		document.querySelector('.btypeE').style.color  = "var(--white)"
	}else if(btype =='E'){
		document.querySelector('.btypeE').style.borderBottom  = "3px solid var(--dgray)";
		document.querySelector('.btypeE').style.backgroundColor  = "var(--dyellow)";
		document.querySelector('.btypeN').style.backgroundColor  = " #000";
		document.querySelector('.btypeN').style.borderBottom  = "3px solid #000";
		document.querySelector('.btypeN').style.color  = "var(--white)"

	}
	console.log("onList");
	//아작스함수 (출력)호출

    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"get",
        data: {type : btype , btarget:'R',limit : limit },

        success: r => {
            console.log(r);

             let bContBox = document.querySelector('.bContBox');
             let html = ``;
             r.forEach( b => {
             html +=	`<div class="btitle">
             				[${btype == 'N'? '공지' : '이벤트' }]
             				<a href="rBoardView.jsp?bno=${ b.bno }&bt=R">${b.btitle}</a>
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





























