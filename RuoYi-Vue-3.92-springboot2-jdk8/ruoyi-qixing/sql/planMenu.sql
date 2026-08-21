-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目计划', '3', '1', 'plan', 'system/plan/index', 1, 0, 'C', '0', '0', 'system:plan:list', '#', 'admin', sysdate(), '', null, '项目计划菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目计划查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目计划新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目计划修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目计划删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目计划导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:plan:export',       '#', 'admin', sysdate(), '', null, '');