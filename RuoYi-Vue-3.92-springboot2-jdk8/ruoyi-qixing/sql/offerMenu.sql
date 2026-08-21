-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Offer发放', '3', '1', 'offer', 'system/offer/index', 1, 0, 'C', '0', '0', 'system:offer:list', '#', 'admin', sysdate(), '', null, 'Offer发放菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Offer发放查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:offer:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Offer发放新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:offer:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Offer发放修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:offer:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Offer发放删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:offer:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('Offer发放导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:offer:export',       '#', 'admin', sysdate(), '', null, '');