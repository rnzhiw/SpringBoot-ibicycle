package com.rnzhiw.ibicycle.service;

import java.util.Date;

/**
 * 数据业务接口
 *
 * @author fuenhui
 * @date 2019/1/6
 */
public interface DataService {

    /**
     * 插入电动车的位置信息
     *
     * @param deviceSn
     * @param gpsLng
     * @param gpsLat
     */
    boolean insertLocation(String deviceSn, Double gpsLng, Double gpsLat, Date receiveTime);
}
