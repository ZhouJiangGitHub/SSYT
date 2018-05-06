/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2015/5/18 18:32:00                           */
/*==============================================================*/


drop table assist_message cascade constraints;

drop table assit_db_data cascade constraints;

drop table exam_plan cascade constraints;

drop table exam_records cascade constraints;

drop table qes_exam_paper cascade constraints;

drop table qes_qestions cascade constraints;

drop table sys_class cascade constraints;

drop table sys_course cascade constraints;

drop table sys_menu cascade constraints;

drop table sys_role cascade constraints;

drop table sys_user cascade constraints;

/*==============================================================*/
/* Table: assist_message                                        */
/*==============================================================*/
create table assist_message 
(
   id                   CHAR(32)             not null,
   title                VARCHAR2(255)        not null,
   content              CLOB                 not null,
   send_time            DATE                 not null,
   sender_id            CHAR(32)             not null,
   receiver_user_ids    VARCHAR2(1000),
   receiver_class_ids   CHAR(32),
   expire_time          DATE                 not null,
   state                NUMBER(1)            not null,
   constraint PK_ASSIST_MESSAGE primary key (id)
);

comment on column assist_message.title is
'标题';

comment on column assist_message.content is
'内容';

comment on column assist_message.send_time is
'发送时间';

comment on column assist_message.sender_id is
'对应用户表的id';

comment on column assist_message.receiver_user_ids is
'指定接收者（个人，可选），以拼串形式存放！';

comment on column assist_message.receiver_class_ids is
'指定接收者（班级，可选）';

comment on column assist_message.expire_time is
'过期时间';

comment on column assist_message.state is
'状态：1、生效；2、待生效；3、已删除。';

/*==============================================================*/
/* Table: assit_db_data                                         */
/*==============================================================*/
create table assit_db_data 
(
   id                   CHAR(32)             not null,
   bak_url              VARCHAR2(255)        not null,
   bak_time             DATE                 not null,
   bak_memo             VARCHAR2(255)        not null,
   restore_time         DATE,
   restore_memo         VARCHAR2(255),
   operate_user_id      CHAR(32)             not null,
   is_new_data          NUMBER(1)            not null,
   constraint PK_ASSIT_DB_DATA primary key (id)
);

comment on table assit_db_data is
'数据备份';

comment on column assit_db_data.bak_url is
'存放地址，对应服务器上所在文件夹的地址或名称';

comment on column assit_db_data.bak_time is
'备份时间';

comment on column assit_db_data.bak_memo is
'备份备注';

comment on column assit_db_data.restore_time is
'还原时间';

comment on column assit_db_data.restore_memo is
'还原备注';

comment on column assit_db_data.operate_user_id is
'对应用户表的id';

comment on column assit_db_data.is_new_data is
'是否为最新数据，1：是；2：否';

/*==============================================================*/
/* Table: exam_plan                                             */
/*==============================================================*/
create table exam_plan 
(
   id                   CHAR(32)             not null,
   exam_time_start      DATE                 not null,
   exam_time_stop       DATE                 not null,
   exam_classroom       VARCHAR2(40)         not null,
   exam_paper_id        CHAR(32)             not null,
   to_user_ids          VARCHAR2(1000),
   to_class_id          CHAR(32),
   operate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255),
   constraint PK_EXAM_PLAN primary key (id)
);

comment on table exam_plan is
'考试安排';

comment on column exam_plan.exam_time_start is
'考试时间（起）';

comment on column exam_plan.exam_time_stop is
'考试时间（止）';

comment on column exam_plan.exam_classroom is
'考试教室';

comment on column exam_plan.exam_paper_id is
'对应试卷表的id';

comment on column exam_plan.to_user_ids is
'指定学员（个人，可选），使用拼串形式存放。';

comment on column exam_plan.to_class_id is
'指定班级（班级，可选）';

comment on column exam_plan.operate_user_id is
'操作者';

comment on column exam_plan.operate_time is
'操作时间';

comment on column exam_plan.state is
'状态：1、生效；2、待生效；3、已删除。';

comment on column exam_plan.memo is
'备注';

/*==============================================================*/
/* Table: exam_records                                          */
/*==============================================================*/
create table exam_records 
(
   id                   CHAR(32)             not null,
   user_id              CHAR(32)             not null,
   exam_plan_id         CHAR(32)             not null,
   get_point            NUMBER(3),
   submit_time          DATE,
   start_time           DATE                 not null,
   is_pass              NUMBER(1)            not null,
   constraint PK_EXAM_RECORDS primary key (id)
);

comment on table exam_records is
'考试记录';

comment on column exam_records.user_id is
'所属用户';

comment on column exam_records.get_point is
'得分数';

comment on column exam_records.submit_time is
'提交时间';

comment on column exam_records.start_time is
'开始时间';

comment on column exam_records.is_pass is
'是否通过：1、通过；2、未通过；3、未提交或超时。';

/*==============================================================*/
/* Table: qes_exam_paper                                        */
/*==============================================================*/
create table qes_exam_paper 
(
   id                   CHAR(32)             not null,
   name                 VARCHAR2(40)         not null,
   paper_url            VARCHAR2(255)        not null,
   paper_type           NUMBER(1)            not null,
   course_id            CHAR(32)             not null,
   total_point          NUMBER(3)            not null,
   pass_point           NUMBER(3)            not null,
   total_minutes        NUMBER(3)            not null,
   single_option_number NUMBER(2),
   single_option_each_point NUMBER(2),
   single_option_difficulty NUMBER(1),
   multi_option_number  NUMBER(2),
   multi_option_each_point NUMBER(2),
   multi_option_difficulty NUMBER(1),
   judge_number         NUMBER(2),
   judge_each_point     NUMBER(2),
   judge_difficulty     NUMBER(1),
   opreate_user_id      CHAR(32)             not null,
   opreate_time         DATE                 not null,
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255)        not null,
   constraint PK_QES_EXAM_PAPER primary key (id)
);

comment on table qes_exam_paper is
'试卷';

comment on column qes_exam_paper.name is
'名称';

comment on column qes_exam_paper.paper_url is
'试卷存放';

comment on column qes_exam_paper.paper_type is
'试卷类型：1、平时测试；2、结业考试。';

comment on column qes_exam_paper.course_id is
'对应课程的表id';

comment on column qes_exam_paper.total_point is
'总分分数';

comment on column qes_exam_paper.pass_point is
'通过分数';

comment on column qes_exam_paper.total_minutes is
'总时间（分）';

comment on column qes_exam_paper.single_option_number is
'单选题个数';

comment on column qes_exam_paper.single_option_each_point is
'单选题单个分数';

comment on column qes_exam_paper.single_option_difficulty is
'单选题难度：1、低；2、中；3、较高；4、高。';

comment on column qes_exam_paper.multi_option_number is
'多选题个数';

comment on column qes_exam_paper.multi_option_each_point is
'多选题单个分数';

comment on column qes_exam_paper.multi_option_difficulty is
'多选题难度：1、低；2、中；3、较高；4、高。';

comment on column qes_exam_paper.judge_number is
'判断题个数';

comment on column qes_exam_paper.judge_each_point is
'判断题单个分数';

comment on column qes_exam_paper.judge_difficulty is
'判断题难度：1、低；2、中；3、较高；4、高。';

comment on column qes_exam_paper.opreate_user_id is
'对应用户表id';

comment on column qes_exam_paper.opreate_time is
'操作时间';

comment on column qes_exam_paper.state is
'状态：1、有效；2、无效。';

comment on column qes_exam_paper.memo is
'备注';

/*==============================================================*/
/* Table: qes_qestions                                          */
/*==============================================================*/
create table qes_qestions 
(
   id                   CHAR(32)             not null,
   question             VARCHAR2(3000)       not null,
   attachment           VARCHAR2(1000),
   question_type        NUMBER(1)            not null,
   course_id            CHAR(32)             not null,
   answer_a             VARCHAR2(1000),
   answer_b             VARCHAR2(1000),
   answer_c             VARCHAR2(1000),
   answer_d             VARCHAR2(1000),
   answer               VARCHAR2(3000)       not null,
   difficulty           NUMBER(1)            not null,
   analysis             VARCHAR2(1000),
   keywords             VARCHAR2(255),
   operate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255),
   constraint PK_QES_QESTIONS primary key (id)
);

comment on column qes_qestions.question is
'题目';

comment on column qes_qestions.attachment is
'附件（图片、视频等）';

comment on column qes_qestions.question_type is
'题型：1、单选；2、多选；3、判断。';

comment on column qes_qestions.course_id is
'对应课程表的id';

comment on column qes_qestions.answer_a is
'选项A';

comment on column qes_qestions.answer_b is
'选项B';

comment on column qes_qestions.answer_c is
'选项C';

comment on column qes_qestions.answer_d is
'选项D';

comment on column qes_qestions.answer is
'答案（多选题以拼串以形式存放）';

comment on column qes_qestions.difficulty is
'难度：1、低；2、中；3、较高；4、高。';

comment on column qes_qestions.analysis is
'分析';

comment on column qes_qestions.keywords is
'关键词（以拼串形式存放）';

comment on column qes_qestions.operate_user_id is
'操作者';

comment on column qes_qestions.operate_time is
'操作时间';

comment on column qes_qestions.state is
'状态：1、有效；2、无效。';

comment on column qes_qestions.memo is
'备注';

/*==============================================================*/
/* Table: sys_class                                             */
/*==============================================================*/
create table sys_class 
(
   id                   CHAR(32)             not null,
   name                 VARCHAR2(40)         not null,
   course_id            CHAR(32)             not null,
   operate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255),
   constraint PK_SYS_CLASS primary key (id)
);

comment on table sys_class is
'班级';

comment on column sys_class.name is
'名称';

comment on column sys_class.course_id is
'关联课程：参考课程表的id';

comment on column sys_class.operate_user_id is
'对应用户表的id';

comment on column sys_class.operate_time is
'操作时间';

comment on column sys_class.state is
'状态：1、有效；2、无效。';

comment on column sys_class.memo is
'备注';

/*==============================================================*/
/* Table: sys_course                                            */
/*==============================================================*/
create table sys_course 
(
   id                   CHAR(32)             not null,
   pid                  CHAR(32)             not null,
   is_leaf              NUMBER(1)            not null,
   name                 VARCHAR2(40)         not null,
   operate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255),
   constraint PK_SYS_COURSE primary key (id)
);

comment on table sys_course is
'课程';

comment on column sys_course.pid is
'父节点';

comment on column sys_course.is_leaf is
'是否叶子节点，1、是；2、否';

comment on column sys_course.name is
'名称';

comment on column sys_course.operate_user_id is
'对应用户表的id';

comment on column sys_course.operate_time is
'操作时间';

comment on column sys_course.state is
'状态：1、有效；2、无效。';

comment on column sys_course.memo is
'备注';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu 
(
   id                   CHAR(32)             not null,
   pid                  CHAR(32)             not null,
   is_leaf              NUMBER(1)            not null,
   name                 VARCHAR2(40)         not null,
   url                  VARCHAR2(255),
   sort                 NUMBER(2)            not null,
   opreate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   state                CHAR(32)             not null,
   memo                 VARCHAR2(255),
   constraint PK_SYS_MENU primary key (id)
);

comment on table sys_menu is
'菜单';

comment on column sys_menu.pid is
'上级id';

comment on column sys_menu.is_leaf is
'是否叶子节点，1、是；2、否';

comment on column sys_menu.name is
'菜单名';

comment on column sys_menu.url is
'对应地址';

comment on column sys_menu.sort is
'顺序';

comment on column sys_menu.opreate_user_id is
'对应用户表的id';

comment on column sys_menu.operate_time is
'操作时间';

comment on column sys_menu.state is
'状态：1、有效；2、无效。';

comment on column sys_menu.memo is
'desc';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role 
(
   id                   CHAR(32)             not null,
   name                 VARCHAR2(50)         not null,
   menu_ids             VARCHAR2(1000),
   operate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255),
   constraint PK_SYS_ROLE primary key (id)
);

comment on table sys_role is
'角色';

comment on column sys_role.name is
'角色名';

comment on column sys_role.menu_ids is
'对应菜单表里的id，以逗号分隔';

comment on column sys_role.operate_user_id is
'对应用户表的id';

comment on column sys_role.operate_time is
'操作时间';

comment on column sys_role.state is
'状态：1、有效；2、无效。';

comment on column sys_role.memo is
'备注';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user 
(
   id                   CHAR(32)             not null,
   code                 VARCHAR2(40)         not null,
   password             CHAR(32)             not null,
   name                 VARCHAR2(40)         not null,
   sex                  NUMBER(1)            not null,
   phone                VARCHAR2(40)         not null,
   email                VARCHAR2(40)         not null,
   create_time          DATE                 not null,
   operate_user_id      CHAR(32)             not null,
   operate_time         DATE                 not null,
   role_id              CHAR(32)             not null,
   class_id             CHAR(32),
   state                NUMBER(1)            not null,
   memo                 VARCHAR2(255),
   constraint PK_SYS_USER primary key (id)
);

comment on table sys_user is
'用户';

comment on column sys_user.code is
'帐号';

comment on column sys_user.password is
'密码';

comment on column sys_user.name is
'姓名';

comment on column sys_user.sex is
'性别：1、男；2、女。';

comment on column sys_user.phone is
'电话';

comment on column sys_user.email is
'邮箱';

comment on column sys_user.create_time is
'创建时间';

comment on column sys_user.operate_user_id is
'对应用户表的id';

comment on column sys_user.operate_time is
'操作时间';

comment on column sys_user.role_id is
'对应角色表的id';

comment on column sys_user.class_id is
'学员所属班级：对应班级表里的id';

comment on column sys_user.state is
'状态：1、有效；2、无效。';

comment on column sys_user.memo is
'备注';

