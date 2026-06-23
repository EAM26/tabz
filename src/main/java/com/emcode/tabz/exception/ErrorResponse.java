package com.emcode.tabz.exception;

public record ErrorResponse(
        int status,
        String message,
        String uri
) {

}
