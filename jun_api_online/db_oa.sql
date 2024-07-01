alter table oa_note drop create_time;
alter table oa_note add create_time datetime default CURRENT_TIMESTAMP;

 alter table oa_note drop update_time;
alter table oa_note add update_time datetime default CURRENT_TIMESTAMP;


alter table oa_note drop start_time;
alter table oa_note add start_time datetime default CURRENT_TIMESTAMP;

 alter table oa_note drop end_time;
alter table oa_note add end_time datetime default CURRENT_TIMESTAMP;