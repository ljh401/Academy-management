console.log('list연결');


 //출력
onViewList();
function onViewList(){
	console.log("onViewList");
	//아작스함수 (출력)호출
    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"get",
        data: {'type' : 'getList'},

        success: r => {
            console.log(r);

             let listbody = document.querySelector('.listbody');
             let html = ``;
             r.forEach( b => {
             html +=	`	<ul class="listcont">
						<li class="btype">${b.btype == 'N'? '공지' : '이벤트' }</li>
						<li class="btarget">${b.btarget}</li>
						<li class="btitle"><a href="aBoardView.jsp?bno=${ b.bno }">${b.btitle}</a></li>
						<li class="bdate">${b.bdate}</li>
					</ul>`;

               });
               listbody.innerHTML = html;


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
function onWrite(){
	location.href="aBoardWrite.jsp"
}


























