package com.jaeyeonling.oauth2.utils;

import com.jaeyeonling.oauth2.Application;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BeanUtilsTests {
    @Test
    public void loadBeanFromSpringContext() {
        var application = BeanUtils.getBean(Application.class);

        Assertions.assertThat(application).isNotNull();
    }
}
