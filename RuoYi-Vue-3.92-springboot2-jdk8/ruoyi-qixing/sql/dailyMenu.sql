-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报周报', '3', '1', 'daily', 'system/daily/index', 1, 0, 'C', '0', '0', 'system:daily:list', '#', 'admin', sysdate(), '', null, '项目日报周报菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报周报查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:daily:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报周报新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:daily:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报周报修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:daily:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报周报删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:daily:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目日报周报导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:daily:export',       '#', 'admin', sysdate(), '', null, '');