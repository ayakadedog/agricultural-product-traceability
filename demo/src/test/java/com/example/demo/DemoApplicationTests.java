package com.example.demo;

import com.example.demo.mapper.aaa;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private aaa a;

    @Test
    void  asd(){
        Employee e = new Employee();
        e.setId(123456789L);
        a.insert(e);
    }
    @Test
    void contextLoads() {
    }

}
