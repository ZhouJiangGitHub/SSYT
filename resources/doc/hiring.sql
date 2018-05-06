-- 创建数据库
CREATE DATABASE hiring;

-- 使用数据库
USE hiring;

-- 企业：company 
CREATE TABLE company (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键
	code VARCHAR(50) NOT NULL,					-- 帐号（用于企业登录）
	password VARCHAR(50) NOT NULL,				-- 密码（用于企业登录）
	name VARCHAR(50) NOT NULL,					-- 企业名称
	email VARCHAR(50) NOT NULL,					-- 企业邮箱
	phone VARCHAR(20) NOT NULL,					-- 联系电话
	linkman VARCHAR(20) NOT NULL,				-- 联系人
	address VARCHAR(100) NOT NULL,				-- 联系地址
	-- trade INT NOT NULL,						-- 行业（可选，暂时不启用此项）
	-- type INT NOT NULL,						-- 性质（可选，暂时不启用此项）
	numbers INT NOT NULL,						-- 人数
	slogan VARCHAR(200) NOT NULL				-- 服务宗旨
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 职位：position 
CREATE TABLE position (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键
	company_id INT UNSIGNED NOT NULL,			-- 所属企业(参考company的id)
	name VARCHAR(50) NOT NULL,					-- 职位名称
	duty VARCHAR(1000) NOT NULL,			 	-- 职责说明
	ability VARCHAR(1000) NOT NULL,				-- 能力要求									 
	salary1 INT NOT NULL,				 		-- 薪资（起）
	salary2 INT NOT NULL,						-- 薪资（止）
	release_time DATETIME NOT NULL				-- 发布时间
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
-- SELECT * FROM position ORDER BY id DESC;
-- SELECT * FROM position WHERE id = ?

-- 人员：talent
CREATE TABLE talent (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键	 
	code VARCHAR(50) NOT NULL,					-- 帐号（用于求职者登录）
	password VARCHAR(32) NOT NULL,				-- 密码（用于求职者登录）
	name VARCHAR(45) NOT NULL,					-- 姓名
	phone VARCHAR(20) NOT NULL,					-- 电话
	email VARCHAR(50) NOT NULL,					-- 邮箱
	age INT NOT NULL,							-- 年龄
	gender INT NOT NULL,						-- 性别（1=男；2=女）
	hobby VARCHAR(200)							-- 爱好(如：Java|PHP)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 简历：resume
CREATE TABLE resume(
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键	
	talent_id INT UNSIGNED NOT NULL,			-- 所属求职者(参考talent的id)
	intention VARCHAR(200) NOT NULL,			-- 求职意向
	work_experience	TEXT NOT NULL,				-- 工作经历
	project_experience TEXT NOT NULL			-- 项目经验
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 职位申请：application
CREATE TABLE application (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键	 
	position_id INT UNSIGNED NOT NULL,			-- 所属职位（参考position的id）
	talent_id INT UNSIGNED NOT NULL,			-- 所属求职者（参考talent的id）
	resume_id INT UNSIGNED NOT NULL, 			-- 所属求职者投递的简历(参考resume的id)
	status INT NOT NULL,					    -- 申请状态（1=待审核；2=审核通过；3=已拒绝）
	app_time DATETIME NOT NULL,				    -- 求职者的申请时间
	handle_time DATETIME						-- 企业的处理时间
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- 测试数据
-- company
INSERT INTO company VALUES(1,'direct','direct0407','重庆上善云图信息技术有限公司','13389667698','759694429@163.com','张韬','重庆沙坪坝西永微电园',350,'资源共享，成就价值！');
-- position
INSERT INTO position VALUES(1,1,'培训讲师','主要负责Java和Android方向的教学','精通Java，精通Android，精通前端',7000,15000,NOW());
-- talent
INSERT INTO talent VALUES(null,'759694429@qq.com','xuweijun','hsu.','13389667698','759694429@qq.com',23,1,'Java|PHP');
-- resume
INSERT INTO resume VALUES(1,1,'培训讲师','曾任xxx公司总裁、经理、开发者、保洁人员','让xxx公司成功上市！');
-- application
INSERT INTO application VALUES(1,1,1,1,1,NOW(),NULL);
-- 允许MySQL从远程访问
-- grant all privileges on *.* to root@'%' identified by "123456";