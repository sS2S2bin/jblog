desc user;
desc blog;
desc category;
desc post;

insert
into user
values('soobin', '수빈', '0000', current_date());

insert 
into blog
values('soobin','title','content');

select *
from user u, blog b, category c, post p
where u.id=b.id
and c.blog_id = b.id
and p.category_no = c.no
;

select * 
from category c, blog b
where c.blog_id = b.id
;


select * from category;
insert into category values(null, '1의 4번째 카테고리','ㅇㅇd',current_date(), 1);
-- category list
select min(no), name from category
where blog_id=1;
select * from user;
select * from blog;

select * from post;
select * from category;

insert into post values(null, '11212','contents',current_date(),3);
insert into post values(null, '61626','contents',current_date(),6);

select * from post
where category_no = 2
;



select * 
from post p, category c, blog b
where p.category_no = c.no
and b.id = c.blog_id
;

select *
from category;
insert into post values(null, 'sample', 'ㅣ게 내 길이구나 !! \n 하지만! 그게 아니라는 것을 알게되었다.',current_date(), 2);
-- 특정 id의 category의 post 1개 가져오기
select p.title as title, p.contents as contents, p.reg_date as regDate, p.category_no as categoryNo
from category c, blog b, post p
where c.blog_id = b.id
and p.category_no = c.no 
and b.id = #{id} 
and p.category_no = #{categoryNo}
and p.no = #{postNo}
;

select * 
from blog
where id=1;


-- delete from category;
-- delete from blog;
-- delete from user;


