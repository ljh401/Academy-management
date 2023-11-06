console.log('list연결');
//비로그인 리턴
if(loginState== false){
	alert('로그인이 필요합니다.');
	location.href='/ezenTeam1/gorider/member/mlogin.jsp'
}
//
//var geocoder = new kakao.maps.services.Geocoder();
//
//var callback = function(result, status) {
//    if (status === kakao.maps.services.Status.OK) {
//
//        console.log('지역 명칭 : ' + result[0].address_name);
//        console.log('행정구역 코드 : ' + result[0].code);
//    }
//};

//geocoder.coord2RegionCode(126.9786567, 37.566826, callback);

//goocoder.coord2Address(coord.getLng(), coord.getLat(), callback);

 //리스트출력
getDriveRecord();
function getDriveRecord(){
	
	console.log("getDriveRecord");
	//아작스함수 (출력)호출

    $.ajax({
        url:"/ezenTeam1/MemberFindController",
        method:"get",
        data: {type : 'findHistory' },

        success: r => {
            console.log(r);
			 let addname ;
             let histroyBox = document.querySelector('.histroyBox');
             let html = ``;
             r.forEach( b => {
//				 var callback = function(result, status) {
//				    if (status === kakao.maps.services.Status.OK) {
//						//console.log(result[0].address_name)
//						console.log(result[0].address.address_name)
//						
//				      //  console.log('행정구역 코드 : ' + result[0].code);
//				    }
//				};
								
								//geocoder.coord2RegionCode(126.9786567, 37.566826, callback);
             html +=	`<ul class="hContBox">
				            <li><span class="hTitleA">이용일시</span><span class="hContent">${b.sdate}</span><li>
				            <li><span class="hTitle">출 발</span><span class="hContent">${ b.sfromlo +'/'+b.sfromla}</span><li>
				            <li><span class="hTitle">도 착</span><span class="hContent">${ b.stolo +'/'+b.stola}</span><li>
				            <li><span class="hTitle">금 액</span><span class="hContent"> ${b.spayment}원</span><li>
				        </ul>`;

               });
               histroyBox.innerHTML = html;


            },
        error: e => { console.log("에러"+ e)}

    });

}

//상세보기
function onViewDetails(bno){
	console.log("onViewList");

}




























