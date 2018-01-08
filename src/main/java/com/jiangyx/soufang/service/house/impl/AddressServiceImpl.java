package com.jiangyx.soufang.service.house.impl;

import com.jiangyx.soufang.base.ServiceMultiResult;
import com.jiangyx.soufang.domain.Subway;
import com.jiangyx.soufang.domain.SubwayStation;
import com.jiangyx.soufang.domain.SupportAddress;
import com.jiangyx.soufang.dto.SubwayDTO;
import com.jiangyx.soufang.dto.SubwayStationDTO;
import com.jiangyx.soufang.dto.SupportAddressDTO;
import com.jiangyx.soufang.repository.SubwayRepository;
import com.jiangyx.soufang.repository.SubwayStationRepository;
import com.jiangyx.soufang.repository.SupportAddressRepository;
import com.jiangyx.soufang.service.house.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    private SubwayRepository subwayRepository;

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getVale());
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : addresses) {
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            addressDTOS.add(target);
        }
        return new ServiceMultiResult<>(addressDTOS.size(), addressDTOS);
    }

    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName) {
        List<SupportAddress> addresses = supportAddressRepository.findAllByBelongTo(cityName);
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : addresses) {
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            addressDTOS.add(target);
        }
        return new ServiceMultiResult<>(addressDTOS.size(), addressDTOS);
    }

    @Override
    public ServiceMultiResult<SubwayDTO> findAllSubwayByCity(String cityEnName) {
        List<Subway> subways = subwayRepository.getAllByCityEnName(cityEnName);
        List<SubwayDTO> subwayDTOS = new ArrayList<>();
        for (Subway subway : subways) {
            SubwayDTO target = modelMapper.map(subway, SubwayDTO.class);
            subwayDTOS.add(target);
        }
        return new ServiceMultiResult<>(subwayDTOS.size(), subwayDTOS);
    }

    @Override
    public List<SubwayStationDTO> findAllStationBySubway(Long subwayId) {
        List<SubwayStationDTO> result = new ArrayList<>();
        List<SubwayStation> stations = subwayStationRepository.findAllBySubwayId(subwayId);
        if (stations.isEmpty()) {
            return result;
        }
        stations.forEach(station -> result.add(modelMapper.map(station, SubwayStationDTO.class)));
        return result;
    }
}
