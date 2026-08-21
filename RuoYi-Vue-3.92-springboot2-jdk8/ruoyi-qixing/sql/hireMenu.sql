-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('录用审批', '3', '1', 'hire', 'system/hire/index', 1, 0, 'C', '0', '0', 'system:hire:list', '#', 'admin', sysdate(), '', null, '录用审批菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('录用审批查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:hire:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('录用审批新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:hire:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('录用审批修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:hire:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('录用审批删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:hire:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('录用审批导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:hire:export',       '#', 'admin', sysdate(), '', null, '');