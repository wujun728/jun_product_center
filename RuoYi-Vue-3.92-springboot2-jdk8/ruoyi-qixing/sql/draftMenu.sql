-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目底稿', '3', '1', 'draft', 'system/draft/index', 1, 0, 'C', '0', '0', 'system:draft:list', '#', 'admin', sysdate(), '', null, '项目底稿菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目底稿查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:draft:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目底稿新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:draft:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目底稿修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:draft:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目底稿删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:draft:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目底稿导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:draft:export',       '#', 'admin', sysdate(), '', null, '');