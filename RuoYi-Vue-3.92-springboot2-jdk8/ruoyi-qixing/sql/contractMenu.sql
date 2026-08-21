-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务约定书', '3', '1', 'contract', 'system/contract/index', 1, 0, 'C', '0', '0', 'system:contract:list', '#', 'admin', sysdate(), '', null, '业务约定书菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务约定书查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:contract:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务约定书新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:contract:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务约定书修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:contract:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务约定书删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:contract:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('业务约定书导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:contract:export',       '#', 'admin', sysdate(), '', null, '');