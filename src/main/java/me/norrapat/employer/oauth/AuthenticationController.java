package me.norrapat.employer.oauth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedHashMap;

@Log4j2
@Api("Authentication")
@Tag(name = "Authentication")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Value("${jwt.clientId:myEmployer}")
    private String clientId;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Authenticate with username and password
     *
     * @param username Username
     * @param password Password
     * @return Access token and Refresh token...
     * @throws HttpRequestMethodNotSupportedException
     */
    @Tag(name = "Authentication")
    @ApiOperation("Login get token")
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<OAuth2AccessToken> requestToken(
            @RequestParam String username,
            @RequestParam String password
    ) throws HttpRequestMethodNotSupportedException {

        LinkedHashMap<String, String> newParameters = new LinkedHashMap<>();
        newParameters.put("grant_type", "password");
        newParameters.put("username", username);
        newParameters.put("password", password);
        newParameters.put("scope", "read write");

        Authentication principal = createPrincipal();
        return tokenEndpoint.postAccessToken(principal, newParameters);
    }

    private Authentication createPrincipal() {
        Authentication principal = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return clientId;
            }
        };
        return principal;
    }

}
