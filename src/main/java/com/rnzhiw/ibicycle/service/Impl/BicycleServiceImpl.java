package com.rnzhiw.ibicycle.service.Impl;

import com.rnzhiw.ibicycle.dao.BicycleDAO;
import com.rnzhiw.ibicycle.dao.DataDAO;
import com.rnzhiw.ibicycle.dao.MemberDAO;
import com.rnzhiw.ibicycle.dao.OrganizationDAO;
import com.rnzhiw.ibicycle.form.BicycleModifyForm;
import com.rnzhiw.ibicycle.model.domain.TBicycle;
import com.rnzhiw.ibicycle.model.domain.TMember;
import com.rnzhiw.ibicycle.model.domain.TOrganization;
import com.rnzhiw.ibicycle.model.domain.TSData;
import com.rnzhiw.ibicycle.model.dto.BicycleDTO;
import com.rnzhiw.ibicycle.model.dto.LocationDTO;
import com.rnzhiw.ibicycle.service.BicycleService;
import com.rnzhiw.ibicycle.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电动车业务实现类
 *
 * @author hjb
 * @date 2018/11/28
 */
@Service
public class BicycleServiceImpl implements BicycleService {

    private MemberDAO memberDAO;

    private OrganizationDAO organizationDAO;

    private BicycleDAO bicycleDAO;

    private DataDAO dataDAO;

    @Autowired
    public BicycleServiceImpl(MemberDAO memberDAO, OrganizationDAO organizationDAO, BicycleDAO bicycleDAO,
                              DataDAO dataDAO) {
        this.memberDAO = memberDAO;
        this.organizationDAO = organizationDAO;
        this.bicycleDAO = bicycleDAO;
        this.dataDAO = dataDAO;
    }

    @Override
    public List<BicycleDTO> findAllByUserId(Long userId) {

        if (userId == null) {
            return new ArrayList<>();
        }

        TMember member = memberDAO.getOne(userId);
        if (member == null) {
            return new ArrayList<>();
        }

        TOrganization userOrg = member.getOrganization();
        if (userOrg == null) {
            return new ArrayList<>();
        }

        //获取当前用户所在组织可管理的安装点组织
        List<TOrganization> installOrgs = organizationDAO.findChildInstallOrgsById(userOrg.getId());

        if (installOrgs == null || installOrgs.size() == 0) {
            return new ArrayList<>();
        }

        List<TBicycle> bicycles = bicycleDAO.findByInstallOrgIsIn(installOrgs);

        return e2d(bicycles);
    }

    @Override
    public BicycleDTO findOne(Long id) {

        if (id == null) {
            return null;
        }

        return e2d(bicycleDAO.getOne(id));

    }

    @Override
    public BicycleDTO findByVin(String vin) {

        if (StringUtil.isEmpty(vin)) {
            return null;
        }

        return e2d(bicycleDAO.findByVin(vin));

    }

    @Override
    public BicycleDTO findByMotorNumber(String motorNumber) {

        if (StringUtil.isEmpty(motorNumber)) {
            return null;
        }

        return e2d(bicycleDAO.findByMotorNumber(motorNumber));

    }

    @Override
    public BicycleDTO findByLicenseNumber(String licenseNumber) {

        if (StringUtil.isEmpty(licenseNumber)) {
            return null;
        }

        return e2d(bicycleDAO.findByLicenseNumber(licenseNumber));

    }

    @Override
    public BicycleDTO findByRfid(String rfid) {

        if (StringUtil.isEmpty(rfid)) {
            return null;
        }

        return e2d(bicycleDAO.findByRfid(rfid));

    }

    @Override
    public List<BicycleDTO> findByOwnerId(Long ownerId) {

        if (ownerId == null) {
            return new ArrayList<>();
        }

        TMember owner = memberDAO.getOne(ownerId);
        if (owner == null) {
            return new ArrayList<>();
        }

        return e2d(bicycleDAO.findByOwner(owner));
    }

    @Override
    public BicycleDTO findByDeviceSn(String deviceSn) {

        if (StringUtil.isEmpty(deviceSn)) {
            return null;
        }

        return e2d(bicycleDAO.findByDeviceSn(deviceSn));

    }

    @Override
    public List<BicycleDTO> findByOrgId(Long id) {

        if (id == null) {
            return new ArrayList<>();
        }

        TOrganization tOrganization = organizationDAO.getOne(id);
        if (tOrganization == null) {
            return new ArrayList<>();
        }

        return e2d(bicycleDAO.findByInstallOrg(tOrganization));

    }

    @Override
    public BicycleDTO insert(BicycleModifyForm form) {
        if (form == null) {
            return null;
        }

        TBicycle tBicycle = new TBicycle();
        BeanUtils.copyProperties(form, tBicycle);
        if (form.getOwnerId() != null) {
            TMember tMember = memberDAO.getOne(form.getOwnerId());
            tBicycle.setOwner(tMember);
        }
        if (form.getInstallOrgId() != null) {
            TOrganization tOrganization = organizationDAO.getOne(form.getInstallOrgId());
            tBicycle.setInstallOrg(tOrganization);
        }

        return e2d(bicycleDAO.save(tBicycle));
    }

    @Override
    public BicycleDTO update(BicycleModifyForm form) {
        if (form == null || form.getId() == null) {
            return null;
        }

        TBicycle tBicycle = bicycleDAO.getOne(form.getId());
        if (tBicycle == null) {
            return null;
        }
        BeanUtils.copyProperties(form, tBicycle);

        if (form.getOwnerId() != null) {
            TMember tMember = memberDAO.getOne(form.getOwnerId());
            if (tMember == null) {
                //如果找不到，电动车不绑定用户
                tBicycle.setOwner(null);
            }
            tBicycle.setOwner(tMember);
        } else {
            //如果用户id为空，电动车不绑定用户
            tBicycle.setOwner(null);
        }

        if (form.getInstallOrgId() != null) {
            TOrganization tOrganization = organizationDAO.getOne(form.getInstallOrgId());
            if (tOrganization == null) {
                //如果找不到组织为空，电动车不绑定组织
                tBicycle.setInstallOrg(null);
            }
            tBicycle.setInstallOrg(tOrganization);
        } else {
            //如果组织id为空，电动车不绑定组织
            tBicycle.setInstallOrg(null);
        }
        return e2d(tBicycle);

    }

    @Override
    public LocationDTO getLastLocation(Long id) {

        if (id == null) {
            return null;
        }

        TBicycle bicycle = bicycleDAO.getOne(id);
        if (bicycle == null) {
            return null;
        }

        String deviceSn = bicycle.getDeviceSn();
        if (StringUtil.isEmpty(deviceSn)) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setDeviceSn(deviceSn);
        locationDTO.setBicycleId(bicycle.getId());

        List<TSData> datas = dataDAO.getLastLocation(bicycle.getDeviceSn());

        if (datas == null || datas.size() == 0) {
            return null;
        }

        for (TSData data : datas) {
            switch (data.getCode()) {
                case "GPS_LNG": {
                    locationDTO.setGpsLng(data.getValue());
                    break;
                }
                case "GPS_LAT": {
                    locationDTO.setGpsLat(data.getValue());
                    break;
                }
            }
        }

        locationDTO.setTime(datas.get(0).getReceiveTime());
        return locationDTO;
    }

    @Override
    public List<LocationDTO> getTrack(Long id, Date start, Date stop) {

        if (id == null || start == null || stop == null) {
            return new ArrayList<>();
        }

        TBicycle bicycle = bicycleDAO.getOne(id);
        if (bicycle == null) {
            return new ArrayList<>();
        }

        String deviceSn = bicycle.getDeviceSn();
        if (StringUtil.isEmpty(deviceSn)) {
            return new ArrayList<>();
        }

        List<TSData> lngs = dataDAO.getGpsLngTrack(deviceSn, start, stop);
        if (lngs == null || lngs.size() == 0) {
            return new ArrayList<>();
        }

        List<TSData> lats = dataDAO.getGpsLatTrack(deviceSn, start, stop);
        if (lats == null || lats.size() == 0) {
            return new ArrayList<>();
        }

        if (lats.size() != lngs.size()) {
            return new ArrayList<>();
        }

        List<LocationDTO> locationDTOs = new ArrayList<>();

        for (int i = 0; i < lngs.size(); i++){
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setBicycleId(bicycle.getId());
            locationDTO.setDeviceSn(bicycle.getDeviceSn());
            locationDTO.setGpsLng(lngs.get(i).getValue());
            locationDTO.setGpsLat(lats.get(i).getValue());
            locationDTO.setTime(lngs.get(i).getReceiveTime());

            locationDTOs.add(locationDTO);
        }

        return locationDTOs;
    }

    private BicycleDTO e2d(TBicycle bicycle) {

        if (bicycle == null) {
            return null;
        }

        BicycleDTO bicycleDTO = new BicycleDTO();
        BeanUtils.copyProperties(bicycle, bicycleDTO);

        if (bicycle.getOwner() != null) {
            bicycleDTO.setOwnerId(bicycle.getOwner().getId());
            bicycleDTO.setOwnerName(bicycle.getOwner().getRealName());
            bicycleDTO.setOwnerPhone(bicycle.getOwner().getPhone());
        }

        if (bicycle.getInstallOrg() != null) {
            bicycleDTO.setInstallOrgId(bicycle.getInstallOrg().getId());
            bicycleDTO.setInstallOrgName(bicycle.getInstallOrg().getName());
        }

        return bicycleDTO;

    }

    private List<BicycleDTO> e2d(List<TBicycle> tBicycles) {
        if (tBicycles == null || tBicycles.size() == 0) {
            return new ArrayList<>();
        }

        List<BicycleDTO> bicycleDTOs = new ArrayList<>();

        for (TBicycle tBicycle : tBicycles) {
            if (tBicycle != null) {
                bicycleDTOs.add(e2d(tBicycle));
            }
        }

        return bicycleDTOs;

    }


}
