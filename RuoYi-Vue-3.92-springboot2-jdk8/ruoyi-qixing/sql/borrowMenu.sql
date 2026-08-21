-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目借阅', '3', '1', 'borrow', 'system/borrow/index', 1, 0, 'C', '0', '0', 'system:borrow:list', '#', 'admin', sysdate(), '', null, '项目借阅菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目借阅查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:borrow:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目借阅新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:borrow:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目借阅修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:borrow:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目借阅删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:borrow:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目借阅导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:borrow:export',       '#', 'admin', sysdate(), '', null, '');