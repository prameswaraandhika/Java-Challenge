package com.prameswaradev.CloudGateway.model;

import java.util.Collection;

public record AuthResponse(
        String idUser,
        String accessToken,
        String refreshToken,
        Long expiresAt,

        Collection<String> authorityList
) {
}
