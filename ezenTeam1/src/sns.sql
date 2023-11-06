use jspweb;
drop table if exists sns;
create table sns(
    sno int auto_increment ,                   	-- 식별번호( 게시물번호 ) , 자동번호 부여
    sfile text not null ,              			-- ( 사진파일 ) , 공백불가능 ,
    scontent longtext not null  ,     			-- ( sns내용 ) , 공백불가능 중복불가
	spwd varchar(20) not null,  				-- ( 비밀번호 ) , 공백불가능
    sdate datetime default now() not null,      -- ( 등록일 )  , 공백불가능
    slike int,									-- ( 좋아요 카운트 )  , 공백가능
    shate int,									-- ( 싫어요 카운트 )  , 공백가능
    primary key( sno )
);

# 1. 호출 
insert into sns(sfile,scontent,spwd) values('default.webp','하이','1234');
insert into sns(sfile,scontent,spwd) values('default.webp','이하','12345');
insert into sns(sfile,scontent,spwd) values('default.webp','이리','123467');
insert into sns(sfile,scontent,spwd) values('default.webp','지비','123486');


select * from sns;

# 2. 수정 
update sns set sfile = 'efault.webp' , scontent = '안녕' , spwd = '1234' where sno = 1;
-- update sns set sfile = ? , scontent = ? , spwd = ? where sno = ?

# # 3. 개별 글 출력 [  게시물 조회 ]
select * from sns where sno = ?;

# 삭제 
delete from sns where sno = 1 and spwd = '1234';
-- delete from sns where sno = ? and spwd = ?
# 검색 
select count(sno) from sns where scontent like '%q%' ;
# select * from sns where scontent like '%?%';

# 댓글저장 테이블
drop table if exists reply;
create table reply(
    rno int auto_increment ,                   	-- 식별번호( 게시물번호 ) , 자동번호 부여
    rcontent text not null ,              		-- ( 사진파일 ) , 공백불가능 ,
    rpwd varchar(20) not null,  				-- ( 비밀번호 ) , 공백불가능
    rdate datetime default now() not null,      -- ( 등록일 )  , 공백불가능
    sno int  ,     								-- ( sns내용 ) , 공백불가능 중복불가
    primary key( rno ),
    foreign key( sno) references sns(sno)
      on delete cascade
	  on update cascade   -- sns게시물 삭제시 댓글도 삭제 , 댓글 변경시 fk도 같이 변경   [ 제약조건 ]
);


