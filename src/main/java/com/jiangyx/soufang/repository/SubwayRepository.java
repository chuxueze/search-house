package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.domain.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Long> {

    List<Subway> getAllByCityEnName(String city);
}
