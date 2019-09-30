package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.common.Constants;
import com.rnzhiw.ibicycle.form.RoleForm;
import com.rnzhiw.ibicycle.model.form.SessionInfo;
import com.rnzhiw.ibicycle.service.ResourceService;
import com.rnzhiw.ibicycle.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

/**
 * 角色控制器
 *
 * @author ruanzhiwei
 * @date 2019/7/8
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {

    private RoleService roleService;
    private ResourceService resourceService;

    @Autowired
    public RoleController(RoleService roleService, ResourceService resourceService) {
        this.roleService = roleService;
        this.resourceService = resourceService;
    }

    @RequestMapping
    public String index() {
        return "role";
    }

    /**
     * 列出所有的角色
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        return AjaxResult.success(roleService.findAll(RoleService.ID_SORT));
    }

    /**
     * 保存角色
     *
     * @param roleForm
     * @param bindingResult
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(@Validated(Default.class) RoleForm roleForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : bindingResult.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + "; ");
            }
            return AjaxResult.error(sb.toString());
        } else {
            return roleService.save(roleForm);
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        roleService.delete(id);
        return AjaxResult.success();
    }

    @RequestMapping("/resource/list")
    @ResponseBody
    public AjaxResult getResource(@RequestParam(name = "id", required = false) Long id, @SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {

        return AjaxResult.success(resourceService.getResources(id));

    }

    /**
     * 保存角色资源访问权限
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    @RequestMapping("/resource/save")
    @ResponseBody
    public AjaxResult saveResource(Long roleId, Long[] resourceIds) {

//        System.out.println(JSON.toJSONString(resourceIds));
        roleService.saveResources(roleId, resourceIds);
        return AjaxResult.success();
    }
}
