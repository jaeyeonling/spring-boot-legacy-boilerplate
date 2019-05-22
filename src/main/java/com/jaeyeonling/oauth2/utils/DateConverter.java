package com.jaeyeonling.oauth2.utils;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
public class DateConverter {
    public Optional<Date> localDateTimeToDate(@Nullable final LocalDateTime localDateTime) {
        return localDateTimeToDate(localDateTime, ZoneId.systemDefault());
    }

    public Optional<Date> localDateTimeToDate(
            @Nullable final LocalDateTime localDateTime,
            final ZoneId zoneId
    ) {
        if (Objects.isNull(localDateTime)) {
            return Optional.empty();
        }

        return Optional.of(Date.from(localDateTime.atZone(zoneId).toInstant()));
    }

    public Optional<LocalDateTime> dateToLocalDateTime(@Nullable final Date date) {
        return dateToLocalDateTime(date, ZoneId.systemDefault());
    }

    public Optional<LocalDateTime> dateToLocalDateTime(
            @Nullable final Date date,
            final ZoneId zoneId
    ) {
        if (Objects.isNull(date)) {
            return Optional.empty();
        }

        return Optional.of(LocalDateTime.ofInstant(millisToInstant(date.getTime()), zoneId));
    }

    public Instant millisToInstant(final long millis) {
        return Instant.ofEpochMilli(millis);
    }
}
