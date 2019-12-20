package com.duxiutest;

import com.duxiutianlang.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringUnitTest {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void testAccessBaidu(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String forObject = restTemplate.getForObject("http://www.baidu.com/", String.class);
        System.out.println(forObject);
    }

}
