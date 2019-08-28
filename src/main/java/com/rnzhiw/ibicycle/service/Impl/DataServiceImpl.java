package com.rnzhiw.ibicycle.service.Impl;

import com.rnzhiw.ibicycle.dao.DataDAO;
import com.rnzhiw.ibicycle.model.domain.TSData;
import com.rnzhiw.ibicycle.model.dto.DataDTO;
import com.rnzhiw.ibicycle.service.DataService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据业务实现类
 *
 * @author fuenhui
 * @date 2019/1/6
 */
@Service
@Transactional
public class DataServiceImpl implements DataService {

    private DataDAO dataDAO;

    @Autowired
    public DataServiceImpl(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }

    @Override
    public boolean insertLocation(String deviceSn, Double gpsLng, Double gpsLat, Date receiveTime) {

        if (gpsLng == null || gpsLat == null) {
            return false;
        }

        dataDAO.insert(deviceSn, "GPS_LNG", gpsLng, receiveTime);
        dataDAO.insert(deviceSn, "GPS_LAT", gpsLat, receiveTime);
        return true;
    }

    private DataDTO e2d(TSData data) {

        if (data == null) {
            return null;
        }

        DataDTO dataDTO = new DataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        return dataDTO;
    }

    private List<DataDTO> e2d(List<TSData> datas) {

        if (datas == null || datas.size() == 0) {
            return new ArrayList<>();
        }

        List<DataDTO> dataDTOs = new ArrayList<>();
        for (TSData data : datas) {
            if (data != null) {
                dataDTOs.add(e2d(data));
            }
        }

        return dataDTOs;
    }
}
