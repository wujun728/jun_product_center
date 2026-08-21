-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转正', '3', '1', 'member', 'system/member/index', 1, 0, 'C', '0', '0', 'system:member:list', '#', 'admin', sysdate(), '', null, '转正菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转正查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:member:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转正新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:member:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转正修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:member:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转正删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:member:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('转正导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:member:export',       '#', 'admin', sysdate(), '', null, '');