package com.centralway.contactbook.security.auth.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
