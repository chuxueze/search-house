package com.jiangyx.soufang.service.house;

import com.jiangyx.soufang.base.ServiceMultiResult;
import com.jiangyx.soufang.domain.SupportAddress;
import com.jiangyx.soufang.dto.SubwayDTO;
import com.jiangyx.soufang.dto.SubwayStationDTO;
import com.jiangyx.soufang.dto.SupportAddressDTO;

import java.util.List;

/**
 * 地址服务接口
 */
public interface IAddressService {

    /**
     * 获取所有支持的城市列表
     * @return
     */
    ServiceMultiResult<SupportAddressDTO> findAllCities();

    /**
     * 根据城市英文简写获取该城市所有支持的区域信息
     * @param cityName              城市英文简写
     * @return
     */
    ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName);

    /**
     * 获取该城市所有的地铁线路
     * @param cityEnName
     * @return
     */
    ServiceMultiResult<SubwayDTO> findAllSubwayByCity(String cityEnName);

    /**
     * 获取地铁线路所有的站点
     * @param subwayId
     * @return
     */
    List<SubwayStationDTO> findAllStationBySubway(Long subwayId);

}
