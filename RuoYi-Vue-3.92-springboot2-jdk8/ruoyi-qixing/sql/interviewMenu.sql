-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试汇总', '3', '1', 'interview', 'system/interview/index', 1, 0, 'C', '0', '0', 'system:interview:list', '#', 'admin', sysdate(), '', null, '面试汇总菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试汇总查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:interview:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试汇总新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:interview:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试汇总修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:interview:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试汇总删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:interview:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试汇总导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:interview:export',       '#', 'admin', sysdate(), '', null, '');