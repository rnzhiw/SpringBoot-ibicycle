package com.rnzhiw.ibicycle.model.domain;

import java.util.Date;

/**
 * 短信语音发送记录
 *
 * @author fuenhui
 * @date 2018/11/10
 */
public class TSVSMSData {

    /**
     * 发送流水号：短信接口为bizid，语音接口为callid
     */
    private String vsmsId;

    /**
     * 接收人手机号
     */
    private String vsmsPhone;

    /**
     * 短信、语音发送内容参数，json格式
     */
    private String content;

    /**
     * 发送状态：-1：发送请求失败；0：未确认终端是否成功接收；1：等待回执；2：接收失败；3：接收成功
     */
    private Short status;

    /**
     * 消息类型：1：短信；2：语音
     */
    private Short type;

    /**
     * 指令发送时间
     */
    private Date sendTime;

    /**
     * 错误码：
     * 1.若发送请求失败则显示请求失败原因。
     * 2.若发送请求成功但还未确认是否被终端接收到则为null。
     * 3.若发送请求成功且确认被终端收到则显示为OK。
     * 4.若发送请求成功但未被终端收到则显示失败原因。
     */
    private String errCode;

    /**
     * 发送的次数，最多为3，超过3次之后不再重发
     */
    private Short times;

    public String getVsmsId() {
        return vsmsId;
    }

    public void setVsmsId(String vsmsId) {
        this.vsmsId = vsmsId;
    }

    public String getVsmsPhone() {
        return vsmsPhone;
    }

    public void setVsmsPhone(String vsmsPhone) {
        this.vsmsPhone = vsmsPhone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public Short getTimes() {
        return times;
    }

    public void setTimes(Short times) {
        this.times = times;
    }
}
