package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TSData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 数据表访问接口
 *
 * @author fuenhui
 * @date 2019/1/6
 */
@Repository
@Mapper
public interface DataDAO {

    /**
     * 插入数据
     *
     * @param deviceSn
     * @param code
     * @param value
     * @param receiveTime
     */
    @Insert("insert into ts_data(device_sn, code, value, receive_time) " +
            "values (#{deviceSn},#{code},#{value},#{receiveTime})")
    void insert(@Param("deviceSn") String deviceSn, @Param("code") String code, @Param("value") Double value,
                @Param("receiveTime") Date receiveTime);

    /**
     * 通过设备sn获取设备最新位置
     *
     * @param deviceSn
     * @return
     */
    @Select("select * from ts_data where device_sn=#{deviceSn} and code in ('GPS_LNG','GPS_LAT') " +
            "order by receive_time desc limit 2")
    List<TSData> getLastLocation(@Param("deviceSn") String deviceSn);

    /**
     * 查询一段时间区间经度的变化
     *
     * @param deviceSn
     * @param start
     * @param stop
     * @return
     */
    @Select("select * from ts_data where device_sn=#{deviceSn} and code='GPS_LNG' and receive_time > #{start} " +
            "and receive_time < #{stop} order by receive_time asc")
    List<TSData> getGpsLngTrack(@Param("deviceSn") String deviceSn, @Param("start") Date start,
                                @Param("stop") Date stop);

    /**
     * 查询一段时间区间纬度的变化
     *
     * @param deviceSn
     * @param start
     * @param stop
     * @return
     */
    @Select("select * from ts_data where device_sn=#{deviceSn} and code='GPS_LAT' and receive_time > #{start} " +
            "and receive_time < #{stop} order by receive_time asc")
    List<TSData> getGpsLatTrack(@Param("deviceSn") String deviceSn, @Param("start") Date start,
                                @Param("stop") Date stop);
}
