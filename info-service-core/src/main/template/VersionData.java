package com.nusmanov.common.infoservice.core;

public class VersionData {

    public static final String MAJOR = "${app.version.major}";
    public static final String MINOR = "${app.version.minor}";
    public static final String MICRO = "${app.version.micro}";
    public static final String APP_NAME = "${app.name}";
    public static final String APP_VERSION = MAJOR + "." + MINOR + "." + MICRO;
    public static final String MODULE_NAME = "${module.name}";

    public static final String MAJOR_MINOR = MAJOR + "." + MINOR;
    public static final String APP_NAME_MAJOR_MINOR = APP_NAME + "-v" + MAJOR_MINOR;
    public static final String MODULE_NAME_MAJOR_MINOR = MODULE_NAME + "-v" + MAJOR_MINOR;

}