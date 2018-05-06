-- �������ݿ�
CREATE DATABASE hiring;

-- ʹ�����ݿ�
USE hiring;

-- ��ҵ��company 
CREATE TABLE company (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����
	code VARCHAR(50) NOT NULL,					-- �ʺţ�������ҵ��¼��
	password VARCHAR(50) NOT NULL,				-- ���루������ҵ��¼��
	name VARCHAR(50) NOT NULL,					-- ��ҵ����
	email VARCHAR(50) NOT NULL,					-- ��ҵ����
	phone VARCHAR(20) NOT NULL,					-- ��ϵ�绰
	linkman VARCHAR(20) NOT NULL,				-- ��ϵ��
	address VARCHAR(100) NOT NULL,				-- ��ϵ��ַ
	-- trade INT NOT NULL,						-- ��ҵ����ѡ����ʱ�����ô��
	-- type INT NOT NULL,						-- ���ʣ���ѡ����ʱ�����ô��
	numbers INT NOT NULL,						-- ����
	slogan VARCHAR(200) NOT NULL				-- ������ּ
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ְλ��position 
CREATE TABLE position (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����
	company_id INT UNSIGNED NOT NULL,			-- ������ҵ(�ο�company��id)
	name VARCHAR(50) NOT NULL,					-- ְλ����
	duty VARCHAR(1000) NOT NULL,			 	-- ְ��˵��
	ability VARCHAR(1000) NOT NULL,				-- ����Ҫ��									 
	salary1 INT NOT NULL,				 		-- н�ʣ���
	salary2 INT NOT NULL,						-- н�ʣ�ֹ��
	release_time DATETIME NOT NULL				-- ����ʱ��
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
-- SELECT * FROM position ORDER BY id DESC;
-- SELECT * FROM position WHERE id = ?

-- ��Ա��talent
CREATE TABLE talent (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����	 
	code VARCHAR(50) NOT NULL,					-- �ʺţ�������ְ�ߵ�¼��
	password VARCHAR(32) NOT NULL,				-- ���루������ְ�ߵ�¼��
	name VARCHAR(45) NOT NULL,					-- ����
	phone VARCHAR(20) NOT NULL,					-- �绰
	email VARCHAR(50) NOT NULL,					-- ����
	age INT NOT NULL,							-- ����
	gender INT NOT NULL,						-- �Ա�1=�У�2=Ů��
	hobby VARCHAR(200)							-- ����(�磺Java|PHP)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ������resume
CREATE TABLE resume(
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����	
	talent_id INT UNSIGNED NOT NULL,			-- ������ְ��(�ο�talent��id)
	intention VARCHAR(200) NOT NULL,			-- ��ְ����
	work_experience	TEXT NOT NULL,				-- ��������
	project_experience TEXT NOT NULL			-- ��Ŀ����
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ְλ���룺application
CREATE TABLE application (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,	-- ��ʶ����	 
	position_id INT UNSIGNED NOT NULL,			-- ����ְλ���ο�position��id��
	talent_id INT UNSIGNED NOT NULL,			-- ������ְ�ߣ��ο�talent��id��
	resume_id INT UNSIGNED NOT NULL, 			-- ������ְ��Ͷ�ݵļ���(�ο�resume��id)
	status INT NOT NULL,					    -- ����״̬��1=����ˣ�2=���ͨ����3=�Ѿܾ���
	app_time DATETIME NOT NULL,				    -- ��ְ�ߵ�����ʱ��
	handle_time DATETIME						-- ��ҵ�Ĵ���ʱ��
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- ��������
-- company
INSERT INTO company VALUES(1,'direct','direct0407','����������ͼ��Ϣ�������޹�˾','13389667698','759694429@163.com','���','����ɳƺ������΢��԰',350,'��Դ�����ɾͼ�ֵ��');
-- position
INSERT INTO position VALUES(1,1,'��ѵ��ʦ','��Ҫ����Java��Android����Ľ�ѧ','��ͨJava����ͨAndroid����ͨǰ��',7000,15000,NOW());
-- talent
INSERT INTO talent VALUES(null,'759694429@qq.com','xuweijun','hsu.','13389667698','759694429@qq.com',23,1,'Java|PHP');
-- resume
INSERT INTO resume VALUES(1,1,'��ѵ��ʦ','����xxx��˾�ܲá����������ߡ�������Ա','��xxx��˾�ɹ����У�');
-- application
INSERT INTO application VALUES(1,1,1,1,1,NOW(),NULL);
-- ����MySQL��Զ�̷���
-- grant all privileges on *.* to root@'%' identified by "123456";