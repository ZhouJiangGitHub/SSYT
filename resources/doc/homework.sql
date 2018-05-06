CREATE TABLE sys_user (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����
	code VARCHAR(50) NOT NULL,					-- �ʺţ�������ҵ��¼��
	password VARCHAR(50) NOT NULL,				-- ���루������ҵ��¼��
	name VARCHAR(50) NOT NULL,					-- ����
	gender INT NOT NULL,						-- �Ա�1=�У�2=Ů��
	role_id VARCHAR(50) NOT NULL,               -- ��ɫ
	create_time DATETIME NOT NULL,				-- ����ʱ��
	state INT NOT NULL                        -- ״̬��1=�����ã�2=���ã�
);
CREATE TABLE sys_role (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����
	name VARCHAR(50) NOT NULL,					-- ��ɫ��
	create_time DATETIME NOT NULL,				-- ����ʱ��
	comment TEXT NOT NULL			            -- ��ɫ����
);
INSERT INTO sys_user VALUES(null,'admin','123456','����',1,'����Ա','2015-03-21',1);
INSERT INTO sys_user VALUES(null,'lisi ','123456','����',2,'��ͨ','2015-03-21',2);
INSERT INTO sys_role VALUES(null,'����Ա','2015-03-21','�������Ա�Ĺ���Ա');

