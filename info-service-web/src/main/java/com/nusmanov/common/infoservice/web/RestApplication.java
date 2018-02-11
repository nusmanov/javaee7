package com.nusmanov.common.infoservice.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.nusmanov.common.infoservice.web.rest.InfoServiceRest;

@ApplicationPath("/rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();
        classes.add(InfoServiceRest.class);

        classes.addAll(getExceptionMapper());
        return classes;
    }

    private Collection<Class<?>> getExceptionMapper() {

        Set<Class<?>> exceptionClasses = new HashSet<>();

        return exceptionClasses;
    }

}
