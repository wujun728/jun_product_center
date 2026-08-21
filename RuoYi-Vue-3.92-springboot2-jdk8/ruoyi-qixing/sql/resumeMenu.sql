-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试候选人', '3', '1', 'resume', 'system/resume/index', 1, 0, 'C', '0', '0', 'system:resume:list', '#', 'admin', sysdate(), '', null, '面试候选人菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试候选人查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:resume:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试候选人新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:resume:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试候选人修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:resume:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试候选人删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:resume:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('面试候选人导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:resume:export',       '#', 'admin', sysdate(), '', null, '');