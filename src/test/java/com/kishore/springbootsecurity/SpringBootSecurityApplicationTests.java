package com.kishore.springbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.kishore.dao")
@EntityScan(basePackages="com.kishore.model")
@ComponentScan(basePackages = {"com.kishore"})
@SpringBootTest
class SpringBootSecurityApplicationTests {

    @Test
    void contextLoads() {
    }

}
