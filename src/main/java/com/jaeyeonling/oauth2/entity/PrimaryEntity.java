package com.jaeyeonling.oauth2.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class PrimaryEntity {
    abstract long getId();
    abstract boolean isEqualsId(final long another);
}
