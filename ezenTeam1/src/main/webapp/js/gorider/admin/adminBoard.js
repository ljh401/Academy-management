console.log('연결');
//1.첨부파일드래그앤드랍
let bfiles = document.querySelector('.bfiles');
let bfileList = []; //현재 드래그된 파일배열

bfiles.addEventListener("dragenter", (e)=>{
	e.preventDefault(); // 브라우저가 가진 이벤트를 실행하지 못하도록 prevent!!
	console.log('드래그 들어옴');
});

bfiles.addEventListener("dragleave", (e)=>{
	console.log('드래그 나감');
	bfiles.style.backgroundColor='#FFFFFF';
	e.preventDefault(); // 브라우저가 가진 이벤트를 실행하지 못하도록 prevent!!
});


bfiles.addEventListener("dragover", (e)=>{
	console.log('드래그상자위를  위치중');
	e.preventDefault(); // 브라우저가 가진 이벤트를 실행하지 못하도록 prevent!!
});


bfiles.addEventListener("drop", (e)=>{
	console.log('드래그상자위를  떨어짐');
	console.log(e);
	e.preventDefault(); // 브라우저가 가진 이벤트를 실행하지 못하도록 prevent!!


	//------------------드랍된 파일의 정보??------------------------//

	// 1. 드랍된 데이터 정보를 이벤트 결과에서 찾기
		console.log(e.dataTransfer)
		console.log(e.dataTransfer.files)
		console.log(e.dataTransfer.files[0])
		console.log(e.dataTransfer.files[0].name)

	let files =	e.dataTransfer.files
		for(let i=0; i <files.length; i++){
			if(files[i] != null && files[i] != undefined){
				bfileList.push(files[i])
			}
		}
	bfiles.style.bakcgroundColor = '#ffffff';
	fileListPrint();
}); //f


 function fileListPrint(){

	 let html = ``;
	 bfileList.forEach((f,i)=>{
		 let fname = f.name;
		 let fsize = (f.size/1024).toFixed(1);//파일용량이  바이트단위이므로 kb로 변환
		 //(f.size/1024).toFixed(1); toFixed(소숫점자리수)
		 html += `<div>
				<span>${fname}</span>
				<span>${fsize}</span>
				<span><button onclick="fileDelete(${i})" type="button">삭제</button></span>

			</div>`


	 })
	 // 3 대입
	 bfiles.innerHTML= html;


 }

function fileDelete(index){
	 bfileList.splice(index, 1);
	 fileListPrint();

 }


/* 캘린더------------------------------------------------*/


let year = new Date().getFullYear(); //현재연도
let month = new Date().getMonth()+1;  // 현재 월[0~11] +1 (7월)
	month = month < 10 ? '0'+month : month;
let date = new Date().getDate();// 현재 날. (10일)
let eDateType= ''; // 이벤트 시작일과 종료일을 선택하기위한 구분자 s이면 시작일 / e 종료일
let eStartDate = null;
let eEndDate = null;

function onCalendar(se){
	document.querySelector('.modalBox').style.display = 'flex';

	eDateType = se;
	console.log('eDateType :: '+eDateType)
}

calPrint();

function calPrint(){
	//1. 현재 연도와 월을 출력
	document.querySelector('.caldate').innerHTML = `${year}년 ${month}`;
	console.log('현재 날짜의 getDate()  :  '+new Date().getDate());

	let now = new Date( year, month-1, 1); //month의 경우 +1했던 값이므로 원상태로 돌려줌. 2023년의 1일을 반환
	console.log('현재 날짜의 now  :  '+now);
 	//2. 요일과 일 출력
 		//현재의 달이 시작되는 요일
 	let sWeek = now.getDay(); // 현재 월의 시작 요일/
	console.log('요일 :(0일요일~6토요일)  :'+sWeek)

		//현재달에 해당하는 마지막 날 구하기 ( 30? 28? 31 ? 29??)
	let now2= new Date(year, month, 0); //  현재달 +1 (8월)	,  이전달의  뒤로(0)
	let eDay = now2.getDate();
    console.log('eDay'+eDay);


	let calendar = document.querySelector('.calendar');
	let html ='';
		//1. 요일 출력
		html += `	<div class="week sunday"> 일 </div>
					<div class="week"> 월 </div>
					<div class="week"> 화 </div>
					<div class="week"> 수 </div>
					<div class="week"> 목 </div>
					<div class="week"> 금 </div>
					<div class="week"> 토 </div>	`
		// ** 현재 달력 1일의 요일까지 공백 출력

		for (let b = 1 ; b <= sWeek ; b++  ){ // 1일이 시작되는 요일이 되기 전까지 공란으로 만들어주기
			html +=`<div class="days">  </div>`;
		}
		// 일 출력.

		for(let i = 1 ; i <=eDay ;  i++){

			if(date == i) {


				html +=
					`<div onclick="setEDate(${i})" class="today">
						${i}

					</div>`
			} else{ html +=
					`<div onclick="setEDate(${i})" class="days">
				  		${i}


					</div>`;
			}
			//
		}

	calendar.innerHTML = html;

}

function onNext(check){
	console.log(check);
	if(check == 0){
		month--;
		//만약에 월을 차감했는데 1보다 작아지면 12월로 변경하되 연도 1 차람
		//23년도 1월	인데 1차감되면 2022년 12월로
		if(month < 1 ){ month=12; year--;}

	} else {
		month ++;
		if(month>12){month=1; year++;}
	}
	calPrint();
}

function setEDate(eday){
	document.querySelector('.modalBox').style.display = 'none';

	eday = eday < 10 ? '0'+eday: eday;
	console.log(year +'/'+month+'/'+eday);

	if(eDateType == 'S') { //시작일 설정하는 경우
		eStartDate = year+''+month+''+eday;
		document.querySelector('.startdate').innerHTML = eStartDate;
	} else if(eDateType == 'E') { //종료일설정하는 경우
		eEndDate = year+''+month+''+eday;
		document.querySelector('.enddate').innerHTML = eEndDate;
	}
}


/* 게시글의 타입(공지사항인지 이벤트인지) radio버튼 가져오기------------------------- */
let btype;
if(btype == null){
	document.querySelector('.bePeriodBox').style.display = 'none'
}
function getBtype(){
	//class명이 'btarget'인요소 모두 가져오기
	let type = document.querySelectorAll('.btype')

	//반복분을 사용해서 각 요소의 checked값을 확인
 	type.forEach((bt)=> {
		 if(bt.checked){
			 //체크된 요소의 value를 btype변수에 대입
			 btype = bt.value
			 console.log(btype)

			 let bePeriodBox = document.querySelector('.bePeriodBox');
			 //이벤트가 선택된 경우
			 if(btype == 'E'){
				 //해당위치에 캘린터 출력하기
				 bePeriodBox.style.display='block';
				 let html = `<span class="startdate">Event시작일</span><i onclick="onCalendar('S')" class="fa-regular fa-calendar-check"></i>
							<span class="gubun"> - </span>
							<span class="enddate">Event 종료일</span><i onclick="onCalendar('E')"class="fa-solid fa-calendar-check"></i>`
					bePeriodBox.innerHTML = html;

			 } else{
				 bePeriodBox.style.display='none';
			 }
		 }
	 })
}
/* 게시글의 타겟(사용자인지.라이더인지)radio버튼 가져오기------------------------- */
let btarget;
function getBtarget(){
	let type = document.querySelectorAll('.btarget')

 	type.forEach((bt)=> {
		 if(bt.checked){
			 btarget = bt.value
			 console.log(btarget)
		 }
	 })
}


 //저장

function onSave(){
    //폼객체 가져오기 * querySelectorAll(선택자)[0]
    let bWriteForm = document.querySelectorAll('.bWriteForm')[0];

    //폼데이터 객체에 담기
    let bWriteData = new  FormData(bWriteForm);

    bWriteData.append('bstartdate',eStartDate);
    bWriteData.append('benddate',eEndDate);
    console.log("btype  :: "+ btype);
    console.log("btarget  :: "+ btarget);
    console.log("bstartdate  :: "+ eStartDate);
    console.log("benddate  :: "+ eEndDate);
    //파일이 있으면
    if(bfileList.length >= 1 ){
        //파일의 갯수만큼
        bfileList.forEach(f=>{
            //파일리스트라는 키값으로 bWriteData객체에 데이터 추가하기
            bWriteData.append('fileList',f);
            console.log(f);
        })
    }  



    //아작스함수 (저장)호출
    $.ajax({
        url:"/ezenTeam1/BoardController",
        method:"post",
        data: bWriteData,
        contentType:false,
        processData : false,
        success: r => {
            console.log("성공"+ r)
            if(r == true){
                alert('등록');
                location.href = '/ezenTeam1/gorider/admin/board/aBoardList.jsp'
            }else{
                alert('등록실패');
            }
        },
        error: e => { console.log("에러"+ e)}

    });

}

 //출력

function onViewList(){

}

//상세보기
function onViewDetails(){

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


























