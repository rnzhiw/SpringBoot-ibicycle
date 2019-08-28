package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.AjaxResult;
import com.rnzhiw.ibicycle.common.Constants;
import com.rnzhiw.ibicycle.model.dto.BicycleDTO;
import com.rnzhiw.ibicycle.model.form.SessionInfo;
import com.rnzhiw.ibicycle.service.BicycleService;
import com.rnzhiw.ibicycle.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 电动车控制器
 *
 * @author fuenhui
 * @date 2018/11/10
 */
@Controller
@RequestMapping("/system/bicycle")
public class EbicycleController {

    private BicycleService bicycleService;

    private DataService dataService;

    @Autowired
    public EbicycleController(BicycleService bicycleService, DataService dataService){
        this.bicycleService = bicycleService;
        this.dataService = dataService;
    }

    @GetMapping
    public String index() {
        return "bicycle";
    }

    @GetMapping("/detail")
    public ModelAndView detail(String id) {
        ModelAndView modelAndView = new ModelAndView("bicycle-detail");
        modelAndView.addObject("id",id);
        return modelAndView;
    }

    @GetMapping("/get")
    @ResponseBody
    public BicycleDTO searchInfo(Long id){
        System.out.println("id: "+id);
        return bicycleService.findOne(id);
    }


    /**
     * 列出所有该登录用户可以管理的电动车
     *
     * @param sessionInfo
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(@SessionAttribute(name = Constants.SESSION_INFO) SessionInfo sessionInfo){
        return AjaxResult.success(bicycleService.findAllByUserId(sessionInfo.getUserId()));
    }

    /**
     * 获取轨迹
     *
     * @param id
     * @param start
     * @param stop
     * @return
     */
    @GetMapping("/getTrack")
    @ResponseBody
    public AjaxResult getTrack(Long id, Date start, Date stop){
        return AjaxResult.success(bicycleService.getTrack(id, start, stop));
    }

    /**
     * 获取最近一次定位
     *
     * @param id
     * @return
     */
    @GetMapping("/getLastLocation")
    @ResponseBody
    public AjaxResult getLastLocation(Long id){
        return AjaxResult.success(bicycleService.getLastLocation(id));
    }

    @RequestMapping("/test")
    @ResponseBody
    public boolean dataReceive(Long id, Double lng, Double lat){
        BicycleDTO bicycleDTO = bicycleService.findOne(id);
        return dataService.insertLocation(bicycleDTO.getDeviceSn(), lng, lat, new Date());
    }
}
