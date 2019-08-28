package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TBicycle;
import com.rnzhiw.ibicycle.model.domain.TMember;
import com.rnzhiw.ibicycle.model.domain.TOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电动车表访问接口
 *
 * @author hjb
 * @date 2018/11/28
 */
@Repository
public interface BicycleDAO extends JpaRepository<TBicycle, Long> {

    /**
     * 根据车架号查询电动车
     *
     * @param vin
     * @return
     */
    TBicycle findByVin(String vin);

    /**
     * 根据电机号查询电动车
     *
     * @param motorNumber
     * @return
     */
    TBicycle findByMotorNumber(String motorNumber);

    /**
     * 根据牌照号查询电动车
     *
     * @param licenseNumber
     * @return
     */
    TBicycle findByLicenseNumber(String licenseNumber);

    /**
     * 根据电子标签号查询电动车
     *
     * @param rfid
     * @return
     */
    TBicycle findByRfid(String rfid);

    /**
     * 根据所有者查询电动车
     *
     * @param tMember
     * @return
     */
    List<TBicycle> findByOwner(TMember tMember);

    /**
     * 根据sn编号查询电动车
     *
     * @param deviceSn
     * @return
     */
    TBicycle findByDeviceSn(String deviceSn);

    /**
     * 根据安装点查询电动车
     *
     * @param tOrganization
     * @return
     */
    List<TBicycle> findByInstallOrg(TOrganization tOrganization);

    /**
     * 通过安装点集合查询电动车
     *
     * @param installOrgs
     * @return
     */
    List<TBicycle> findByInstallOrgIsIn(List<TOrganization> installOrgs);

}
