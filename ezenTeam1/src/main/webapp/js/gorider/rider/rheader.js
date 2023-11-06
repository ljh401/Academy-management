console.log('js실행');

let 헤더변수 = "헤더변수데이터";
let loginState = false;  // 로그인 상태 true:로그인중 false면 비로그인
let loginRname = ''; // 로그인 성공된 라이더 이름 가져오는 변수  
let loginRno = 0;
// 1. 현재 로그인된 라이더정보 요청 
getRiderInfo();
function getRiderInfo(){
	
	   $.ajax({
      url : "/ezenTeam1/RiderInfoController",      
      data : {type : "info"} ,      
      async: false , // 비동기화
      method : "get",   
      success : r => {console.log(r)
      	
      	let riderinformation = document.querySelector('.riderinformation');
      	let html = ``;
      	
      	if( r == null ){ // 비로그인 , 로그인이 안된상태 
      		console(r)
			  loginState = false; loginRname = ''; loginRbikenum = ''; 
			  	html += `<span><a href="/ezenTeam1/gorider/rider/riderSignup.jsp"> 회원가입 </a> </span>
			  			 <span><a href="/ezenTeam1/gorider/rider/rlogin.jsp"> 로그인 </a> </span>`;
			  
		  }else{ // 로그인된 상태 
			  loginState = true; loginRname = r.rname; loginRbikenum = r.rbikenum; loginRno = r.rno;
			  console.log(loginRname);  console.log(loginRbikenum);
			  
		  }
		  // html 대입 
      	riderinformation.innerHTML = html;
      } ,       
      error : e => {} ,         
   });
	
}// f e

// 2. 로그아웃 함수 
function logout(){
	
	$.ajax({
      url : "/ezenTeam1/RiderInfoController",      
      data : {type : "logout"},      
      method : "get",   
      success : r => {console.log(r)
      	alert('로그아웃 되었습니다.');
      	location.href="/ezenTeam1/login.jsp"
      } ,       
      error : e => {} ,         
   });
	
}//  f  e