-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品申领申购', '3', '1', 'count2', 'system/count2/index', 1, 0, 'C', '0', '0', 'system:count2:list', '#', 'admin', sysdate(), '', null, '办公用品申领申购菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品申领申购查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:count2:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品申领申购新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:count2:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品申领申购修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:count2:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品申领申购删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:count2:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('办公用品申领申购导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:count2:export',       '#', 'admin', sysdate(), '', null, '');