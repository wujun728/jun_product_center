WITH RECURSIVE submenus AS (
    -- 基础查询：获取指定ID的直接子菜单
    SELECT *
    FROM sys_menu
    WHERE menu_id =  1  
	
    UNION ALL
    
    -- 递归查询：获取所有后代菜单
    SELECT m.*
    FROM sys_menu m
    INNER JOIN submenus s ON m.parent_id = s.menu_id
)
-- 最终查询：返回所有子菜单
SELECT * FROM submenus;



DELETE FROM sys_menu
WHERE menu_id IN (
    WITH RECURSIVE all_menus AS (
        -- 基础查询：先获取指定ID的菜单本身
        SELECT menu_id
        FROM sys_menu
        WHERE menu_id = 98938034892813
        
        UNION ALL
        
        -- 递归查询：获取所有后代菜单的ID
        SELECT m.menu_id
        FROM sys_menu m
        INNER JOIN all_menus a ON m.parent_id = a.menu_id
    )
    SELECT menu_id FROM all_menus
);