package com.jaeyeonling.oauth2.entity.converter;

import com.jaeyeonling.oauth2.utils.BeanUtils;
import com.jaeyeonling.oauth2.utils.DateConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.util.Date;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(final LocalDateTime attribute) {
        return BeanUtils.getBean(DateConverter.class).localDateTimeToDate(attribute).orElseGet(Date::new);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(final Date dbData) {
        return BeanUtils.getBean(DateConverter.class).dateToLocalDateTime(dbData).orElseGet(LocalDateTime::now);
    }
}
