package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓储
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String username);
}
