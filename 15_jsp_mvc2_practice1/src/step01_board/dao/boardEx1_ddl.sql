USE MVC2_PRACTICE;

CREATE TABLE BOARD1 (
	BOARD_ID 	BIGINT AUTO_INCREMENT PRIMARY KEY,
	WRITER		VARCHAR(50),
	EMAIL		VARCHAR(50),
	SUBJECT		VARCHAR(100),
	PASSWORD	VARCHAR(20),
	CONTENT		VARCHAR(2000),
	READ_CNT	BIGINT,
	ENROLL_DT	TIMESTAMP
);

ALTER TABLE BOARD1 AUTO_INCREMENT = 100000;