let keyword =''; 


// 1.출력
onList(keyword);
function onList(keyword){
	console.log('리스트 로드');


	$.ajax({
		url : "/ezenTeam1/SnsController",
		method : "get",
		data : { type : 1 , keyword : keyword },
		success : r => {
			let html ='';
			let cWrap = document.querySelector('.cWrap')


			r.forEach( s => {
				let commenthtml = ``;
				s.replyList.forEach( c =>{
					let rtime = getTime(Number(c.rdate))
					 console.log("?")
					 console.log( c )
					 
					commenthtml += 	`<div class="reBox">
								<span class="rContent">${c.rcontent}</span>
								<span class="rTime">${rtime}</span>
								<button onclick ="RcheckPwd(${c.rno})" type="button">X</button>
							</div>`
				})
				//console.log("s" + s)
				//시간변환 -
				let stime = getTime(Number(s.sdate))
				//console.log("stime"+stime);
				html+= `
					<div class="contBox">
						<div class="sphoto"><img src="/ezenTeam1/sns/upload/${s.sfile}"></div>
						<div class="sdate">${stime}</div>
						<div class="scontent">
						${s.scontent}
						</div>
						<div class="btnbox">

							<div class="btnL">
								<button class="btn" onclick ="checkPwd(${s.sno},'U')" type="button">수정</button>
								<button class="btn" onclick ="checkPwd(${s.sno},'D')" type="button">삭제</button>
								<button class="btn" onclick ="replyBtn(${s.sno})"  type="button">답글</button>
								<button class="btn" onclick ="fileDownload(${s.sno})"  type="button">다운로드</button>
							</div>
							<div class="btnR">
								<button class="" onclick ="" type="button"><img src= "/ezenTeam1/img/like.png" /></button>
								<span class="lcnt"> 3 <span>
								<button class="" onclick ="" type="button"><img src= "/ezenTeam1/img/dislike.png" /></button>
								<span class="dislcnt"> 2 <span>
							</div>
						</div>

						<div class="replyWrap">
						${commenthtml}


						</div>
					</div>


				`;
				//commenthtml =``
			});


			cWrap.innerHTML = html;
			// keyword 로 검색을 받아올 갯수
			rCount(keyword);


		} ,
		error : e => { console.log('error :: '+ e);}
	});

}
// 2. 글쓰기 화면으로 이동
function onWrite(){
	location.href='/ezenTeam1/sns/snsWrite.jsp';
}

// 검색 레코드 
function rCount(keyword){

	  $.ajax({
      url : "/ezenTeam1/SnsController",
      data : { type : 4 , keyword : keyword },
      method : "get",
      success : r => {
		  console.log(r)
		  // 피드 수 출력
		  document.querySelector('.rcount').innerHTML = r;


	  } ,
      error : e => {} ,
   });

}// f e

// 3. 검색 버튼을 클릭했을때.
function onSearch(){
	console.log('onSearch() open');
	// 입력받은 keyword 값을
	let keyword = document.querySelector('.keyword').value;
	// onList 에 keyword를 보낸다
	onList(keyword);
	// 전체 list rcount를 받아야한다
	let rcount = document.querySelector('.rcount');
	// 데이터 갯수를 가져와야한다.


}//f  e


// 4. 비번체크
function checkPwd(sno, target){
	let spwd = prompt('비밀번호를 입력하세요');

	$.ajax({
		url : "/ezenTeam1/SnsController",
		method : "get",
		data : { type : 3 , sno : sno, spwd : spwd},
		success : r => {
			console.log('비번체크success :: '+ r)

			if(r) {

				if(target == 'U'){
					location.href = `/ezenTeam1/sns/snsUpdate.jsp?sno=${sno}`

				}  else if( target == 'D'){

					// 삭제 함수 호출
					sdelete(sno);
				}
			} else {
					alert('비밀번호가 일치하지 않습니다.')
			}


		} ,
		error : e => { console.log('error :: '+ e);}
	});
}


// 5. 댓글 삭제시 비번 체크
function  RcheckPwd(rno){
	console.log("rno  :: " + rno);
	let rpwd = prompt('비밀번호 입력해주세요');
	console.log("rpwd  :: " + rpwd);
	
	$.ajax({
		url : "/ezenTeam1/SnsReplyController",
		method : "delete",
		data : { rno : rno , rpwd : rpwd},
		success : r => {
			console.log('비번체크success :: '+ r)

			if(r) {
				alert('삭제되었습니다..');
				location.href = `/ezenTeam1/sns/snsList.jsp`
			} else if (r == false) {
				alert('비밀번호가 일치하지 않습니다.');
			}


		} ,
		error : e => { console.log('error :: '+ e);}
	});
}
// 6.
function replyBtn(sno){
	console.log("sno :: "+sno)
	let rcontent= prompt('내용(최대30글자)')
	let rpwd = prompt('비밀번호(8글자이상)')

	$.ajax({
            url : "/ezenTeam1/SnsReplyController",
            method : "post",
            data : {rcontent : rcontent, rpwd : rpwd, sno:sno},
           success : r =>{console.log(r);
             if(r){
				 alert('답글 달기 성공');
				 onList(keyword);
			 }else{
				 alert('답글 달기 실패');
			 }
          } ,
            error : e=>{} ,
   });
 }
// 7. 분 (min) -> 시간으로 변환
function getTime(min){
	let time;
	if(min <= 0 ){ 	time = '방금'}  //1분미만
	else if ( min < 60 ) {time = min +'분 전'	} //1분이상 1시간 미만
	else if( min >=60 && min/60 < 24 ) {time = Math.floor((min/60))+ '시간 전' }//1시간 이상 1일 미만
	else if ( min/60/24 >= 1 && min/60/24/30  < 1   ){ time = Math.floor(min/60/24) +'일 전'} //1일 이상 30일 미만
	else if( min/60/24/30 >= 1 && min/60/24/30/12 < 1) {time = Math.floor(min/60/24/30) +'달 전'} //한달이상~1년 미만
	else { time =  Math.floor(min/60/24/30/12)+'년 전' } //1년 이상

	return time;
}








