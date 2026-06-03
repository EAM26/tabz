package com.emcode.tabz.dto;

public record ClaimRequest(
        Long userId,
        Boolean claim
) {
}
