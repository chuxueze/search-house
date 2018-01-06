package com.jiangyx.soufang.service.house;

import com.jiangyx.soufang.base.ServiceMultiResult;
import com.jiangyx.soufang.domain.SupportAddress;
import com.jiangyx.soufang.dto.SupportAddressDTO;

import java.util.List;

/**
 * 地址服务接口
 */
public interface IAddressService {

    ServiceMultiResult<SupportAddressDTO> findAllCities();
}
