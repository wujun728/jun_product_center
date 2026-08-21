-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目复核', '3', '1', 'recheck', 'system/recheck/index', 1, 0, 'C', '0', '0', 'system:recheck:list', '#', 'admin', sysdate(), '', null, '项目复核菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目复核查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:recheck:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目复核新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:recheck:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目复核修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:recheck:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目复核删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:recheck:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目复核导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:recheck:export',       '#', 'admin', sysdate(), '', null, '');