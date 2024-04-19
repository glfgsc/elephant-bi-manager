create table ds_elephant_bi.workbook (
 id int primary key auto_increment,
 name varchar(2000) not null,
 creator varchar(200) not null,
 createTime varchar(200) not null,
 parentId int not null,
 shareAnalysis int not null,
 position text not null,
 size text not null,
 updateTime varchar(200),
 type int not null,
 fileType int,
 path text
)


create table ds_elephant_bi.sql_table (
	id int primary key auto_increment,
	name varchar(2000) not null,
	creator varchar(200) not null,
	createTime varchar(200) not null,
	comment text,
	shareAnalysis int not null,
	dataOriginId int not null,
	updateTime varchar(200),
	workbookId int not null,
	sqlText text not null,
	params json
)

create table ds_elephant_bi.excel_table (
	id int primary key auto_increment,
	name varchar(2000) not null,
	creator varchar(200) not null,
	createTime varchar(200) not null,
	comment text,
	shareAnalysis int not null,
	updateTime varchar(200),
	workbookId int not null,
	data json,
	fields json,
	sheetIndex int not null
)
ALTER TABLE ds_elephant_bi.sql_table 
ADD CONSTRAINT fk_sql_workbook
FOREIGN KEY (workbookId)
REFERENCES ds_elephant_bi.workbook(id);

ALTER TABLE ds_elephant_bi.sql_table 
ADD CONSTRAINT fk_sql_dataorigin
FOREIGN KEY (dataoriginId)
REFERENCES ds_elephant_bi.data_origin(id);

ALTER TABLE ds_elephant_bi.excel_table 
ADD CONSTRAINT fk_excel_workbook
FOREIGN KEY (workbookId)
REFERENCES ds_elephant_bi.workbook(id);

