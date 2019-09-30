package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 行政区域控制器
 *
 * @author ruanzhiwei
 * @date 2019/7/8
 */
@Controller
@RequestMapping("/system/area")
public class AreaController {

    private AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService){
        this.areaService = areaService;
    }

    /**
     * 获取所有省份信息
     *
     * @return
     */
    @GetMapping("/province")
    @ResponseBody
    public AjaxResult getProvinces(){
        return AjaxResult.success(areaService.getProvinces());
    }

    /**
     * 根据省份编号获取指定省份的城市信息
     *
     * @param provinceCode
     * @return
     */
    @GetMapping("/city")
    @ResponseBody
    public AjaxResult getCitiesByProvinceCode(String provinceCode){
        return AjaxResult.success(areaService.getCitiesByProvinceCode(provinceCode));
    }

    /**
     * 根据城市编号获取指定城市的县/区信息
     *
     * @param cityCode
     * @return
     */
    @GetMapping("/district")
    @ResponseBody
    public AjaxResult getDistrictsByCityCode(String cityCode){
        return AjaxResult.success(areaService.getDistrictsByCityCode(cityCode));
    }

    /**
     * 根据县、区编号获取指定县/区的街道信息
     *
     * @param districtCode
     * @return
     */
    @GetMapping("/town")
    @ResponseBody
    public AjaxResult getTownsByDistrictCode(String districtCode){
        return AjaxResult.success(areaService.getTownsByDistrictCode(districtCode));
    }
}
