package com.rnzhiw.ibicycle.service.Impl;

import com.rnzhiw.ibicycle.dao.AreaDAO;
import com.rnzhiw.ibicycle.dao.AreaTownDAO;
import com.rnzhiw.ibicycle.model.domain.TArea;
import com.rnzhiw.ibicycle.model.domain.TAreaTown;
import com.rnzhiw.ibicycle.model.dto.AreaDTO;
import com.rnzhiw.ibicycle.service.AreaService;
import com.rnzhiw.ibicycle.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区域业务实现类
 *
 * @author csy
 * @date 2018/11/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {

    private AreaDAO areaDAO;

    private AreaTownDAO areaTownDAO;

    @Autowired
    public AreaServiceImpl(AreaDAO areaDAO, AreaTownDAO areaTownDAO) {
        this.areaDAO = areaDAO;
        this.areaTownDAO = areaTownDAO;
    }

    @Override
    public List<AreaDTO> getProvinces() {

        List<AreaDTO> areaDTOs = new ArrayList<>();
        List<TArea> areas = areaDAO.findByCodeFix("%0000");

        for (TArea area : areas) {
            if (area != null) {
                areaDTOs.add(e2d(area));
            }
        }

        return areaDTOs;
    }

    @Override
    public List<AreaDTO> getCitiesByProvinceCode(String provinceCode) {

        if (StringUtil.isEmpty(provinceCode) || provinceCode.length() != 6) {
            return new ArrayList<>();
        }

        String code = provinceCode.substring(0, 2);
        List<AreaDTO> areaDTOs = new ArrayList<>();
        List<TArea> areas = areaDAO.findByCodeFix(code + "%00");

        for (TArea area : areas) {
            if (area != null && !area.getCode().equals(provinceCode)) {
                areaDTOs.add(e2d(area));
            }
        }

        return areaDTOs;
    }

    @Override
    public List<AreaDTO> getDistrictsByCityCode(String cityCode) {

        if (StringUtil.isEmpty(cityCode) || cityCode.length() != 6) {
            return new ArrayList<>();
        }

        String code = cityCode.substring(0, 4);
        List<AreaDTO> areaDTOs = new ArrayList<>();
        List<TArea> areas = areaDAO.findByCodeFix(code + "%");

        for (TArea area : areas) {
            if (area != null && !area.getCode().equals(cityCode)) {
                areaDTOs.add(e2d(area));
            }
        }

        return areaDTOs;
    }

    @Override
    public List<AreaDTO> getTownsByDistrictCode(String districtCode) {
        List<TAreaTown> areaTowns = areaTownDAO.findByParentCode(districtCode);
        return e2d(areaTowns);
    }

    /**
     * 通过编号查询该编号对应的行政区域所在省
     * <p>
     * 编号支持省市区编号（6位）和乡镇街道编号（9位）
     *
     * @param code
     * @return
     */
    private TArea findProvinceByCode(String code) {

        if (StringUtil.isEmpty(code) || (code.length() != 6 && code.length() != 9)) {
            return null;
        }

        String cut = code.substring(0, 2);
        return areaDAO.findByCode(cut + "0000");
    }

    /**
     * 通过编号查询该编号对应的行政区域所在市
     * <p>
     * 编号支持省市区编号（6位）和乡镇街道编号（9位）
     *
     * @param code
     * @return
     */
    private TArea findCityByCode(String code) {
        if (StringUtil.isEmpty(code) || (code.length() != 6 && code.length() != 9)) {
            return null;
        }

        if (code.endsWith("0000")) {
            return null;
        }

        String cut = code.substring(0, 4);
        return areaDAO.findByCode(cut + "00");
    }

    /**
     * 通过编号查询该编号对应的行政区域所在区
     * <p>
     * 编号支持省市区编号（6位）和乡镇街道编号（9位）
     *
     * @param code
     * @return
     */
    private TArea findDistrictByCode(String code) {

        if (StringUtil.isEmpty(code) || (code.length() != 6 && code.length() != 9)) {
            return null;
        }

        if (code.endsWith("00")) {
            return null;
        }

        if (code.length() == 6) {
            return areaDAO.findByCode(code);
        } else {
            String cut = code.substring(0, 6);
            return areaDAO.findByCode(cut);
        }
    }

    private AreaDTO e2d(TArea area) {

        if (area == null) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();
        BeanUtils.copyProperties(area, areaDTO);

        return areaDTO;
    }

    private AreaDTO e2d(TAreaTown areaTown) {

        if (areaTown == null) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();
        BeanUtils.copyProperties(areaTown, areaDTO);
        return areaDTO;
    }


    private List<AreaDTO> e2d(List<TAreaTown> areaTowns) {

        if (areaTowns == null || areaTowns.size() == 0) {
            return new ArrayList<>();
        }

        List<AreaDTO> areaDTOs = new ArrayList<>();

        for (TAreaTown areaTown : areaTowns) {
            if (areaTown != null) {
                areaDTOs.add(e2d(areaTown));
            }
        }

        return areaDTOs;
    }

}
