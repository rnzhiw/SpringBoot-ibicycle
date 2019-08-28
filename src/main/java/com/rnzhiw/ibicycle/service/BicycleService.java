package com.rnzhiw.ibicycle.service;

import com.rnzhiw.ibicycle.form.BicycleModifyForm;
import com.rnzhiw.ibicycle.model.dto.BicycleDTO;
import com.rnzhiw.ibicycle.model.dto.LocationDTO;

import java.util.Date;
import java.util.List;

/**
 * 电动车业务接口
 *
 * @author hjb
 * @date 2018/11/28
 */
public interface BicycleService {

    /**
     * 返回当前登录用户所有可管理的电动车
     *
     * @param userId
     * @return
     */
    List<BicycleDTO> findAllByUserId(Long userId);

    /**
     * 根据id获取电动车
     *
     * @param id
     * @return
     */
    BicycleDTO findOne(Long id);

    /**
     * 根据车架号查询电动车
     *
     * @param vin
     * @return
     */
    BicycleDTO findByVin(String vin);

    /**
     * 根据电机号查询电动车
     *
     * @param motorNumber
     * @return
     */
    BicycleDTO findByMotorNumber(String motorNumber);

    /**
     * 根据牌照号查询电动车
     *
     * @param licenseNumber
     * @return
     */
    BicycleDTO findByLicenseNumber(String licenseNumber);

    /**
     * 根据电子标签号查询电动车
     *
     * @param rfid
     * @return
     */
    BicycleDTO findByRfid(String rfid);

    /**
     * 根据车主Id查询电动车
     *
     * @param ownerId
     * @return
     */
    List<BicycleDTO> findByOwnerId(Long ownerId);

    /**
     * 根据sn编号查询电动车
     *
     * @param deviceSn
     * @return
     */
    BicycleDTO findByDeviceSn(String deviceSn);

    /**
     * 根据安装点查询电动车
     *
     * @param id
     * @return
     */
    List<BicycleDTO> findByOrgId(Long id);

    /**
     * 添加电动车信息（基本方法）
     *
     * @param form
     * @return
     */
    BicycleDTO insert(BicycleModifyForm form);

    /**
     * 更改电动车信息(基本方法，不判断电动车是否与组织和用户绑定)
     *
     * @param form
     * @return
     */
    BicycleDTO update(BicycleModifyForm form);

    /**
     * 根据电动车id查询电动车最近的定位
     *
     * @param id
     * @return
     */
    LocationDTO getLastLocation(Long id);

    /**
     * 查询电动车的GPS轨迹
     *
     * @param id
     * @param start
     * @param stop
     * @return
     */
    List<LocationDTO> getTrack(Long id, Date start, Date stop);
}
