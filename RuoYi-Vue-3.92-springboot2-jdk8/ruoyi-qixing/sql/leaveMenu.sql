-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工请假', '3', '1', 'leave', 'system/leave/index', 1, 0, 'C', '0', '0', 'system:leave:list', '#', 'admin', sysdate(), '', null, '员工请假菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工请假查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:leave:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工请假新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:leave:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工请假修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:leave:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工请假删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:leave:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工请假导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:leave:export',       '#', 'admin', sysdate(), '', null, '');