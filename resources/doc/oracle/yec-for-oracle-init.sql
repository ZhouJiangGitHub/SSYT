INSERT INTO sys_course VALUES('e10adc3949ba59abbe56e057f20f8831', NULL, 2, '全部课程', 1, SYSDATE, 1, NULL);
INSERT INTO sys_course VALUES('e10adc3949ba59abbe56e057f20f8832', 'e10adc3949ba59abbe56e057f20f8831', 2, 'java', 1, SYSDATE, 1, NULL);
INSERT INTO sys_course VALUES('e10adc3949ba59abbe56e057f20f8833', 'e10adc3949ba59abbe56e057f20f8831', 2, '数字艺术', 1, SYSDATE, 1, NULL);
INSERT INTO sys_course VALUES('e10adc3949ba59abbe56e057f20f8834', 'e10adc3949ba59abbe56e057f20f8831', 2, '.net', 1, SYSDATE, 1, NULL);
INSERT INTO sys_course VALUES('e10adc3949ba59abbe56e057f20f8835', 'e10adc3949ba59abbe56e057f20f8831', 2, 'android', 1, SYSDATE, 1, NULL);
INSERT INTO sys_course VALUES('e10adc3949ba59abbe56e057f20f8836', 'e10adc3949ba59abbe56e057f20f8831', 2, 'ios', 1, SYSDATE, 1, NULL);

INSERT INTO sys_role VALUES('e10adc3949ba59abbe56e057f20f8837', '管理员', NULL, 1, SYSDATE, 1, NULL);
INSERT INTO sys_role VALUES('e10adc3949ba59abbe56e057f20f8838', '教师', NULL, 1, SYSDATE, 1, NULL);
INSERT INTO sys_role VALUES('e10adc3949ba59abbe56e057f20f8839', '学员', NULL, 1, SYSDATE, 1, NULL);

INSERT INTO sys_user VALUES('e10adc3949ba59abbe56e057f20f8810', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', 1, '18523381991', '759694429@qq.com', SYSDATE, 'e10adc3949ba59abbe56e057f20f8810', SYSDATE, 'e10adc3949ba59abbe56e057f20f8837', NULL, 1, NULL);