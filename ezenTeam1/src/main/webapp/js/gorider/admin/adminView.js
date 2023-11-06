let rno = new URL( location.href ).searchParams.get("rno")
console.log(rno);
let approvalDenied = false; // 승인 또는 거부 여부를 추적하는 변수입니다.

 $.ajax({
        url: "/ezenTeam1/AdminController",
        method: "get",
        data: { type: 2, rno: rno },
        success: r => { console.log(r);


       		 // 1. 해당 라이더에 면허증이미지 출력
			 document.querySelector('.rimg1').src=`/ezenTeam1/gorider/rider/img/${r.rphoto}`;
			 // 1. 해당 라이더에 프로필이미지 출력
			 document.querySelector('.rimg2').src=`/ezenTeam1/gorider/rider/img/${r.rlicense}`;
			 // 1. 해당 라이더에 차량등록증 출력
			 document.querySelector('.rimg3').src=`/ezenTeam1/gorider/rider/img/${r.rregistration}`;
			 // 1. 해당 라이더에 이름 출력
         	 document.querySelector('.rname').innerHTML=`이름 : ${r.rname}`;
         	 // 1. 해당 라이더에 연락처 출력
         	 document.querySelector('.rphone').innerHTML=`연락처 : ${r.rphone}`;


        },

});

function onapprove() {
    if (approvalDenied) {
        return; // 이미 승인 또는 거부한 경우 함수를 종료합니다.
    }

    approvalDenied = true; // 승인 또는 거부함을 표시합니다.

    $.ajax({
        url: "/ezenTeam1/AdminController",
        method: "post",
        data: { type: 2, rno: rno },
        success: r => {
            console.log(r);

            location.href="/ezenTeam1/gorider/admin/aRequestList.jsp"
            alert('승인 성공 했습니다.')
        },
        error: e => {
            console.error(e);
        }
    });

    // 승인 버튼을 비활성화합니다.
    document.getElementById("approveButton").disabled = true;
}

// 승인 거부 함수
function ondeny() {
    if (approvalDenied) {
        return; // 이미 승인 또는 거부한 경우 함수를 종료합니다.
    }

    let rcomment = prompt('승인거부 사유를 알려주세요: ');

    if (!rcomment) {
        return; // 거부 사유를 입력하지 않은 경우 함수를 종료합니다.
    }

    approvalDenied = true; // 승인 또는 거부함을 표시합니다.

    $.ajax({
		url: "/ezenTeam1/AdminController",
        method: "post",
        data: { type: 1, rno: rno, rcomment: rcomment },
        success: r => {
            console.log(r);
            // 성공한 후에 아무것도 하지 않습니다.
            location.href="/ezenTeam1/gorider/admin/aRequestList.jsp"
            alert('승인 거부 했습니다.');
        },
        error: e => {
            console.log(e);
        }
    });

    // 거부 버튼을 비활성화합니다.
    document.getElementById("denyButton").disabled = true;
}


