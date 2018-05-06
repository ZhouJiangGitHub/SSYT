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
'����';

comment on column assist_message.content is
'����';

comment on column assist_message.send_time is
'����ʱ��';

comment on column assist_message.sender_id is
'��Ӧ�û����id';

comment on column assist_message.receiver_user_ids is
'ָ�������ߣ����ˣ���ѡ������ƴ����ʽ��ţ�';

comment on column assist_message.receiver_class_ids is
'ָ�������ߣ��༶����ѡ��';

comment on column assist_message.expire_time is
'����ʱ��';

comment on column assist_message.state is
'״̬��1����Ч��2������Ч��3����ɾ����';

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
'���ݱ���';

comment on column assit_db_data.bak_url is
'��ŵ�ַ����Ӧ�������������ļ��еĵ�ַ������';

comment on column assit_db_data.bak_time is
'����ʱ��';

comment on column assit_db_data.bak_memo is
'���ݱ�ע';

comment on column assit_db_data.restore_time is
'��ԭʱ��';

comment on column assit_db_data.restore_memo is
'��ԭ��ע';

comment on column assit_db_data.operate_user_id is
'��Ӧ�û����id';

comment on column assit_db_data.is_new_data is
'�Ƿ�Ϊ�������ݣ�1���ǣ�2����';

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
'���԰���';

comment on column exam_plan.exam_time_start is
'����ʱ�䣨��';

comment on column exam_plan.exam_time_stop is
'����ʱ�䣨ֹ��';

comment on column exam_plan.exam_classroom is
'���Խ���';

comment on column exam_plan.exam_paper_id is
'��Ӧ�Ծ���id';

comment on column exam_plan.to_user_ids is
'ָ��ѧԱ�����ˣ���ѡ����ʹ��ƴ����ʽ��š�';

comment on column exam_plan.to_class_id is
'ָ���༶���༶����ѡ��';

comment on column exam_plan.operate_user_id is
'������';

comment on column exam_plan.operate_time is
'����ʱ��';

comment on column exam_plan.state is
'״̬��1����Ч��2������Ч��3����ɾ����';

comment on column exam_plan.memo is
'��ע';

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
'���Լ�¼';

comment on column exam_records.user_id is
'�����û�';

comment on column exam_records.get_point is
'�÷���';

comment on column exam_records.submit_time is
'�ύʱ��';

comment on column exam_records.start_time is
'��ʼʱ��';

comment on column exam_records.is_pass is
'�Ƿ�ͨ����1��ͨ����2��δͨ����3��δ�ύ��ʱ��';

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
'�Ծ�';

comment on column qes_exam_paper.name is
'����';

comment on column qes_exam_paper.paper_url is
'�Ծ���';

comment on column qes_exam_paper.paper_type is
'�Ծ����ͣ�1��ƽʱ���ԣ�2����ҵ���ԡ�';

comment on column qes_exam_paper.course_id is
'��Ӧ�γ̵ı�id';

comment on column qes_exam_paper.total_point is
'�ַܷ���';

comment on column qes_exam_paper.pass_point is
'ͨ������';

comment on column qes_exam_paper.total_minutes is
'��ʱ�䣨�֣�';

comment on column qes_exam_paper.single_option_number is
'��ѡ�����';

comment on column qes_exam_paper.single_option_each_point is
'��ѡ�ⵥ������';

comment on column qes_exam_paper.single_option_difficulty is
'��ѡ���Ѷȣ�1���ͣ�2���У�3���ϸߣ�4���ߡ�';

comment on column qes_exam_paper.multi_option_number is
'��ѡ�����';

comment on column qes_exam_paper.multi_option_each_point is
'��ѡ�ⵥ������';

comment on column qes_exam_paper.multi_option_difficulty is
'��ѡ���Ѷȣ�1���ͣ�2���У�3���ϸߣ�4���ߡ�';

comment on column qes_exam_paper.judge_number is
'�ж������';

comment on column qes_exam_paper.judge_each_point is
'�ж��ⵥ������';

comment on column qes_exam_paper.judge_difficulty is
'�ж����Ѷȣ�1���ͣ�2���У�3���ϸߣ�4���ߡ�';

comment on column qes_exam_paper.opreate_user_id is
'��Ӧ�û���id';

comment on column qes_exam_paper.opreate_time is
'����ʱ��';

comment on column qes_exam_paper.state is
'״̬��1����Ч��2����Ч��';

comment on column qes_exam_paper.memo is
'��ע';

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
'��Ŀ';

comment on column qes_qestions.attachment is
'������ͼƬ����Ƶ�ȣ�';

comment on column qes_qestions.question_type is
'���ͣ�1����ѡ��2����ѡ��3���жϡ�';

comment on column qes_qestions.course_id is
'��Ӧ�γ̱��id';

comment on column qes_qestions.answer_a is
'ѡ��A';

comment on column qes_qestions.answer_b is
'ѡ��B';

comment on column qes_qestions.answer_c is
'ѡ��C';

comment on column qes_qestions.answer_d is
'ѡ��D';

comment on column qes_qestions.answer is
'�𰸣���ѡ����ƴ������ʽ��ţ�';

comment on column qes_qestions.difficulty is
'�Ѷȣ�1���ͣ�2���У�3���ϸߣ�4���ߡ�';

comment on column qes_qestions.analysis is
'����';

comment on column qes_qestions.keywords is
'�ؼ��ʣ���ƴ����ʽ��ţ�';

comment on column qes_qestions.operate_user_id is
'������';

comment on column qes_qestions.operate_time is
'����ʱ��';

comment on column qes_qestions.state is
'״̬��1����Ч��2����Ч��';

comment on column qes_qestions.memo is
'��ע';

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
'�༶';

comment on column sys_class.name is
'����';

comment on column sys_class.course_id is
'�����γ̣��ο��γ̱��id';

comment on column sys_class.operate_user_id is
'��Ӧ�û����id';

comment on column sys_class.operate_time is
'����ʱ��';

comment on column sys_class.state is
'״̬��1����Ч��2����Ч��';

comment on column sys_class.memo is
'��ע';

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
'�γ�';

comment on column sys_course.pid is
'���ڵ�';

comment on column sys_course.is_leaf is
'�Ƿ�Ҷ�ӽڵ㣬1���ǣ�2����';

comment on column sys_course.name is
'����';

comment on column sys_course.operate_user_id is
'��Ӧ�û����id';

comment on column sys_course.operate_time is
'����ʱ��';

comment on column sys_course.state is
'״̬��1����Ч��2����Ч��';

comment on column sys_course.memo is
'��ע';

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
'�˵�';

comment on column sys_menu.pid is
'�ϼ�id';

comment on column sys_menu.is_leaf is
'�Ƿ�Ҷ�ӽڵ㣬1���ǣ�2����';

comment on column sys_menu.name is
'�˵���';

comment on column sys_menu.url is
'��Ӧ��ַ';

comment on column sys_menu.sort is
'˳��';

comment on column sys_menu.opreate_user_id is
'��Ӧ�û����id';

comment on column sys_menu.operate_time is
'����ʱ��';

comment on column sys_menu.state is
'״̬��1����Ч��2����Ч��';

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
'��ɫ';

comment on column sys_role.name is
'��ɫ��';

comment on column sys_role.menu_ids is
'��Ӧ�˵������id���Զ��ŷָ�';

comment on column sys_role.operate_user_id is
'��Ӧ�û����id';

comment on column sys_role.operate_time is
'����ʱ��';

comment on column sys_role.state is
'״̬��1����Ч��2����Ч��';

comment on column sys_role.memo is
'��ע';

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
'�û�';

comment on column sys_user.code is
'�ʺ�';

comment on column sys_user.password is
'����';

comment on column sys_user.name is
'����';

comment on column sys_user.sex is
'�Ա�1���У�2��Ů��';

comment on column sys_user.phone is
'�绰';

comment on column sys_user.email is
'����';

comment on column sys_user.create_time is
'����ʱ��';

comment on column sys_user.operate_user_id is
'��Ӧ�û����id';

comment on column sys_user.operate_time is
'����ʱ��';

comment on column sys_user.role_id is
'��Ӧ��ɫ���id';

comment on column sys_user.class_id is
'ѧԱ�����༶����Ӧ�༶�����id';

comment on column sys_user.state is
'״̬��1����Ч��2����Ч��';

comment on column sys_user.memo is
'��ע';

