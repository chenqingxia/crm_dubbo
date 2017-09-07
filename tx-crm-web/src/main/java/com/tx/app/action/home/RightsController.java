package com.tx.app.action.home;

import com.tx.app.action.BaseController;
import com.tx.app.annotation.Security;
import com.tx.app.dao.home.Rights;
import com.tx.app.dao.home.RoleRights;
import com.tx.app.form.home.RightsForm;
import com.tx.app.service.RightsDto;
import com.tx.app.service.RightsService;
import com.tx.app.service.RoleRightsService;
import com.tx.app.util.PaginationSupport;
import com.tx.app.util.RequestUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author chenqingxia
 */
@Controller
@RequestMapping("/home/permission")
public class RightsController extends BaseController {

    @Autowired
    private RightsService rightsService;

    @Autowired
    private RoleRightsService roleRightsService;

    /** ajax url */
    @Security(url = "/home/permission/rights")
    @RequestMapping(value = "/rights/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(RightsForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        this.logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());
        PaginationSupport<RightsDto> ps = this.rightsService.search(form);
        System.out.println("RightsController.list="+ps.getItems());
        return dataTableJson(ps.getTotalCount(), ps.getItems());
    }


    /**
     * 查询所有权限 rights
     * 按照父子目录搜索
     * ajax调用
     */

    @Security(url = "/home/permission/user")
    @RequestMapping(value = "/rights/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchRights() {
        List<RightsDto> rights = this.rightsService.getAllByParentId(-1);
        logger.debug(rights.toString());
        System.out.println("rights="+rights.toString());
        return data2json(rights);
    }

    /**
     * 权限详情
     * @param id
     * @param resultState
     * @param model
     * @return
     */
    @Security(url = "/home/permission/rights")
    @RequestMapping(value = "/rights/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id,
                       @RequestParam(required = false) String resultState, Model model) {
        Rights rights = this.rightsService.get(id);
        //logger.debug(rights.toString());
        model.addAttribute("rights", rights);
        model.addAttribute("resultState", resultState);
        return "home/permission/rights-show";
    }

    /**
     * 权限编辑
     * @param id
     * @param model
     * @return
     */
    @Security(url = "/home/permission/rights/edit")
    @RequestMapping(value = "/rights/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        Rights rights = this.rightsService.get(id);
        //logger.debug(rights.toString());
        model.addAttribute("rights", rights);
        return "home/permission/rights-edit";
    }

    /**
     * 权限修改
     * @param id
     * @param form
     * @return
     */
    @Security(url = "/home/permission/rights/edit")
    @RequestMapping(value = "/rights/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Integer id, RightsForm form,
                         RedirectAttributes redirectAttrs,  HttpServletRequest request) {
        //logger.debug(form.toString());
        try {
            this.rightsService.update(form);
            redirectAttrs.addAttribute("resultState", SUCCESS);

            // 保存操作记录
            //this.saveOptLog(request, "editRights", "修改权限", id.toString());
        } catch (DataAccessException dae) {
            //logger.error("Update rights failed", dae);
            redirectAttrs.addAttribute("resultState", ERROR);
        }
        return "redirect:/home/permission/rights/{id}";
    }


    /**
     * 新增权限窗口
     * @param resultState
     * @param model
     * @return
     */
    @Security(url = "/home/permission/rights/new")
    @RequestMapping(value = "/rights/new", method = RequestMethod.GET)
    public String add(@RequestParam(required = false) String resultState, Model model) {
        model.addAttribute("resultState", resultState);
        return "home/permission/rights-new";
    }

    /**
     * 新增 权限
     * @param form
     * @param redirectAttrs
     * @return
     */
    @Security(url = "/home/permission/rights/new")
    @RequestMapping(value = "/rights", method = RequestMethod.POST)
    public String create(RightsForm form, RedirectAttributes redirectAttrs, HttpServletRequest request) {
        //logger.debug(form.toString());
        try {
            int rightsId = this.rightsService.save(form);
            redirectAttrs.addAttribute("resultState", SUCCESS);
            //this.saveOptLog(request, "addRights", "新增权限", String.valueOf(rightsId));
        } catch (DataAccessException dae) {
            //logger.error("Add card failed", dae);
            redirectAttrs.addAttribute("resultState", ERROR);
        } catch(Exception e){
            e.printStackTrace();

        }
        return "redirect:/home/permission/rights/new";
    }

    /**
     * 新增角色之前，先判断角色名称是否存在
     */
    @Security(url = "/home/permission/rights/new")
    @RequestMapping(value = "/rights/search", method = RequestMethod.GET)
    @ResponseBody
    public String searchByCardNo(@RequestParam("name") String name) {
        Rights rights = this.rightsService.queryByName(name);
        if (rights != null) {
            return ERROR;
        }
        return SUCCESS;
    }


    /**
     * 删除权限
     * @param ids 注意RequestParam("ids[]")
     * @return {@link #SUCCESS} OR {@link  #ERROR}
     */
    @Security(url = "/home/permission/rights/delete")
    @RequestMapping(value = "/rights/delete", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("ids[]") List<Integer> ids, HttpServletRequest request) {
        //logger.debug("Action.URL={},param={}", ids);
        try {
            // 在删除权限之前要先去判断，其是否被分配给某些角色，或者是否有子权限
            // 定义一个返回提示
            StringBuilder sb = new StringBuilder("权限：");
            boolean isConfirm = true;
            for (Integer id : ids) {
                Rights rights = this.rightsService.get(id);
                List<RoleRights> roleRights = this.roleRightsService.queryByRightsId(id);
                if (CollectionUtils.isNotEmpty(roleRights)) {
                    isConfirm = false;
                    sb.append(rights.getName()).append("—被角色引用，");
                }

                List<RightsDto> sonRights= this.rightsService.getByParentId(id);
                if (CollectionUtils.isNotEmpty(sonRights)) {
                    isConfirm = false;
                    sb.append("且有子权限；");
                }
            }
            if (!isConfirm) {
                sb.append("请确认后在删除！！！");
                return sb.toString();
            }
            this.rightsService.remove(ids);
            //this.saveOptLog(request, "deleteRights", "删除权限" , StringUtils.substringBeforeLast(ids.toString(), "]").substring(1));
        } catch (DataAccessException dae) {
            //logger.error("Delete rights failed", dae);
            return ERROR;
        }
        return SUCCESS;
    }
}
