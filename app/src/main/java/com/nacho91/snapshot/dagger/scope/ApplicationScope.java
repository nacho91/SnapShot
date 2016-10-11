package com.nacho91.snapshot.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ignacio on 26/05/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ApplicationScope {
}
