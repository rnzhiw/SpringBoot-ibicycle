package com.rnzhiw.ibicycle.service;

import com.rnzhiw.ibicycle.common.PageInfo;
import com.rnzhiw.ibicycle.form.MemberForm;
import com.rnzhiw.ibicycle.model.dto.MemberDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface MemberService {

    /**
     * 用户登录
     *
     * @param mobile 用户登录手机号
     * @param password 用户登录密码
     * @param session 用户会话
     */
    public String login(String mobile, String password, HttpSession session);

    /**
     * 查找符合查询条件的人员
     *
     * @param pageSize 每页显示的条数
     * @param pageNum 页码
     * @param userId 当前登录用户id
     * @param keyword 关键字
     * @param orgId 需要查询的人员所在组织id
     * @return
     */
    PageInfo<MemberDTO> searchMembers(Integer pageSize, Integer pageNum, Long userId, String keyword, Long orgId);

    /**
     * 根据id获取人员
     *
     * @param userId 用户id
     * @return
     */
    public MemberDTO findOne(Long userId);

    /**
     * 查询出所有的用户并分页
     *
     * @param pageSize 一页有多少纪录
     * @param pageNum 页数
     * @param userId 用户Id
     * @return
     */
    public PageInfo<MemberDTO> findAllMember(Integer pageSize, Integer pageNum, Long userId);

    /**
     * 删除用户
     *
     * @param id 前端传来的id
     * @param userId 用户id
     * @return
     */
    public boolean deleteMember(Long id, Long userId);

    /**
     * 新增用户信息
     *
     * @param form 前端传来的表单
     * @param userId 用户id
     * @return
     */
    public MemberDTO save(MemberForm form, Long userId);

    /**
     * 修改用户信息
     *
     * @param form 前端传来的表单
     * @param userId 用户id
     * @return
     */
    public MemberDTO update(MemberForm form, Long userId);

    /**
     * 根据前端传来的id获取用户的个人信息
     *
     * @param id 前端传来的id
     * @param userId 用户id
     * @return
     */
    public MemberDTO get(Long id, Long userId);
}
