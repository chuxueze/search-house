package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
