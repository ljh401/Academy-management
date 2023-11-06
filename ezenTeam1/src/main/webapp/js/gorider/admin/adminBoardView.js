console.log('연결');

//상세보기
onView();
function onView(){
	//URL의 쿼리스트링가져오기
	let urlParams = new URL(location.href).searchParams
		console.log( urlParams );
	let bno = urlParams.get("bno");

	//아작스함수 (저장)호출
    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"get",
        data: {'type':'getDetail', 'bno':bno},
        success: r => {
            console.log("성공");
            console.log(r);
            console.log(Object.values(r.fileList).length);

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


//수정하기
function onUpdate(){

}

//삭제하기
function onDelete(){

}
//글목록 페이지로
function onList(){
	location.href="aBoardList.jsp"
}