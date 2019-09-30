package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.common.Constants;
import com.rnzhiw.ibicycle.form.OrganizationForm;
import com.rnzhiw.ibicycle.model.dto.OrganizationDTO;
import com.rnzhiw.ibicycle.model.form.SessionInfo;
import com.rnzhiw.ibicycle.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;


/**
 * 组织控制器
 *
 * @author ruanzhiwei
 * @date 2019/7/8
 */
@Controller
@RequestMapping("/system/organization")
public class OrganizationController {

    @Autowired(required = false)
    OrganizationService organizationService;

    @RequestMapping
    public String index(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo,
                        HttpServletRequest request) {
        request.setAttribute("org", organizationService.findOne(sessionInfo.getOrgId()));
        return "organization";
    }

    @GetMapping("/get")
    @ResponseBody
    public AjaxResult get(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo,
                          Long id) {
        OrganizationDTO organizationDTO = organizationService.get(id, sessionInfo.getUserId());

        if (organizationDTO == null) {
            return AjaxResult.error("无法查看该组织");
        } else {
            return AjaxResult.success(organizationDTO);
        }
    }

    /**
     * 列出所有用户可管理的组织
     *
     * @param sessionInfo
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {
        return AjaxResult.success(organizationService.findAllByUserId(sessionInfo.getUserId()));
    }

    /**
     * 根据模糊查询条件列出所有用户可管理的组织
     *
     * @param sessionInfo
     * @param name
     * @return
     */
    @GetMapping("/listByName")
    @ResponseBody
    public AjaxResult listByName(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo,
                                 String name) {
        return AjaxResult.success(organizationService.findAllByUserIdAndNameLike(sessionInfo.getUserId(), name));
    }

    /**
     * 添加组织
     *
     * @param form
     * @param br
     * @param sessionInfo
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(@Validated(Default.class) OrganizationForm form, BindingResult br,
                           @SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {

        if (br.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : br.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + ";");
            }
            return AjaxResult.error(sb.toString());
        }

        OrganizationDTO organizationDTO = organizationService.save(form, sessionInfo.getUserId());

        if (organizationDTO == null) {
            return AjaxResult.error("添加失败");
        } else {
            return AjaxResult.success(organizationDTO);
        }
    }

    /**
     * 编辑组织
     *
     * @param form
     * @param br
     * @param sessionInfo
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@Validated(Default.class) OrganizationForm form, BindingResult br,
                             @SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {
        if (br.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : br.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + ";");
            }
            return AjaxResult.error(sb.toString());
        }

        OrganizationDTO organizationDTO = organizationService.update(form, sessionInfo.getUserId());

        if (organizationDTO == null) {
            return AjaxResult.error("编辑失败");
        } else {
            return AjaxResult.success(organizationDTO);
        }
    }

    /**
     * 删除组织
     *
     * @param sessionInfo
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo, Long id) {

        boolean success = organizationService.delete(id, sessionInfo.getUserId());
        if (success) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败，可能由于权限不足或该组织拥有下级组织!");
        }
    }
}
