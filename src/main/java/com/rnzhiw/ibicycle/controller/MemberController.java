package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.common.Constants;
import com.rnzhiw.ibicycle.common.PageInfo;
import com.rnzhiw.ibicycle.form.MemberForm;
import com.rnzhiw.ibicycle.model.dto.MemberDTO;
import com.rnzhiw.ibicycle.model.form.SessionInfo;
import com.rnzhiw.ibicycle.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.groups.Default;

/**
 * @author ruanzhiwei
 * @date 2019/8/23
 */

@Controller
@RequestMapping("/system/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping
    public String index() {
        return "/member";
    }

    /**
     * 列出所有的用户并分页
     *
     * @param sessionInfo 会话
     * @param pageSize 一个的记录数
     * @param pageNum 页数
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public AjaxResult list(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo,
                           Integer pageSize,Integer pageNum) {
        Long userId = sessionInfo.getUserId();
        PageInfo<MemberDTO> pageInfo;
        pageInfo = memberService.findAllMember(pageSize, pageNum, userId);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 查询用户
     *
     * @param pageSize
     * @param pageNum
     * @param orgId
     * @param keyword
     * @param sessionInfo
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public AjaxResult search(Integer pageSize, Integer pageNum, Long orgId, String keyword,
                             @SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {
        Long userId = sessionInfo.getUserId();
        PageInfo<MemberDTO> members = memberService.searchMembers(pageSize, pageNum, userId, keyword, orgId);
        return AjaxResult.success(members);
    }

    /**
     * 删除用户
     *
     * @param id 前端传来的id
     * @param sessionInfo 会话
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(Long id,@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {
        Long userId = sessionInfo.getUserId();
        boolean result = memberService.deleteMember(id, userId);
        if(result) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 新增用户信息
     *
     * @param sessionInfo 会话
     * @param br 参数验证
     * @param form 前端传来的表单
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(@Validated(Default.class) MemberForm form, BindingResult br,
                           @SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {

        if (br.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : br.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + ";");
            }
            return AjaxResult.error(sb.toString());
        }

        MemberDTO memberDTO = memberService.save(form, sessionInfo.getUserId());

        if (memberDTO == null) {
            return AjaxResult.error("添加失败");
        } else {
            return AjaxResult.success(memberDTO);
        }
    }

    /**
     * 编辑成员
     *
     * @param form
     * @param br
     * @param sessionInfo
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@Validated(Default.class) MemberForm form, BindingResult br,
                             @SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo) {

        if (br.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            sb.append("参数错误:");
            for (ObjectError allError : br.getAllErrors()) {
                sb.append(allError.getDefaultMessage() + ";");
            }
            return AjaxResult.error(sb.toString());
        }

        MemberDTO memberDTO = memberService.update(form, sessionInfo.getUserId());

        if (memberDTO == null) {
            return AjaxResult.error("编辑失败");
        } else {
            return AjaxResult.success(memberDTO);
        }
    }

    /**
     * 根据前端传来的id获取用户信息
     *
     * @param sessionInfo 会话
     * @param id 前端传来的id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public AjaxResult get(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo,
                          Long id) {
        Long userId = sessionInfo.getUserId();
        MemberDTO memberDTO = memberService.get(id, userId);
        if(memberDTO == null) {
            return AjaxResult.error("用户信息获取失败");
        } else {
            return AjaxResult.success(memberDTO);
        }
    }

}
