drop table if exists comment cascade;
drop table if exists reimbursement cascade;
drop table if exists department  cascade;
drop table if exists employee cascade;
drop table if exists status cascade;
drop table if exists event_type cascade;
drop table if exists user_role cascade;
drop table if exists grading_format;

create table status (
	status_id serial primary key,
	status_name varchar(64) not null,
	approver varchar (64) not null
);
	
create table event_type (
	type_id serial primary key,
	type_name varchar(64) not null,
	percent_covered numeric(3, 2) not null,
	check (percent_covered <= 1.00 and percent_covered >= 0.00)
);
	
create table user_role (
	role_id serial primary key,
	role_name varchar(64) not null
);
	
create table grading_format (
	format_id serial primary key,
	format_name varchar(64) not null,
	example varchar(64)
);
	
create table employee (
	emp_id serial primary key,
	first_name varchar(64) not null,
	last_name varchar(64) not null,
	username varchar(32) not null,
	passwd varchar(32) not null,
	role_id integer not null references user_role,
	funds numeric(6, 2) not null,
	supervisor_id integer references employee(emp_id)
);

create table department (
	dept_id serial primary key,
	dept_name varchar(64) not null,
	dept_head_id integer references employee(emp_id)
);

alter table employee add dept_id integer references department;

create table reimbursement(
	req_id serial primary key,
	emp_id integer references employee,
	event_date date,
	event_time time,
	location varchar(128) not null,
	description varchar(128) not null,
	cost numeric(8, 2) not null,
	grading_format_id integer not null references grading_format,
	event_type_id integer not null references event_type,
	status_id integer not null references status,
	submitted_at timestamp not null
);

create table comment(
	comment_id serial primary key,
	comment_text varchar(256) not null,
	req_id integer references reimbursement,
	approver_id integer references employee(emp_id),
	sent_at timestamp not null
);