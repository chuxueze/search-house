package com.jiangyx.soufang.repository;

import com.jiangyx.soufang.SouFangApplicationTests;
import com.jiangyx.soufang.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends SouFangApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(1L);

    }
}
