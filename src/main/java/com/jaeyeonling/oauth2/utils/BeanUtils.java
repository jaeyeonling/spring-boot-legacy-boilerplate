package com.jaeyeonling.oauth2.utils;

import lombok.experimental.UtilityClass;

/**
 * Spring Bean은 static으로 사용이 불가능하기 때문에 동적으로 사용하기 위한 클래스
 */
@UtilityClass
public class BeanUtils {

    public <T> T getBean(final Class<T> classType) {
        return ApplicationContextProvider.getContext().getBean(classType);
    }
}
