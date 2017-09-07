package com.tx.app.action.home;

import com.tx.app.action.BaseController;
import com.tx.app.annotation.Security;
import com.tx.app.dao.home.User;
import com.tx.app.dao.home.Role;
import com.tx.app.dao.home.UserRole;
import com.tx.app.form.home.UserForm;
import com.tx.app.service.RoleService;
import com.tx.app.service.UserRoleService;
import com.tx.app.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 权限分配
 *
 * @author chenqingxia
 */
@Controller
@RequestMapping("/home/permission")
public class PermissionController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 权限分配首页 - 用户管理
     * @param model
     * @return
     */
    @Security(url = "/home/permission/user")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String index(Model model) {
        List<User> users = this.userService.getAll();
        List<Role> roles = this.roleService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "home/permission/permission-index";
    }

    /**
     * 修改帐号锁定状态页面
     * @param no
     * @param model
     * @return
     */
    @Security(url = "/home/permission/user/locked")
    @RequestMapping(value = "/user/{no}", method = RequestMethod.GET)
    public String edit(@PathVariable String no, Model model) {
        User user = this.userService.queryByNo(no);
        model.addAttribute("user",user);
        return "home/permission/user-edit";
    }

    /**
     * 修改帐号锁定
     * @param no
     * @param locked
     * @return
     */
    @Security(url = "/home/permission/user/locked")
    @RequestMapping(value = "/user/{no}/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editUser(@PathVariable String no, @RequestParam Integer locked, HttpServletRequest request) {
        User user = this.userService.queryByNo(no);
        user.setLocked(locked);
        try {
            this.userService.update(user);
            //this.saveOptLog(request, "lockUser", "锁定用户", no);
        } catch (DataAccessException dae) {
            //logger.error("Lock or unlock user failed");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 展示某个用户对应的角色和权限
     */
    @Security(url = "/home/permission/user")
    @RequestMapping(value = "/user/{no}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> show(@PathVariable String no) {
        return data2json(this.userService.queryRoleAndRightsByNo(no));
    }

    /**
     * 更改用户角色
     * 先删除该用户之前所有的角色
     * @param id
     * @param form
     * @return
     */
    @Security(url = "/home/permission/user/edit")
    @RequestMapping(value = "/user/{id}/{no}/editRole", method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable Integer id,
                         @PathVariable String no, UserForm form, HttpServletRequest request) {
        //logger.debug("UserForm param={}", form.toString());
        // 将用户角色全部清空
        this.userRoleService.remove(id);
        UserRole ur = new UserRole();
        if (CollectionUtils.isNotEmpty(form.getRoles())) {
            for (Integer roleId : form.getRoles()) {
                ur.setUserId(id);
                ur.setRoleId(roleId);
                try {
                    this.userRoleService.save(ur);
                } catch (DataAccessException dae) {
                    logger.error("save userRole failed", dae);
                }
            }
        }
        //this.saveOptLog(request, "editUserRole", "修改用户角色", no);
        return SUCCESS;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Security(url = "/home/permission/user/delete")
    @RequestMapping(value = "/user/{no}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable String no, @RequestParam("id") Integer id, HttpServletRequest request) {
        //logger.debug("Action.URL={},param={}", id);
        try {
            // 删除用户之前，先将其对应的角色关系删除
            this.userRoleService.remove(id);
            this.userService.remove(id);

            //this.saveOptLog(request, "deleteUser", "删除用户", no);
        } catch (DataAccessException dae) {
            //logger.error("Delete role failed", dae);
            return ERROR;
        }
        return SUCCESS;
    }
}
