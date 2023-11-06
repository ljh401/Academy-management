// 라이더 프로필 js
console.log('js실행됩니다.');
//비로그인 리턴
if(loginState== false){
	alert('로그인이 필요합니다.');
	location.href='/ezenTeam1/gorider/rider/rlogin.jsp'
}

// - 로그인된 라이더 이름과 프로필사진이 등록되야한다.

// 1. 로그인된 라이더 프로필사진 , 이름 출력

   $.ajax({
      url : "/ezenTeam1/RiderInfoController",
      data : { type: "info" },  // 로그인정보를 갖고있는 info 로 보낸다.
      method : "get" ,
      success : r => {console.log(r);
      		console.log("프로필사진" + r.rphoto )
      		// 1.  해당 로그인된 라이더 프로필사진 이미지 출력
      		document.querySelector('.rphoto img').src=`/ezenTeam1/gorider/rider/img/${r.rphoto == null ? 'default.webp' : r.rphoto}`;

      		// 2. 해당 로그인된 라이더 이름 출력
      		document.querySelector('.rpname').innerHTML =`${r.rname}`;
      			console.log(r.rname);


      } ,
      error :  e => {} ,
   });

 // 2. 프로필 사진 수정 하기
 function changeImg(){

	 // 1. from 객체 호출
	 let rpChange = document.querySelectorAll('.rpChange')[0];
	 	console.log('from객체 호출 했습니다.'+rpChange);
	 // 2. from 데이터 객체화
	 let rpData = new FormData(rpChange);
	 	console.log(rpData);

	 // 3. 프로필사진 수정 타입으로 보내기
	rpData.append('type' , '프로필사진'); // put 에 타입을 보내서 프로필사진 수정을한다.


	 $.ajax({
      url : "/ezenTeam1/RiderInfoController",
      data : rpData ,
      method : "put",
      contentType : false ,
      processData : false ,
      success : r => { console.log(r);

      		if(r == true){
				  alert('이미지 수정했습니다.')
			  }else{
				  alert('이미지 수정 실패')
			  }

      } ,
      error :  e => {} ,
   });


 }