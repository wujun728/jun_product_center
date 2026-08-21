-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目总结及评价', '3', '1', 'appraise', 'system/appraise/index', 1, 0, 'C', '0', '0', 'system:appraise:list', '#', 'admin', sysdate(), '', null, '项目总结及评价菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目总结及评价查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:appraise:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目总结及评价新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:appraise:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目总结及评价修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:appraise:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目总结及评价删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:appraise:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目总结及评价导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:appraise:export',       '#', 'admin', sysdate(), '', null, '');