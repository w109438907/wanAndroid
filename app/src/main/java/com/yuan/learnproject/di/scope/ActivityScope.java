package com.yuan.learnproject.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yuan
 * @date 2019/1/27
 **/
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
