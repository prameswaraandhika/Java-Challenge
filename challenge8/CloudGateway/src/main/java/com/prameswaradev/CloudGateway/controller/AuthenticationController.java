package com.prameswaradev.CloudGateway.controller;

import com.prameswaradev.CloudGateway.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping(value = "/login")
    public ResponseEntity<?> doLogin(
            @AuthenticationPrincipal OidcUser user,
            Model model,
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
            ){
        AuthResponse authResponse =
                new AuthResponse(
                        user.getEmail(),
                        client.getAccessToken().getTokenValue(),
                        client.getRefreshToken().getTokenValue(),
                        client.getAccessToken().getExpiresAt().getEpochSecond(),
                        user.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                );

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
