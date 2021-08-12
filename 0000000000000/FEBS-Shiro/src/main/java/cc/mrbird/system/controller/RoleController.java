package cc.mrbird.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtil;
import cc.mrbird.system.domain.Role;
import cc.mrbird.system.service.RoleService;

@Controller
public class RoleController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    @Log("获取角色信息")
    @RequestMapping("role")
    @RequiresPermissions("role:list")
    public String index() {
        return "system/role/role";
    }

    @RequestMapping("role/list")
    @RequiresPermissions("role:list")
    @ResponseBody
    public Map<String, Object> roleList(QueryRequest request, Role role) {
        return super.selectByPageNumSize(request, () -> this.roleService.findAllRole(role));
    }

    @RequestMapping("role/excel")
    @ResponseBody
    public ResponseBo roleExcel(Role role) {
        try {
            List<Role> list = this.roleService.findAllRole(role);
            return FileUtil.createExcelByPOIKit("角色表", list, Role.class);
        } catch (Exception e) {
            log.error("导出角色信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("role/csv")
    @ResponseBody
    public ResponseBo roleCsv(Role role) {
        try {
            List<Role> list = this.roleService.findAllRole(role);
            return FileUtil.createCsv("角色表", list, Role.class);
        } catch (Exception e) {
            log.error("导出角色信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("role/getRole")
    @ResponseBody
    public ResponseBo getRole(Long roleId) {
        try {
            Role role = this.roleService.findRoleWithMenus(roleId);
            return ResponseBo.ok(role);
        } catch (Exception e) {
            log.error("获取角色信息失败", e);
            return ResponseBo.error("获取角色信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("role/checkRoleName")
    @ResponseBody
    public boolean checkRoleName(String roleName, String oldRoleName) {
        if (StringUtils.isNotBlank(oldRoleName) && roleName.equalsIgnoreCase(oldRoleName)) {
            return true;
        }
        Role result = this.roleService.findByName(roleName);
        return result == null;
    }

    @Log("新增角色")
    @RequiresPermissions("role:add")
    @RequestMapping("role/add")
    @ResponseBody
    public ResponseBo addRole(Role role, Long[] menuId) {
        try {
            this.roleService.addRole(role, menuId);
            return ResponseBo.ok("新增角色成功！");
        } catch (Exception e) {
            log.error("新增角色失败", e);
            return ResponseBo.error("新增角色失败，请联系网站管理员！");
        }
    }

    @Log("删除角色")
    @RequiresPermissions("role:delete")
    @RequestMapping("role/delete")
    @ResponseBody
    public ResponseBo deleteRoles(String ids) {
        try {
            this.roleService.deleteRoles(ids);
            return ResponseBo.ok("删除角色成功！");
        } catch (Exception e) {
            log.error("删除角色失败", e);
            return ResponseBo.error("删除角色失败，请联系网站管理员！");
        }
    }

    @Log("修改角色")
    @RequiresPermissions("role:update")
    @RequestMapping("role/update")
    @ResponseBody
    public ResponseBo updateRole(Role role, Long[] menuId) {
        try {
            this.roleService.updateRole(role, menuId);
            return ResponseBo.ok("修改角色成功！");
        } catch (Exception e) {
            log.error("修改角色失败", e);
            return ResponseBo.error("修改角色失败，请联系网站管理员！");
        }
    }
}
