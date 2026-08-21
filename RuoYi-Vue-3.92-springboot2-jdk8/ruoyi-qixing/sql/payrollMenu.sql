-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工资审核发放', '3', '1', 'payroll', 'system/payroll/index', 1, 0, 'C', '0', '0', 'system:payroll:list', '#', 'admin', sysdate(), '', null, '工资审核发放菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工资审核发放查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:payroll:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工资审核发放新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:payroll:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工资审核发放修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:payroll:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工资审核发放删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:payroll:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工资审核发放导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:payroll:export',       '#', 'admin', sysdate(), '', null, '');