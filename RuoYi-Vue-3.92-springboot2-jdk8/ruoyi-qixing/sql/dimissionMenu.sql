-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('离职', '3', '1', 'dimission', 'system/dimission/index', 1, 0, 'C', '0', '0', 'system:dimission:list', '#', 'admin', sysdate(), '', null, '离职菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('离职查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:dimission:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('离职新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:dimission:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('离职修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:dimission:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('离职删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:dimission:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('离职导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:dimission:export',       '#', 'admin', sysdate(), '', null, '');