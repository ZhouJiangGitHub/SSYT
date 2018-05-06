/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/5/11 18:39:46                           */
/*==============================================================*/
create database yec;
use yec;

drop table if exists assist_message;

drop table if exists assit_db_data;

drop table if exists exam_plan;

drop table if exists exam_records;

drop table if exists qes_exam_paper;

drop table if exists qes_qestions;

drop table if exists sys_class;

drop table if exists sys_course;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_user;
-- ----------------------------
-- Table structure for rightinfo
-- ----------------------------
DROP TABLE IF EXISTS rightinfo;
CREATE TABLE rightinfo (
  rightId varchar(11) NOT NULL,
  rightname varchar(30) DEFAULT NULL,
  righturl varchar(255) DEFAULT NULL,
  parentId varchar(11) DEFAULT NULL,
  PRIMARY KEY (rightId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rightinfo
-- ----------------------------
INSERT INTO rightinfo VALUES ('M001', '系统管理', 'xxx', 'root');
INSERT INTO rightinfo VALUES ('M001001', '用户管理', 'SysServlet?command=sysindex', 'M001');
INSERT INTO rightinfo VALUES ('M001002', '角色管理', 'RoleServlet?command=list', 'M001');
INSERT INTO rightinfo VALUES ('M001003', '班级管理', 'ClassServlet?command=List', 'M001');
INSERT INTO rightinfo VALUES ('M001004', '课程管理', 'CourseServlet?command=List', 'M001');
INSERT INTO rightinfo VALUES ('M001005', '授权管理', 'SysServlet?command=getRoles', 'M001');
INSERT INTO rightinfo VALUES ('M001006', '数据备份', 'AssistServlet?command=list', 'M001');
INSERT INTO rightinfo VALUES ('M002', '题库模块', 'xxxx', 'root');
INSERT INTO rightinfo VALUES ('M002001', '试题管理', 'QuestionServlet?command=listPage', 'M002');
INSERT INTO rightinfo VALUES ('M002002', '试卷管理', 'ExaminationPaperServlet?command=list', 'M002');
INSERT INTO rightinfo VALUES ('M002003', '错题管理', 'StudentServlet?command=error_questions', 'M002');
INSERT INTO rightinfo VALUES ('M003', '考试模块', 'xxx', 'root');
INSERT INTO rightinfo VALUES ('M003001', '考试安排', 'ExamManageServlet?command=listExamManageByPage', 'M003');
INSERT INTO rightinfo VALUES ('M003002', '成绩统计', 'ExamServlet?command=List', 'M003');
INSERT INTO rightinfo VALUES ('M004', '辅助模块', 'xxx', 'root');
INSERT INTO rightinfo VALUES ('M004001', '站内消息', 'StandServlet?command=List', 'M004');
INSERT INTO rightinfo VALUES ('M004002', '新闻公告', 'xxx', 'M004');
INSERT INTO rightinfo VALUES ('M005', '学员模块', 'xxx', 'root');
INSERT INTO rightinfo VALUES ('M005001', '进行考试', 'StudentServlet?command=list', 'M005');
INSERT INTO rightinfo VALUES ('M005002', '考试记录', 'ExamServlet?command=List', 'M005');
INSERT INTO rightinfo VALUES ('M005003', '个人信息', 'LoginServlet?command=user-detail', 'M005');
INSERT INTO rightinfo VALUES ('M005004', '查看待考', 'StudentServlet?command=list', 'M005');




-- ----------------------------
-- Table structure for rolerightinfo
-- ----------------------------
DROP TABLE IF EXISTS rolerightinfo;
CREATE TABLE rolerightinfo (
  rrId int(11) NOT NULL AUTO_INCREMENT,
  rightId varchar(255) DEFAULT NULL,
  roleId int(11) DEFAULT NULL,
  PRIMARY KEY (rrId)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolerightinfo
-- ----------------------------
INSERT INTO rolerightinfo VALUES ('1', 'M001', '1');
INSERT INTO rolerightinfo VALUES ('2', 'M001001', '1');
INSERT INTO rolerightinfo VALUES ('3', 'M001002', '1');
INSERT INTO rolerightinfo VALUES ('4', 'M001003', '1');
INSERT INTO rolerightinfo VALUES ('5', 'M001004', '1');
INSERT INTO rolerightinfo VALUES ('6', 'M001005', '1');
INSERT INTO rolerightinfo VALUES ('7', 'M001006', '1');
INSERT INTO rolerightinfo VALUES ('8', 'M002', '2');
INSERT INTO rolerightinfo VALUES ('9', 'M002001', '2');
INSERT INTO rolerightinfo VALUES ('10', 'M002002', '2');
INSERT INTO rolerightinfo VALUES ('11', 'M002003', '2');
INSERT INTO rolerightinfo VALUES ('12', 'M003', '2');
INSERT INTO rolerightinfo VALUES ('13', 'M003001', '2');
INSERT INTO rolerightinfo VALUES ('14', 'M003002', '2');
INSERT INTO rolerightinfo VALUES ('15', 'M004', '1');
INSERT INTO rolerightinfo VALUES ('16', 'M004001', '1');
INSERT INTO rolerightinfo VALUES ('17', 'M004002', '1');
INSERT INTO rolerightinfo VALUES ('18', 'M005', '3');
INSERT INTO rolerightinfo VALUES ('19', 'M005001', '3');
INSERT INTO rolerightinfo VALUES ('20', 'M005002', '3');
INSERT INTO rolerightinfo VALUES ('21', 'M005003', '3');
INSERT INTO rolerightinfo VALUES ('22', 'M005004', '3');

/*==============================================================*/
/* Table: assist_message                                        */
/*==============================================================*/
create table assist_message
(
   id                   int unsigned not null auto_increment,
   title                varchar(255) not null comment '标题',
   content              text not null comment '内容',
   send_time            datetime not null comment '发送时间',
   sender_id            int unsigned not null comment '对应用户表的id',
   receiver_user_ids    varchar(1000) comment '指定接收者（个人，可选），以拼串形式存放！',
   receiver_class_ids   int unsigned comment '指定接收者（班级，可选）',
   expire_time          datetime not null comment '过期时间',
   state                tinyint not null comment '状态：1、生效；2、待生效；3、已删除。',
   primary key (id)
);

/*==============================================================*/
/* Table: assit_db_data                                         */
/*==============================================================*/
create table assit_db_data
(
   id                   int not null auto_increment,
   bak_url              varchar(255) not null comment '存放地址，对应服务器上所在文件夹的地址或名称',
   bak_time             datetime not null comment '备份时间',
   bak_memo             varchar(50) not null comment '备份备注',
   restore_time         datetime comment '还原时间',
   restore_memo         varchar(50) comment '还原备注',
   operate_user_id      int unsigned not null comment '对应用户表的id',
   is_new_data          tinyint not null comment '是否为最新数据，1：是；2：否',
   primary key (id)
);
INSERT INTO assit_db_data VALUES(NULL,'e://SSSS',NOW(),'备份管理备注',NOW(),'还原备注',1,2);
alter table assit_db_data comment '数据备份';

/*==============================================================*/
/* Table: exam_plan                                             */
/*==============================================================*/
create table exam_plan
(
   id                   int unsigned not null auto_increment,
   exam_time_start      datetime not null comment '考试时间（起）',
   exam_time_stop       datetime not null comment '考试时间（止）',
   exam_classroom       varchar(40) not null comment '考试教室',
   exam_paper_id        int unsigned not null comment '对应试卷表的id',
   to_user_ids          varchar(1000) comment '指定学员（个人，可选），使用拼串形式存放。',
   to_class_id          int unsigned comment '指定班级（班级，可选）',
   operate_user_id      int unsigned not null comment '操作者',
   operate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、生效；2、待生效；3、已删除。',
   memo                 varchar(255) comment '备注',
   primary key (id)
);
INSERT INTO exam_plan VALUES(null,'2016-5-16 14:30:00','2016-5-16 17:30:00','云图一班',1,'张三',1,1,NOW(),1,'sys_sys_class');
alter table exam_plan comment '考试安排';

/*==============================================================*/
/* Table: exam_records                                          */
/*==============================================================*/
create table exam_records
(
   id                   int unsigned not null auto_increment,
   user_id              int unsigned not null comment '所属用户',
   exam_plan_id         int unsigned not null,
   get_point            int comment '得分数',
   submit_time          datetime comment '提交时间',
   start_time           datetime not null comment '开始时间',
   is_pass              tinyint not null comment '是否通过：1、通过；2、未通过；3、未提交或超时。',
   primary key (id)
);

alter table exam_records comment '考试记录';

/*==============================================================*/
/* Table: qes_exam_paper                                        */
/*==============================================================*/
create table qes_exam_paper
(
   id                   int unsigned not null auto_increment,
   name                 varchar(40) not null comment '名称',
   paper_url            varchar(255) not null comment '试卷存放',
   paper_type           tinyint not null comment '试卷类型：1、平时测试；2、结业考试。',
   course_id            int unsigned not null comment '对应课程的表id',
   total_point          int not null comment '总分分数',
   pass_point           int not null comment '通过分数',
   total_minutes        int not null comment '总时间（分）',
   single_option_number tinyint comment '单选题个数',
   single_option_each_point tinyint comment '单选题单个分数',
   single_option_difficulty tinyint comment '单选题难度：1、低；2、中；3、较高；4、高。',
   multi_option_number  tinyint comment '多选题个数',
   multi_option_each_point tinyint comment '多选题单个分数',
   multi_option_difficulty tinyint comment '多选题难度：1、低；2、中；3、较高；4、高。',
   judge_number         tinyint comment '判断题个数',
   judge_each_point     tinyint comment '判断题单个分数',
   judge_difficulty     tinyint comment '判断题难度：1、低；2、中；3、较高；4、高。',
   opreate_user_id      int unsigned not null comment '对应用户表id',
   opreate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) not null comment '备注',
   primary key (id)
);
INSERT INTO qes_exam_paper VALUES(null,'php','ss',1,1,100,60,120,10,5,2,10,5,2,10,5,2,2,NOW(),1,'qes_exam_paper');
alter table qes_exam_paper comment '试卷';

/*==============================================================*/
/* Table: qes_qestions                                          */
/*==============================================================*/
create table qes_qestions
(
   id                   int unsigned not null auto_increment,
   question             varchar(3000) not null comment '题目',
   attachment           varchar(1000) comment '附件（图片、视频等）',
   question_type        tinyint not null comment '题型：1、单选；2、多选；3、判断。',
   course_id            int unsigned not null comment '对应课程表的id',
   answer_a             varchar(1000) comment '选项A',
   answer_b             varchar(1000) comment '选项B',
   answer_c             varchar(1000) comment '选项C',
   answer_d             varchar(1000) comment '选项D',
   answer               varchar(3000) not null comment '答案（多选题以拼串以形式存放）',
   difficulty           tinyint not null comment '难度：1、低；2、中；3、较高；4、高。',
   analysis             varchar(1000) comment '分析',
   keywords             varchar(255) comment '关键词（以拼串形式存放）',
   operate_user_id      int unsigned not null comment '操作者',
   operate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) comment '备注',
   primary key (id)
);
insert into qes_qestions values(null,'上善云图小学加减法','图片，av视频',1,2,'选项A','选项B','选项C','选项c','正确答案','4','做人要走地下水道','你看你这汗样',2,NOW(),1,'地下通道');
/*==============================================================*/
/* Table: sys_class                                             */
/*==============================================================*/
create table sys_class
(
   id                   int unsigned not null auto_increment,
   name                 varchar(40) not null comment '名称',
   course_id            int unsigned not null comment '关联课程：参考课程表的id',
   operate_user_id      int unsigned not null comment '对应用户表的id',
   operate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) comment '备注',
   primary key (id)
);
INSERT INTO sys_class VALUES(null,'云图一班',1,1,NOW(),1,'sys_sys_class');
alter table sys_class comment '班级';

/*==============================================================*/
/* Table: sys_course                                            */
/*==============================================================*/
create table sys_course
(
   id                   int unsigned not null auto_increment,
   pid                  int unsigned not null comment '父节点',
   is_leaf              tinyint not null comment '是否叶子节点，1、是；2、否',
   name                 varchar(40) not null comment '名称',
   operate_user_id      int unsigned not null comment '对应用户表的id',
   operate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) comment '备注',
   primary key (id)
);


/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   int unsigned not null,
   pid                  int unsigned not null comment '上级id',
   is_leaf              tinyint not null comment '是否叶子节点，1、是；2、否',
   name                 varchar(40) not null comment '菜单名',
   url                  varchar(255) comment '对应地址',
   sort                 tinyint not null comment '顺序',
   opreate_user_id      int unsigned not null comment '对应用户表的id',
   operate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) comment 'desc',
   primary key (id)
);
INSERT INTO sys_menu VALUES(1,1,1,'PHP','www.baidu.com',1,1,NOW(),1,'sys_menu_memo');
alter table sys_menu comment '菜单';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   int unsigned not null auto_increment,
   name                 varchar(50) not null comment '角色名',
   menu_ids             varchar(1000) comment '对应菜单表里的id，以逗号分隔',
   operate_user_id      int unsigned not null comment '对应用户表的id',
   operate_time         datetime not null comment '操作时间',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) comment '备注',
   primary key (id)
);
insert into sys_role values(null,"系统管理员",'menu_ids',1,NOW(),1,"系统默认添加");
insert into sys_role values(null,"老师",'menu_ids',1,NOW(),1,"系统默认添加");
INSERT INTO sys_role VALUES(null,'学生','menu_ids',1,NOW(),1,'sys_role_memo');
alter table sys_role comment '角色';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   int unsigned not null auto_increment,
   code                 varchar(40) not null comment '帐号',
   password             varchar(32) not null comment '密码',
   name                 varchar(40) not null comment '姓名',
   sex                  tinyint not null comment '性别：1、男；2、女。',
   phone                varchar(40) not null comment '电话',
   email                varchar(40) not null comment '邮箱',
   create_time          datetime not null comment '创建时间',
   operate_user_id      int unsigned not null comment '对应用户表的id',
   operate_time         datetime not null comment '操作时间',
   role_id              int unsigned not null comment '对应角色表的id',
   class_id             int unsigned comment '学员所属班级：对应班级表里的id',
   state                tinyint not null comment '状态：1、有效；2、无效。',
   memo                 varchar(255) comment '备注',
   primary key (id)
);
insert into sys_user values(null,"admin","admin","admin",1,"18785463594","1245678@qq.com",'2015-02-05',1,'2015-02-05',1,1,1,"该用户为系统默认添加");
insert into sys_user values(null,"zhangsan","zhangsan","张三",1,"13765432101","9876541238@qq.com",'2015-02-05',1,'2015-02-05',3,1,1,"该用户为系统默认添加");
INSERT INTO sys_user VALUES(null,'teacher','123456','Runs',1,'18798801610','327972574@qq.com',NOW(),1,NOW(),2,1,1,'sssss');
alter table sys_user comment '用户';


CREATE TABLE student (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键	 
	code VARCHAR(50) NOT NULL,					-- 帐号（用于求职者登录）
	password VARCHAR(32) NOT NULL,				-- 密码（用于求职者登录）
	name VARCHAR(45) NOT NULL,					-- 姓名
	phone VARCHAR(20) NOT NULL,					-- 电话
	email VARCHAR(50) NOT NULL,					-- 邮箱
	age INT NOT NULL,							-- 年龄
	gender INT NOT NULL,						-- 性别（1=男；2=女）
	hobby VARCHAR(200)							-- 爱好(如：Java|PHP)
);


DROP TABLE IF EXISTS `error_questions`;
CREATE TABLE `error_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `error_answer` varchar(10) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;


INSERT INTO student VALUES(null,'runs1314','123456','Runs','18798801610','327972574@qq.com',18,1,'Java|LOL');





insert into sys_class values(null,"云图1班",1,1,'2015-03-08',1,"云图1班………………");
insert into sys_class values(null,"雷电1班",1,1,'2015-01-01',2,"雷电1班………………");


INSERT INTO `sys_course` VALUES (1, 0, 2, '课程管理', 1, '2016-3-16 09:13:46', 1, 'sys_course_memo');
INSERT INTO `sys_course` VALUES (11, 1, 2, 'java', 1, '2016-3-16 09:24:15', 1, '11111');
INSERT INTO `sys_course` VALUES (12, 1, 2, 'HTML', 1, '2016-3-16 09:28:08', 1, '444444444');
INSERT INTO `sys_course` VALUES (111, 11, 1, 'java Web', 1, '2016-3-16 09:25:27', 1, '2222222222');
INSERT INTO `sys_course` VALUES (112, 11, 1, 'javaScript', 1, '2016-3-16 09:26:02', 1, '33333333');
INSERT INTO `sys_course` VALUES (121, 12, 1, 'HTML5+CSS3', 2, '2016-3-16 17:27:00', 1, '555555555');
INSERT INTO `sys_course` VALUES (122, 12, 1, 'CSS3', 2, '2016-3-16 17:33:23', 2, '6666666666');
