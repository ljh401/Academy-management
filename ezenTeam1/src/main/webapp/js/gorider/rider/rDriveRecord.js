

console.log('rDriveRecord.js');


if(loginState == true){ // 로그인이 되있으면
	//document.querySelector('.rname').innerHTML = loginRname;
}else{
	alert('로그인 후 사용해주세요.');
	location.href="/ezenTeam1/gorider/rider/rlogin.jsp";
}
//운행기록 출력
getDriveRecord();
function getDriveRecord(){

	$.ajax({
        url:"/ezenTeam1/RiderServiceController",
        method:"get",
        data: {type : 'DR'},

        success: rList => {
        	Array.isArray(rList);
            console.log(rList);

             let histroyBox = document.querySelector('.histroyBox');
             let html = ``;
             Array.isArray(rList);
             rList.forEach (b=>{
            	 console.log(b);
             		html +=	` <ul class="hContBox">
					            <li><span class="hTitleA">운행일시</span><span class="hContent">${b.sdate}</span><li>
					            <li><span class="hTitle">출 발</span><span class="hContent">${b.sfromla / b.sfromlo}</span><li>
					            <li><span class="hTitle">도 착</span><span class="hContent">${b.stola / b.stolo}</span><li>
					            <li><span class="hTitle">금 액</span><span class="hContent">${b.spayment}</span><li>
             					</ul>`;

	            });
             histroyBox.innerHTML = html;


            },
        error: e => { console.log(e)}

    });
}




