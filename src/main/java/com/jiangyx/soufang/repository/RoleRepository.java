package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色仓储
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRolesByUserId(Long userId);
}
