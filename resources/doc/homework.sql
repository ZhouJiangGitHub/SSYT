CREATE TABLE sys_user (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键
	code VARCHAR(50) NOT NULL,					-- 帐号（用于企业登录）
	password VARCHAR(50) NOT NULL,				-- 密码（用于企业登录）
	name VARCHAR(50) NOT NULL,					-- 姓名
	gender INT NOT NULL,						-- 性别（1=男；2=女）
	role_id VARCHAR(50) NOT NULL,               -- 角色
	create_time DATETIME NOT NULL,				-- 申请时间
	state INT NOT NULL                        -- 状态（1=已启用；2=禁用）
);
CREATE TABLE sys_role (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- 标识主键
	name VARCHAR(50) NOT NULL,					-- 角色名
	create_time DATETIME NOT NULL,				-- 创建时间
	comment TEXT NOT NULL			            -- 角色描述
);
INSERT INTO sys_user VALUES(null,'admin','123456','张三',1,'管理员','2015-03-21',1);
INSERT INTO sys_user VALUES(null,'lisi ','123456','李四',2,'普通','2015-03-21',2);
INSERT INTO sys_role VALUES(null,'管理员','2015-03-21','管理管理员的管理员');

