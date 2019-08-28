package com.rnzhiw.ibicycle.service;

import com.rnzhiw.ibicycle.model.dto.AreaDTO;

import java.util.List;

/**
 * 区域服务接口
 *
 * @author  csy
 * @date 2018/11/24
 */
public interface AreaService {

    /**
     * 获取所有省份信息
     *
     * @return
     */
    List<AreaDTO> getProvinces();

    /**
     * 根据省份编号获取指定省份的城市信息
     *
     * @param provinceCode 省份编号
     * @return
     */
    List<AreaDTO> getCitiesByProvinceCode(String provinceCode);

    /**
     * 根据城市编号获取指定城市的县/区信息
     *
     * @param cityCode 城市编号
     * @return
     */
    List<AreaDTO> getDistrictsByCityCode(String cityCode);


    /**
     * 根据县、区编号获取指定县/区的街道信息
     *
     * @param districtCode 县、区编号
     * @return
     */
    List<AreaDTO> getTownsByDistrictCode(String districtCode);

}
