insert into event_type(type_id, type_name, percent_covered) values (default,'Certification', 1.00);
insert into event_type(type_id, type_name, percent_covered) values (default,'Technical Training',0.90);
insert into event_type(type_id, type_name, percent_covered) values (default,'University Course',0.80);
insert into event_type(type_id, type_name, percent_covered) values (default,'Certification Preparation Course', 0.75);
insert into event_type(type_id, type_name, percent_covered) values (default,'Seminar',0.60);
insert into event_type(type_id, type_name, percent_covered) values (default,'Other',0.30);

insert into status(status_id, status_name, approver) values (default,'Pending Approval' ,'Supervisor');
insert into status(status_id, status_name, approver) values (default,'Pending Approval' ,'Department Head');
insert into status(status_id, status_name, approver) values (default,'Pending Approval' ,'Benefits Coordinator');
insert into status(status_id, status_name, approver) values (default,'Approved' ,'Benefits Coordinator');
insert into status(status_id, status_name, approver) values (default,'Rejected' ,'Supervisor');
insert into status(status_id, status_name, approver) values (default,'Rejected' ,'Department Head');
insert into status(status_id, status_name, approver) values (default,'Rejected' ,'Benefits Coordinator');

insert into user_role(role_id, role_name) values (default, 'Employee');
insert into user_role(role_id, role_name) values (default, 'Supervisor');
insert into user_role(role_id, role_name) values (default, 'Department Head');
insert into user_role(role_id, role_name) values (default, 'Benefits Coordinator');

insert into grading_format(format_id, format_name, example) values (default, 'Letter Grade', 'A, B, C, D, F');
insert into grading_format(format_id, format_name, example) values (default, 'Pass/No Pass', 'P, NP');
insert into grading_format(format_id, format_name, example) values (default, 'Percentage', '90%');

insert into department(dept_id, dept_name, dept_head_id) values (default, 'Design', null);
insert into department(dept_id, dept_name, dept_head_id) values (default, 'Engineering', null);
insert into department(dept_id, dept_name, dept_head_id) values (default, 'QA', null);
insert into department(dept_id, dept_name, dept_head_id) values (default, 'HR', null);
insert into department(dept_id, dept_name, dept_head_id) values (default, 'General', null);

insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Mac', 'Tubbles', 'mtubbles0', 'pass', 3, 792.19, null, 1);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Anderson', 'Grayshan', 'agrayshan1', 'pass', 3, 750.63, null, 2);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Crissy', 'Iredell', 'ciredell2', 'pass', 3, 1000.00, null, 3);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Tasia', 'Batey', 'tbatey3', 'pass', 4, 881.18, null, 4);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Heinrick', 'Jersch', 'hjersch4', 'pass', 2, 1000.00, 1, 1);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Risa', 'Gilfoy', 'rgilfoy5', 'pass', 2, 668.55, 2, 2);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Brocky', 'Melling', 'bmelling6', 'pass', 2, 329.84, 3, 3);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Codi', 'Spenclay', 'cspenclay7', 'pass', 1, 1000.00, 4, 1);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Ketty', 'Casterou', 'kcasterou8', 'pass', 1, 1000.00, 5, 2);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Sibelle', 'Goldson', 'sgoldson9', 'pass', 1, 750.55, 6, 3);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'Cornelius', 'Smith', 'csmith26', 'pass', 1, 750.55, 4, 4);
insert into employee (emp_id, first_name, last_name, username, passwd, role_id, funds, supervisor_id, dept_id) values (default, 'General', 'Admin', 'admin123', 'admin', 3, 0.00, null, 5);

insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 5, '2022-09-17', '18:30', 'University of Pannonia', 'Profit-focused 6th generation internet solution', 301.57, 2, 5, 3, '2021-12-28 16:01:01');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 6, '2022-03-03', '19:30', 'Balti State University "Alecu Russo"', 'Integrated mission-critical database', 78.59, 2, 3, 3, '2021-11-03 04:58:05');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 7, '2022-09-22', '17:00', 'Ural State Academy of Law', 'Multi-layered dedicated circuit', 411.12, 1, 2, 3, '2021-12-21 13:19:22');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 8, '2022-05-01', '19:00', 'Duquesne University', 'Compatible upward-trending installation', 105.52, 3, 4, 2, '2021-12-11 20:55:04');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 9, '2022-01-17', '10:30', 'Universidad de Castilla La Mancha', 'Versatile zero administration project', 337.05, 3, 4, 1, '2021-12-09 08:49:49');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 10, '2022-08-24', '8:00', 'Eiilm University', 'User-centric system-worthy productivity', 307.39, 1, 5, 2, '2021-12-20 07:21:05');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 11, '2022-04-09', '16:15', 'College of St. Mary', 'User-friendly systemic parallelism', 82.94, 1, 4, 1, '2021-11-22 11:24:21');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 8, '2022-05-25', '12:30', 'Universität Graz', 'Cloned web-enabled task-force', 427.6, 3, 4, 4, '2021-12-22 11:18:35');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 9, '2022-08-25', '12:00', 'Université Sorbonne-Nouvelle (Paris III)', 'Grass-roots zero administration website', 64.02, 3, 4, 5, '2021-12-14 17:49:01');
insert into reimbursement (req_id, emp_id, event_date, event_time, location, description, cost, grading_format_id, event_type_id, status_id, submitted_at) values (default, 10, '2022-07-28', '10:15', 'Université du Centre, Sousse', 'Front-line non-volatile service-desk', 125.68, 3, 5, 2, '2021-11-27 12:24:52');

insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Innovative value-added moratorium', 1, 7, '2022-01-07 19:35:55');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Multi-layered high-level open architecture', 2, 2, '2022-01-05 02:59:39');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Re-contextualized optimizing info-mediaries', 3, 2, '2022-01-03 20:38:11');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Networked object-oriented protocol', 4, 5, '2021-12-24 16:57:23');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Enhanced zero administration leverage', 5, 6, '2021-12-22 09:18:39');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Seamless mission-critical policy', 6, 2, '2022-01-08 14:10:18');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Phased fresh-thinking moratorium', 7, 4, '2021-12-31 15:15:59');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Implemented encompassing function', 8, 2, '2021-12-30 13:34:17');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Organic foreground paradigm', 9, 6, '2021-12-28 00:09:21');
insert into comment (comment_id, comment_text, req_id, approver_id, sent_at) values (default, 'Secured systemic structure', 10, 3, '2021-12-30 19:22:05');
