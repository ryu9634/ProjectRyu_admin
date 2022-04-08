-- tb_admin
create table tb_admin (
    admin_idx number(7) primary key,
    admin_id varchar2(20),
    admin_pw varchar2(20),
    admin_name varchar2(20),
    admin_status varchar2(20)
);

create sequence seq_admin
    increment by 1
    start with 1;


-- tb_user 
create table tb_user (
    user_idx number(7) primary key,
    user_userid varchar2(20) ,
    user_userpw varchar2(20) ,
    user_name varchar2(20) ,
    user_hp varchar2(20),
    user_email varchar2(50),
    user_regdate date default sysdate,
    user_sns varchar2(20) ,
    user_zipcode varchar2(10) ,
    user_address1 varchar2(100) ,
    user_address2 varchar2(100) 
);

create sequence seq_user
    increment by 1
    start with 1;
    
-- qna
create table tb_qna (
    q_idx number(7) primary key,
    q_useridx number(7) ,
    q_title varchar2(50) ,
    q_regdate date default sysdate,
    q_content varchar2(100) ,
    q_answer varchar2(300) ,
    q_number number(7) ,
    q_category varchar2(20) ,
    q_status varchar2(20) ,
    --
    constraint fk_qna foreign key(q_useridx) references tb_user(user_idx)
);
    
create sequence seq_qna
    increment by 1
    start with 1;    
    

-- tb_goods
create table tb_goods (
    gd_idx number(7) primary key,
    gd_name varchar2(20),
    gd_price number(7) ,
    gd_count number(7) ,
    gd_brand varchar2(20) ,
    gd_regdate date default sysdate,
    gd_category varchar2(20) ,
    gd_saleprice number(7) ,
    gd_salepercent number(3) ,
    gd_hit number(7) default 0,
    gd_img varchar(1000) ,
    gd_option varchar2(100) ,  
    gd_status varchar(20),
    gd_detailimg varchar2(1000),
    gd_content varchar2(200)
); 

create sequence seq_goods
    increment by 1
    start with 1; 
    
    
    -- th_cart
create table tb_cart (
    cart_idx number(7) primary key,
    gd_idx number(7),
    user_idx number(7),
    --
    constraint fk_cart1 foreign key(gd_idx) references tb_goods(gd_idx),
    constraint fk_cart2 foreign key(user_idx) references tb_user(user_idx)
);

create sequence seq_cart
    increment by 1
    start with 1; 
    
    
    
-- tb_regist_address
create table tb_regist_address (
    rga_idx number(7) primary key,
    user_idx number(7) ,
    rga_revname varchar2(100) ,
    rga_zipcode varchar2(100) ,
    rga_userhp varchar2(100) ,
    rga_address1 varchar2(100) ,
    rga_address2 varchar2(100) ,
    rga_revaddname varchar2(100) ,
    --�ܷ�Ű
    constraint fk_regist_address foreign key(user_idx) references tb_user(user_idx)
);
create sequence seq_regist_address
    increment by 1
    start with 1; 
    
-- tb_faq
create table tb_faq (
    faq_idx number(7) primary key,
    faq_title varchar2(100) ,
    faq_anwser varchar2(1000) ,
    faq_category varchar2(50) ,
    faq_img varchar2(1000)
);

create sequence seq_faq
    increment by 1
    start with 1;

-- tb_Dpoint
create table tb_dpoint(
    dp_idx number(7) primary key,
    dp_regdate date default sysdate,
    dp_content varchar2(200),
    dp_pp number(7),
    dp_mp number(7),
    dp_sum number(7) default 0,
    dp_enddate date default SYSDATE + (INTERVAL '1' MONTH),
    user_idx number(7),
    CONSTRAINT fk_dpoint1 foreign key(user_idx) references tb_user(user_idx)
);

create sequence seq_dpoint
    increment by 1
    start with 1;
    
-- tb_dpoint_goods
create table tb_dpoint_goods (
    dpg_idx number(7) primary key,
    dpg_name varchar2(20),
    dpg_price number(7) ,
    dpg_count number(7) ,
    dpg_category varchar2(100),
    dpg_enddate date default SYSDATE + (INTERVAL '1' year),
    dpg_hit number(7) ,
    dpg_img varchar2(7)    
);
    
create sequence seq_dpoint_goods
    increment by 1
    start with 1;
    
-- tb_review 
create table tb_review (
    rv_idx number(7) primary key,
    user_idx number(7) ,
    rv_regdate date default sysdate,
    rv_title varchar2(100) ,
    rv_content varchar2(1000),
    rv_star number(5) ,
    rv_img varchar2(1000) ,
    --�ܷ�Ű
     constraint fk_review foreign key(user_idx) references tb_user(user_idx)
);

create sequence seq_review
    increment by 1
    start with 1;
    

-- tb_coupon
create table tb_coupon(
    cp_idx number(7) primary key,
    user_idx number(7) ,
    cp_discount number(7),
    cp_name varchar2(100),
    cp_regdate date default sysdate,
    cp_enddate date default SYSDATE + (INTERVAL '1' year),
    cp_limitprice varchar2(100),
    cp_startdate date default sysdate,
    cp_status varchar2(100) ,
    --�ܷ�Ű
    constraint fk_coupon foreign key(user_idx) references tb_user(user_idx)
    
    
);
create sequence seq_coupon
    increment by 1
    start with 1;   
    
    
-- th_order
create table tb_order (
    order_idx number(7) primary key,
    user_idx number(7) ,
    gd_idx number(7) ,
    order_num varchar2(100) ,
    order_regdate date default sysdate,
    order_seller varchar2(100),
    order_status varchar2(100) ,
    --�ܷ�Ű
    constraint fk_order1 foreign key(user_idx) references tb_user(user_idx),
    constraint fk_order2 foreign key(gd_idx) references tb_goods(gd_idx)
);
drop table tb_order;
drop table tb_point;
drop table tb_member;
drop table tb_address;
drop table users;
drop table order_detail;
drop table order_group;
drop table partner;
drop table item;
drop table category;
drop table tabnam;
ALTER TABLE users DROP CONSTRAINT SYS_C0018205;
ALTER TABLE users DROP CONSTRAINT SYS_C0018206;
create sequence seq_order
    increment by 1
    start with 1;   

    
--tb_zzim
create table tb_zzim (
    zz_idx number(7) primary key,
    user_idx number(7) ,
    gd_idx number(7) ,
    --�ܷ�Ű
    constraint fk_zzim1 foreign key(user_idx) references tb_user(user_idx),
    constraint fk_zzim2 foreign key(gd_idx) references tb_goods(gd_idx)
);

create sequence seq_zzim
    increment by 1
    start with 1; 

--tb_recent
create table tb_recent (
    rc_idx number(7) primary key,
    user_idx number(7),
    gd_idx number(7),
    --�ܷ�Ű
    constraint fk_recent1 foreign key(user_idx) references tb_user(user_idx),
    constraint fk_recent2 foreign key(gd_idx) references tb_goods(gd_idx)
    
);

create sequence seq_recent
    increment by 1
    start with 1; 
    
-- tb_notice
create table tb_notice (
    nt_idx number(7) primary key,
    nt_title varchar2(100) ,
    nt_regdate date default SYSDATE,
    nt_content varchar(1000),
    nt_category varchar2(50) ,
    nt_hit number(5) default 0
);

create sequence seq_notice
    increment by 1
    start with 1; 
    
    
drop table tb_admin;
drop table tb_user;
drop table tb_qna;
drop table tb_goods;
drop table tb_cart;
drop table tb_regist_address;
drop table tb_faq;
drop table tb_dpoint;
drop table tb_dpoint_goods;
drop table tb_review;
drop table tb_coupon;
drop table tb_order;
drop table tb_recent;
drop table tb_zzim;

select * from  tb_admin;
select * from  tb_user;
select * from tb_qna;
select * from tb_goods;
select * from tb_cart;
select * from tb_regist_address;
select * from tb_faq;
select * from  tb_dpoint;
select * from tb_dpoint_goods;
select * from tb_review;
select * from tb_coupon;
select * from tb_order;
select * from tb_recent;
select * from tb_zzim;

create table tb_file (
    file_idx number(7) primary key,
    file_name number(7),
    file_url number(7)
);
