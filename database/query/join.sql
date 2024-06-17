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

select * 
from post p, category c, blog b
where p.category_no = c.no
and b.id = c.blog_id
;

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
from post;
-- delete from category;
-- delete from blog;
-- delete from user;


