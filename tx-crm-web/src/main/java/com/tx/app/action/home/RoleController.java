package com.tx.app.action.home;

import com.tx.app.annotation.Security;
import com.tx.app.dao.home.Role;
import com.tx.app.dao.home.RoleRights;
import com.tx.app.dao.home.UserRole;
import com.tx.app.form.home.RoleForm;
import com.tx.app.service.*;
import com.tx.app.action.BaseController;
import com.tx.app.util.PaginationSupport;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 角色管理
 *
 * @author chenqingxia
 */
@Controller
@RequestMapping("/home/permission")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRightsService roleRightsService;

    @Autowired
    private RightsService rightsService;

    @Autowired
    private UserRoleService userRoleService;

    /** ajax url */
    @Security(url = "/home/permission/role")
    @RequestMapping(value = "/role/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(RoleForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        //this.logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());
        PaginationSupport<RoleDto> ps = this.roleService.search(form);
        return dataTableJson(ps.getTotalCount(), ps.getItems());
    }

    /**
     * 查询某个角色对应的所有权限 rights
     * 按照父子目录搜索
     * 包含所有的权限信息，包括某个角色其对应的权限信息
     * ajax调用
     */
    @Security(url = "/home/permission/role")
    @RequestMapping(value = "/role/rights/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchRights( @RequestParam("id") Integer id) {
        List<RightsDto> rights = this.rightsService.getAllByParentId(-1);
        List<RoleRights> roleRightses = this.roleRightsService.queryByRoleId(id);
        for (RoleRights rr : roleRightses) {
            for (RightsDto right : rights) {
                if (rr.getRightsId().compareTo(right.getId()) == 0) {
                    right.setChecked("checked");
                }
            }
        }
        //logger.debug(rights.toString());
        return data2json(rights);
    }

    /**
     * 查询出某个角色对应的权限关系
     * @param roleId
     * @return
     */
    @Security(url = "/home/permission/user")
    @RequestMapping(value = "/role/rights/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> searchRightsByRoleId( @RequestParam("roleId") Integer roleId) {
        List<RoleRights> roleRightses = this.roleRightsService.queryByRoleId(roleId);
        //logger.debug(roleRightses.toString());
        return data2json(roleRightses);
    }

    /**
     * 角色详细
     * @param id
     * @param resultState
     * @param model
     * @return
     */
    @Security(url = "/home/permission/role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id,
                       @RequestParam(required = false) String resultState, Model model) {
        Role role = this.roleService.get(id);
        //logger.debug(role.toString());
        model.addAttribute("role", role);
        model.addAttribute("resultState", resultState);
        return "home/permission/role-show";
    }

    /**
     * 角色编辑页面
     * @param id
     * @param model
     * @return
     */
    @Security(url = "/home/permission/role/edit")
    @RequestMapping(value = "/role/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        Role role = this.roleService.get(id);
        //logger.debug(role.toString());
        model.addAttribute("role", role);
        return "home/permission/role-edit";
    }

    /**
     * 角色修改，同时修改权限
     * @param id
     * @param form
     * @return
     */
    @Security(url = "/home/permission/role/edit")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Integer id, RoleForm form,
                         RedirectAttributes redirectAttrs, HttpServletRequest request) {
        //logger.debug(form.toString());
        try {
            this.roleService.update(form);
            redirectAttrs.addAttribute("resultState", SUCCESS);
            this.saveRights(id, form.getRights(), redirectAttrs);

            //this.saveOptLog(request, "editRole", "修改角色", id.toString());
        } catch (DataAccessException dae) {
            //logger.error("Update rights failed", dae);
            redirectAttrs.addAttribute("resultState", ERROR);
        }
        return "redirect:/home/permission/role/{id}";
    }

    /**
     * 新增角色窗口，同时分配权限
     * @param resultState
     * @param model
     * @return
     */
    @Security(url = "/home/permission/role/new")
    @RequestMapping(value = "/role/new", method = RequestMethod.GET)
    public String add(@RequestParam(required = false) String resultState, Model model) {
        model.addAttribute("resultState", resultState);
        return "home/permission/role-new";
    }

    /**
     * 新增 角色，同时给角色分配权限
     * @param form
     * @param redirectAttrs
     * @return
     */
    @Security(url = "/home/permission/role/new")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public String create(RoleForm form, RedirectAttributes redirectAttrs, HttpServletRequest request) {
        //logger.debug(form.toString());
        try {
            int roleId = this.roleService.save(form);
            redirectAttrs.addAttribute("resultState", SUCCESS);

            this.saveRights(roleId, form.getRights(), redirectAttrs);

            //this.saveOptLog(request, "addRole", "新增角色", String.valueOf(roleId));
        } catch (DataAccessException dae) {
            //logger.error("Add role failed", dae);
            redirectAttrs.addAttribute("resultState", ERROR);
        }
        return "redirect:/home/permission/role/new";
    }

    /**
     * 新增角色之前，先判断角色名称是否存在
     */
    @Security(url = "/home/permission/role/new")
    @RequestMapping(value = "/role/search", method = RequestMethod.GET)
    @ResponseBody
    public String searchByCardNo(@RequestParam("name") String name) {
        Role role = this.roleService.queryByName(name);
        if (role != null) {
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 删除角色
     * @param ids 注意RequestParam("ids[]")
     * @return {@link #SUCCESS} OR {@link  #ERROR}
     */
    @Security(url = "/home/permission/role/delete")
    @RequestMapping(value = "/role/delete", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("ids[]") List<Integer> ids, HttpServletRequest request) {
        //logger.debug("Action.URL={},param={}", ids);
        try {
            // 在删除角色之前要先去判断，其是否被分配给用户
            // 定义一个返回提示
            StringBuilder sb = new StringBuilder("角色：");
            boolean isConfirm = true;
            for (Integer id : ids) {
                Role role = this.roleService.get(id);
                List<UserRole> userRole = this.userRoleService.queryByRoleId(id);
                if (CollectionUtils.isNotEmpty(userRole)) {
                    isConfirm = false;
                    sb.append(role.getName()).append("—已分配给用户，");
                }
            }
            if (!isConfirm) {
                sb.append("请确认后在删除！！！");
                return sb.toString();
            }
            this.roleRightsService.remove(ids);
            this.roleService.remove(ids);

            //this.saveOptLog(request, "deleteRole", "删除角色", StringUtils.substringBeforeLast(ids.toString(), "]").substring(1));
        } catch (DataAccessException dae) {
            //logger.error("Delete role failed", dae);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 为角色分配权限，先删除该角色之前所有的权限
     * @param roleId
     * @param rights
     * @param redirectAttrs
     */
    private void saveRights(Integer roleId, List<Integer> rights, RedirectAttributes redirectAttrs) {
        List<Integer> roleIds = new ArrayList(Arrays.asList(roleId));
        this.roleRightsService.remove(roleIds);
        RoleRights rr = new RoleRights();
        if (CollectionUtils.isNotEmpty(rights)) {
            for (Integer rightsId : rights) {
                rr.setRoleId(roleId);
                rr.setRightsId(rightsId);
                try {
                    this.roleRightsService.save(rr);
                } catch (DataAccessException dae) {
                    //logger.error("save roleRights failed", dae);
                    redirectAttrs.addAttribute("resultState", FAIL);
                }
            }
        }
    }
}
