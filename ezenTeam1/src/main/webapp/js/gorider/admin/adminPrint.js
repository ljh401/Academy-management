
ApprovalPrint();
function ApprovalPrint() {
    $.ajax({
        url: "/ezenTeam1/AdminController",
        method: "get",
        data: {type : 1} ,
        success : r =>{    console.log(r);
            let listbox = document.querySelector('.listbox');
            let html = `
            	<ul class="listTitle rlistH">
                <li class="col10">No</li>
                <li class="col35">라이더ID</li>
                <li class="col35">요청일</li>
                <li class="col20">상세보기</li> <br/>
                 </ul>
            `;

        	    for (let i = 0; i < r.length; i++) {
               
				 let rno = r[i].rno; // rno 변수 설정
  				 let rid = r[i].rid; // rid 변수 설정
    			 let rdate = r[i].rdate; // rdate 변수 설정
                html += `
               	 <ul class="listContent rlistC">
                    <li class="col10">${rno}</li>
                    <li class="col35">${rid}</li>
                    <li class="col35">${rdate}</li>
                    <li class="col20"><button class="btnPd6" type="button" onclick="ApprovalView(${rno})">상세보기</button></li>
                </ul>
                `;
            }

            listbox.innerHTML = html;
            
        },
        error: function (e) {
            console.error('에러이유 : '+e);
        }
    });
}

 // 1. 라이더가 회원가입했을때 그라이더에 대한 상세보기 함수.
function ApprovalView(rno) {
     location.href = "/ezenTeam1/gorider/admin/aRequestView.jsp?rno="+rno;
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 