package com.kw.pet.config;

public class JwtProperties {
    public static final String SECRET = "asdfasdfasdfasdfasdfasdfasdf";
    public static final long EXPIRATION_TIME = 2*24*60*60*1000L; //유효기간 7일
    public static final String ACCESS_HEADER_STRING = "X_ACCESS_TOKEN";
}
