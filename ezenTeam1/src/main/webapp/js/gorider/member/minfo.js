/**
 *
 */
if(loginState== false){
	location.href='/ezenTeam1/gorider/member/mlogin.jsp'
} else {
	document.querySelector('.mi_mname').innerHTML = loginMname;
	document.querySelector('.mi_memail').innerHTML = loginMeamil;
	document.querySelector('.mphoto').src = `/ezenTeam1/gorider/member/file/${loginMphoto}`;
}

// 1. 첨부파일변경
function onPhotomModal(){
	document.querySelector('.modalwrap').style.display = 'flex';
}

let isFile = false;
// 2. 첨부파일 저장하기
function onChangePhoto(){
	if(isFile == false){
		alert('선택된 파일없음.');
		return;
	} else {

		let myform = document.querySelectorAll('.myform')[0];
		let myformData = new FormData(myform);
		console.log(myformData);
		//myformData.append('type','PFile');
		//console.log(myformData.toString());

		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"put",
			data : myformData ,
			contentType:false,
			processData:false,
			success : r =>{
				//document.querySelector('.modalwrap').style.display = 'none';
				alert('로그인이 필요합니다.')
				logout()
			},
			error : e =>{ console.log(e ) }

		});



	}
}



//3. 모달창 닫기 (취소버튼 클릭시 화면 가리기)
function onModalClose(type){
	if(type == 'P'){
		document.querySelector('.modalwrap').style.display = 'none';
	} else{
		document.querySelector('.modalwrap2').style.display = 'none';
	}


}
//4. 프로필미리보기
function preview(obj){

	//2. JS파일클래스 선언
	let file = new FileReader(); //파일 읽기
	file.readAsDataURL(obj.files[0]);
	console.log(obj.files[0]);
	//읽어온 파일 해당 html에 img태그에  load
	file.onload = e=>{
		isFile = true;
		console.log("isFile  ::"+ isFile);
		//console.log('e  : >>> '+ e);
		//console.log('e.target  : >>> '+ e.target);
		//console.log(".target.result >>>" + e.target.result);
		document.querySelector('.preview').src = e.target.result;
	}
}
//5.결제정보 수정할 모달창 띄우기
function onPayModal(){
	document.querySelector('.modalwrap2').style.display = 'flex';
	$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"get",
			data : {type:'login'} ,
			success : r =>{
				document.querySelector('.usedPay').innerHTML= `${r.mpayinfo.substr(0,4)}-${r.mpayinfo.substr(4,4)}-${r.mpayinfo.substr(9,4)}-${r.mpayinfo.substr(12)} `;
			},
			error : e =>{ console.log(e ) }

		});


}

// 6. 프로필사진 수정하기
function onChangePhoto(){
	if(isFile == false){
		alert('선택된 파일없음.');
		return;
	} else {

		let myform = document.querySelectorAll('.myform')[0];
		let myformData = new FormData(myform);
		console.log(myformData);
		//myformData.append('type','PFile');
		//console.log(myformData.toString());

		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"put",
			data : myformData ,
			contentType:false,
			processData:false,
			success : r =>{
				//document.querySelector('.modalwrap').style.display = 'none';
				alert('프로필정보가 수정되었습니다.')
				logout()
			},
			error : e =>{ console.log(e ) }

		});
	}
}

//7. 결제정보수정
function onChangePay(){
	let cPay01 = document.querySelector('.cpay01').value;
	let cPay02 = document.querySelector('.cpay02').value;
	let cPay03 = document.querySelector('.cpay03').value;
	let cPay04 = document.querySelector('.cpay04').value;

	if(cPay01==''||cPay02==''||cPay03==''||cPay04==''){
		alert('카드번호를 정확히 입력해주세요');
		return;
	} else {

		let myform = document.querySelectorAll('.myformpay')[0];
		let myformData = new FormData(myform);
		console.log(myformData);
		//myformData.append('type','PFile');
		//console.log(myformData.toString());

		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"put",
			data : myformData ,
			contentType:false,
			processData:false,
			success : r =>{
				//document.querySelector('.modalwrap').style.display = 'none';
				alert('결제정보가 수정되었습니다..')
				logout()
			},
			error : e =>{ console.log(e ) }

		});

	}
}
// 8.이용내역 리스트로 이동
function onUsedList(){
	location.href ='/ezenTeam1/gorider/member/mUsedList.jsp';
}

//9.공지사항 리스트로 이동
function onBoardList(){
	location.href ='/ezenTeam1/gorider/member/mBoardList.jsp';
}
