console.log('list연결');
// 비로그인 리턴
if(loginState== false){
	alert('로그인이 필요합니다.');
	location.href='/ezenTeam1/gorider/member/mlogin.jsp'
}
//상세보기
onView();
function onView(){
	//URL의 쿼리스트링가져오기
	let urlParams = new URL(location.href).searchParams
		console.log( urlParams );
	let bno = urlParams.get("bno");
	let bt = urlParams.get("bt")


	//아작스함수 (저장)호출
    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"get",
        data: {'type':'getDetail', 'bno':bno},
        success: r => {
            //console.log("성공");
            //console.log(r);
            //console.log(Object.values(r.fileList).length);

            //let btarget = "'" +r.btarget+"'"
            if(bt !== r.btarget){ alert('해당 게시물에 대한 권한이 없습니다.'); return;}
            let bviewBox = document.querySelector('.bviewBox');
            let html = ` <li class="bvTitle">[${r.btype == 'N'? '공지':'이벤트'}] ${r.btitle}<span class="bvDate"> ${r.bdate}</span></li>
            			<div class="scrollbox">	`

            if (r.btype == 'E'){
				html +=		`<li class="bvEventPeriod">이벤트기간 :  ${r.bstartdate} ~  ${r.benddate}</li>`
			}

			if (Object.values(r.fileList).length > 0) {
				Object.values(r.fileList).forEach((img)=>{
					html += `<li class="bvImg"><img src="/ezenTeam1/gorider/admin/board/bfiles/${img}"></li>`

				});
			}

				html += `<li class="bvCont"> ${r.bcontent}</li></div> `

           bviewBox.innerHTML = html;


        },
        error: e => { console.log("에러"+ e)}

    });
}

//상세보기
function onViewDetails(bno){
	console.log("onViewList");

}

//수정하기
function onUpdate(){

}

//삭제하기
function onDelete(){

}
//글작성하기 페이지로
function onList(){
	location.href="mBoardList.jsp"
}


























