-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('入职报道', '3', '1', 'reported', 'system/reported/index', 1, 0, 'C', '0', '0', 'system:reported:list', '#', 'admin', sysdate(), '', null, '入职报道菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('入职报道查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:reported:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('入职报道新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:reported:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('入职报道修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:reported:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('入职报道删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:reported:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('入职报道导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:reported:export',       '#', 'admin', sysdate(), '', null, '');