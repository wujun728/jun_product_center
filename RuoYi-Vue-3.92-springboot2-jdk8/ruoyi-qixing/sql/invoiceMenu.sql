-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目开票', '3', '1', 'invoice', 'system/invoice/index', 1, 0, 'C', '0', '0', 'system:invoice:list', '#', 'admin', sysdate(), '', null, '项目开票菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目开票查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:invoice:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目开票新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:invoice:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目开票修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:invoice:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目开票删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:invoice:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目开票导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:invoice:export',       '#', 'admin', sysdate(), '', null, '');