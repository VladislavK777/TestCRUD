package com.vladislavk.testcrud.model;

import com.vladislavk.testcrud.util.PropertyUtil;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 10.06.2019
 */

public final class ConstantsToken {
    public static PropertyUtil propertyUtil = new PropertyUtil();
    public static final long EXPIRATION_TIME = Long.parseLong(propertyUtil.getProperty("auth.expiration.time"));
    public static final String SECRET_KEY = propertyUtil.getProperty("auth.secret.key");
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_SCOPE = "scopes";
    public static final String HEADER_AUTH = "Authorization";
}
