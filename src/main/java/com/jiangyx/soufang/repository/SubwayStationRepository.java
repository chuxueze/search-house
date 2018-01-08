package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.domain.SubwayStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubwayStationRepository extends JpaRepository<SubwayStation, Long> {
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}

