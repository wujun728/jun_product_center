-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目报告文号', '3', '1', 'reportnumber', 'system/reportnumber/index', 1, 0, 'C', '0', '0', 'system:reportnumber:list', '#', 'admin', sysdate(), '', null, '项目报告文号菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目报告文号查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:reportnumber:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目报告文号新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:reportnumber:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目报告文号修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:reportnumber:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目报告文号删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:reportnumber:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目报告文号导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:reportnumber:export',       '#', 'admin', sysdate(), '', null, '');