package com.rnzhiw.ibicycle.model.domain;

import java.util.Date;

/**
 * 下发指令记录
 *
 * @author fuenhui
 * @date 2018/11/22
 */
public class TSCommand {

    /**
     * 终端设备sn
     */
    private String deviceSn;

    /**
     * 命令类型
     *
     * 1.查询数据指令
     * 2.配置设备指令
     */
    private Short cmdType;

    /**
     * 指令的HEX16字符串
     */
    private String cmdHex;

    /**
     * 指令的下发时间
     */
    private Date cmdTime;

    /**
     * 下发指令的人
     */
    private Long senderId;

    /**
     * 设备云生成该命令的uuid作为该命令的唯一标识，
     * 用于提取命令状态和返回结果。
     * 指令发送失败时存放失败信息
     */
    private String onenetCmdUuid;

    /**
     * 设备返回的命令执行响应：用户自定义Json或二进制数据（小于64K）
     */
    private String resp;

    /**
     * 指令状态（可为null，命令发送成功但还没有查询/刷新命令状态）：
     * -1：发送失败；
     * 0：设备不在线;
     * 1：命令已创建;
     * 2：命令已发往设备;
     * 3：命令发往设备失败;
     * 4：设备正常响应;
     * 5：命令执行超时;
     * 6：设备响应消息过长
     */
    private Short status;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Short getCmdType() {
        return cmdType;
    }

    public void setCmdType(Short cmdType) {
        this.cmdType = cmdType;
    }

    public String getCmdHex() {
        return cmdHex;
    }

    public void setCmdHex(String cmdHex) {
        this.cmdHex = cmdHex;
    }

    public Date getCmdTime() {
        return cmdTime;
    }

    public void setCmdTime(Date cmdTime) {
        this.cmdTime = cmdTime;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getOnenetCmdUuid() {
        return onenetCmdUuid;
    }

    public void setOnenetCmdUuid(String onenetCmdUuid) {
        this.onenetCmdUuid = onenetCmdUuid;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
