drop table if exists  work_model;
create table work_model ( id bigint(255) NOT NULL AUTO_INCREMENT, work_finished timestamp null, constraint work_model_pk primary key (id) );

drop table if exists  request_log;
create table request_log ( id bigint(255) NOT NULL AUTO_INCREMENT, request_data VARCHAR(255) null, constraint request_log_pk primary key (id) );
