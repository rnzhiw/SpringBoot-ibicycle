package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.form.ResourceForm;
import com.rnzhiw.ibicycle.model.dto.ResourceDTO;
import com.rnzhiw.ibicycle.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.groups.Default;

/**
 * 资源控制器
 *
 * @author ruanzhiwei
 * @date 2019/7/8
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public String index() {
        return "resource";
    }

    /**
     * 列出所有资源，只有超级管理员可以管理资源
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        return AjaxResult.success(resourceService.findAll(ResourceService.WEIGHT_SORT));
    }

    /**
     * 添加资源
     *
     * @param form
     * @param br
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(@Validated(Default.class) ResourceForm form, BindingResult br) {

        if (br.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : br.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + ";");
            }
            return AjaxResult.error(sb.toString());
        }

        ResourceDTO resourceDTO = resourceService.save(form);
        if (resourceDTO == null) {
            return AjaxResult.error("添加失败");
        } else {
            return AjaxResult.success(resourceDTO);
        }
    }

    /**
     * 编辑资源
     *
     * @param form
     * @param br
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@Validated(Default.class) ResourceForm form, BindingResult br) {

        if (br.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : br.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + ";");
            }
            return AjaxResult.error(sb.toString());
        }

        ResourceDTO resourceDTO = resourceService.update(form);
        if (resourceDTO == null) {
            return AjaxResult.error("编辑失败");
        } else {
            return AjaxResult.success(resourceDTO);
        }
    }

    /**
     * 根据id获取资源
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public AjaxResult get(Long id) {
        ResourceDTO resourceDTO = resourceService.findOne(id);
        if (resourceDTO == null) {
            return AjaxResult.error("无法获取对应资源");
        } else {
            return AjaxResult.success(resourceDTO);
        }
    }

    /**
     * 根据id删除资源
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        boolean success = resourceService.delete(id);
        if (success) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }
}
