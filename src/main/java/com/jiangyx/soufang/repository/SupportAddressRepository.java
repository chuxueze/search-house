package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.domain.SupportAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportAddressRepository extends JpaRepository<SupportAddress, Long> {

    /**
     * 获取所有对应行政级别信息
     * @return
     */
    List<SupportAddress> findAllByLevel(String level);
}
