-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目成员与结算', '3', '1', 'member', 'system/member/index', 1, 0, 'C', '0', '0', 'system:member:list', '#', 'admin', sysdate(), '', null, '项目成员与结算菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目成员与结算查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:member:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目成员与结算新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:member:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目成员与结算修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:member:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目成员与结算删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:member:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('项目成员与结算导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:member:export',       '#', 'admin', sysdate(), '', null, '');