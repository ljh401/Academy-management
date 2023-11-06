use gorider;
# -------------------------------------------------------- 조작어 -------------------------------------------------------------------------------------

# member 샘플
insert into
	member( mname , mid , mpwd , memail ,  mpayinfo )
    values( '홍길동' , 'qweqwe' , 'qwe1234' , 'qweqwe@naver.com' , '1234-1234' );

insert into
	member( mname , mid , mpwd , memail ,  mpayinfo )
    values( '정희락' , 'qweqww' , 'qwe1234' , 'qweasd@naver.com' , '1234-1234' );


insert into
	member( mname , mid , mpwd , memail ,  mpayinfo )
    values( '박상빈' , 'qweqwa' , 'qwe1234' , 'qweqwa@naver.com' , '1234-1234' );

# rider 샘플

insert into
	rider( rname , rid , rpwd , rphone , rphoto , rlicense ,  rregistration , raccount , rbank , rbikenum )
	values ( '이진형 ' , 'ljh401' , '123123' , '01012341234' , 'default.webp' , 'default.webp' ,'default.webp', '123123-123123' , '국민' , '바3583' );

insert into
	rider( rname , rid , rpwd , rphone , rphoto , rlicense ,  rregistration , raccount , rbank , rbikenum )
	values ( '황태웅 ' , 'hw0308' , '456789' , '01012341234' , 'default.webp' , 'default.webp' ,'default.webp', '123123-456456' , '국민' , '바3583' );

insert into
	rider( rname , rid , rpwd , rphone , rphoto , rlicense ,  rregistration , raccount , rbank , rbikenum  )
    values ( '김현수 ' , 'itdanja' , '2072' , '01012341234' , 'default.webp' , 'default.webp' ,'default.webp', '123123-207272' , '국민' , '바3583' );

#  라이더 회원가입
insert into
	rider( rname , rid , rpwd , rphone , rphoto , rlicense ,  rregistration , raccount , rbank , rbikenum )
	values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? );

# 라이더 로그인
select * from rider where rid = 'itdanja' and rpwd = '2072';
# select * from rider where rid = ? and rpwd = ?

select * from rider;

# 라이더(내정보) 호출
	-- 호출시 라이더번호 , 아이디 , 프로필사진 , 면허증 , 차량등록증 출력
select rno , rname , rid , rphone , rphoto , rlicense , rregistration from rider where rid = 'hee9399';
-- select rno , rname , rid , rphone , rphoto , rlicense , rregistration from rider where rid = ?

# 라이더 계정 삭제
delete from rider where rno = 13 and rpwd = 'As13511351';
# delete from rider where rno = ? and rpwd = ?

select * from rider;
# 라이더 상태 샘플
insert into riderstate( rno ,  rstart , rcall ) values( 4 , 'Y' , 'Y' );
insert into riderstate( rno , rstart , rcall ) values( 12 , 'N' , 'N' );

select rno from rider natural join riderstate where rid = 'hee9399';
update rider set rstatus = 'Y';
#service
insert into service values(1,1,1,'2023-09-27 15:41:38',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');
insert into service values(2,2,2,'2023-09-27 15:41:38',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');
insert into service values(3,3,3,'2023-09-27 15:41:40',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');


insert into service values(3,3,3,'2023-09-27 15:41:40',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');
insert into service(sno,rno) values(4,2);
insert into service(sno) values(3);
insert into service(sno,rno,sfromla) values(5,2,32356.123);
#board 추가/ 삭제
insert into board (btarget,btype,btitle,bcontent,bstartdate,benddate) values('U','N','ddddddd','aaaaaaa',null,null);
delete from board where bno = '1';
select * from board b natural join boardfiles bf order by bno desc;
#공지 상세
select 
		b.bno as bno
	  , b.btarget as btarget
	  , b.btype as btype
      , b.btitle as btitle
      , b.bcontent as bcontent
      , DATE_FORMAT(bdate, '%Y-%m-%d') as bdate
      , b.bview as bview
      , DATE_FORMAT(b.bstartdate, '%Y-%m-%d') as bstartdate
      , DATE_FORMAT(b.benddate, '%Y-%m-%d') as benddate
      , bf.bfno as bfno
      , bf.bfile as bfile
 from board b left outer join boardfiles bf on b.bno = bf.bno where b.bno = 2 ;