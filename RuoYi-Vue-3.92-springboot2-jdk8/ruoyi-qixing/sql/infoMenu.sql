-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('政策法规', '3', '1', 'info', 'system/info/index', 1, 0, 'C', '0', '0', 'system:info:list', '#', 'admin', sysdate(), '', null, '政策法规菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('政策法规查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:info:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('政策法规新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:info:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('政策法规修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:info:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('政策法规删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:info:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('政策法规导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:info:export',       '#', 'admin', sysdate(), '', null, '');