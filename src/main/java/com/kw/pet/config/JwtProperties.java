package com.kw.pet.config;

public class JwtProperties {
    public static final String SECRET = "asdfasdfasdfasdfasdfasdfasdf";
    public static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 60; // 유효기간 2달
    public static final String ACCESS_HEADER_STRING = "X_ACCESS_TOKEN";
}
