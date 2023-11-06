drop database if exists gorider;
create database gorider;
use gorider;

# ---------------------------------------------------  정의어 ------------------------------------------------------------------------------
drop table if exists member;
create table member(
    mno int auto_increment ,                # 회원번호
    mname varchar(20) not null ,           # 회원이름
    mid varchar(20) not null,              # 회원아이디
    mpwd varchar(20) not null,             # 회원비밀번호
    memail varchar(50) not null unique,   # 회원이메일
    mpayinfo varchar(16) not null,         # 결제카드번호
    mdate datetime default now(),           # 회원등록일
    mphoto longtext,                         # 회원프로필사진
    primary key( mno )
);

drop table if exists rider;
create table rider(
    rno int auto_increment ,     		# 라이더번호
    rname varchar(20) not null ,		# 라이더이름
    rid varchar(20) not null,  			# 라이더아이디
    rpwd varchar(20) not null,			# 라이더비밀번호
    rphone varchar(11) not null , 		# 라이더 전화번호
	rphoto longtext,					# 라이더프로필사진 //이미지
    rlicense longtext,					# 면허증  //이미지
    rregistration longtext,				# 차량등록증  //이미지
    rdate datetime default now(),		# 등록일
    raccount varchar(20),				# 계좌번호
    rbank varchar(3) , 					# 라이더 은행명
    rstatus varchar(1) default 'N',					# 승인상태 Y / N / D
    rcomment text,						# 승인거부시 사유
    rbikenum varchar(20),				# 바이크넘		
    primary key( rno )
);


# 	 라이더상태테이블 - 라이더번호 , 라이더출근상태 , 라이더콜가능상태
drop table if exists riderstate;
create table riderstate(
	 rno int ,     			# 라이더번호
     rstart varchar(2) default 'N'  ,    # 라이더출근상태  Y(출근),  N(퇴근)
     rcall varchar(2) default 'N' ,  	# 라이더콜가능상태 Y(출근),  N(퇴근)
     foreign key(rno) references rider(rno) on delete cascade on update cascade
);

drop table if exists service;
create table service(
	sno	 int auto_increment,			# 서비스번호
	mno int, 				    		# 회원번호
    rno int  ,     						# 회원번호
    sdate datetime default now(),		# 서비스이용일
    sriderla varchar(30) ,				# 라이더 출발위치 위도
    sriderlo varchar(30),				# 라이더 출발위치 경도
    sfromla varchar(30) ,				# 서비스 시작위치 위도
    sfromlo varchar(30),				# 서비스 시작위치 경도
    stola varchar(30),					# 서비스 도착위치 위도
    stolo varchar(30),					# 서비스 도착위치 경도
    spayment int,						# 서비스결제금액
    spayYN varchar(1),					# 결제여부
    sreview text,						# 리뷰
    spoint int,							# 별점
    sdepositYN varchar(1) default 'N',	# 라이더에게 입금여부(추가)
    primary key( sno ),
    foreign key( mno) references member(mno) on delete cascade on update cascade ,
	foreign key( rno) references rider(rno) on delete cascade on update cascade
);

drop table if exists deposit;
create table deposit ( 				# 입금 테이블
	dno int auto_increment,			# 입금번호
    rno int  ,     					# 라이더번호
    sno	 int,
    ddate datetime default now(),	# 입금일
    ddeposit int,					# 입금액
	primary key( dno ),
    foreign key( rno) references rider(rno) on delete cascade on update cascade,
    foreign key( sno) references service(sno) on delete cascade on update cascade 

);

# 게시판(공지사항/이벤트)
drop table if exists board;
create table board (
   bno int auto_increment,
   btarget varchar(2) not null,
   btype varchar(3) not null,
   btitle varchar(100) not null,
   bcontent text not null,
   bdate datetime default now(),
   bview int default 0 ,
   bstartdate datetime,
   benddate datetime,
    primary key(bno)
);

drop table if exists boardfiles;
create table boardfiles(
   bfno int auto_increment,
    bno int,
    bfile longtext,
    primary key(bfno),
    foreign key(bno) references board(bno) on delete cascade
);
select * from member;
select * from rider;
select * from service;
select * from deposit;
select * from board;
select * from boardfiles;

select * from rider where rid = 'hee9399';








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


# 라이더 상태 샘플
insert into riderstate( rno ,  rstart , rcall ) values( 9 , 'Y' , 'Y' );
insert into riderstate( rno , rstart , rcall ) values( 12 , 'N' , 'N' );

select rno from rider natural join riderstate where rid = 'hee9399';

#service
insert into service values(1,1,1,'2023-09-27 15:41:38',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');
insert into service values(2,2,2,'2023-09-27 15:41:38',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');
insert into service values(3,3,3,'2023-09-27 15:41:40',123.123,123.123,123.123,123.123,1000,'Y','개빡치네','5');


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





